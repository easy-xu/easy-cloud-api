package pro.simplecloud.quna.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.quna.constant.AnswerFlow;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.service.AnswerService;
import pro.simplecloud.quna.service.IQunaAnswerQuestionnaireService;
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

    @Override
    public AnswerDto init(Long questionnaireId) {
        //检验是否已存在问卷
        QunaAnswerQuestionnaire answerQuestionnaire = new QunaAnswerQuestionnaire();
        answerQuestionnaire.setQuestionnaireId(questionnaireId);
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
        return answerDto;
    }

}
