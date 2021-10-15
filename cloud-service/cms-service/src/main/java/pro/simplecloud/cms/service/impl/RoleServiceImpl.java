package pro.simplecloud.cms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.simplecloud.cms.dto.RoleDto;
import pro.simplecloud.cms.entity.CmsRole;
import pro.simplecloud.cms.entity.CmsRoleAuth;
import pro.simplecloud.cms.entity.CmsRoleMenu;
import pro.simplecloud.cms.service.ICmsRoleAuthService;
import pro.simplecloud.cms.service.ICmsRoleMenuService;
import pro.simplecloud.cms.service.ICmsRoleService;
import pro.simplecloud.cms.service.RoleService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: RoleServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Resource
    private ICmsRoleService cmsRoleService;

    @Resource
    private ICmsRoleAuthService cmsRoleAuthService;

    @Override
    public void save(RoleDto roleDto) {
        CmsRole cmsRole = new CmsRole();
        BeanUtils.copy(roleDto, cmsRole);
        cmsRoleService.saveOrUpdate(cmsRole);
        Long roleId = cmsRole.getId();
        //删除关联历史
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId(roleId);
        cmsRoleAuthService.remove(Wrappers.query(cmsRoleAuth));
        //新增关联
        List<Long> authIds = roleDto.getAuthIds();
        List<CmsRoleAuth> cmsRoleAuths = authIds.stream().map(authId -> {
            CmsRoleAuth roleAuth = new CmsRoleAuth();
            roleAuth.setAuthId(authId);
            roleAuth.setRoleId(roleId);
            return roleAuth;
        }).collect(Collectors.toList());
        cmsRoleAuthService.saveBatch(cmsRoleAuths);
    }

    @Override
    public RoleDto getDetail(Long id) {
        RoleDto roleDto = new RoleDto();
        CmsRole cmsRole = cmsRoleService.getById(id);
        BeanUtils.copy(cmsRole, roleDto);
        //查询关联
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId(id);
        List<CmsRoleAuth> roleAuths = cmsRoleAuthService.list(Wrappers.query(cmsRoleAuth));
        List<Long> authIds = roleAuths.stream().map(CmsRoleAuth::getAuthId).collect(Collectors.toList());
        roleDto.setAuthIds(authIds);
        return roleDto;
    }
}
