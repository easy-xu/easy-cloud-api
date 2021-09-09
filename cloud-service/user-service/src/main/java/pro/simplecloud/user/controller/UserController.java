package pro.simplecloud.user.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.user.dto.UserInfo;
import pro.simplecloud.user.service.UserService;
import pro.simplecloud.web.annotation.AroundLog;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;

import javax.annotation.Resource;

/**
 * Title: UserController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @AroundLog
    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserInfo userInfo) {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())) {
            return HttpResponse.reject(Messages.USERNAME_EMPTY);
        }
        userInfo = userService.login(userInfo);
        return HttpResponse.ok(userInfo);
    }

    @AroundLog
    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody UserInfo userInfo) {
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())) {
            return HttpResponse.reject(Messages.USERNAME_EMPTY);
        }
        userService.signIn(userInfo);
        return HttpResponse.ok();
    }

    @AroundLog
    @GetMapping("/info")
    public ApiResponse info() {
        return HttpResponse.ok();
    }
}

