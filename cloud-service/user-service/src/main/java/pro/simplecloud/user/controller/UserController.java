package pro.simplecloud.user.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.user.dto.UserDto;
import pro.simplecloud.user.service.UserService;
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

    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserDto userDto) {
        if (userDto == null || !StringUtils.hasLength(userDto.getUsername())) {
            return HttpResponse.reject(Messages.USERNAME_EMPTY);
        }
        userDto = userService.login(userDto);
        return HttpResponse.ok(userDto);
    }

    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody UserDto userDto) {
        if (userDto == null || !StringUtils.hasLength(userDto.getUsername())) {
            return HttpResponse.reject(Messages.USERNAME_EMPTY);
        }
        userService.signIn(userDto);
        return HttpResponse.ok();
    }
}

