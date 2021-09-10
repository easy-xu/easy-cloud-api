package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.QuestionnaireDto;

/**
 * Title: QuestionnaireService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface QuestionnaireService {
    /**
     * 获取问卷详情
     *
     * @param questionnaireId 问卷id
     * @return QuestionnaireDto
     */
    QuestionnaireDto getDetail(Long questionnaireId);

    /**
     * 初始化问卷
     *
     * @param questionnaireId 问卷id
     * @return AnswerDto
     */
    QuestionnaireDto init(Long questionnaireId);

    /**
     * 查询问题
     *
     * @param questionnaireId 问卷id
     * @return QuestionnaireDto
     */
    QuestionnaireDto getQuestions(Long questionnaireId);
}
