package pro.simplecloud.quna.dto;

import lombok.Data;
import pro.simplecloud.entity.ApiRequest;

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
}
