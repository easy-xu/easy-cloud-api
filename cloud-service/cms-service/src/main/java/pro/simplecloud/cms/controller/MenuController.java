package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.simplecloud.cms.dto.MenuDto;
import pro.simplecloud.cms.entity.CmsMenu;
import pro.simplecloud.cms.entity.CmsUser;
import pro.simplecloud.cms.service.ICmsMenuService;
import pro.simplecloud.cms.service.MenuService;
import pro.simplecloud.base.controller.BaseController;
import pro.simplecloud.base.dto.PageQueryDto;
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

    @Resource
    private MenuService menuService;

    @Autowired
    public MenuController(ICmsMenuService service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody CmsMenu entity) {
        return super.queryEntity(entity);
    }

    @Override
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsMenu entity) {
        return super.saveEntity(entity);
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsMenu entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsMenu> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsMenu entity) {
        return super.listEntity(entity);
    }

    @PostMapping("/tree")
    public ApiResponse tree(@RequestBody CmsUser cmsUser) {
        List<MenuDto> tree = menuService.tree(notNull(cmsUser).getUserNo());
        return HttpResponse.ok(tree);
    }
}
