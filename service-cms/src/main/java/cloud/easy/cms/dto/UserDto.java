package cloud.easy.cms.dto;

import cloud.easy.base.dto.PrimaryKeyDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: UserDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends PrimaryKeyDto {
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
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
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;
    /**
     * 认证
     */
    @ApiModelProperty("认证")
    private String token;
}
