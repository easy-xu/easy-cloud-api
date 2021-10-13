package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.dto.UserDto;
import pro.simplecloud.cms.entity.CmsUser;
import pro.simplecloud.cms.service.ICmsUserService;
import pro.simplecloud.cms.service.UserService;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.controller.BaseController;
import pro.simplecloud.dto.PageQueryDto;
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

