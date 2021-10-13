package pro.simplecloud.cms.service;

import pro.simplecloud.cms.dto.MenuDto;

import java.util.List;

/**
 * Title: MenuService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface MenuService {
    /**
     * 用户菜单树
     *
     * @return List<MenuDto>
     * @param userNo 用户编号
     */
    List<MenuDto> tree(String userNo);
}
