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
 * 接口日志表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-30
 */
@Getter
@Setter
@TableName("sys_api_log")
@ApiModel(value = "SysApiLog对象", description = "接口日志表")
public class SysApiLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("请求流水号")
    private String requestId;

    @ApiModelProperty("用户编号")
    private String userNo;

    @ApiModelProperty("设备编号")
    private String deviceNo;

    @ApiModelProperty("接口编码")
    private String requestCode;

    @ApiModelProperty("接口地址")
    private String requestPath;

    @ApiModelProperty("业务编号")
    private String businessNo;

    @ApiModelProperty("系统编号")
    private String sysCode;

    @ApiModelProperty("接口耗时")
    private Long usedTime;

    @ApiModelProperty("结果编码")
    private Long responseCode;

    @ApiModelProperty("结果描述")
    private String responseMessage;


}
