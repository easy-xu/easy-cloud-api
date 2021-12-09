package cloud.easy.generator.config.field;

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
    private String initial;
    private String key;
    private String pageType;
    private String subPage;
    private String comment;
    private String pkg;
    private List<String> rules;
    private String style;
    private boolean extend;

    /**
     * 固定映射
     */
    private Map<String, String> codeMapping;

    /**
     * 其他表映射
     */
    private MappingConfig tableMapping;


}
