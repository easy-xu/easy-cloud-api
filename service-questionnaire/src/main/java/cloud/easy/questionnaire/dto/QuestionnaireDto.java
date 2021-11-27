package cloud.easy.questionnaire.dto;

import cloud.easy.entity.ApiRequest;
import cloud.easy.questionnaire.entity.QunaAnswerQuestionnaire;
import cloud.easy.questionnaire.entity.QunaConfigQuestionnaire;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
