package cloud.easy.questionnaire.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: AnswerFlowEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum AnswerFlowEnum {
    /**
     * 0初始化 1回答中 2回答完毕 3完成计算
     */
    INIT(0L, "初始化"),
    ANSWER(1L, "回答中"),
    SUBMIT(2L, "回答完毕"),
    CALCULATE(3L, "开始计算"),
    CALCULATED(4L, "完成计算"),
    FINISH(5L, "完成计算");

    @JsonValue
    @EnumValue
    private final Long code;
    private final String name;

    AnswerFlowEnum(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
