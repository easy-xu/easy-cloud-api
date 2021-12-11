package cloud.easy.cms.service;

import cloud.easy.cms.dto.MenuDto;

import java.util.List;

/**
 * Title: MenuService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface CmsService {
    /**
     * 用户菜单树
     *
     * @param userNo 用户编号
     * @return List<MenuDto>
     */
    List<MenuDto> tree(String userNo);

    /**
     * 查询用户当前菜单的操作
     *
     * @param menuCode 菜单
     * @param userNo   用户编号
     * @return List<String>
     */
    List<String> userMenuOptions(String menuCode, String userNo);
}
