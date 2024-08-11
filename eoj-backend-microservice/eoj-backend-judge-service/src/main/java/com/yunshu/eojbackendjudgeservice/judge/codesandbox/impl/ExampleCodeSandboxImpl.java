package com.yunshu.eojbackendjudgeservice.judge.codesandbox.impl;

import com.yunshu.eojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.yunshu.eojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.yunshu.eojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.yunshu.eojbackendmodel.model.codesandbox.JudgeInfo;
import com.yunshu.eojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.yunshu.eojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
