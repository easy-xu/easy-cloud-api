package pro.simplecloud.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.simplecloud.cms.constant.UserType;
import pro.simplecloud.cms.dto.UserDto;
import pro.simplecloud.cms.entity.CmsUser;
import pro.simplecloud.cms.entity.CmsUserRole;
import pro.simplecloud.cms.service.ICmsUserRoleService;
import pro.simplecloud.cms.service.ICmsUserService;
import pro.simplecloud.cms.service.UserService;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.exception.SystemErrorException;
import pro.simplecloud.idgenerator.IDGeneratorInstance;
import pro.simplecloud.utils.BeanUtils;
import pro.simplecloud.utils.PasswordUtils;
import pro.simplecloud.utils.UserTokenUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: UserService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private ICmsUserService cmsUserService;

    @Resource
    private ICmsUserRoleService cmsUserRoleService;

    @Override
    public void signIn(UserDto userDto) {
        String username = userDto.getUsername();
        //是否重复注册
        QueryWrapper<CmsUser> queryWrapper = new QueryWrapper<CmsUser>().eq("userNo", username);
        int count = cmsUserService.count(queryWrapper);
        if (count >= 1) {
            throw new RequestException(Messages.USERNAME_EXIST);
        }
        //更新游客信息为注册用户
        ApiHeader apiHeader = ApiHeaderHelper.get();
        String userNo = apiHeader.getUserNo();
        String token = apiHeader.getToken();
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUserNo(userNo);
        cmsUser.setToken(token);
        List<CmsUser> users = cmsUserService.list(Wrappers.query(cmsUser));
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
        cmsUserService.saveOrUpdate(cmsUser);
    }

    @Override
    public UserDto login(UserDto userDto) {
        //查询用户
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUsername(userDto.getUsername());
        cmsUser.setPassword(PasswordUtils.encrypt(userDto.getPassword()));
        List<CmsUser> users = cmsUserService.list(Wrappers.query(cmsUser));
        if (users.isEmpty()) {
            throw new RequestException(Messages.LOGIN_ERROR);
        } else if (users.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        cmsUser = users.get(0);
        //更新token
        String token = UserTokenUtils.generateToken(cmsUser.getUserNo());
        cmsUser.setToken(token);
        cmsUserService.saveOrUpdate(cmsUser);
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
        cmsUserService.save(cmsUser);
        UserDto userDto = new UserDto();
        BeanUtils.copy(cmsUser, userDto);
        return userDto;
    }

    @Override
    public void save(UserDto userDto) {
        CmsUser cmsUser = new CmsUser();
        BeanUtils.copy(userDto, cmsUser);
        cmsUserService.saveOrUpdate(cmsUser);
        Long userId = cmsUser.getId();
        //删除关联历史
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId(userId);
        cmsUserRoleService.remove(Wrappers.query(cmsUserRole));
        //新增关联
        List<Long> roleIds = userDto.getRoleIds();
        List<CmsUserRole> cmsUserRoles = roleIds.stream().map(roleId -> {
            CmsUserRole userRole = new CmsUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        cmsUserRoleService.saveBatch(cmsUserRoles);
    }

    @Override
    public UserDto getDetail(Long id) {
        UserDto userDto = new UserDto();
        CmsUser cmsUser = cmsUserService.getById(id);
        BeanUtils.copy(cmsUser, userDto);
        //查询关联角色
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId(id);
        List<CmsUserRole> roleMenus = cmsUserRoleService.list(Wrappers.query(cmsUserRole));
        List<Long> roleIds = roleMenus.stream().map(CmsUserRole::getRoleId).collect(Collectors.toList());
        userDto.setRoleIds(roleIds);
        return userDto;
    }
}
