package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题选项表
 * </p>
 *
 * @author Generator
 * @since 2021-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigOption extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 选项文本
     */
    private String text;

    /**
     * 选项值
     */
    private String value;


}
