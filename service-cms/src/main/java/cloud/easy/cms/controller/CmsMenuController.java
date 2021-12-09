package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsMenu;
import cloud.easy.cms.service.ICmsMenuService;
import cloud.easy.base.controller.BaseController;

/**
 * 菜单控制器
 *
 * @author generator
 * @since 2021-12-09
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
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @OptionLog("菜单保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsMenu entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("菜单删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
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

}

