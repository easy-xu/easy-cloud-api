package cloud.easy.cms.controller;

import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.cms.dto.RoleDto;
import cloud.easy.cms.entity.CmsRole;
import cloud.easy.cms.service.ICmsRoleService;
import cloud.easy.cms.service.RoleService;
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
 * Title: MenuController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/role")
public class RoleController extends BaseController<CmsRole, ICmsRoleService> {

    @Resource
    private RoleService roleService;

    @Autowired
    public RoleController(ICmsRoleService service) {
        super(service);
    }

    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody RoleDto roleDto) {
        roleDto = roleService.getDetail(requireId(roleDto));
        return HttpResponse.ok(roleDto);
    }

    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody RoleDto roleDto) {
        roleService.save(notNull(roleDto));
        return HttpResponse.ok();
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsRole entity) {
        return super.deleteEntity(entity);
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
