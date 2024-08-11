package com.yunshu.eojbackendjudgeservice.judge.codesandbox;

import com.yunshu.eojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.yunshu.eojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
