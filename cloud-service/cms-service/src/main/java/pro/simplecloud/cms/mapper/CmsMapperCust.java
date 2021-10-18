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
    @Select("SELECT DISTINCT" +
            " m.id " +
            "FROM" +
            " cms_menu m" +
            " LEFT JOIN cms_auth_menu am ON m.id = am.menu_id" +
            " LEFT JOIN cms_auth a ON a.id = am.auth_id" +
            " LEFT JOIN cms_role_auth ra ON ra.auth_id = a.id" +
            " LEFT JOIN cms_role r ON r.id = ra.role_id" +
            " LEFT JOIN cms_user_role ur ON ur.role_id = r.id" +
            " LEFT JOIN cms_user u ON u.id = ur.user_id " +
            "WHERE" +
            " m.deleted = '0' " +
            " AND a.deleted = '0' " +
            " AND r.deleted = '0' " +
            " AND u.deleted = '0'" +
            " and u.user_no = #{userNo}")
    List<Long> userMenuIds(@Param("userNo") String userNo);


    /**
     * 查询用户当前菜单的操作
     * @param menuCode 菜单
     * @param userNo 用户编号
     * @return List<String>
     */
    @Select("SELECT DISTINCT" +
            " o.CODE " +
            "FROM" +
            " cms_option o" +
            " LEFT JOIN cms_auth_option ao ON ao.option_id = o.id" +
            " LEFT JOIN cms_auth a ON a.id = ao.auth_id" +
            " LEFT JOIN cms_auth_menu am ON am.auth_id = a.id" +
            " LEFT JOIN cms_menu m ON m.id = am.menu_id" +
            " LEFT JOIN cms_role_auth ra ON ra.auth_id = a.id" +
            " LEFT JOIN cms_role r ON r.id = ra.role_id" +
            " LEFT JOIN cms_user_role ur ON ur.role_id = r.id" +
            " LEFT JOIN cms_user u ON u.id = ur.user_id " +
            "WHERE" +
            " o.deleted = '0' " +
            " AND m.deleted = '0' " +
            " AND a.deleted = '0' " +
            " AND r.deleted = '0' " +
            " AND u.deleted = '0' " +
            " AND m.CODE = #{menuCode}" +
            " AND u.user_no = #{userNo}")
    List<String> userMenuOptions(@Param("menuCode") String menuCode, @Param("userNo")String userNo);
}
