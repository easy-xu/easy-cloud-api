package pro.simplecloud.quna.entity;

import pro.simplecloud.base.entity.PrimaryDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题回答表
 * </p>
 *
 * @author Generator
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaAnswerQuestion extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷回答ID
     */
    private Long answerId;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 问题选择选项ID
     */
    private Long optionId;

    /**
     * 问题选择选项值
     */
    private String optionValue;


}
