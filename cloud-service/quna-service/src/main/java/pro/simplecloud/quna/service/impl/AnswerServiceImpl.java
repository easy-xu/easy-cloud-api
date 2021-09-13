package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.quna.constant.AnswerFlow;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.AnswerQuestionDto;
import pro.simplecloud.quna.entity.QunaAnswerQuestion;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
import pro.simplecloud.quna.service.AnswerService;
import pro.simplecloud.quna.service.IQunaAnswerQuestionService;
import pro.simplecloud.quna.service.IQunaAnswerQuestionnaireService;
import pro.simplecloud.quna.service.IQunaConfigQuestionnaireService;
import pro.simplecloud.utils.BeanUtils;

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

    @Override
    public AnswerDto init(Long questionnaireId) {
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
        AnswerDto answerDto = new AnswerDto();
        BeanUtils.copy(answerQuestionnaire, answerDto);
        //更新问卷配置信息
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        LambdaQueryWrapper<QunaAnswerQuestionnaire> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(QunaAnswerQuestionnaire::getQuestionnaireId, questionnaireId);
        int count = answerQuestionnaireService.count(queryWrapper);
        questionnaire.setParticipantNum((long)count);
        questionnaireService.updateById(questionnaire);
        return answerDto;
    }

    @Override
    public AnswerDto getDetail(Long answerId) {
        QunaAnswerQuestionnaire answer = answerQuestionnaireService.getById(answerId);
        if (answer == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        AnswerDto answerDto = new AnswerDto();
        BeanUtils.copy(answer, answerDto);
        return answerDto;
    }

    @Override
    public AnswerQuestionDto saveAnswerQuestion(AnswerQuestionDto answerQuestionDto) {
        Long answerId = answerQuestionDto.getAnswerId();
        Long questionId = answerQuestionDto.getQuestionId();
        QunaAnswerQuestion answerQuestion = queryQunaAnswerQuestion(answerId, questionId);
        answerQuestion.setOptionId(answerQuestionDto.getOptionId());
        answerQuestion.setOptionValue(answerQuestionDto.getOptionValue());
        answerQuestionService.saveOrUpdate(answerQuestion);
        BeanUtils.copy(answerQuestion, answerQuestionDto);
        return answerQuestionDto;
    }


    @Override
    public AnswerQuestionDto getAnswerQuestion(Long answerId, Long questionId) {
        QunaAnswerQuestion answerQuestion = queryQunaAnswerQuestion(answerId, questionId);
        AnswerQuestionDto answerQuestionDto = new AnswerQuestionDto();
        BeanUtils.copy(answerQuestion, answerQuestionDto);
        return answerQuestionDto;
    }

    private QunaAnswerQuestion queryQunaAnswerQuestion(Long answerId, Long questionId) {
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
