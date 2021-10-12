package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.entity.CmsMenu;
import pro.simplecloud.cms.service.ICmsMenuService;
import pro.simplecloud.controller.BaseController;

/**
 * Title: MenuController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/menu")
public class MenuController extends BaseController<CmsMenu, ICmsMenuService> {

    @Autowired
    public MenuController(ICmsMenuService service) {
        super(service);
    }

}
