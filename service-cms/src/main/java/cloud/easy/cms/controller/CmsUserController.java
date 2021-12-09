package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 用户控制器
 *
 * @author generator
 * @since 2021-12-09
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
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        CmsUser cmsUser = userService.getDetail(requireId(primaryKey));
        return HttpResponse.ok(cmsUser);
    }

    @Override
    @OptionLog("用户保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsUser entity) {
        userService.save(entity);
        return HttpResponse.ok();
    }

    @Override
    @OptionLog("用户删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        userService.deleteDetail(requireId(primaryKey));
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

}

