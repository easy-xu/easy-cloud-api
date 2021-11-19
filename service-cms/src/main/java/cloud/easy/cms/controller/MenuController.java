package cloud.easy.cms.controller;

import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.cms.dto.MenuDto;
import cloud.easy.cms.entity.CmsMenu;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.service.ICmsMenuService;
import cloud.easy.cms.service.MenuService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cloud.easy.base.utils.BaseUtil.notNull;

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
