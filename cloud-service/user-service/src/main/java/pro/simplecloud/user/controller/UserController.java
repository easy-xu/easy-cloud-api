package pro.simplecloud.user.controller;

import org.springframework.web.bind.annotation.*;
import pro.simplecloud.user.dto.UserInfo;
import pro.simplecloud.utils.UserTokenUtils;
import pro.simplecloud.web.api.HttpResponse;
import pro.simplecloud.web.api.ApiResponse;

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

    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserInfo userInfo){
        String token = UserTokenUtils.generateToken(userInfo.getUsername());
        userInfo.setToken(token);
        return HttpResponse.ok(userInfo);
    }

    @GetMapping("/info")
    public ApiResponse info(){
        return HttpResponse.ok();
    }
}

