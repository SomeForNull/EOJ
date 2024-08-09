package com.yunshu.easyoj.judge;

import com.yunshu.easyoj.judge.strategy.DefaultJudgeStrategy;
import com.yunshu.easyoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yunshu.easyoj.judge.strategy.JudgeContext;
import com.yunshu.easyoj.judge.strategy.JudgeStrategy;
import com.yunshu.easyoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;
import com.yunshu.easyoj.model.dto.questionsubmit.JudgeInfo;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
