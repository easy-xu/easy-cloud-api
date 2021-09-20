package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷答案结果表
 * </p>
 *
 * @author Generator
 * @since 2021-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaAnswerResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 结果ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问卷回答ID
     */
    private Long answerId;

    /**
     * 结果ID
     */
    private Long resultId;

    /**
     * 分值
     */
    private Long score;


}
