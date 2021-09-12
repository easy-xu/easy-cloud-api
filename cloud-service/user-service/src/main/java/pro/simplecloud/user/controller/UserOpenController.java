package pro.simplecloud.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.user.dto.UserDto;
import pro.simplecloud.user.service.UserService;

import javax.annotation.Resource;

/**
 * Title: UserOpenController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/open")
public class UserOpenController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public ApiResponse hello() {
        UserDto userDto = userService.initUser();
        return HttpResponse.ok(userDto);
    }
}
