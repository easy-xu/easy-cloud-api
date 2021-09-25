package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷结果配置表
 * </p>
 *
 * @author Generator
 * @since 2021-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigResult extends BaseEntity {

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
     * 结果描述文本
     */
    private String text;


}
