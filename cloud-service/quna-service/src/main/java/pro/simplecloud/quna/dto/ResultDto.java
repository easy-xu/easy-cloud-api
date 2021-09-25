package pro.simplecloud.quna.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.quna.entity.QunaAnswerResult;

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
}
