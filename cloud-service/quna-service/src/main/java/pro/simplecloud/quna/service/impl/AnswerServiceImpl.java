package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.quna.constant.AnswerFlow;
import pro.simplecloud.quna.entity.QunaAnswerQuestion;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
import pro.simplecloud.quna.service.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Title: AnswerServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private IQunaAnswerQuestionnaireService answerQuestionnaireService;

    @Resource
    private IQunaAnswerQuestionService answerQuestionService;

    @Resource
    private IQunaConfigQuestionnaireService questionnaireService;

    @Resource
    private ResultService resultService;

    @Override
    public QunaAnswerQuestionnaire init(Long questionnaireId) {
        //检验是否已存在问卷
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
        String username = ApiHeaderHelper.get().getUsername();
        answerQuestionnaire.setCreateBy(username);
        List<QunaAnswerQuestionnaire> answerQuestionnaires = answerQuestionnaireService.list(Wrappers.query(answerQuestionnaire));
        if (!answerQuestionnaires.isEmpty()) {
            answerQuestionnaireService.remove(Wrappers.query(answerQuestionnaire));
        }
        //初始化回答
        answerQuestionnaire.setFlow(AnswerFlow.INIT.value);
        answerQuestionnaire.setQuestionIndex(1L);
        answerQuestionnaireService.save(answerQuestionnaire);
        //更新问卷配置信息
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        LambdaQueryWrapper<QunaAnswerQuestionnaire> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(QunaAnswerQuestionnaire::getQuestionnaireId, questionnaireId);
        int count = answerQuestionnaireService.count(queryWrapper);
        questionnaire.setParticipantNum((long) count);
        questionnaireService.updateById(questionnaire);
        return answerQuestionnaire;
    }

    @Override
    public QunaAnswerQuestionnaire status(Long answerId) {
        QunaAnswerQuestionnaire answerQuestionnaire = answerQuestionnaireService.getById(answerId);
        if (answerQuestionnaire == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        Long flow = answerQuestionnaire.getFlow();
        if (AnswerFlow.INIT.value.equals(flow)) {
            //查看是否回答结束
            QunaAnswerQuestion answerQuestion = new QunaAnswerQuestion();
            answerQuestion.setAnswerId(answerId);
            int count = answerQuestionService.count(Wrappers.query(answerQuestion));
            QunaConfigQuestionnaire questionnaire = questionnaireService.getById(answerQuestionnaire.getQuestionnaireId());
            if (count < questionnaire.getQuestionNum()) {
                answerQuestionnaire.setFlow(AnswerFlow.ANSWER.value);
                answerQuestionnaireService.saveOrUpdate(answerQuestionnaire);
            } else if (count == questionnaire.getQuestionNum()) {
                answerQuestionnaire.setFlow(AnswerFlow.SUBMIT.value);
                answerQuestionnaireService.saveOrUpdate(answerQuestionnaire);
                //触发计算结果异步
                new Thread(() -> {
                    resultService.calculateScore(answerId);
                }).start();
            } else {
                throw new SystemErrorException(Messages.DB_DATA_ERROR);
            }
        }
        return answerQuestionnaire;
    }

    @Override
    public QunaAnswerQuestionnaire getDetail(Long answerId) {
        QunaAnswerQuestionnaire answer = answerQuestionnaireService.getById(answerId);
        if (answer == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        return answer;
    }

    @Override
    public QunaAnswerQuestionnaire getDetailByQuestionnaireId(Long questionnaireId) {
        //查询是否正在进行
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
        answerQuestionnaire.setCreateBy(ApiHeaderHelper.get().getUsername());
        List<QunaAnswerQuestionnaire> answerQuestionnaires = answerQuestionnaireService.list(Wrappers.query(answerQuestionnaire));
        if (answerQuestionnaires.size() > 1) {
            //只能有一个问题正在进行
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        if (answerQuestionnaires.size() == 1) {
            return answerQuestionnaires.get(0);
        }
        return null;
    }

    @Override
    public void saveAnswerQuestion(QunaAnswerQuestion answerQuestion) {
        answerQuestionService.saveOrUpdate(answerQuestion);
    }


    @Override
    public QunaAnswerQuestion getAnswerQuestion(Long answerId, Long questionId) {
        QunaAnswerQuestion answerQuestion = new QunaAnswerQuestion();
        answerQuestion.setAnswerId(answerId);
        answerQuestion.setQuestionId(questionId);
        List<QunaAnswerQuestion> answerQuestions = answerQuestionService.list(Wrappers.query(answerQuestion));
        if (answerQuestions.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        if (answerQuestions.size() == 1) {
            answerQuestion = answerQuestions.get(0);
        }
        return answerQuestion;
    }

}
