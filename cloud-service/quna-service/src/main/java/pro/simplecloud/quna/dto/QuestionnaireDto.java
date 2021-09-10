package pro.simplecloud.quna.dto;

import lombok.Data;
import pro.simplecloud.entity.ApiRequest;

import java.util.List;

/**
 * Title: QuestionnaireDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class QuestionnaireDto implements ApiRequest {
    private Long id;
    private String title;
    private Long questionNum;

    private List<QuestionDto> questions;

    private AnswerDto answer;
}
