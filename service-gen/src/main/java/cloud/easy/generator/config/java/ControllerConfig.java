package cloud.easy.generator.config.java;

/**
 * ControllerConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:11
 */
public class ControllerConfig extends JavaFileConfig {

    public ControllerConfig(String basePackage, String model, String entityName) {
        super(basePackage, model, entityName);
        this.pkg = basePackage + "." + model + ".controller";
        this.name = entityName + "Controller";
    }

    @Override
    public String template() {
        return "controller.java.ftl";
    }
}
