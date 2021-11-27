package cloud.easy.questionnaire.dto;

import cloud.easy.entity.ApiRequest;
import cloud.easy.questionnaire.entity.QunaConfigOption;
import cloud.easy.questionnaire.entity.QunaConfigQuestion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Title: QuestionDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionDto extends QunaConfigQuestion implements ApiRequest {

    private List<QunaConfigOption> options;

}
