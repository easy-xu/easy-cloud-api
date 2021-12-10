package cloud.easy.generator.config.field;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private Set<String> importPkg = new HashSet<>();
    private List<String> pageRules;
    private List<String> entityRules;
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
