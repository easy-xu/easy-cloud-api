package cloud.easy.quna.dto;

import lombok.Data;

/**
 * Title: AnswerQuestionDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class AnswerQuestionDto {

    private Long id;

    /**
     * 问卷回答ID
     */
    private Long answerId;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 问题选择选项ID
     */
    private Long optionId;

    /**
     * 问题选择选项值
     */
    private String optionValue;
}
