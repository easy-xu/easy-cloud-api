package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.entity.QunaConfigOption;
import pro.simplecloud.quna.entity.QunaConfigQuestion;
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
    private IQunaConfigOptionService optionService;

    @Override
    public QuestionDto getDetail(Long questionId) {
        //查询问题
        QunaConfigQuestion question = questionService.getById(questionId);
        if (question == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //查询问题选项
        List<QunaConfigOption> optionDtos = getOptionDtos(question.getId());
        //数据封装 QuestionDto
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copy(question, questionDto);
        questionDto.setOptions(optionDtos);
        return questionDto;
    }

    @Override
    public QuestionDto getDetailByIndex(Long questionnaireId, Long questionIndex) {
        //查询问题
        QunaConfigQuestion question = new QunaConfigQuestion();
        question.setQuestionnaireId(questionnaireId);
        question.setOrderNum(questionIndex);
        //查询问题
        List<QunaConfigQuestion> questions = questionService.list(Wrappers.query(question));
        if (questions.isEmpty()) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        if (questions.size() > 1) {
            throw new RequestException(Messages.DB_DATA_ERROR);
        }
        question = questions.get(0);
        List<QunaConfigOption> optionDtos = getOptionDtos(question.getId());
        //数据封装 QuestionDto
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copy(question, questionDto);
        questionDto.setOptions(optionDtos);
        return questionDto;
    }

    private List<QunaConfigOption> getOptionDtos(Long questionId) {
        //查询问题选项
        LambdaQueryWrapper<QunaConfigOption> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(QunaConfigOption::getQuestionId, questionId);
        return optionService.list(queryWrapper);
    }
}
