package com.yunshu.easyoj.judge;

import com.yunshu.easyoj.model.entity.QuestionSubmit;


public interface JudgeService {

    QuestionSubmit doJudge(Long questionSubmitId);
}
