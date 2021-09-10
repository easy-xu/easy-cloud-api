package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.QuestionDto;

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
    QuestionDto getDetail(String questionId);
}
