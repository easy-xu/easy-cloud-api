package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.dto.MenuDto;
import pro.simplecloud.cms.entity.CmsMenu;
import pro.simplecloud.cms.service.ICmsMenuService;
import pro.simplecloud.cms.service.MenuService;
import pro.simplecloud.controller.BaseController;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * Title: MenuController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/menu")
public class MenuController extends BaseController<CmsMenu, ICmsMenuService> {

    @Autowired
    public MenuController(ICmsMenuService service) {
        super(service);
    }

    @Resource
    private MenuService menuService;

    @GetMapping("/tree")
    public ApiResponse tree(){
        List<MenuDto> tree = menuService.tree();
        return HttpResponse.ok(tree);
    }
}
