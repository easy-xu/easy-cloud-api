package cloud.easy.cms.service.impl;

import cloud.easy.cms.entity.CmsRole;
import cloud.easy.cms.entity.CmsRoleAuth;
import cloud.easy.cms.service.ICmsRoleAuthService;
import cloud.easy.cms.service.ICmsRoleService;
import cloud.easy.cms.service.RoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(CmsRole cmsRole) {
        cmsRoleService.saveOrUpdate(cmsRole);
        Long roleId = cmsRole.getId();
        //删除关联历史
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId(roleId);
        cmsRoleAuthService.remove(Wrappers.query(cmsRoleAuth));
        //新增关联
        List<Long> authIds = cmsRole.getAuthIds();
        List<CmsRoleAuth> cmsRoleAuths = authIds.stream().map(authId -> {
            CmsRoleAuth roleAuth = new CmsRoleAuth();
            roleAuth.setAuthId(authId);
            roleAuth.setRoleId(roleId);
            return roleAuth;
        }).collect(Collectors.toList());
        cmsRoleAuthService.saveBatch(cmsRoleAuths);
    }

    @Override
    public CmsRole getDetail(Long id) {
        CmsRole cmsRole = cmsRoleService.getById(id);
        //查询关联
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId(id);
        List<CmsRoleAuth> roleAuths = cmsRoleAuthService.list(Wrappers.query(cmsRoleAuth));
        List<Long> authIds = roleAuths.stream().map(CmsRoleAuth::getAuthId).collect(Collectors.toList());
        cmsRole.setAuthIds(authIds);
        return cmsRole;
    }

    @Override
    public void deleteDetail(Long id) {
        //删除关联
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId(id);
        cmsRoleAuthService.remove(Wrappers.query(cmsRoleAuth));
        cmsRoleService.removeById(id);
    }
}
