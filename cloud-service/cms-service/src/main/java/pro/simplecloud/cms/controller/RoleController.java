package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.dto.RoleDto;
import pro.simplecloud.cms.entity.CmsRole;
import pro.simplecloud.cms.service.ICmsRoleService;
import pro.simplecloud.cms.service.RoleService;
import pro.simplecloud.base.controller.BaseController;
import pro.simplecloud.base.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;

import javax.annotation.Resource;

import static pro.simplecloud.base.utils.BaseUtil.notNull;
import static pro.simplecloud.base.utils.BaseUtil.requireId;

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
