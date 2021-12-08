package cloud.easy.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;

/**
 * 操作记录实体类
 *
 * @author generator
 * @since 2021-12-08
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
    @ApiModelProperty("用户编号")
    private String userNo;
    /**
     * 设备编号
     */
    @ApiModelProperty("设备编号")
    private String deviceNo;
    /**
     * 操作名称
     */
    @ApiModelProperty("操作名称")
    private String optionName;

}
