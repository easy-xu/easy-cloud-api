package cloud.easy.base.mapper;

import cloud.easy.base.entity.AuthEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Title: BaseMapperCust
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface BaseMapperCust {

    /**
     * 查询用户昵称
     *
     * @param userNo 用户编号
     * @return 昵称
     */
    @Select("SELECT u.nickname FROM cms_user u WHERE u.user_no = #{userNo}")
    String getNickname(@Param("userNo") String userNo);

    /**
     * 获取用户当前分组
     *
     * @param userNo 用户编号
     * @return 分组id
     */
    @Select("SELECT u.default_group_id FROM cms_user u WHERE u.user_no = #{userNo}")
    Long getDefaultGroup(@Param("userNo") String userNo);

    /**
     * 获取数据库记录的基础属性
     *
     * @param id    主键
     * @param table 表名
     * @return 基础属性
     */
    @Select("SELECT t.id, t.deleted, t.group_id, t.own_mode, t.group_mode, t.other_mode, t.create_by, t.create_time, t.update_by, t.update_time FROM ${table} t WHERE t.id = #{id}")
    AuthEntity getProperty(@Param("table") String table, @Param("id") Long id);

    /**
     * 查询是否存在
     *
     * @param table 表名
     * @param column 字段名
     * @param value 字段值
     * @return 个数
     */
    @Select("SELECT count(*) FROM ${table} t WHERE t.${column} = #{value}")
    Long countValue(@Param("table") String table, @Param("column") String column, @Param("value") String value);
}
