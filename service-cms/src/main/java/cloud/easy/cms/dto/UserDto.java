package cloud.easy.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * Title: UserDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class UserDto {

    /**
     * 主键
     */
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
    /**
     * 用户编号
     */
    @ApiModelProperty(hidden = true)
    private String userNo;
    /**
     * 设备编号
     */
    @ApiModelProperty(hidden = true)
    private String deviceNo;
    /**
     * 认证
     */
    @ApiModelProperty(hidden = true)
    private String token;

}
