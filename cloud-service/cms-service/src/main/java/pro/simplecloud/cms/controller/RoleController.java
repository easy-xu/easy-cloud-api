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
import pro.simplecloud.constant.Messages;
import pro.simplecloud.controller.BaseController;
import pro.simplecloud.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;

import javax.annotation.Resource;

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
        if (roleDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long id = roleDto.getId();
        if (id == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        roleDto = roleService.getDetail(id);
        return HttpResponse.ok(roleDto);
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

    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody RoleDto roleDto) {
        if (roleDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        roleService.save(roleDto);
        return HttpResponse.ok();
    }

}
