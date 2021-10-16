package pro.simplecloud.cms.entity;

import pro.simplecloud.entity.PrimaryDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息主表
 * </p>
 *
 * @author Generator
 * @since 2021-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsUser extends PrimaryDataEntity {

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
    private String type;

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


}
