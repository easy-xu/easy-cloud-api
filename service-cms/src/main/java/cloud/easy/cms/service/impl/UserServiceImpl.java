package cloud.easy.cms.service.impl;

import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.entity.CmsUserGroup;
import cloud.easy.cms.entity.CmsUserRole;
import cloud.easy.cms.service.ICmsUserGroupService;
import cloud.easy.cms.service.ICmsUserRoleService;
import cloud.easy.cms.service.ICmsUserService;
import cloud.easy.cms.service.UserService;
import cloud.easy.constant.Messages;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.exception.RequestException;
import cloud.easy.exception.SystemErrorException;
import cloud.easy.idgenerator.IDGeneratorInstance;
import cloud.easy.sys.entity.SysOptionLog;
import cloud.easy.utils.BeanUtils;
import cloud.easy.utils.PasswordUtils;
import cloud.easy.utils.UserTokenUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private ICmsUserGroupService cmsUserGroupService;

    @Override
    public void signIn(UserDto userDto) {
        String username = userDto.getUsername();
        //是否重复注册
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUsername(username);
        long count = cmsUserService.count(Wrappers.query(cmsUser));
        if (count >= 1) {
            throw new RequestException(Messages.USERNAME_EXIST);
        }
        //生成用户编号
        String userNo = IDGeneratorInstance.USER_NO.generate();
        cmsUser.setUserNo(userNo);
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
        //更新记录
        ApiHeader header = ApiHeaderHelper.get();
        String deviceNo = header.getDeviceNo();
        String token = UserTokenUtils.generateToken(cmsUser.getUserNo(), deviceNo);
        cmsUser.setToken(token);
        cmsUser.setDeviceNo(deviceNo);
        //更新线程变量
        header.setUserNo(cmsUser.getUserNo());
        header.setToken(token);
        header.setDefaultGroup(cmsUser.getDefaultGroupId());
        //不校验权限
        header.setCheckAuth(false);
        cmsUserService.saveOrUpdate(cmsUser);
        BeanUtils.copy(cmsUser, userDto);
        return userDto;
    }

    @Override
    public UserDto logout() {
        ApiHeader header = ApiHeaderHelper.get();
        String userNo = header.getUserNo();
        //查询用户
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUserNo(userNo);
        List<CmsUser> users = cmsUserService.list(Wrappers.query(cmsUser));
        if (users.isEmpty()) {
            throw new RequestException(Messages.NOT_FOUND);
        } else if (users.size() > 1) {
            throw new SystemErrorException(Messages.DB_DATA_ERROR);
        }
        cmsUser = users.get(0);
        cmsUser.setToken(null);
        //不校验权限
        header.setCheckAuth(false);
        cmsUserService.saveOrUpdate(cmsUser);
        String deviceNo = header.getDeviceNo();
        UserDto userDto = new UserDto();
        userDto.setDeviceNo(deviceNo);
        String token = UserTokenUtils.generateToken(null, deviceNo);
        userDto.setToken(token);
        return userDto;
    }

    @Override
    public UserDto initDevice() {
        String deviceNo = IDGeneratorInstance.DEVICE_NO.generate();
        String token = UserTokenUtils.generateToken(null, deviceNo);
        SysOptionLog optionLog = new SysOptionLog();
        optionLog.setDeviceNo(deviceNo);
        optionLog.setOptionName("初始化");
        UserDto userDto = new UserDto();
        userDto.setDeviceNo(deviceNo);
        userDto.setToken(token);
        return userDto;
    }

    @Override
    public void save(CmsUser cmsUser) {
        String userNo = cmsUser.getUserNo();
        //新增用户
        if (userNo == null) {
            userNo = IDGeneratorInstance.USER_NO.generate();
            cmsUser.setUserNo(userNo);
        }
        String password = cmsUser.getPassword();
        if (password != null) {
            cmsUser.setPassword(PasswordUtils.encrypt(password));
        }
        cmsUserService.saveOrUpdate(cmsUser);
        Long userId = cmsUser.getId();
        //删除关联历史
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId(userId);
        cmsUserRoleService.remove(Wrappers.query(cmsUserRole));
        //新增关联
        List<Long> roleIds = cmsUser.getRoleIds();
        List<CmsUserRole> cmsUserRoles = roleIds.stream().map(roleId -> {
            CmsUserRole userRole = new CmsUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        cmsUserRoleService.saveBatch(cmsUserRoles);

        //删除关联历史
        CmsUserGroup cmsUserGroup = new CmsUserGroup();
        cmsUserGroup.setUserId(userId);
        cmsUserGroupService.remove(Wrappers.query(cmsUserGroup));
        //新增关联
        List<Long> groupIds = cmsUser.getGroupIds();
        List<CmsUserGroup> cmsUserGroups = groupIds.stream().map(groupId -> {
            CmsUserGroup userGroup = new CmsUserGroup();
            userGroup.setUserId(userId);
            userGroup.setGroupId(groupId);
            return userGroup;
        }).collect(Collectors.toList());
        cmsUserGroupService.saveBatch(cmsUserGroups);
    }

    @Override
    public CmsUser getDetail(Long id) {
        CmsUser cmsUser = cmsUserService.getById(id);
        //查询关联角色
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId(id);
        List<CmsUserRole> userRoles = cmsUserRoleService.list(Wrappers.query(cmsUserRole));
        List<Long> roleIds = userRoles.stream().map(CmsUserRole::getRoleId).collect(Collectors.toList());
        cmsUser.setRoleIds(roleIds);
        //查询关联分组
        CmsUserGroup cmsUserGroup = new CmsUserGroup();
        cmsUserGroup.setUserId(id);
        List<CmsUserGroup> userGroups = cmsUserGroupService.list(Wrappers.query(cmsUserGroup));
        List<Long> groupIds = userGroups.stream().map(CmsUserGroup::getGroupId).collect(Collectors.toList());
        cmsUser.setGroupIds(groupIds);
        return cmsUser;
    }

    @Override
    public void deleteDetail(Long id) {
        //删除关联角色
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId(id);
        cmsUserRoleService.remove(Wrappers.query(cmsUserRole));
        cmsUserService.removeById(id);
    }

    @Override
    public void resetPassword(UserDto userDto) {
        Long id = userDto.getId();
        CmsUser cmsUser = cmsUserService.getById(id);
        cmsUser.setPassword(PasswordUtils.encrypt(userDto.getPassword()));
        cmsUserService.saveOrUpdate(cmsUser);
    }
}
