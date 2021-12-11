package cloud.easy.cms.controller;

import cloud.easy.annotation.Option;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.cms.entity.CmsRole;
import cloud.easy.cms.service.ICmsRoleService;
import cloud.easy.cms.service.RoleService;
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
 * 角色控制器
 *
 * @author generator
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/api/cms/role")
@Api(tags = "角色接口")
public class CmsRoleController extends BaseController<CmsRole, ICmsRoleService> {

    @Resource
    private RoleService roleService;

    @Autowired
    public CmsRoleController(ICmsRoleService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        CmsRole cmsRole = roleService.getDetail(primaryKey.getId());
        return HttpResponse.ok(cmsRole);
    }

    @Override
    @Option("角色保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@Validated @RequestBody CmsRole entity) {
        roleService.save(entity);
        return HttpResponse.ok();
    }

    @Override
    @Option("角色删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        roleService.deleteDetail(primaryKey.getId());
        return HttpResponse.ok();
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsRole> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsRole entity) {
        return super.listEntity(entity);
    }

    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsRole entity) {
        return super.countEntity(entity);
    }

}

