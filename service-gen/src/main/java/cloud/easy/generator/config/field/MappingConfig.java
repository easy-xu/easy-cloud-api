package cloud.easy.generator.config.field;

import cloud.easy.generator.utils.TableUtils;
import lombok.Data;

/**
 * MappingConfig
 *
 * @author xu honglin
 * @date 2021/12/6 22:38
 */
@Data
public class MappingConfig {
    /**
     * 关联字段映射
     */
    private String model;
    private String code;
    private String codeField;
    private String nameField;


    public MappingConfig(String tableName, String codeColumn, String nameColumn) {
        this.model = TableUtils.getModel(tableName);
        this.code = TableUtils.getCode(tableName);
        this.codeField = TableUtils.column2field(codeColumn);
        this.nameField = TableUtils.column2field(nameColumn);
    }
}
