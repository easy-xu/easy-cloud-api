package pro.simplecloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.user.constant.UserType;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
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
        String username = userDto.getUsername();
        //是否重复注册
        QueryWrapper<UserMaster> queryWrapper = new QueryWrapper<UserMaster>().eq("username", username);
        int count = userMasterService.count(queryWrapper);
        if (count >= 1) {
            throw new RequestException(Messages.USERNAME_EXIST);
        }
        //更新游客信息为注册用户
        ApiHeader apiHeader = ApiHeaderHelper.get();
        String userNo = apiHeader.getUsername();
        String token = apiHeader.getToken();
        UserMaster userMaster = new UserMaster();
        userMaster.setUserNo(userNo);
        userMaster.setToken(token);
        List<UserMaster> userMasters = userMasterService.list(Wrappers.query(userMaster));
        if (userMasters.isEmpty()) {
            throw new RequestException(Messages.NOT_FOUND);
        }
        if (userMasters.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        userMaster = userMasters.get(0);
        userMaster.setType(UserType.USER.code);
        userMaster.setUsername(username);
        //密码加密
        userMaster.setPassword(PasswordUtils.encrypt(userMaster.getPassword()));
        userMasterService.saveOrUpdate(userMaster);
    }

    @Override
    public UserDto login(UserDto userDto) {
        //查询用户
        UserMaster userMaster = new UserMaster();
        userMaster.setUsername(userDto.getUsername());
        userMaster.setPassword(PasswordUtils.encrypt(userMaster.getPassword()));
        List<UserMaster> userMasters = userMasterService.list(Wrappers.query(userMaster));
        if (userMasters.isEmpty()) {
            throw new RequestException(Messages.LOGIN_ERROR);
        } else if (userMasters.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        userMaster = userMasters.get(0);
        //更新token
        String token = UserTokenUtils.generateToken(userMaster.getUsername());
        userMaster.setToken(token);
        userMasterService.saveOrUpdate(userMaster);
        BeanUtils.copy(userMaster, userDto);
        return userDto;
    }

    @Override
    public UserDto initUser() {
        String userNo = IDGeneratorInstance.USER_NO.generate();
        String token = UserTokenUtils.generateToken(userNo);
        UserMaster userMaster = new UserMaster();
        userMaster.setUsername(userNo);
        userMaster.setUserNo(userNo);
        userMaster.setToken(token);
        userMasterService.save(userMaster);
        UserDto userDto = new UserDto();
        BeanUtils.copy(userMaster, userDto);
        return userDto;
    }
}
