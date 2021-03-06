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
public class QunaConfigResult extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 标题
     */
    private String title;

    /**
     * 简要描述
     */
    private String shortDesc;


}
