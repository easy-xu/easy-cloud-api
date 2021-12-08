package cloud.easy.generator.config.react;

import cloud.easy.generator.config.GenerateConfig;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Paths;

/**
 * JavaFileConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:13
 */
@Getter
@Setter
public class PageConfig implements GenerateConfig {
    private String model;
    private String name;
    private String code;
    private String menuParent;
    private String menuCode;

    public PageConfig(String model, String code, String name, String menuParent, String menuCode) {
        this.model = model;
        this.name = name;
        this.code = code;
        this.menuParent = menuParent == null ? model : menuParent;
        this.menuCode = menuCode == null ? code : menuCode;
    }

    @Override
    public String template() {
        return "page.tsx.ftl";
    }

    @Override
    public String outPath() {
        return Paths.get("src", "pages", "cms", menuParent, menuCode + ".tsx").toString();
    }
}
