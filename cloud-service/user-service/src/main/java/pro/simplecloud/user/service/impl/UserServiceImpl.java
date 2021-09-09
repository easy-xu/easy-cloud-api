package pro.simplecloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.user.dto.UserInfo;
import pro.simplecloud.user.entity.UserMaster;
import pro.simplecloud.user.service.IUserMasterService;
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
    private IUserMasterService userMasterService;

    @Override
    public void signIn(UserInfo userInfo) {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())) {
            throw new RequestException("用户名不能为为空");
        }
        //是否重复注册
        QueryWrapper<UserMaster> queryWrapper = new QueryWrapper<UserMaster>().eq("username", userInfo.getUsername());
        int count = userMasterService.count(queryWrapper);
        if (count >= 1) {
            throw new RequestException("用户名已注册");
        }
        //新增注册数据
        UserMaster userMaster = new UserMaster();
        BeanUtils.copy(userInfo, userMaster);
        //密码加密
        userMaster.setPassword(PasswordUtils.encrypt(userMaster.getPassword()));
        userMasterService.save(userMaster);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {

        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())) {
            throw new RequestException("用户名不能为为空");
        }
        //查询用户
        LambdaQueryWrapper<UserMaster> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserMaster::getUsername, userInfo.getUsername())
                .eq(UserMaster::getPassword, PasswordUtils.encrypt(userInfo.getPassword()));
        List<UserMaster> userMasters = userMasterService.list(queryWrapper);
        if (userMasters.isEmpty()) {
            throw new RequestException("用户名或密码错误");
        } else if (userMasters.size() > 1) {
            throw new SystemErrorException("数据错误");
        }
        UserMaster userMaster = userMasters.get(0);
        userInfo.setNickname(userMaster.getNickname());
        String token = UserTokenUtils.generateToken(userInfo.getUsername());
        userInfo.setToken(token);
        return userInfo;
    }
}
