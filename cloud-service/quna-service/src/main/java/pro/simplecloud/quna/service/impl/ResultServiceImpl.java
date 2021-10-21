package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.simplecloud.annotation.AroundLog;
import pro.simplecloud.quna.dto.ResultDto;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.entity.QunaAnswerResult;
import pro.simplecloud.quna.entity.QunaConfigResultDescription;
import pro.simplecloud.quna.enums.AnswerFlowEnum;
import pro.simplecloud.quna.mapper.QunaMapperCust;
import pro.simplecloud.quna.service.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private IQunaAnswerResultService answerResultService;

    @Resource
    private IQunaAnswerQuestionnaireService answerQuestionnaireService;

    @Resource
    private IQunaConfigResultService configResultService;

    @Resource
    private IQunaConfigResultDescriptionService configResultDescriptionService;

    @Resource
    private QunaMapperCust qunaMapperCust;

    @AroundLog
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculateScore(Long answerId) {
        //查询回答
        QunaAnswerQuestionnaire answerQuestionnaire = answerQuestionnaireService.getById(answerId);
        AnswerFlowEnum flow = answerQuestionnaire.getFlow();
        if (AnswerFlowEnum.SUBMIT.equals(flow)) {
            answerQuestionnaire.setFlow(AnswerFlowEnum.CALCULATE);
            answerQuestionnaireService.saveOrUpdate(answerQuestionnaire);
            //删除历史结果
            QunaAnswerResult answerResult = new QunaAnswerResult();
            answerResult.setAnswerId(answerId);
            answerResultService.remove(Wrappers.query(answerResult));
            //计算结果
            List<QunaAnswerResult> answerResults = qunaMapperCust.sunScore(answerId);
            answerResultService.saveBatch(answerResults);
            //更新状态
            answerQuestionnaire.setFlow(AnswerFlowEnum.CALCULATED);
            answerQuestionnaireService.saveOrUpdate(answerQuestionnaire);
        }

    }

    @Override
    public List<ResultDto> listByAnswerId(Long answerId) {
        //查询回答
        QunaAnswerQuestionnaire answerQuestionnaire = answerQuestionnaireService.getById(answerId);
        AnswerFlowEnum flow = answerQuestionnaire.getFlow();
        if (AnswerFlowEnum.CALCULATED.equals(flow)) {
            List<ResultDto> resultDtos = qunaMapperCust.listByAnswerId(answerId);
            resultDtos.forEach(resultDto -> {
                Long resultId = resultDto.getResultId();
                QunaConfigResultDescription description = new QunaConfigResultDescription();
                description.setResultId(resultId);
                List<QunaConfigResultDescription> descriptions = configResultDescriptionService.list(Wrappers.query(description));
                resultDto.setDescriptions(descriptions);
            });
            return resultDtos;
        }
       return new ArrayList<>();
    }
}
