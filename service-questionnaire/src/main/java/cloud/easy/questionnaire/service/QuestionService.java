package cloud.easy.questionnaire.service;

import cloud.easy.questionnaire.dto.QuestionDto;

/**
 * Title: QuestionService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface QuestionService {
    /**
     * 查询问题详情
     *
     * @param questionId 问题ID
     * @return QuestionDto
     */
    QuestionDto getDetail(Long questionId);

    /**
     * 根据问题编号查询问题
     *
     * @param questionnaireId 问卷Id
     * @param questionIndex   问题index
     * @return QuestionDto
     */
    QuestionDto getDetailByIndex(Long questionnaireId, Integer questionIndex);
}
