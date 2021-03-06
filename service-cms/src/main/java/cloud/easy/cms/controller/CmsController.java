package cloud.easy.cms.controller;

import cloud.easy.annotation.Option;
import cloud.easy.cms.dto.CodeDto;
import cloud.easy.cms.dto.MenuDto;
import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.service.CmsService;
import cloud.easy.cms.service.UserService;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private CmsService cmsService;

    @Resource
    private UserService userService;

    @PostMapping("/menu/tree")
    public ApiResponse tree(@RequestBody UserDto userDto) {
        //指定UserNO查询已配置菜单，配置界面不指定UserNo, 显示全部
        String userNo = userDto.getUserNo();
        List<MenuDto> tree = cmsService.tree(userNo);
        return HttpResponse.ok(tree);
    }

    @PostMapping("/user/menu-option")
    public ApiResponse userMenuOptions(@Validated @RequestBody CodeDto codeDto) {
        String menuCode = notNull(notNull(codeDto).getCode());
        String userNo = ApiHeaderHelper.get().getUserNo();
        List<String> options = cmsService.userMenuOptions(menuCode, userNo);
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
