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
import pro.simplecloud.quna.mapper.QunaAnswerQuestionMapperCust;
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
    private QunaAnswerQuestionMapperCust answerQuestionMapperCust;

    @Resource
    private ResultService resultService;

    @Override
    public QunaAnswerQuestionnaire init(Long questionnaireId) {
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        if (questionnaire == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //检验是否已存在问卷
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
        String username = ApiHeaderHelper.get().getUserNo();
        answerQuestionnaire.setCreateBy(username);
        List<QunaAnswerQuestionnaire> answerQuestionnaires = answerQuestionnaireService.list(Wrappers.query(answerQuestionnaire));
        if (!answerQuestionnaires.isEmpty()) {
            answerQuestionnaireService.remove(Wrappers.query(answerQuestionnaire));
        }
        //初始化回答
        answerQuestionnaire.setFlow(AnswerFlow.INIT.value);
        answerQuestionnaire.setQuestionIndex(1);
        answerQuestionnaireService.save(answerQuestionnaire);
        //更新问卷配置信息
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
        //需要重新计数是否完成答题
        if (AnswerFlow.INIT.value.equals(flow) || AnswerFlow.ANSWER.value.equals(flow)) {
            //查看是否回答结束
            QunaAnswerQuestion answerQuestion = new QunaAnswerQuestion();
            answerQuestion.setAnswerId(answerId);
            int count = answerQuestionService.count(Wrappers.query(answerQuestion));
            QunaConfigQuestionnaire questionnaire = questionnaireService.getById(answerQuestionnaire.getQuestionnaireId());
            if (count < questionnaire.getQuestionNum()) {
                answerQuestionnaire.setFlow(AnswerFlow.ANSWER.value);
                //查询没有回答的第一个问题
                Integer questionIndex = answerQuestionMapperCust.firstNotAnswerQuestionIndex(questionnaire.getId(), answerId);
                if (questionIndex == null) {
                    throw new SystemErrorException(Messages.DB_DATA_ERROR);
                }
                answerQuestionnaire.setQuestionIndex(questionIndex);
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
        answerQuestionnaire.setCreateBy(ApiHeaderHelper.get().getUserNo());
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
