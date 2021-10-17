package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.dto.UserDto;
import pro.simplecloud.cms.entity.CmsUser;
import pro.simplecloud.cms.service.ICmsUserService;
import pro.simplecloud.cms.service.UserService;
import pro.simplecloud.base.controller.BaseController;
import pro.simplecloud.base.dto.PageQueryDto;
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
@RequestMapping("/api/cms/user")
public class UserController extends BaseController<CmsUser, ICmsUserService> {

    @Resource
    private UserService userService;

    @Autowired
    public UserController(ICmsUserService service) {
        super(service);
    }

    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody UserDto userDto) {
        userDto = userService.getDetail(requireId(userDto));
        return HttpResponse.ok(userDto);
    }

    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody UserDto userDto) {
        userService.save(notNull(userDto));
        return HttpResponse.ok();
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsUser entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsUser> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsUser entity) {
        return super.listEntity(entity);
    }

    @PostMapping("/reset-password")
    public ApiResponse resetPassword(@RequestBody UserDto userDto) {
        requireId(userDto);
        userService.resetPassword(userDto);
        return HttpResponse.ok();
    }

}

