package com.yunshu.eojbackendjudgeservice.judge;


import com.yunshu.eojbackendmodel.model.entity.QuestionSubmit;

public interface JudgeService {

    QuestionSubmit doJudge(Long questionSubmitId);
}
