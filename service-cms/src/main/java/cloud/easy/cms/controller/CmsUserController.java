package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.service.ICmsUserService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 用户控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/cms/user")
@Api(tags = "用户接口")
public class CmsUserController extends BaseController<CmsUser, ICmsUserService> {

    @Autowired
    public CmsUserController(ICmsUserService service) {
        super(service);
    }

    @Override
    @Option(value = "用户详情", menuCode = "user", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "用户新增", menuCode = "user", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody CmsUser entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "用户更新", menuCode = "user", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody CmsUser entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "用户删除", menuCode = "user", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "用户分页查询", menuCode = "user", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsUser> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "用户列表查询", menuCode = "user", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsUser entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "用户计数", menuCode = "user", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsUser entity) {
        return super.countEntity(entity);
    }

}

