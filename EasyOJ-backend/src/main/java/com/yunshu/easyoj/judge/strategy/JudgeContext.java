package com.yunshu.easyoj.judge.strategy;


import com.yunshu.easyoj.model.dto.question.JudgeCase;
import com.yunshu.easyoj.model.dto.questionsubmit.JudgeInfo;
import com.yunshu.easyoj.model.entity.Question;
import com.yunshu.easyoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
