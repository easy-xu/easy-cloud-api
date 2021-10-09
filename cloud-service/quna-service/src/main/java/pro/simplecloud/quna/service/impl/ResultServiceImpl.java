package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.annotation.AroundLog;
import pro.simplecloud.quna.dto.ResultDto;
import pro.simplecloud.quna.entity.QunaAnswerResult;
import pro.simplecloud.quna.entity.QunaConfigResultDescription;
import pro.simplecloud.quna.mapper.QunaAnswerResultMapperCust;
import pro.simplecloud.quna.service.IQunaAnswerResultService;
import pro.simplecloud.quna.service.IQunaConfigResultDescriptionService;
import pro.simplecloud.quna.service.IQunaConfigResultService;
import pro.simplecloud.quna.service.ResultService;

import javax.annotation.Resource;
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
    private IQunaAnswerResultService resultService;

    @Resource
    private IQunaConfigResultService configResultService;

    @Resource
    private IQunaConfigResultDescriptionService configResultDescriptionService;

    @Resource
    private QunaAnswerResultMapperCust resultMapperCust;

    @AroundLog
    @Override
    public void calculateScore(Long answerId) {
        List<QunaAnswerResult> answerResults = resultMapperCust.sunScore(answerId);
        resultService.saveBatch(answerResults);
    }

    @Override
    public List<ResultDto> listByAnswerId(Long answerId) {
        List<ResultDto> resultDtos = resultMapperCust.listByAnswerId(answerId);
        resultDtos.forEach(resultDto -> {
            Long resultId = resultDto.getResultId();
            QunaConfigResultDescription description = new QunaConfigResultDescription();
            description.setResultId(resultId);
            List<QunaConfigResultDescription> descriptions = configResultDescriptionService.list(Wrappers.query(description));
            resultDto.setDescriptions(descriptions);
        });
        return resultDtos;
    }
}
