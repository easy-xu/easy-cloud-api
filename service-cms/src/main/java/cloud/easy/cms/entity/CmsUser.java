package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import cloud.easy.base.entity.AuthEntity;

/**
 * 用户实体类
 *
 * @author generator
 * @since 2021-12-09
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
    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String email;
    /**
     * 手机号码
     */
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
