package cloud.easy.quna.entity;

import cloud.easy.base.entity.PrimaryDataEntity;
import cloud.easy.quna.enums.AnswerFlowEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷回答表
 * </p>
 *
 * @author Generator
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaAnswerQuestionnaire extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 当前问题编号
     */
    private Integer questionIndex;

    /**
     * 问卷回答流程（0初始化 1回答中 2回答完毕 3生成结果）
     */
    private AnswerFlowEnum flow;


}
