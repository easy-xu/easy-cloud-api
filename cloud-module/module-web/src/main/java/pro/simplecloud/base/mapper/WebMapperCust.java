package pro.simplecloud.base.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Title: WebMapperCust
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface WebMapperCust {

    /** 查询用户昵称
     * @param userNo 用户编号
     * @return 昵称
     */
    @Select("SELECT u.nickname FROM cms_user u WHERE u.user_no = #{userNo}")
    String getNickname(@Param("userNo") String userNo);
}
