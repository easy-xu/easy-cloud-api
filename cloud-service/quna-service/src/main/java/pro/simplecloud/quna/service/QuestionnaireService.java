package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;

import java.util.List;

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
     * 查询问题
     *
     * @param questionnaireId 问卷id
     * @return List<QuestionDto>
     */
    List<QuestionDto> getQuestions(Long questionnaireId);
}
