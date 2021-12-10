package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.cms.entity.CmsMenu;
import cloud.easy.cms.service.ICmsMenuService;
import cloud.easy.entity.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cloud.easy.base.utils.BaseUtil.uniqueValue;

/**
 * 菜单控制器
 *
 * @author generator
 * @since 2021-12-10
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
    public ApiResponse saveEntity(@Validated @RequestBody CmsMenu entity) {
        uniqueValue("code", entity.getCode(), service);
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

    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsMenu entity) {
        return super.countEntity(entity);
    }

}

