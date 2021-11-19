package cloud.easy.cms.controller;

import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.service.UserService;
import cloud.easy.constant.Messages;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserLoginController {

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

