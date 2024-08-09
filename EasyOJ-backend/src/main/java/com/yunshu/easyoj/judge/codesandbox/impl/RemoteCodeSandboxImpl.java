package com.yunshu.easyoj.judge.codesandbox.impl;

import com.yunshu.easyoj.judge.codesandbox.CodeSandbox;
import com.yunshu.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yunshu.easyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口）
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
