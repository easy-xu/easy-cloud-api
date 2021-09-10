package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷回答表
 * </p>
 *
 * @author Generator
 * @since 2021-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaAnswerQuestionnaire extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷回答ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 当前问题编号
     */
    private Long questionIndex;

    /**
     * 问卷回答流程（0初始化 1回答中 2回答完毕 3生成结果）
     */
    private Long flow;


}
