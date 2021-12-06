package cloud.easy.generator.config.java;

/**
 * ServiceImplConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:11
 */
public class ServiceImplConfig extends JavaFileConfig {

    public ServiceImplConfig(String basePackage, String model, String entityName) {
        super(basePackage, model, entityName);
        this.pkg = basePackage + "." + model + ".service.impl";
        this.name = entityName + "ServiceImpl";
    }

    @Override
    public String template() {
        return "serviceImpl.java.ftl";
    }
}
