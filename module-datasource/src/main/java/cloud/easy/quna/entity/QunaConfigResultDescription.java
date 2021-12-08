package cloud.easy.quna.entity;

import cloud.easy.base.entity.AuthEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷结果配置表
 * </p>
 *
 * @author Generator
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigResultDescription extends AuthEntity {

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
