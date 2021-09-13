package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.AnswerQuestionDto;

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
    AnswerDto init(Long questionnaireId);

    /**
     * 获取回答情况
     *
     * @param answerId 回答id
     * @return AnswerDto
     */
    AnswerDto getDetail(Long answerId);

    /**
     * 保存问题答案
     *
     * @param answerQuestionDto 问题答案
     * @return AnswerQuestionDto
     */
    AnswerQuestionDto saveAnswerQuestion(AnswerQuestionDto answerQuestionDto);

    /**
     * 查询问题答案
     *
     * @param answerId   回答id
     * @param questionId 问题id
     * @return AnswerQuestionDto
     */
    AnswerQuestionDto getAnswerQuestion(Long answerId, Long questionId);
}
