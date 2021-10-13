package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题表
 * </p>
 *
 * @author Generator
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigQuestion extends LogicDeleteEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题排序
     */
    private Integer orderNum;


}
