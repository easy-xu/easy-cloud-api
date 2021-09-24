package pro.simplecloud.quna.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pro.simplecloud.quna.dto.PageDto;
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

    /**
     * 分页查询
     * @param pageDto 分页对象
     * @return Page<QuestionnaireDto>
     */
    PageDto<QuestionnaireDto> pageList(PageDto<QuestionnaireDto> pageDto);
}
