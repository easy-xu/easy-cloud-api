package cloud.easy.generator.config.java;

import lombok.Getter;
import lombok.Setter;

/**
 * ControllerConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:11
 */
@Getter
@Setter
public class EntityConfig extends JavaFileConfig {

    public EntityConfig(String basePackage, String model, String entityName) {
        super(basePackage, model, entityName);
        this.pkg = basePackage + "." + model + ".entity";
        this.name = entityName;
    }

    @Override
    public String template() {
        return "entity.java.ftl";
    }
}
