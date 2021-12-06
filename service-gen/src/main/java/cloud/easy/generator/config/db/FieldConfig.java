package cloud.easy.generator.config.db;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * FieldInfo
 *
 * @author xu honglin
 * @date 2021/12/4 20:23
 */
@Data
public class FieldConfig {
    private String name;
    private String type;
    private String pageType;
    private String comment;
    private String pkg;
    private Map<String, String> codeMapping;
    private List<String> rules;
    private boolean search;

}
