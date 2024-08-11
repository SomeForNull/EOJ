package com.yunshu.eojbackendjudgeservice.judge.codesandbox.impl;

import com.yunshu.eojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.yunshu.eojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.yunshu.eojbackendmodel.model.codesandbox.ExecuteCodeResponse;

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
