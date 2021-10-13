package pro.simplecloud.cms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.simplecloud.cms.dto.RoleDto;
import pro.simplecloud.cms.entity.CmsRole;
import pro.simplecloud.cms.entity.CmsRoleMenu;
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
    private ICmsRoleMenuService cmsRoleMenuService;

    @Override
    public void save(RoleDto roleDto) {
        CmsRole cmsRole = new CmsRole();
        BeanUtils.copy(roleDto, cmsRole);
        cmsRoleService.saveOrUpdate(cmsRole);
        Long roleId = cmsRole.getId();
        //删除关联历史
        CmsRoleMenu cmsRoleMenu = new CmsRoleMenu();
        cmsRoleMenu.setRoleId(roleId);
        cmsRoleMenuService.remove(Wrappers.query(cmsRoleMenu));
        //新增关联
        List<Long> menuIds = roleDto.getMenuIds();
        List<CmsRoleMenu> cmsRoleMenus = menuIds.stream().map(menuId -> {
            CmsRoleMenu roleMenu = new CmsRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            return roleMenu;
        }).collect(Collectors.toList());
        cmsRoleMenuService.saveBatch(cmsRoleMenus);
    }

    @Override
    public RoleDto getDetail(Long id) {
        RoleDto roleDto = new RoleDto();
        CmsRole cmsRole = cmsRoleService.getById(id);
        BeanUtils.copy(cmsRole, roleDto);
        //查询关联菜单
        CmsRoleMenu cmsRoleMenu = new CmsRoleMenu();
        cmsRoleMenu.setRoleId(id);
        List<CmsRoleMenu> roleMenus = cmsRoleMenuService.list(Wrappers.query(cmsRoleMenu));
        List<Long> menuIds = roleMenus.stream().map(CmsRoleMenu::getMenuId).collect(Collectors.toList());
        roleDto.setMenuIds(menuIds);
        return roleDto;
    }
}
