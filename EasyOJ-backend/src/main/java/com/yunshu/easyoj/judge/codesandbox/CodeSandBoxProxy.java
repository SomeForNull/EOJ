package com.yunshu.easyoj.judge.codesandbox;

import com.yunshu.easyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yunshu.easyoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandBoxProxy implements CodeSandbox{
    private CodeSandbox codeSandbox;

    public CodeSandBoxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码请求信息："+executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息："+executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
