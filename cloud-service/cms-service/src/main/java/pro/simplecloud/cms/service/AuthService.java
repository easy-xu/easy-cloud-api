package pro.simplecloud.cms.service;

import pro.simplecloud.cms.dto.AuthDto;
import pro.simplecloud.cms.entity.CmsOption;

import java.util.List;

/**
 * Title: AuthService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface AuthService {
    /**
     * 保存权限及关联
     *
     * @param authDto 权限及关联
     */
    void save(AuthDto authDto);

    /**
     * 查询权限详情
     *
     * @param id 权限Id
     * @return AuthDto
     */
    AuthDto getDetail(Long id);

    /**
     * 查询用户当前菜单的操作
     * @param menuCode 菜单
     * @param userNo 用户编号
     * @return List<String>
     */
    List<String> userMenuOptions(String menuCode, String userNo);
}
