package cloud.easy.generator.config.db;

import lombok.Data;

import java.util.List;

/**
 * Table
 *
 * @author xu honglin
 * @date 2021/12/4 12:25
 */
@Data
public class TableInfo {
    private String name;
    private String comment;
    private List<ColumnInfo> columns;
}
