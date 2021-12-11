package cloud.easy.cms.controller;

import cloud.easy.annotation.Option;
import cloud.easy.cms.dto.CodeDto;
import cloud.easy.cms.dto.MenuDto;
import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.service.AuthService;
import cloud.easy.cms.service.MenuService;
import cloud.easy.cms.service.UserService;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/cms")
@Api(tags = "后台管理其他接口")
public class CmsController {
    @Resource
    private MenuService menuService;
    @Resource
    private AuthService authService;
    @Resource
    private UserService userService;

    @GetMapping("/menu/tree")
    public ApiResponse tree() {
        ApiHeader header = ApiHeaderHelper.get();
        String userNo = header.getUserNo();
        List<MenuDto> tree = menuService.tree(userNo);
        return HttpResponse.ok(tree);
    }

    @PostMapping("/user/menu-option")
    public ApiResponse userMenuOptions(@Validated @RequestBody CodeDto codeDto) {
        String menuCode = notNull(notNull(codeDto).getCode());
        String userNo = ApiHeaderHelper.get().getUserNo();
        List<String> options = authService.userMenuOptions(menuCode, userNo);
        return HttpResponse.ok(options);
    }

    @Option("重置密码")
    @PostMapping("/user/reset-password")
    public ApiResponse resetPassword(@Validated @RequestBody UserDto userDto) {
        notNull(userDto.getId());
        userService.resetPassword(userDto);
        return HttpResponse.ok();
    }
}
