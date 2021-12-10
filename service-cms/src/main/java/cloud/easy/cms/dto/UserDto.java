package cloud.easy.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "用户名不能为空")
    @Max(value = 60, message = "用户名长度不能超过60")
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
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
