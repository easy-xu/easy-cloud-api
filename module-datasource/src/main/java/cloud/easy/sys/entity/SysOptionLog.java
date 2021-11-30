package cloud.easy.sys.entity;

import cloud.easy.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作记录表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-30
 */
@Getter
@Setter
@TableName("sys_option_log")
@ApiModel(value = "SysOptionLog对象", description = "操作记录表")
public class SysOptionLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    private String userNo;

    @ApiModelProperty("设备编号")
    private String deviceNo;

    @ApiModelProperty("操作名称")
    private String optionName;


}
