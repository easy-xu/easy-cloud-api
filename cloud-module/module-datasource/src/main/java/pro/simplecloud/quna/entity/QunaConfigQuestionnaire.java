package pro.simplecloud.quna.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷主表
 * </p>
 *
 * @author Generator
 * @since 2021-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QunaConfigQuestionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问卷标题
     */
    private String title;

    /**
     * 问题个数
     */
    private Long questionNum;

    /**
     * 参与人数
     */
    private Long participantNum;

    /**
     * 喜欢个数
     */
    private Long likeNum;

    /**
     * 问卷状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
