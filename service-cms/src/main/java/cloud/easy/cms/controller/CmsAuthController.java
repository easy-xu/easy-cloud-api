package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsAuth;
import cloud.easy.cms.service.ICmsAuthService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 权限控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/cms/auth")
@Api(tags = "权限接口")
public class CmsAuthController extends BaseController<CmsAuth, ICmsAuthService> {

    @Autowired
    public CmsAuthController(ICmsAuthService service) {
        super(service);
    }

    @Override
    @Option(value = "权限详情", menuCode = "auth", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "权限新增", menuCode = "auth", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody CmsAuth entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "权限更新", menuCode = "auth", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody CmsAuth entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "权限删除", menuCode = "auth", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "权限分页查询", menuCode = "auth", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsAuth> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "权限列表查询", menuCode = "auth", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsAuth entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "权限计数", menuCode = "auth", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsAuth entity) {
        return super.countEntity(entity);
    }

}

