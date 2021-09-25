package pro.simplecloud.quna.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.entity.ApiRequest;
import pro.simplecloud.quna.entity.QunaConfigOption;
import pro.simplecloud.quna.entity.QunaConfigQuestion;

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
