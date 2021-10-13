package pro.simplecloud.cms.service;

import pro.simplecloud.cms.dto.UserDto;

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
     * 初始化用户
     *
     * @return UserDto
     */
    UserDto initUser();

    /**
     * 保存用户
     * @param userDto 用户信息
     */
    void save(UserDto userDto);

    /**
     * 查询用户详情
     * @param id 用户id
     * @return UserDto
     */
    UserDto getDetail(Long id);
}
