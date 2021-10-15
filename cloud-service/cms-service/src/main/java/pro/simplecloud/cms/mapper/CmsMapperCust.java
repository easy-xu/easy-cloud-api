package pro.simplecloud.cms.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pro.simplecloud.cms.entity.CmsMenu;
import pro.simplecloud.cms.entity.CmsOption;

import java.util.List;

/**
 * <p>
 * 用户自定义 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
public interface CmsMapperCust {

    /**
     * 查询用户所有菜单id
     *
     * @param userNo  用户编号
     * @return Long
     */
    @Select("SELECT DISTINCT am.menu_id FROM cms_auth_menu am WHERE am.auth_id IN ( SELECT DISTINCT ra.auth_id FROM cms_role_auth ra WHERE ra.role_id IN ( SELECT ur.role_id FROM cms_user_role ur WHERE ur.user_id IN ( SELECT u.id FROM cms_user u WHERE u.user_no = #{userNo} )))")
    List<Long> userMenuIds(@Param("userNo") String userNo);


    /**
     * 查询用户当前菜单的操作
     * @param menuCode 菜单
     * @param userNo 用户编号
     * @return List<String>
     */
    @Select("SELECT o.CODE FROM cms_option o LEFT JOIN cms_auth_option ao ON ao.option_id = o.id LEFT JOIN cms_auth_menu am ON am.auth_id = ao.auth_id LEFT JOIN cms_menu m ON am.menu_id = m.id WHERE m.CODE = #{menuCode} AND am.auth_id IN ( SELECT DISTINCT ra.auth_id FROM cms_role_auth ra WHERE ra.role_id IN ( SELECT ur.role_id FROM cms_user_role ur WHERE ur.user_id IN ( SELECT u.id FROM cms_user u WHERE u.user_no = #{userNo} )))")
    List<String> userMenuOptions(@Param("menuCode") String menuCode, @Param("userNo")String userNo);
}
