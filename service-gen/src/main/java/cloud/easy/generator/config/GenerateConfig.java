package cloud.easy.generator.config;

/**
 * EntityConfig
 *
 * @author xu honglin
 * @date 2021/12/4 17:51
 */
public interface  GenerateConfig {

    /**
     * 模板文件
     * @return 模板文件名
     */
    String template();

    /**
     * 输出路径
     * @return 相对路径
     */
    String outPath();
}
