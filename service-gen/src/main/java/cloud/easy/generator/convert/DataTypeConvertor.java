package cloud.easy.generator.convert;

/**
 * DataTypeConvertor
 *
 * @author xu honglin
 * @date 2021/12/4 21:11
 */
public interface DataTypeConvertor {

    /**
     * 数据库类型转换Java类型
     *
     * @param columnType 数据库类型
     * @return Java类型
     */
    ColumnType columnTypeToJavaType(String columnType);

    /**
     * 数据库类型转换Js类型
     *
     * @param columnType 数据库类型
     * @return Js类型
     */
    String columnTypeToJsType(String columnType);
}
