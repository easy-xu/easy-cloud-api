package cloud.easy.cms.controller;

import cloud.easy.annotation.Option;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.service.ICmsUserService;
import cloud.easy.cms.service.UserService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author generator
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/api/cms/user")
@Api(tags = "用户接口")
public class CmsUserController extends BaseController<CmsUser, ICmsUserService> {

    @Resource
    private UserService userService;

    @Autowired
    public CmsUserController(ICmsUserService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        CmsUser cmsUser = userService.getDetail(primaryKey.getId());
        return HttpResponse.ok(cmsUser);
    }

    @Override
    @Option("用户保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@Validated @RequestBody CmsUser entity) {
        userService.save(entity);
        return HttpResponse.ok();
    }

    @Override
    @Option("用户删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        userService.deleteDetail(primaryKey.getId());
        return HttpResponse.ok();
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

    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsUser entity) {
        return super.countEntity(entity);
    }

}

