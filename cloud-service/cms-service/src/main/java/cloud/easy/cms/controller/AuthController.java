package cloud.easy.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cloud.easy.cms.dto.AuthDto;
import cloud.easy.cms.entity.CmsAuth;
import cloud.easy.cms.service.ICmsAuthService;
import cloud.easy.cms.service.AuthService;
import cloud.easy.base.controller.BaseController;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;

import javax.annotation.Resource;
import java.util.List;

import static cloud.easy.base.utils.BaseUtil.notNull;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * Title: AuthController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/auth")
public class AuthController extends BaseController<CmsAuth, ICmsAuthService> {

    @Resource
    private AuthService authService;

    @Autowired
    public AuthController(ICmsAuthService service) {
        super(service);
    }

    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody AuthDto authDto) {
        authDto = authService.getDetail(requireId(authDto));
        return HttpResponse.ok(authDto);
    }

    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody AuthDto authDto) {
        authService.save(notNull(authDto));
        return HttpResponse.ok();
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsAuth entity) {
        return super.deleteEntity(entity);
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
