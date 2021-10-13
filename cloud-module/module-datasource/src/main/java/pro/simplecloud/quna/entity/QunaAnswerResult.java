package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷答案结果表
 * </p>
 *
 * @author Generator
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaAnswerResult extends LogicDeleteEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷回答ID
     */
    private Long answerId;

    /**
     * 结果ID
     */
    private Long resultId;

    /**
     * 分值
     */
    private Long score;


}
