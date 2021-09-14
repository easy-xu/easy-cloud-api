package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷结果配置表
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 结果ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 结果描述文本
     */
    private String text;


}
