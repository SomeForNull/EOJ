package com.yunshu.easyoj.model.dto.questionsubmit;

/**
 * 判题信息
 */

import lombok.Data;

@Data
public class JudgeInfo {
    /**
     * 程序执行信息（ms）
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
