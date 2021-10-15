package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.dto.AuthDto;
import pro.simplecloud.cms.entity.CmsAuth;
import pro.simplecloud.cms.entity.CmsOption;
import pro.simplecloud.cms.service.ICmsAuthService;
import pro.simplecloud.cms.service.AuthService;
import pro.simplecloud.controller.BaseController;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;

import javax.annotation.Resource;
import java.util.List;

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
