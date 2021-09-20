package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷结果分值配置表
 * </p>
 *
 * @author Generator
 * @since 2021-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigResultScore extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 结果ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 选项ID
     */
    private Long optionId;

    /**
     * 结果ID
     */
    private Long resultId;

    /**
     * 分值
     */
    private Long score;


}
