package com.yunshu.eojcodesandbox;

import cn.hutool.core.io.resource.ResourceUtil;
import com.yunshu.eojcodesandbox.model.ExecuteCodeRequest;
import com.yunshu.eojcodesandbox.model.ExecuteCodeResponse;
import com.yunshu.eojcodesandbox.template.CodeSandBoxTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class JavaNativeCodeSandbox extends CodeSandBoxTemplate {
    @Override
    protected boolean checkBlank(String code) {
        return false;
    }

    public static void main(String[] args) {
        JavaNativeCodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2", "1 3"));
//        String code = ResourceUtil.readStr("testCode/simpleComputeArgs/Main.java", StandardCharsets.UTF_8);
        String code = ResourceUtil.readStr("testCode/simpleComputeArgs/Main.java", StandardCharsets.UTF_8);
//        String code = ResourceUtil.readStr("testCode/simpleCompute/Main.java", StandardCharsets.UTF_8);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage("java");
        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }

}



