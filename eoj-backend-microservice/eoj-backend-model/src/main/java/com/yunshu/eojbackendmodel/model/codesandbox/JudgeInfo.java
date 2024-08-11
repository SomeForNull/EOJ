package com.yunshu.eojbackendmodel.model.codesandbox;

/**
 * 判题信息
 */

import lombok.Data;

@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;
    /**
     * 消耗内容
     */
    private Long memory;
    /**
     * 消耗时间
     */
    private Long time;
}
