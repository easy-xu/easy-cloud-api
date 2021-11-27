package cloud.easy.questionnaire.entity;

import cloud.easy.base.entity.PrimaryDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题选项表
 * </p>
 *
 * @author Generator
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigOption extends PrimaryDataEntity {

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
