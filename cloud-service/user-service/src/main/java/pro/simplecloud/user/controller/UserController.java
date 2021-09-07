package pro.simplecloud.user.controller;

import org.springframework.web.bind.annotation.*;
import pro.simplecloud.user.dto.UserInfo;
import pro.simplecloud.user.service.UserService;
import pro.simplecloud.user.service.impl.UserServiceImpl;
import pro.simplecloud.utils.UserTokenUtils;
import pro.simplecloud.web.api.HttpResponse;
import pro.simplecloud.web.api.ApiResponse;

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

    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserInfo userInfo){
        String token = UserTokenUtils.generateToken(userInfo.getUsername());
        userInfo.setToken(token);
        return HttpResponse.ok(userInfo);
    }

    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody UserInfo userInfo){
        userService.signIn(userInfo);
        return HttpResponse.ok();
    }

    @GetMapping("/info")
    public ApiResponse info(){
        return HttpResponse.ok();
    }
}

