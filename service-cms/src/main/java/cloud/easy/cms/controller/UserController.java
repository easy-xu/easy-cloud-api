package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.cms.dto.UserDto;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.service.ICmsUserService;
import cloud.easy.cms.service.UserService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cloud.easy.base.utils.BaseUtil.notNull;
import static cloud.easy.base.utils.BaseUtil.requireId;

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

    @OptionLog("用户详情")
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody UserDto userDto) {
        userDto = userService.getDetail(requireId(userDto));
        return HttpResponse.ok(userDto);
    }

    @OptionLog("用户保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody UserDto userDto) {
        userService.save(notNull(userDto));
        return HttpResponse.ok();
    }

    @Override
    @OptionLog("用户删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsUser entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @OptionLog("用户查询")
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsUser> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsUser entity) {
        return super.listEntity(entity);
    }

    @OptionLog("重置密码")
    @PostMapping("/reset-password")
    public ApiResponse resetPassword(@RequestBody UserDto userDto) {
        requireId(userDto);
        userService.resetPassword(userDto);
        return HttpResponse.ok();
    }

}

