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

    private Long id;
    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题排序
     */
    private Long orderNum;


    private List<OptionDto> options;

}
