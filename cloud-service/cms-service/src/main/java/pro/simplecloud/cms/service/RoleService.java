package pro.simplecloud.cms.service;

import pro.simplecloud.cms.dto.RoleDto;

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
     * @param roleDto 角色及权限
     */
    void save(RoleDto roleDto);

    /**
     * 查询角色详情
     *
     * @param id 角色Id
     * @return RoleDto
     */
    RoleDto getDetail(Long id);
}
