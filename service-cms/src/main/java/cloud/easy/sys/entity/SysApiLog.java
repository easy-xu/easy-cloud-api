package cloud.easy.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;
import javax.validation.constraints.Max;

/**
 * 接口日志实体类
 *
 * @author generator
 * @since 2021-12-10
 */
@Data
@TableName("sys_api_log")
@ApiModel(value = "SysApiLog", description = "接口日志实体类")
@EqualsAndHashCode(callSuper = true)
public class SysApiLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 请求流水号
     */
    @Max(value = 60, message = "请求流水号长度不能超过60")
    @ApiModelProperty("请求流水号")
    private String requestId;
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
     * 接口编码
     */
    @Max(value = 20, message = "接口编码长度不能超过20")
    @ApiModelProperty("接口编码")
    private String requestCode;
    /**
     * 接口地址
     */
    @Max(value = 200, message = "接口地址长度不能超过200")
    @ApiModelProperty("接口地址")
    private String requestPath;
    /**
     * 业务编号
     */
    @Max(value = 20, message = "业务编号长度不能超过20")
    @ApiModelProperty("业务编号")
    private String businessNo;
    /**
     * 系统编号
     */
    @Max(value = 20, message = "系统编号长度不能超过20")
    @ApiModelProperty("系统编号")
    private String sysCode;
    /**
     * 接口耗时
     */
    @ApiModelProperty("接口耗时")
    private Long usedTime;
    /**
     * 结果编码
     */
    @ApiModelProperty("结果编码")
    private Long responseCode;
    /**
     * 结果描述
     */
    @Max(value = 65535, message = "结果描述长度不能超过65535")
    @ApiModelProperty("结果描述")
    private String responseMessage;

}
