package cloud.easy.cms.service;

import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.entity.CmsUser;

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
     *
     * @param userDto 用户信息
     */
    void signIn(UserDto userDto);

    /**
     * 用户登录
     *
     * @param userDto 用户信息
     * @return userDto 用户信息
     */
    UserDto login(UserDto userDto);


    /**
     * 用户退出
     * @return userDto
     */
    UserDto logout();

    /**
     * 初始化用户
     *
     * @return UserDto
     */
    UserDto initDevice();

    /**
     * 保存用户
     *
     * @param cmsUser 用户信息
     */
    void save(CmsUser cmsUser);

    /**
     * 查询用户详情
     *
     * @param id 用户id
     * @return UserDto
     */
    UserDto getDetail(Long id);

    /**
     * 删除用户详情
     *
     * @param id 用户id
     */
    void deleteDetail(Long id);

    /**
     * 重置密码
     *
     * @param userDto 用户信息
     */
    void resetPassword(UserDto userDto);


}
