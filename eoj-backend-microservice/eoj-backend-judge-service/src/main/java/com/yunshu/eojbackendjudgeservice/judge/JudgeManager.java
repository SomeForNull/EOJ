package com.yunshu.eojbackendjudgeservice.judge;


import com.yunshu.eojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.yunshu.eojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.yunshu.eojbackendjudgeservice.judge.strategy.JudgeContext;
import com.yunshu.eojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.yunshu.eojbackendmodel.model.codesandbox.JudgeInfo;
import com.yunshu.eojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

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
//        if ("java".equals(language)) {
//            judgeStrategy = new JavaLanguageJudgeStrategy();
//        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
