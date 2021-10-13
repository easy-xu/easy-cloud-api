package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题选项表
 * </p>
 *
 * @author Generator
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigOption extends LogicDeleteEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 选项文本
     */
    private String text;

    /**
     * 选项值
     */
    private String value;


}
