package pro.simplecloud.quna.service.impl;

import org.springframework.stereotype.Service;
import pro.simplecloud.quna.service.IQunaAnswerQuestionService;
import pro.simplecloud.quna.service.IQunaAnswerQuestionnaireService;
import pro.simplecloud.quna.service.IQunaConfigQuestionnaireService;
import pro.simplecloud.quna.service.ResultService;
import pro.simplecloud.web.annotation.AroundLog;

import javax.annotation.Resource;

/**
 * Title: ResultServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Resource
    private IQunaAnswerQuestionnaireService answerQuestionnaireService;

    @Resource
    private IQunaAnswerQuestionService answerQuestionService;

    @Resource
    private IQunaConfigQuestionnaireService questionnaireService;


    @AroundLog
    @Override
    public void calculateScore(Long answerId) {

    }
}
