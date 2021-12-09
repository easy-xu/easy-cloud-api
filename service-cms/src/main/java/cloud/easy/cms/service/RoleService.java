package cloud.easy.cms.service;

import cloud.easy.cms.entity.CmsRole;

/**
 * Title: RoleService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface RoleService {
    /**
     * 保存角色及权限
     *
     * @param cmsRole 角色及权限
     */
    void save(CmsRole cmsRole);

    /**
     * 查询角色详情
     *
     * @param id 角色Id
     * @return RoleDto
     */
    CmsRole getDetail(Long id);

    /**
     * 删除角色详情
     *
     * @param id 角色Id
     */
    void deleteDetail(Long id);
}
