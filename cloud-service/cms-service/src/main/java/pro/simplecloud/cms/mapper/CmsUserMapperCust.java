package pro.simplecloud.cms.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户自定义 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
public interface CmsUserMapperCust {

    /**
     * 查询用户所有菜单id
     *
     * @param userNo  用户编号
     * @return Long
     */
    @Select("SELECT DISTINCT rm.menu_id FROM cms_role_menu rm WHERE rm.role_id IN ( SELECT ur.role_id FROM cms_user_role ur WHERE ur.user_id IN ( SELECT u.id FROM cms_user u WHERE u.user_no = #{userNo} ))")
    List<Long> userMenuIds(@Param("userNo") String userNo);

}
