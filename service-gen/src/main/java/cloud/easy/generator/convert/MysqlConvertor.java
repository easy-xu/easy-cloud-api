package cloud.easy.generator.convert;

/**
 * MysqlConvertor
 *
 * @author xu honglin
 * @date 2021/12/4 21:14
 */
public class MysqlConvertor implements DataTypeConvertor {
    @Override
    public ColumnType columnTypeToJavaType(String columnType) {
        switch (columnType) {
            case "bigint":
                return ColumnType.LONG;
            case "int":
                return ColumnType.INTEGER;
            case "float":
                return ColumnType.FLOAT;
            case "double":
                return ColumnType.DOUBLE;
            case "decimal":
                return ColumnType.BIG_DECIMAL;
            case "blob":
                return ColumnType.BLOB;
            case "date":
                return ColumnType.LOCAL_DATE;
            case "time":
                return ColumnType.LOCAL_TIME;
            case "datetime":
                return ColumnType.LOCAL_DATE_TIME;
            default:
                return ColumnType.STRING;
        }
    }

    @Override
    public String columnTypeToJsType(String columnType) {
        switch (columnType) {
            case "bigint":
            case "int":
            case "float":
            case "double":
            case "decimal":
                return "number";
            case "date":
            case "time":
            case "datetime":
                return "datetime";
            default:
                return "string";
        }
    }

}
