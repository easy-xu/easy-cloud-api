package cloud.easy.cms.service.impl;

import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.entity.CmsUserGroup;
import cloud.easy.cms.entity.CmsUserRole;
import cloud.easy.cms.mapper.CmsUserMapper;
import cloud.easy.cms.service.ICmsUserGroupService;
import cloud.easy.cms.service.ICmsUserRoleService;
import cloud.easy.cms.service.ICmsUserService;
import cloud.easy.idgenerator.IDGeneratorInstance;
import cloud.easy.utils.PasswordUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户业务实现类
 *
 * @author generator
 * @since 2021-12-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements ICmsUserService {

    @Resource
    private ICmsUserRoleService cmsUserRoleService;

    @Resource
    private ICmsUserGroupService cmsUserGroupService;

    @Override
    public boolean saveOrUpdate(CmsUser cmsUser) {
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
        super.saveOrUpdate(cmsUser);
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
        return true;
    }

    @Override
    public CmsUser getById(Serializable id) {
        CmsUser cmsUser = super.getById(id);
        //查询关联角色
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId((Long) id);
        List<CmsUserRole> userRoles = cmsUserRoleService.list(Wrappers.query(cmsUserRole));
        List<Long> roleIds = userRoles.stream().map(CmsUserRole::getRoleId).collect(Collectors.toList());
        cmsUser.setRoleIds(roleIds);
        //查询关联分组
        CmsUserGroup cmsUserGroup = new CmsUserGroup();
        cmsUserGroup.setUserId((Long) id);
        List<CmsUserGroup> userGroups = cmsUserGroupService.list(Wrappers.query(cmsUserGroup));
        List<Long> groupIds = userGroups.stream().map(CmsUserGroup::getGroupId).collect(Collectors.toList());
        cmsUser.setGroupIds(groupIds);
        return cmsUser;
    }

    @Override
    public boolean removeById(Serializable id) {
        //删除关联角色
        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setUserId((Long) id);
        cmsUserRoleService.remove(Wrappers.query(cmsUserRole));
        super.removeById(id);
        return true;
    }
}

