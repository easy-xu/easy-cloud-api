package pro.simplecloud.quna.dto;

import lombok.Data;
import pro.simplecloud.entity.ApiRequest;

import java.util.List;

/**
 * Title: QuestionDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class QuestionDto implements ApiRequest {

    private String title;
    private String question;
    private List<OptionDto> options;
}
