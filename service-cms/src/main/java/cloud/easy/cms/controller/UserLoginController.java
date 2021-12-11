package cloud.easy.cms.controller;

import cloud.easy.annotation.NonStandardRequest;
import cloud.easy.annotation.Option;
import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.service.UserService;
import cloud.easy.constant.Messages;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Title: UserController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/user")
@Api(tags = "用户登录接口")
public class UserLoginController {

    @Resource
    private UserService userService;

    @Option("用户登录")
    @PostMapping("/login")
    public ApiResponse login(@Validated @RequestBody UserDto userDto) {
        userDto = userService.login(userDto);
        return HttpResponse.ok(userDto);
    }

    @Option("用户退出")
    @PostMapping("/logout")
    public ApiResponse logout() {
        UserDto userDto = userService.logout();
        return HttpResponse.ok(userDto);
    }

    @Option("用户注册")
    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody UserDto userDto) {
        if (userDto == null || !StringUtils.hasLength(userDto.getUsername())) {
            return HttpResponse.reject(Messages.USERNAME_EMPTY);
        }
        userService.signIn(userDto);
        return HttpResponse.ok();
    }


    @Option("初始化")
    @GetMapping("/device")
    @NonStandardRequest
    public ApiResponse getDevice() {
        UserDto userDto = userService.initDevice();
        return HttpResponse.ok(userDto);
    }
}

