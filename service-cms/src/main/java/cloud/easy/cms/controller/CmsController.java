package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
import cloud.easy.cms.dto.AuthDto;
import cloud.easy.cms.dto.MenuDto;
import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.service.AuthService;
import cloud.easy.cms.service.MenuService;
import cloud.easy.cms.service.UserService;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cloud.easy.base.utils.BaseUtil.notNull;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * Title: MenuController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms")
public class CmsController {
    @Resource
    private MenuService menuService;
    @Resource
    private AuthService authService;
    @Resource
    private UserService userService;

    @PostMapping("/menu/tree")
    public ApiResponse tree(@RequestBody CmsUser cmsUser) {
        List<MenuDto> tree = menuService.tree(notNull(cmsUser).getUserNo());
        return HttpResponse.ok(tree);
    }

    @PostMapping("/auth/user-menu-option")
    public ApiResponse userMenuOptions(@RequestBody AuthDto authDto) {
        String menuCode = notNull(notNull(authDto).getMenuCode());
        String userNo = ApiHeaderHelper.get().getUserNo();
        List<String> options = authService.userMenuOptions(menuCode, userNo);
        return HttpResponse.ok(options);
    }

    @OptionLog("重置密码")
    @PostMapping("/user/reset-password")
    public ApiResponse resetPassword(@RequestBody UserDto userDto) {
        requireId(userDto);
        userService.resetPassword(userDto);
        return HttpResponse.ok();
    }
}
