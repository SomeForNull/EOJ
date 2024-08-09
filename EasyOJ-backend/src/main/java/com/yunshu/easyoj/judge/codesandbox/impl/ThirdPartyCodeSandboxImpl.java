package com.yunshu.easyoj.judge.codesandbox.impl;

import com.yunshu.easyoj.judge.codesandbox.CodeSandbox;
import com.yunshu.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yunshu.easyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用第三方接口）
 */
public class ThirdPartyCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
