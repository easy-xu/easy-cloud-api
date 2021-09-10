package pro.simplecloud.quna.dto;

import lombok.Data;

/**
 * Title: AnswerDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class AnswerDto {

    private Long id;
    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 当前问题编号
     */
    private Long questionIndex;

    /**
     * 问卷回答流程（0初始化 1回答中 2回答完毕 3生成结果）
     */
    private Long flow;
}
