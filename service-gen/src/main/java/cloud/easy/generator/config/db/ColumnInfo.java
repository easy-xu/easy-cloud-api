package cloud.easy.generator.config.db;

import lombok.Data;

/**
 * Column
 *
 * @author xu honglin
 * @date 2021/12/4 12:27
 */
@Data
public class ColumnInfo {
    private String name;
    private Integer order;
    private String nullable;
    private String dataType;
    private Integer maxLength;
    private String comment;
}
