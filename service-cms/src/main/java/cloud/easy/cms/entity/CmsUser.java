package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import cloud.easy.base.entity.AuthEntity;
import cloud.easy.validation.UniqueColumn;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.annotation.TableField;
import org.hibernate.validator.constraints.Length;

/**
 * 用户实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_user")
@ApiModel(value = "CmsUser", description = "用户实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsUser extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Length(max = 60, message = "用户名长度不能超过60")
    @UniqueColumn(table = "cms_user", column = "username", message = "用户名已存在")
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 用户编号
     */
    @Length(max = 60, message = "用户编号长度不能超过60")
    @UniqueColumn(table = "cms_user", column = "user_no", message = "用户编号已存在")
    @ApiModelProperty("用户编号")
    private String userNo;
    /**
     * 设备编号
     */
    @Length(max = 60, message = "设备编号长度不能超过60")
    @ApiModelProperty("设备编号")
    private String deviceNo;
    /**
     * 密码
     */
    @Length(max = 100, message = "密码长度不能超过100")
    @ApiModelProperty("密码")
    private String password;
    /**
     * 用户昵称
     */
    @Length(max = 30, message = "用户昵称长度不能超过30")
    @ApiModelProperty("用户昵称")
    private String nickname;
    /**
     * 认证
     */
    @Length(max = 300, message = "认证长度不能超过300")
    @ApiModelProperty("认证")
    private String token;
    /**
     * 用户邮箱
     */
    @Length(max = 50, message = "用户邮箱长度不能超过50")
    @ApiModelProperty("用户邮箱")
    private String email;
    /**
     * 手机号码
     */
    @Length(max = 11, message = "手机号码长度不能超过11")
    @ApiModelProperty("手机号码")
    private String phoneNumber;
    /**
     * 用户性别
     */
    @ApiModelProperty("用户性别")
    private String sex;
    /**
     * 头像地址
     */
    @Length(max = 100, message = "头像地址长度不能超过100")
    @ApiModelProperty("头像地址")
    private String avatar;
    /**
     * 当前分组
     */
    @ApiModelProperty("当前分组")
    private Long defaultGroupId;
    /**
     * 角色
     */
    @ApiModelProperty("角色")
    @TableField(exist = false)
    private List<Long> roleIds;
    /**
     * 分组
     */
    @ApiModelProperty("分组")
    @TableField(exist = false)
    private List<Long> groupIds;

}
