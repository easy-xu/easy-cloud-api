package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷问题表
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigQuestion extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
    private Long orderNum;


}
