package cloud.easy.generator.utils;

import cloud.easy.generator.config.GlobalConfig;
import cloud.easy.generator.config.db.ColumnInfo;
import cloud.easy.generator.config.db.FieldConfig;
import cloud.easy.generator.config.db.TableInfo;
import cloud.easy.generator.convert.ColumnType;
import cloud.easy.generator.convert.DataTypeConvertor;
import cloud.easy.utils.BeanUtils;
import cloud.easy.utils.RegUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TableUtils
 *
 * @author xu honglin
 * @date 2021/12/5 23:25
 */
public class TableUtils {

    private TableUtils() {

    }

    public static String getModel(String tableName) {
        return tableName.split("_")[0];
    }

    public static String getCode(String tableName) {
        return tableName.replace("_", "").replace(getModel(tableName), "");
    }

    public static String getComment(String tableComment) {
        return  tableComment.replace("表", "");
    }

    public static String table2entity(String tableName) {
        String[] splits = tableName.split("_");
        for (int i = 0; i < splits.length; i++) {
            splits[i] = splits[i].substring(0, 1).toUpperCase() + splits[i].substring(1);
        }
        return String.join("", splits);
    }

    public static String column2field(String columnName) {
        String[] splits = columnName.split("_");
        for (int i = 0; i < splits.length; i++) {
            if (i != 0) {
                splits[i] = splits[i].substring(0, 1).toUpperCase() + splits[i].substring(1);
            }
        }
        return String.join("", splits);
    }


    public static List<FieldConfig> entityFields(TableInfo tableInfo, GlobalConfig globalConfig, Map<String, FieldConfig> custFieldConfig) {
        List<FieldConfig> fields = new ArrayList<>();
        DataTypeConvertor convertor = globalConfig.getTypeConvertor();
        Class<?> superClass = globalConfig.getEntitySuperClass();
        List<String> superFields = new ArrayList<>();
        while (superClass != null && superClass != Object.class) {
            for (Field field : superClass.getDeclaredFields()) {
                superFields.add(field.getName());
            }
            superClass = superClass.getSuperclass();
        }

        List<ColumnInfo> columns = tableInfo.getColumns();
        for (ColumnInfo column : columns) {
            String columnName = column.getName();
            FieldConfig custField = custFieldConfig == null ? null : custFieldConfig.get(columnName);
            String fieldName = TableUtils.column2field(columnName);
            if (!superFields.contains(fieldName) || "deleted".equals(fieldName)) {
                String dbType = column.getDataType();
                //类型转换
                ColumnType javaType = convertor.columnTypeToJavaType(dbType);
                String jsType = convertor.columnTypeToJsType(dbType);
                FieldConfig field = new FieldConfig();
                field.setName(fieldName);
                field.setComment(column.getComment());
                field.setType(javaType.getType());
                field.setPkg(javaType.getPkg());
                field.setPageType(jsType);
                //根据comment约定内容，解析类型内容
                handleComment(field, column.getComment());
                //解析规则
                handleRules(field, column);
                //覆盖自定义设置
                if (custField != null) {
                    BeanUtils.copyNotNull(custField, field);
                }
                fields.add(field);
            }
        }
        return fields;
    }


    private static void handleRules(FieldConfig field, ColumnInfo column) {
        List<String> rules = new ArrayList<>();
        if ("NO".equals(column.getNullable())) {
            rules.add("{ required: true }");
            //为空则默认可作为查询条件
            field.setSearch(true);
        }
        Integer maxLength = column.getMaxLength();
        String pageType = field.getPageType();
        if (maxLength != null && ("string".equals(pageType) || "number".equals(pageType))) {
            rules.add("{ type: '" + pageType + "', max: " + maxLength + " }");
        }
        field.setRules(rules);
    }

    private static void handleComment(FieldConfig field, String comment) {
        String commentConfig = RegUtils.findAny(comment, "\\(.*\\)");
        if (commentConfig == null) {
            return;
        }
        List<String> selectCodeMap = RegUtils.findAll(commentConfig, "[\\u4E00-\\u9FA5A-Za-z0-9_]+:[\\u4E00-\\u9FA5A-Za-z0-9_]+");
        if (selectCodeMap.isEmpty()) {
            return;
        }
        Map<String, String> codeMap = new HashMap<>();
        for (String select : selectCodeMap) {
            String[] split = select.split(":");
            codeMap.put(split[0], split[1]);
        }
        field.setPageType("select");
        field.setCodeMapping(codeMap);
        field.setComment(comment.replace(commentConfig, ""));
    }

}
