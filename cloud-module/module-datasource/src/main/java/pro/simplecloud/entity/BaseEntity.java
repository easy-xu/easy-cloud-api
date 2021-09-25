package pro.simplecloud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Title: BaseEntity
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 删除标志（0正常 1停用）
     */
    @JsonIgnore
    @TableLogic
    private String deleted;

    /**
     * 创建者
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
