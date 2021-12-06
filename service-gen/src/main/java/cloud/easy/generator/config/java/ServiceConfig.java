package cloud.easy.generator.config.java;

/**
 * ServiceConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:11
 */
public class ServiceConfig extends JavaFileConfig {

    public ServiceConfig(String basePackage, String model, String entityName) {
        super(basePackage, model, entityName);
        this.pkg = basePackage + "." + model + ".service";
        this.name = "I" + entityName + "Service";
    }

    @Override
    public String template() {
        return "service.java.ftl";
    }
}
