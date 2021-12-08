package cloud.easy.generator.utils;

import cloud.easy.generator.config.GlobalConfig;
import cloud.easy.generator.config.db.ColumnInfo;
import cloud.easy.generator.config.db.TableInfo;
import cloud.easy.generator.config.field.FieldConfig;
import cloud.easy.generator.config.field.FieldStyleConfig;
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
        return tableComment.replace("表", "");
    }

    public static String getEntity(String tableName) {
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

    public static void entityFields(List<FieldConfig> fields, TableInfo tableInfo, GlobalConfig globalConfig, Map<String, FieldConfig> custFieldConfig) {
        Class<?> superClass = globalConfig.getEntitySuperClass();
        //获取父类字段名
        List<String> superFieldNames = new ArrayList<>();
        while (superClass != null && superClass != Object.class) {
            for (Field field : superClass.getDeclaredFields()) {
                superFieldNames.add(field.getName());
            }
            superClass = superClass.getSuperclass();
        }
        List<ColumnInfo> columns = tableInfo.getColumns();
        for (ColumnInfo column : columns) {
            String columnName = column.getName();
            FieldConfig custField = custFieldConfig == null ? null : custFieldConfig.get(columnName);
            FieldConfig fieldConfig = getFieldConfig(globalConfig, column, custField);
            String fieldName = fieldConfig.getName();
            //父类字段已经在页面中固定设置
            if (!superFieldNames.contains(fieldName)) {
                fieldConfig.setSubPage("base");
                fields.add(fieldConfig);
            }
        }
    }

    private static FieldConfig getFieldConfig(GlobalConfig globalConfig, ColumnInfo column, FieldConfig custField) {
        DataTypeConvertor convertor = globalConfig.getTypeConvertor();
        String dbType = column.getDataType();
        //名称转换
        String fieldName = TableUtils.column2field(column.getName());
        //类型转换
        ColumnType javaType = convertor.columnTypeToJavaType(dbType);
        String jsType = convertor.columnTypeToJsType(dbType);
        String initial = column.getInitial();
        if ("NULL".equals(initial)) {
            initial = null;
        }
        FieldConfig field = new FieldConfig();
        field.setName(fieldName);
        field.setComment(column.getComment());
        field.setInitial(initial);
        field.setKey(column.getKey());
        field.setType(javaType.getType());
        field.setPkg(javaType.getPkg());
        field.setPageType(jsType);
        //解析comment中的字典映射
        handleComment(field, column.getComment());
        //解析规则, 规则为空则设置默认规则
        handleRules(field, column, custField);
        //添加默认样式
        handleStyle(field, column, custField);
        //覆盖自定义设置
        if (custField != null) {
            BeanUtils.copyNotNull(custField, field);
        }
        return field;
    }

    private static void handleStyle(FieldConfig field, ColumnInfo column, FieldConfig custField) {
        if (custField != null && custField.getStyle() != null) {
            return;
        }
        if ("PRI".equals(column.getKey())) {
            field.setStyle(FieldStyleConfig.idConfig());
            return;
        }
        if ("NO".equals(column.getNullable())) {
            field.setStyle(FieldStyleConfig.searchConfig());
            return;
        }
        field.setStyle(FieldStyleConfig.noSearchConfig());
    }


    private static void handleRules(FieldConfig field, ColumnInfo column, FieldConfig custField) {
        //主键默认自动生成，不添加规则
        if ("PRI".equals(column.getKey())) {
            return;
        }
        List<String> rules = new ArrayList<>();
        if ("NO".equals(column.getNullable())) {
            rules.add("{ required: true }");
        }
        Integer maxLength = column.getMaxLength();
        String pageType = field.getPageType();
        if (custField != null && custField.getPageType() != null) {
            pageType = custField.getPageType();
        }
        if (maxLength != null && ("string".equals(pageType) || "number".equals(pageType))) {
            rules.add("{ type: '" + pageType + "', max: " + maxLength + " }");
        }
        field.setRules(rules.isEmpty() ? null : rules);
    }

    private static void handleComment(FieldConfig field, String comment) {
        String commentConfig = RegUtils.findAny(comment, "\\(.*\\)");
        if (commentConfig == null) {
            return;
        }
        List<String> selectCodeMap = RegUtils.findAll(commentConfig, "[\\u4E00-\\u9FA5A-Za-z0-9_-]+:[\\u4E00-\\u9FA5A-Za-z0-9_-]+");
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
