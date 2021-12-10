package cloud.easy.cms.controller;

import cloud.easy.cms.service.RoleService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import cloud.easy.entity.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsRole;
import cloud.easy.cms.service.ICmsRoleService;
import cloud.easy.base.controller.BaseController;

import javax.annotation.Resource;

import static cloud.easy.base.utils.BaseUtil.requireId;
import static cloud.easy.base.utils.BaseUtil.uniqueValue;

/**
 * 角色控制器
 *
 * @author generator
 * @since 2021-12-09
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
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        CmsRole cmsRole = roleService.getDetail(requireId(primaryKey));
        return HttpResponse.ok(cmsRole);
    }

    @Override
    @OptionLog("角色保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsRole entity) {
        uniqueValue("code", entity.getCode(), service);
        roleService.save(entity);
        return HttpResponse.ok();
    }

    @Override
    @OptionLog("角色删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        roleService.deleteDetail(requireId(primaryKey));
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

}

