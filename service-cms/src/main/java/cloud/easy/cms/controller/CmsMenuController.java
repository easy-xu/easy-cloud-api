package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsMenu;
import cloud.easy.cms.service.ICmsMenuService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 菜单控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/cms/menu")
@Api(tags = "菜单接口")
public class CmsMenuController extends BaseController<CmsMenu, ICmsMenuService> {

    @Autowired
    public CmsMenuController(ICmsMenuService service) {
        super(service);
    }

    @Override
    @Option(value = "菜单详情", menuCode = "menu", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "菜单新增", menuCode = "menu", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody CmsMenu entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "菜单更新", menuCode = "menu", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody CmsMenu entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "菜单删除", menuCode = "menu", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "菜单分页查询", menuCode = "menu", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsMenu> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "菜单列表查询", menuCode = "menu", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsMenu entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "菜单计数", menuCode = "menu", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsMenu entity) {
        return super.countEntity(entity);
    }

}

