package cloud.easy.generator.config.java;

/**
 * MapperConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:11
 */
public class MapperConfig extends JavaFileConfig {

    public MapperConfig(String basePackage, String model, String entityName) {
        super(basePackage, model, entityName);
        this.pkg = basePackage + "." + model + ".mapper";
        this.name = entityName + "Mapper";
    }


    @Override
    public String template() {
        return "mapper.java.ftl";
    }
}
