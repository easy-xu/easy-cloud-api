package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.quna.constant.AnswerFlow;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.entity.QunaConfigQuestion;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
import pro.simplecloud.quna.service.IQunaAnswerQuestionnaireService;
import pro.simplecloud.quna.service.IQunaConfigQuestionService;
import pro.simplecloud.quna.service.IQunaConfigQuestionnaireService;
import pro.simplecloud.quna.service.QuestionnaireService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: QuestionnaireServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Resource
    private IQunaConfigQuestionnaireService questionnaireService;

    @Resource
    private IQunaConfigQuestionService questionService;

    @Resource
    private IQunaAnswerQuestionnaireService answerQuestionnaireService;

    @Override
    public QuestionnaireDto getDetail(Long questionnaireId) {
        //查询文件配置
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        if (questionnaire == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //封装数据
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        BeanUtils.copy(questionnaire, questionnaireDto);
        //查询是否正在进行
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
        //answerQuestionnaire.setFlow(AnswerFlow.ANSWER.value);
        List<QunaAnswerQuestionnaire> answerQuestionnaires = answerQuestionnaireService.list(Wrappers.query(answerQuestionnaire));
        if (answerQuestionnaires.size() > 1) {
            //只能有一个问题正在进行
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        if (answerQuestionnaires.size() == 1) {
            answerQuestionnaire = answerQuestionnaires.get(0);
            AnswerDto answerDto = new AnswerDto();
            BeanUtils.copy(answerQuestionnaire, answerDto);
            questionnaireDto.setAnswer(answerDto);
        }
        return questionnaireDto;
    }


    @Override
    public QuestionnaireDto init(Long questionnaireId) {
        //检验是否已存在问卷
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
        List<QunaAnswerQuestionnaire> answerQuestionnaires = answerQuestionnaireService.list(Wrappers.query(answerQuestionnaire));
        if (!answerQuestionnaires.isEmpty()) {
            throw new RequestException(Messages.ALREADY_DONE);
        }
        //获取问题配置
        QuestionnaireDto questionnaireDto = this.getQuestions(questionnaireId);
        //初始化回答
        answerQuestionnaire.setFlow(AnswerFlow.INIT.value);
        answerQuestionnaire.setQuestionIndex(1L);
        answerQuestionnaireService.save(answerQuestionnaire);
        AnswerDto answerDto = new AnswerDto();
        BeanUtils.copy(answerQuestionnaire, answerDto);
        questionnaireDto.setAnswer(answerDto);
        return questionnaireDto;
    }

    @Override
    public QuestionnaireDto getQuestions(Long questionnaireId) {
        //查询文件配置
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        if (questionnaire == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //封装数据
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        BeanUtils.copy(questionnaire, questionnaireDto);
        //查询问题
        QunaConfigQuestion question = new QunaConfigQuestion();
        question.setQuestionnaireId(questionnaireId);
        List<QunaConfigQuestion> questions = questionService.list(Wrappers.query(question).orderByAsc("order_num"));
        if (questions.isEmpty()) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        //封装数据
        List<QuestionDto> questionDtos = questions.stream().map(item -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copy(item, questionDto);
            return questionDto;
        }).collect(Collectors.toList());
        questionnaireDto.setQuestions(questionDtos);
        return questionnaireDto;
    }
}
