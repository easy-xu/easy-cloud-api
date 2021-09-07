package pro.simplecloud.user.service;

import pro.simplecloud.user.dto.UserInfo;

/**
 * Title: UserService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface UserService {
    /**
     * 用户注册
     * @param userInfo 用户信息
     */
    void signIn(UserInfo userInfo);
}
