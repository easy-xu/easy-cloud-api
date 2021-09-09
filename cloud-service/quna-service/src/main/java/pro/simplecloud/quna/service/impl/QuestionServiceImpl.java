package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.constant.Status;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.quna.dto.OptionDto;
import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.entity.QunaConfig;
import pro.simplecloud.quna.entity.QunaConfigOption;
import pro.simplecloud.quna.entity.QunaConfigQuestion;
import pro.simplecloud.quna.service.IQunaConfigOptionService;
import pro.simplecloud.quna.service.IQunaConfigQuestionService;
import pro.simplecloud.quna.service.IQunaConfigService;
import pro.simplecloud.quna.service.QuestionService;

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
    private IQunaConfigQuestionService configQuestionService;

    @Resource
    private IQunaConfigService configService;

    @Resource
    private IQunaConfigOptionService configOptionService;

    @Override
    public QuestionDto getQuestionDetail(String questionId) {
        //查询问题
        QunaConfigQuestion configQuestion = configQuestionService.getById(questionId);
        if (configQuestion == null) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        //查询主配置
        Long configId = configQuestion.getQunaId();
        QunaConfig config = configService.getById(configId);
        //查询问题配置
        LambdaQueryWrapper<QunaConfigOption> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(QunaConfigOption::getQuestionId, questionId)
                .eq(QunaConfigOption::getStatus, Status.ACTIVE);
        List<QunaConfigOption> configOptions = configOptionService.list(queryWrapper);
        //数据封装
        List<OptionDto> optionDtos = configOptions.stream().map(option -> {
            OptionDto optionDto = new OptionDto();
            optionDto.setText(option.getTxt());
            return optionDto;
        }).collect(Collectors.toList());
        QuestionDto questionDto = new QuestionDto();
        questionDto.setTitle(config.getTitle());
        questionDto.setQuestion(configQuestion.getTitle());
        questionDto.setOptions(optionDtos);
        return questionDto;
    }
}
