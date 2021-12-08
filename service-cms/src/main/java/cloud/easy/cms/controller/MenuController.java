package cloud.easy.cms.controller;

import cloud.easy.cms.dto.MenuDto;
import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.service.MenuService;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cloud.easy.base.utils.BaseUtil.notNull;

/**
 * Title: MenuController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/menu")
public class MenuController  {

    @Resource
    private MenuService menuService;

    @PostMapping("/tree")
    public ApiResponse tree(@RequestBody CmsUser cmsUser) {
        List<MenuDto> tree = menuService.tree(notNull(cmsUser).getUserNo());
        return HttpResponse.ok(tree);
    }
}
