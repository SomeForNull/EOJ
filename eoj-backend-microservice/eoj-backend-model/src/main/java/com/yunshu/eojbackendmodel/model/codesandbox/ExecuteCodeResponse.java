package com.yunshu.eojbackendmodel.model.codesandbox;


import lombok.Data;

import java.util.List;
@Data
public class ExecuteCodeResponse {
    private List<String> outputList;
    /*
     * 接口信息
     */
    private String message;
    /**
     * 执行状态
     */
    private Integer status;
    private JudgeInfo judgeInfo;
}
