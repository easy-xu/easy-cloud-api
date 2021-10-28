package cloud.easy.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import cloud.easy.constant.Messages;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.exception.RequestException;
import cloud.easy.exception.SystemErrorException;
import cloud.easy.quna.enums.AnswerFlowEnum;
import cloud.easy.quna.entity.QunaAnswerQuestion;
import cloud.easy.quna.entity.QunaAnswerQuestionnaire;
import cloud.easy.quna.entity.QunaConfigQuestionnaire;
import cloud.easy.quna.mapper.QunaMapperCust;
import cloud.easy.quna.service.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private QunaMapperCust qunaMapperCust;

    @Resource
    private ResultService resultService;

    @Override
    public QunaAnswerQuestionnaire init(Long questionnaireId) {
       //初始化回答
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
        answerQuestionnaire.setFlow(AnswerFlowEnum.INIT);
        answerQuestionnaire.setQuestionIndex(1);
        answerQuestionnaireService.save(answerQuestionnaire);
        //更新问卷配置信息
        new Thread(()->{
            LambdaQueryWrapper<QunaAnswerQuestionnaire> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(QunaAnswerQuestionnaire::getQuestionnaireId, questionnaireId);
            int count = answerQuestionnaireService.count(queryWrapper);
            QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
            questionnaire.setParticipantNum((long) count);
            questionnaireService.updateById(questionnaire);
        }).start();

        return answerQuestionnaire;
    }

    @Override
    public QunaAnswerQuestionnaire status(Long answerId) {
        QunaAnswerQuestionnaire answerQuestionnaire = answerQuestionnaireService.getById(answerId);
        if (answerQuestionnaire == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        AnswerFlowEnum flow = answerQuestionnaire.getFlow();
        //需要重新计数是否完成答题
        if (AnswerFlowEnum.INIT.equals(flow) || AnswerFlowEnum.ANSWER.equals(flow)) {
            //查看是否回答结束
            QunaAnswerQuestion answerQuestion = new QunaAnswerQuestion();
            answerQuestion.setAnswerId(answerId);
            int count = answerQuestionService.count(Wrappers.query(answerQuestion));
            QunaConfigQuestionnaire questionnaire = questionnaireService.getById(answerQuestionnaire.getQuestionnaireId());
            if (count < questionnaire.getQuestionNum()) {
                answerQuestionnaire.setFlow(AnswerFlowEnum.ANSWER);
                //查询没有回答的第一个问题
                Integer questionIndex = qunaMapperCust.firstNotAnswerQuestionIndex(questionnaire.getId(), answerId);
                if (questionIndex == null) {
                    throw new SystemErrorException(Messages.DB_DATA_ERROR);
                }
                answerQuestionnaire.setQuestionIndex(questionIndex);
                answerQuestionnaireService.saveOrUpdate(answerQuestionnaire);
            } else if (count == questionnaire.getQuestionNum()) {
                answerQuestionnaire.setFlow(AnswerFlowEnum.SUBMIT);
                answerQuestionnaireService.saveOrUpdate(answerQuestionnaire);
                //触发计算结果异步
                new Thread(() -> {
                    resultService.calculateScore(answerId);
                }).start();
            } else {
                throw new SystemErrorException(Messages.DB_DATA_ERROR);
            }
        }
        if (AnswerFlowEnum.SUBMIT.equals(flow)) {
            //触发计算结果异步
            new Thread(() -> {
                resultService.calculateScore(answerId);
            }).start();
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
        String userNo = ApiHeaderHelper.get().getUserNo();
        //查询最近一次记录
        return  qunaMapperCust.latestAnswer(questionnaireId, userNo);
    }

    @Override
    public void saveAnswerQuestion(QunaAnswerQuestion answerQuestion) {
        String userNo = ApiHeaderHelper.get().getUserNo();
        Long answerId = answerQuestion.getAnswerId();
        Long questionId = answerQuestion.getQuestionId();
        String key = userNo + "|" + answerId + "|" + questionId;
        synchronized (key.intern()) {
            QunaAnswerQuestion query = new QunaAnswerQuestion();
            query.setAnswerId(answerId);
            query.setQuestionId(questionId);
            query.setCreateBy(userNo);
            List<QunaAnswerQuestion> answerQuestions = answerQuestionService.list(Wrappers.query(query));
            if (!answerQuestions.isEmpty()) {
                answerQuestionService.removeByIds(answerQuestions.stream().map(QunaAnswerQuestion::getId).collect(Collectors.toList()));
            }
            answerQuestionService.saveOrUpdate(answerQuestion);
        }
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
