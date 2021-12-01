package cloud.easy.quna.entity;

import cloud.easy.base.entity.AuthDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题表
 * </p>
 *
 * @author Generator
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigQuestion extends AuthDataEntity {

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
