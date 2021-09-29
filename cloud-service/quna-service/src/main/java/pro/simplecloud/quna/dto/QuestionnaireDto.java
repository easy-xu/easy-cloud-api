package pro.simplecloud.quna.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.entity.ApiRequest;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;

import java.util.List;

/**
 * Title: QuestionnaireDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionnaireDto extends QunaConfigQuestionnaire implements ApiRequest {

    private List<QuestionDto> questions;

    private QuestionDto question;

    private QunaAnswerQuestionnaire answer;

    private Long fileId;
}
