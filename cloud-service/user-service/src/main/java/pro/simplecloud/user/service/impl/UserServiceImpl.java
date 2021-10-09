package pro.simplecloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.cms.entity.CmsUser;
import pro.simplecloud.cms.service.ICmsUserService;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.idgenerator.IDGeneratorInstance;
import pro.simplecloud.user.constant.UserType;
import pro.simplecloud.user.dto.UserDto;
import pro.simplecloud.user.service.UserService;
import pro.simplecloud.utils.BeanUtils;
import pro.simplecloud.utils.PasswordUtils;
import pro.simplecloud.utils.UserTokenUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Title: UserService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ICmsUserService userService;

    @Override
    public void signIn(UserDto userDto) {
        String username = userDto.getUsername();
        //是否重复注册
        QueryWrapper<CmsUser> queryWrapper = new QueryWrapper<CmsUser>().eq("username", username);
        int count = userService.count(queryWrapper);
        if (count >= 1) {
            throw new RequestException(Messages.USERNAME_EXIST);
        }
        //更新游客信息为注册用户
        ApiHeader apiHeader = ApiHeaderHelper.get();
        String userNo = apiHeader.getUsername();
        String token = apiHeader.getToken();
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUserNo(userNo);
        cmsUser.setToken(token);
        List<CmsUser> users = userService.list(Wrappers.query(cmsUser));
        if (users.isEmpty()) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        if (users.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        cmsUser = users.get(0);
        cmsUser.setType(UserType.USER.code);
        cmsUser.setUsername(username);
        //密码加密
        cmsUser.setPassword(PasswordUtils.encrypt(cmsUser.getPassword()));
        userService.saveOrUpdate(cmsUser);
    }

    @Override
    public UserDto login(UserDto userDto) {
        //查询用户
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUsername(userDto.getUsername());
        cmsUser.setPassword(PasswordUtils.encrypt(cmsUser.getPassword()));
        List<CmsUser> users = userService.list(Wrappers.query(cmsUser));
        if (users.isEmpty()) {
            throw new RequestException(Messages.LOGIN_ERROR);
        } else if (users.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        cmsUser = users.get(0);
        //更新token
        String token = UserTokenUtils.generateToken(cmsUser.getUsername());
        cmsUser.setToken(token);
        userService.saveOrUpdate(cmsUser);
        BeanUtils.copy(cmsUser, userDto);
        return userDto;
    }

    @Override
    public UserDto initUser() {
        String userNo = IDGeneratorInstance.USER_NO.generate();
        String token = UserTokenUtils.generateToken(userNo);
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUsername(userNo);
        cmsUser.setUserNo(userNo);
        cmsUser.setToken(token);
        userService.save(cmsUser);
        UserDto userDto = new UserDto();
        BeanUtils.copy(cmsUser, userDto);
        return userDto;
    }
}
