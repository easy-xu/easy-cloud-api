package cloud.easy.generator.mapper;

import cloud.easy.generator.config.db.ColumnInfo;
import cloud.easy.generator.config.db.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * TableInfoMapper
 *
 * @author xu honglin
 * @date 2021/12/4 14:29
 */
public interface TableInfoMapper {

    /**
     * 查询表信息
     *
     * @param tableName 表名
     * @return TableInfo
     */
    @Select("SELECT t.table_name name, t.table_comment comment FROM information_schema.tables t WHERE t.table_name = #{tableName}")
    TableInfo getTableInfo(@Param("tableName") String tableName);


    /**
     * 查询表信息
     *
     * @param tableName 表名
     * @return TableInfo
     */
    @Select("SELECT t.COLUMN_NAME `name`, t.COLUMN_DEFAULT `initial`, t.COLUMN_KEY `key`, t.ORDINAL_POSITION `order`, t.IS_NULLABLE nullable, t.DATA_TYPE dataType, t.CHARACTER_MAXIMUM_LENGTH maxLength, t.COLUMN_COMMENT `comment`  FROM information_schema.columns t WHERE t.table_name = #{tableName}")
    List<ColumnInfo> getColumnInfo(@Param("tableName") String tableName);
}
