package cloud.easy.cms.controller;

import cloud.easy.annotation.Option;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.cms.entity.CmsAuth;
import cloud.easy.cms.service.AuthService;
import cloud.easy.cms.service.ICmsAuthService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private AuthService authService;

    @Autowired
    public CmsAuthController(ICmsAuthService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        CmsAuth cmsAuth = authService.getDetail(primaryKey.getId());
        return HttpResponse.ok(cmsAuth);
    }

    @Override
    @Option("权限保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@Validated @RequestBody CmsAuth entity) {
        authService.save(entity);
        return HttpResponse.ok();
    }

    @Override
    @Option("权限删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        authService.deleteDetail(primaryKey.getId());
        return HttpResponse.ok();
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsAuth> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsAuth entity) {
        return super.listEntity(entity);
    }

    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsAuth entity) {
        return super.countEntity(entity);
    }

}

