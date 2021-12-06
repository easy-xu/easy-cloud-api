package cloud.easy.generator.config.java;

import cloud.easy.generator.config.GenerateConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * JavaFileConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:13
 */
@Getter
@Setter
public abstract class JavaFileConfig implements GenerateConfig {
    private Class<?> superClass;
    private String superClassName;
    private String basePackage;
    private String model;
    private String entityName;

    protected String pkg;
    protected String name;
    protected Set<String> importPackages = new HashSet<>();

    public JavaFileConfig(String basePackage, String model, String entityName) {
        this.basePackage = basePackage;
        this.model = model;
        this.entityName = entityName;
    }

    public void superClass(Class<?> superClass) {
        this.superClassName = superClass.getSimpleName();
        this.importPackages.add(superClass.getCanonicalName());
    }

    public void addImportPackage(String importPackage) {
        if (StringUtils.hasLength(importPackage)) {
            importPackages.add(importPackage);
        }
    }

    @Override
    public String outPath() {
        String[] split = pkg.split("\\.");
        return Paths.get("java", "src", "main", "java", String.join(File.separator, split), name + ".java").toString();
    }
}
