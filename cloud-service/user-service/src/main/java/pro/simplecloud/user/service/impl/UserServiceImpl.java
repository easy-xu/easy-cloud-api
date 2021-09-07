package pro.simplecloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.user.dto.UserInfo;
import pro.simplecloud.user.entity.UserMaster;
import pro.simplecloud.user.service.IUserMasterService;
import pro.simplecloud.user.service.UserService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;

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
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())){
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
        userMasterService.save(userMaster);
    }
}
