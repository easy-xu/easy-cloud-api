package cloud.easy.cms.controller;

import cloud.easy.cms.dto.AuthDto;
import cloud.easy.cms.service.AuthService;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import cloud.easy.entity.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsAuth;
import cloud.easy.cms.service.ICmsAuthService;
import cloud.easy.base.controller.BaseController;

import javax.annotation.Resource;
import java.util.List;

import static cloud.easy.base.utils.BaseUtil.notNull;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 权限控制器
 *
 * @author generator
 * @since 2021-12-09
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
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        CmsAuth cmsAuth = authService.getDetail(requireId(primaryKey));
        return HttpResponse.ok(cmsAuth);
    }

    @Override
    @OptionLog("权限保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsAuth cmsAuth) {
        authService.save(notNull(cmsAuth));
        return HttpResponse.ok();
    }

    @Override
    @OptionLog("权限删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        authService.deleteDetail(requireId(primaryKey));
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


    @PostMapping("/user-menu-option")
    public ApiResponse userMenuOptions(@RequestBody AuthDto authDto) {
        String menuCode = notNull(notNull(authDto).getMenuCode());
        String userNo = ApiHeaderHelper.get().getUserNo();
        List<String> options = authService.userMenuOptions(menuCode, userNo);
        return HttpResponse.ok(options);
    }
}

