package cloud.easy.cms.service.impl;

import cloud.easy.cms.entity.CmsRole;
import cloud.easy.cms.entity.CmsRoleAuth;
import cloud.easy.cms.mapper.CmsRoleMapper;
import cloud.easy.cms.service.ICmsRoleAuthService;
import cloud.easy.cms.service.ICmsRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色业务实现类
 *
 * @author generator
 * @since 2021-12-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmsRoleServiceImpl extends ServiceImpl<CmsRoleMapper, CmsRole> implements ICmsRoleService {

    @Resource
    private ICmsRoleAuthService cmsRoleAuthService;

    @Override
    public boolean saveOrUpdate(CmsRole cmsRole) {
        super.saveOrUpdate(cmsRole);
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
        return true;
    }

    @Override
    public CmsRole getById(Serializable id) {
        CmsRole cmsRole = super.getById(id);
        //查询关联
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId((Long) id);
        List<CmsRoleAuth> roleAuths = cmsRoleAuthService.list(Wrappers.query(cmsRoleAuth));
        List<Long> authIds = roleAuths.stream().map(CmsRoleAuth::getAuthId).collect(Collectors.toList());
        cmsRole.setAuthIds(authIds);
        return cmsRole;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        //删除关联
        CmsRoleAuth cmsRoleAuth = new CmsRoleAuth();
        cmsRoleAuth.setRoleId((Long) id);
        cmsRoleAuthService.remove(Wrappers.query(cmsRoleAuth));
        return true;
    }
}

