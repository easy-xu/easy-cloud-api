package cloud.easy.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;

/**
 * 接口日志实体类
 *
 * @author generator
 * @since 2021-12-09
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
    @ApiModelProperty("请求流水号")
    private String requestId;
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
     * 接口编码
     */
    @ApiModelProperty("接口编码")
    private String requestCode;
    /**
     * 接口地址
     */
    @ApiModelProperty("接口地址")
    private String requestPath;
    /**
     * 业务编号
     */
    @ApiModelProperty("业务编号")
    private String businessNo;
    /**
     * 系统编号
     */
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
    @ApiModelProperty("结果描述")
    private String responseMessage;

}
