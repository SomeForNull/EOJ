package com.yunshu.eojcodesandbox;

import com.yunshu.eojcodesandbox.model.ExecuteCodeRequest;
import com.yunshu.eojcodesandbox.model.ExecuteCodeResponse;

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
