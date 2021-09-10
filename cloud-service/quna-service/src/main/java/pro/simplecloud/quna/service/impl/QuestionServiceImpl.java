package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.quna.dto.OptionDto;
import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;
import pro.simplecloud.quna.entity.QunaConfigOption;
import pro.simplecloud.quna.entity.QunaConfigQuestion;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
import pro.simplecloud.quna.service.IQunaConfigOptionService;
import pro.simplecloud.quna.service.IQunaConfigQuestionService;
import pro.simplecloud.quna.service.IQunaConfigQuestionnaireService;
import pro.simplecloud.quna.service.QuestionService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: QuestionServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private IQunaConfigQuestionService questionService;

    @Resource
    private IQunaConfigQuestionnaireService questionnaireService;

    @Resource
    private IQunaConfigOptionService optionService;

    @Override
    public QuestionDto getDetail(String questionId) {
        //查询问题
        QunaConfigQuestion question = questionService.getById(questionId);
        if (question == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //查询主配置
        Long questionnaireId = question.getQuestionnaireId();
        QunaConfigQuestionnaire questionnaire = questionnaireService.getById(questionnaireId);
        //查询问题配置
        LambdaQueryWrapper<QunaConfigOption> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(QunaConfigOption::getQuestionId, questionId);
        List<QunaConfigOption> options = optionService.list(queryWrapper);
        //数据封装 QuestionnaireDto
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        BeanUtils.copy(questionnaire, questionnaireDto);
        //数据封装 OptionDto
        List<OptionDto> optionDtos = options.stream().map(option -> {
            OptionDto optionDto = new OptionDto();
            BeanUtils.copy(option, optionDto);
            return optionDto;
        }).collect(Collectors.toList());
        //数据封装 QuestionDto
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copy(question, questionDto);
        questionDto.setQuestionnaire(questionnaireDto);
        questionDto.setOptions(optionDtos);
        return questionDto;
    }
}
