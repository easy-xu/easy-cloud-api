package cloud.easy.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;
import javax.validation.constraints.Max;

/**
 * 操作记录实体类
 *
 * @author generator
 * @since 2021-12-10
 */
@Data
@TableName("sys_option_log")
@ApiModel(value = "SysOptionLog", description = "操作记录实体类")
@EqualsAndHashCode(callSuper = true)
public class SysOptionLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @Max(value = 60, message = "用户编号长度不能超过60")
    @ApiModelProperty("用户编号")
    private String userNo;
    /**
     * 设备编号
     */
    @Max(value = 60, message = "设备编号长度不能超过60")
    @ApiModelProperty("设备编号")
    private String deviceNo;
    /**
     * 操作名称
     */
    @Max(value = 60, message = "操作名称长度不能超过60")
    @ApiModelProperty("操作名称")
    private String optionName;

}
