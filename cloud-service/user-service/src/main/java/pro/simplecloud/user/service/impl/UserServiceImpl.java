package pro.simplecloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.idgenerator.IDGeneratorInstance;
import pro.simplecloud.user.dto.UserDto;
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
    public void signIn(UserDto userDto) {
        //是否重复注册
        QueryWrapper<UserMaster> queryWrapper = new QueryWrapper<UserMaster>().eq("username", userDto.getUsername());
        int count = userMasterService.count(queryWrapper);
        if (count >= 1) {
            throw new RequestException(Messages.USERNAME_EXIST);
        }
        //新增注册数据
        UserMaster userMaster = new UserMaster();
        BeanUtils.copy(userDto, userMaster);
        //密码加密
        userMaster.setPassword(PasswordUtils.encrypt(userMaster.getPassword()));
        userMasterService.save(userMaster);
    }

    @Override
    public UserDto login(UserDto userDto) {
        //查询用户
        LambdaQueryWrapper<UserMaster> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserMaster::getUsername, userDto.getUsername())
                .eq(UserMaster::getPassword, PasswordUtils.encrypt(userDto.getPassword()));
        List<UserMaster> userMasters = userMasterService.list(queryWrapper);
        if (userMasters.isEmpty()) {
            throw new RequestException(Messages.LOGIN_ERROR);
        } else if (userMasters.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        UserMaster userMaster = userMasters.get(0);
        userDto.setNickname(userMaster.getNickname());
        String token = UserTokenUtils.generateToken(userDto.getUsername());
        userDto.setToken(token);
        return userDto;
    }

    @Override
    public UserDto initUser() {
        String userNo = IDGeneratorInstance.USER_NO.generate();
        UserMaster userMaster = new UserMaster();
        userMaster.setUsername(userNo);
        userMaster.setUserNo(userNo);
        userMasterService.save(userMaster);
        String token = UserTokenUtils.generateToken(userNo);
        UserDto userDto = new UserDto();
        BeanUtils.copy(userMaster, userDto);
        userDto.setToken(token);
        return userDto;
    }
}
