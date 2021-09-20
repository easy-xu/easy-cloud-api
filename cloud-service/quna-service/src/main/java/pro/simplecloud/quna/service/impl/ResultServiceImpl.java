package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.quna.dto.ResultDto;
import pro.simplecloud.quna.entity.QunaAnswerResult;
import pro.simplecloud.quna.entity.QunaConfigResult;
import pro.simplecloud.quna.mapper.QunaAnswerResultMapperCust;
import pro.simplecloud.quna.service.IQunaAnswerResultService;
import pro.simplecloud.quna.service.IQunaConfigResultService;
import pro.simplecloud.quna.service.ResultService;
import pro.simplecloud.utils.BeanUtils;
import pro.simplecloud.web.annotation.AroundLog;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private QunaAnswerResultMapperCust resultMapperCust;

    @AroundLog
    @Override
    public void calculateScore(Long answerId) {
        List<QunaAnswerResult> answerResults = resultMapperCust.sunScore(answerId);
        resultService.saveBatch(answerResults);
    }

    @Override
    public List<ResultDto> listByAnswerId(Long answerId) {
        return resultMapperCust.listByAnswerId(answerId);
    }
}
