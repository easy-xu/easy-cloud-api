package pro.simplecloud.cms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import pro.simplecloud.cms.dto.MenuDto;
import pro.simplecloud.cms.entity.CmsMenu;
import pro.simplecloud.cms.service.ICmsMenuService;
import pro.simplecloud.cms.service.MenuService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
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

    @Override
    public List<MenuDto> tree() {
        return getChildren(0L);
    }

    private List<MenuDto> getChildren(Long id) {
        CmsMenu cmsMenu = new CmsMenu();
        cmsMenu.setParentId(id);
        List<CmsMenu> children = cmsMenuService.list(Wrappers.query(cmsMenu));
        return children.stream().map(item -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copy(item, menuDto);
            if (FOLDER.code.equals(item.getType())) {
                menuDto.setChildren(getChildren(item.getId()));
            }
            return menuDto;
        }).collect(Collectors.toList());
    }

}
