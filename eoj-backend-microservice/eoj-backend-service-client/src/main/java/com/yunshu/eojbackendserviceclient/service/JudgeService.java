package com.yunshu.eojbackendserviceclient.service;

import com.yunshu.eojbackendmodel.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudge(Long questionSubmitId);
}
