package cloud.easy.questionnaire.service;

import cloud.easy.quna.entity.QunaAnswerQuestion;
import cloud.easy.quna.entity.QunaAnswerQuestionnaire;

/**
 * Title: AnswerService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface AnswerService {


    /**
     * 初始化问卷
     *
     * @param questionnaireId 问卷id
     * @return AnswerDto
     */
    QunaAnswerQuestionnaire init(Long questionnaireId);


    /**
     * 查看问题状态
     *
     * @param answerId 回答id
     * @return AnswerDto
     */
    QunaAnswerQuestionnaire status(Long answerId);

    /**
     * 获取回答情况
     *
     * @param answerId 回答id
     * @return AnswerDto
     */
    QunaAnswerQuestionnaire getDetail(Long answerId);

    /**
     * 获取回答情况
     *
     * @param questionnaireId 问卷id
     * @return AnswerDto
     */
    QunaAnswerQuestionnaire getDetailByQuestionnaireId(Long questionnaireId);

    /**
     * 保存问题答案
     *
     * @param answerQuestion 问题答案
     * @return QunaAnswerQuestion
     */
    void saveAnswerQuestion(QunaAnswerQuestion answerQuestion);

    /**
     * 查询问题答案
     *
     * @param answerId   回答id
     * @param questionId 问题id
     * @return QunaAnswerQuestion
     */
    QunaAnswerQuestion getAnswerQuestion(Long answerId, Long questionId);

}
