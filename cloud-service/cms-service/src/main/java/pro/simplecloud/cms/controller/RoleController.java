package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.entity.CmsRole;
import pro.simplecloud.cms.service.ICmsRoleService;
import pro.simplecloud.controller.BaseController;

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

    @Autowired
    public RoleController(ICmsRoleService service) {
        super(service);
    }

}
