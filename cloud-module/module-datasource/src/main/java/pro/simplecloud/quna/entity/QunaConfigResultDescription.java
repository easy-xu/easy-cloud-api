package pro.simplecloud.quna.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.entity.BaseEntity;

/**
 * <p>
 * 测试问卷结果配置表
 * </p>
 *
 * @author Generator
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigResultDescription extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 结果ID
     */
    private Long resultId;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述文本
     */
    private String value;

    /**
     * 问题排序
     */
    private Integer orderNum;


}
