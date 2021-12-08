package cloud.easy.cms.entity;

import cloud.easy.base.entity.AuthEntity;
import cloud.easy.cms.enums.UserTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息主表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Getter
@Setter
@TableName("cms_user")
public class CmsUser extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户类型（00访客）
     */
    private UserTypeEnum type;

    /**
     * 认证
     */
    private String token;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户默认分组
     */
    private Long defaultGroupId;


}
