package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;

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
     * @param answerId 回答id
     * @return AnswerDto
     */
    AnswerDto getDetail(Long answerId);
}
