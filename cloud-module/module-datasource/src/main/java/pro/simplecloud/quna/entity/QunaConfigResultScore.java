package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷结果分值配置表
 * </p>
 *
 * @author Generator
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigResultScore extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 选项ID
     */
    private Long optionId;

    /**
     * 结果ID
     */
    private Long resultId;

    /**
     * 分值
     */
    private Long score;


}
