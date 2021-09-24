package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.quna.dto.PageDto;
import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;
import pro.simplecloud.quna.entity.QunaConfigQuestion;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
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
        return questionnaireDto;
    }


    @Override
    public List<QuestionDto> getQuestions(Long questionnaireId) {
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
        return questions.stream().map(item -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copy(item, questionDto);
            return questionDto;
        }).collect(Collectors.toList());
    }

    @Override
    public PageDto<QuestionnaireDto> pageList(PageDto<QuestionnaireDto> pageDto) {
        //分页
        Page<QunaConfigQuestionnaire> page = new Page<>(pageDto.getCurrent(), pageDto.getSize());
        //排序
        page.setOrders(pageDto.getOrders());
        //查询条件
        QueryWrapper<QunaConfigQuestionnaire> queryWrapper;
        if (pageDto.getQuery() != null) {
            QunaConfigQuestionnaire questionnaire = new QunaConfigQuestionnaire();
            BeanUtils.copy(pageDto.getQuery(), questionnaire);
            queryWrapper = Wrappers.query(questionnaire);
        } else {
            queryWrapper = Wrappers.query();
        }
        page = questionnaireService.page(page, queryWrapper);
        //数据封装
        List<QunaConfigQuestionnaire> records = page.getRecords();
        List<QuestionnaireDto> recordsDto = records.stream().map(item -> {
            QuestionnaireDto dto = new QuestionnaireDto();
            BeanUtils.copy(item, dto);
            return dto;
        }).collect(Collectors.toList());
        pageDto.setRecords(recordsDto);
        return pageDto;
    }
}
