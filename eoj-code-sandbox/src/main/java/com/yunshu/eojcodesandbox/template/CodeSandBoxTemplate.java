package com.yunshu.eojcodesandbox.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.WordTree;
import com.yunshu.eojcodesandbox.CodeSandbox;
import com.yunshu.eojcodesandbox.model.ExecuteCodeRequest;
import com.yunshu.eojcodesandbox.model.ExecuteCodeResponse;
import com.yunshu.eojcodesandbox.model.ExecuteMessage;
import com.yunshu.eojcodesandbox.model.JudgeInfo;
import com.yunshu.utils.ProcessUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class CodeSandBoxTemplate implements CodeSandbox {

    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";

    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    private static final long TIME_OUT = 5000L;

    private static final String SECURITY_MANAGER_PATH = "D:\\javaideaproject\\EOJ\\eoj-code-sandbox\\src\\main\\resources\\security";

    private static final String SECURITY_MANAGER_CLASS_NAME = "MySecurityManager";

    private static final List<String> blackList = Arrays.asList("Files", "exec");

    private static final WordTree WORD_TREE;

    static {
        // 初始化字典树
        WORD_TREE = new WordTree();
        WORD_TREE.addWords(blackList);
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
//        System.setSecurityManager(new DenySecurityManager());

        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();
        ExecuteCodeResponse executeCodeResponse=new ExecuteCodeResponse();
        //1.黑名单
        if (checkBlank(code)) return null;

        //2. 把用户的代码保存为文件
        SaveFileResult result = saveFile(code);

        //编译代码，得到 class 文件
        boolean re = compileTheCode(result);
        if(!re){
            executeCodeResponse.setStatus(3);
            executeCodeResponse.setMessage("失败");
            JudgeInfo judgeInfo = new JudgeInfo();
            judgeInfo.setMessage("编译失败");
            executeCodeResponse.setJudgeInfo(judgeInfo);
            return executeCodeResponse;
        }
        // 3. 执行代码，得到输出结果
        List<ExecuteMessage> executeMessageList = executeCode(inputList, result);
        if (executeMessageList == null) return null;

        //4. 收集整理输出结果
        executeCodeResponse = getExecuteCodeMessage(executeMessageList);

        //5. 文件清理
        cleanFiles(result);
        return executeCodeResponse;
    }




    /**
     * 校验代码中是否包含黑名单中的命令
     */
    protected  boolean checkBlank(String code) {
        FoundWord foundWord = WORD_TREE.matchWord(code);
        if (foundWord != null) {
            System.out.println("包含禁止词：" + foundWord.getFoundWord());
            return true;
        }
        return false;
    }

    /**
     * 保存文件
     *
     * @param code
     * @return
     */
    protected  SaveFileResult saveFile(String code) {
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断全局代码目录是否存在，没有则新建
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        // 把用户的代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
        SaveFileResult result = new SaveFileResult(userCodeParentPath, userCodeFile);
        return result;
    }

    /**
     * 编译
     *
     * @param result
     * @return
     */
    protected Boolean compileTheCode(SaveFileResult result) {
        String compileCmd = String.format("javac -encoding utf-8 %s", result.userCodeFile.getAbsolutePath());
        try {
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
            if(StrUtil.isNotBlank(executeMessage.getErrorMessage())){
                return false;
            }
            System.out.println(executeMessage);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 执行代码
     * @param inputList
     * @param result
     * @return
     */
    protected  List<ExecuteMessage> executeCode(List<String> inputList, SaveFileResult result) {
        List<ExecuteMessage> executeMessageList = new ArrayList<>();
        for (String inputArgs : inputList) {
            String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s", result.userCodeParentPath, inputArgs);
            //String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s;%s -Djava.security.manager=%s Main %s", result.userCodeParentPath, SECURITY_MANAGER_PATH, SECURITY_MANAGER_CLASS_NAME, inputArgs);
            try {
                Process runProcess = Runtime.getRuntime().exec(runCmd);
                // 超时控制
                new Thread(() -> {
                    try {
                        Thread.sleep(TIME_OUT);
                        System.out.println("超时了，中断");
                        runProcess.destroy();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                //ExecuteMessage executeMessage = ProcessUtils.runInteractProcessAndGetMessage(runProcess, inputArgs);
                System.out.println(executeMessage);
                executeMessageList.add(executeMessage);
            } catch (Exception e) {
                return null;
            }
        }
        return executeMessageList;
    }

    /**
     * 收集执行信息
     * @param executeMessageList
     * @return
     */
    protected  ExecuteCodeResponse getExecuteCodeMessage(List<ExecuteMessage> executeMessageList) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        List<String> outputList = new ArrayList<>();
        // 取用时最大值，便于判断是否超时
        long maxTime = 0;
        long maxMemory=0;
        for (ExecuteMessage executeMessage : executeMessageList) {
            String errorMessage = executeMessage.getErrorMessage();
            if (StrUtil.isNotBlank(errorMessage)) {
                executeCodeResponse.setMessage(errorMessage);
                // 用户提交的代码执行中存在错误
                executeCodeResponse.setStatus(3);
                break;
            }
            outputList.add(executeMessage.getMessage());
            Long time = executeMessage.getTime();
            Long memory=executeMessage.getMemory();
            if (time != null) {
                maxTime = Math.max(maxTime, time);
            }
            if(memory!=null){
                maxMemory=Math.max(maxMemory,memory);
            }
        }
        // 正常运行完成
        if (outputList.size() == executeMessageList.size()) {
            executeCodeResponse.setStatus(1);
        }
        executeCodeResponse.setOutputList(outputList);
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(maxTime);
        judgeInfo.setMemory(maxMemory);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }

    /**
     * 清理文件
     * @param result
     */
    protected  void cleanFiles(SaveFileResult result) {
        if (result.userCodeFile.getParentFile() != null) {
            boolean del = FileUtil.del(result.userCodeParentPath);
            System.out.println("删除" + (del ? "成功" : "失败"));
        }
    }

    /**
     * 获取错误响应
     *
     * @param e
     * @return
     */
    private   ExecuteCodeResponse getErrorResponse(Throwable e) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(new ArrayList<>());
        executeCodeResponse.setMessage(e.getMessage());
        // 表示代码沙箱错误
        executeCodeResponse.setStatus(2);
        executeCodeResponse.setJudgeInfo(new JudgeInfo());
        return executeCodeResponse;
    }

    /**
     * 保存文件响应
     */
    public  static class SaveFileResult {
        public final String userCodeParentPath;
        public final File userCodeFile;

        public SaveFileResult(String userCodeParentPath, File userCodeFile) {
            this.userCodeParentPath = userCodeParentPath;
            this.userCodeFile = userCodeFile;
        }
    }


}



