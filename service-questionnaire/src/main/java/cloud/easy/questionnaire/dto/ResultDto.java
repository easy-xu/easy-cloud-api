package cloud.easy.questionnaire.dto;

import cloud.easy.quna.entity.QunaAnswerResult;
import cloud.easy.quna.entity.QunaConfigResultDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Title: ResultDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultDto extends QunaAnswerResult {

    /**
     * 标题
     */
    private String title;

    /**
     * 结果描述文本
     */
    private String text;

    /**
     * 结果描述集合
     */
    private List<QunaConfigResultDescription> descriptions;
}
