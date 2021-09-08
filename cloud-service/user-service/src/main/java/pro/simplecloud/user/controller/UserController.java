package pro.simplecloud.user.controller;

import org.springframework.web.bind.annotation.*;
import pro.simplecloud.user.dto.UserInfo;
import pro.simplecloud.user.service.UserService;
import pro.simplecloud.utils.UserTokenUtils;
import pro.simplecloud.web.annotation.AroundLog;
import pro.simplecloud.web.entity.HttpResponse;
import pro.simplecloud.web.entity.ApiResponse;

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
    public ApiResponse login(@RequestBody UserInfo userInfo){
        String token = UserTokenUtils.generateToken(userInfo.getUsername());
        userInfo.setToken(token);
        return HttpResponse.ok(userInfo);
    }

    @AroundLog
    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody UserInfo userInfo){
        userService.signIn(userInfo);
        return HttpResponse.ok();
    }

    @AroundLog
    @GetMapping("/info")
    public ApiResponse info(){
        return HttpResponse.ok();
    }
}

