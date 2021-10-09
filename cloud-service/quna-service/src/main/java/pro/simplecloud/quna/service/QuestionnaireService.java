package pro.simplecloud.quna.service;

import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;

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
     * 查询问题
     *
     * @param questionnaireId 问卷id
     * @return List<QuestionDto>
     */
    List<QuestionDto> getQuestions(Long questionnaireId);

    /**
     * 从已上传文件中导入问卷配置
     *
     * @param fileId 文件id
     * @return QunaConfigQuestionnaire
     */
    QunaConfigQuestionnaire importExcel(Long fileId);

    /**
     * 删除问卷配置
     *
     * @param id 问卷id
     */
    void deleteConfig(Long id);
}
