package cloud.easy.cms.controller;

import cloud.easy.annotation.NonStandardRequest;
import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.service.UserService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Title: UserOpenController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@NonStandardRequest
@RestController
@RequestMapping("/api/cms/open")
public class UserOpenController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public ApiResponse hello() {
        UserDto userDto = userService.initUser();
        return HttpResponse.ok(userDto);
    }
}
