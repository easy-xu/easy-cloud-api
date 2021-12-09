package cloud.easy.cms.service;

import cloud.easy.cms.entity.CmsAuth;

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
     */
    void save(CmsAuth cmsAuth);

    /**
     * 查询权限详情
     *
     * @param id 权限Id
     * @return AuthDto
     */
    CmsAuth getDetail(Long id);

    /**
     * 删除权限及关联
     * @param id 权限Id
     */
    void deleteDetail(Long id);

    /**
     * 查询用户当前菜单的操作
     *
     * @param menuCode 菜单
     * @param userNo   用户编号
     * @return List<String>
     */
    List<String> userMenuOptions(String menuCode, String userNo);


}
