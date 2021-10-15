package pro.simplecloud.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.cms.dto.MenuDto;
import pro.simplecloud.cms.entity.CmsMenu;
import pro.simplecloud.cms.mapper.CmsMapperCust;
import pro.simplecloud.cms.service.ICmsMenuService;
import pro.simplecloud.cms.service.MenuService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pro.simplecloud.cms.constant.MenuType.FOLDER;

/**
 * Title: MenuServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private ICmsMenuService cmsMenuService;

    @Resource
    private CmsMapperCust cmsMapperCust;

    @Override
    public List<MenuDto> tree(String userNo) {
        if (userNo == null) {
            return getChildren(0L, null);
        }
        //查询当前用户所有菜单id
        List<Long> menuIds = cmsMapperCust.userMenuIds(userNo);
        if (menuIds.isEmpty()){
            return new ArrayList<>();
        }
        return getChildren(0L, menuIds);
    }

    private List<MenuDto> getChildren(Long id, List<Long> ids) {
        CmsMenu cmsMenu = new CmsMenu();
        cmsMenu.setParentId(id);
        QueryWrapper<CmsMenu> query = Wrappers.query(cmsMenu);
        if (ids!= null && !ids.isEmpty()) {
            query.in("id", ids);
        }
        query.orderByAsc("order_num");
        List<CmsMenu> children = cmsMenuService.list(query);
        return children.stream().map(item -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copy(item, menuDto);
            if (FOLDER.code.equals(item.getType())) {
                menuDto.setChildren(getChildren(item.getId(), ids));
            }
            return menuDto;
        }).collect(Collectors.toList());
    }

}
