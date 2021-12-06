package cloud.easy.generator.config;

import cloud.easy.base.controller.BaseController;
import cloud.easy.generator.convert.DataTypeConvertor;
import cloud.easy.generator.convert.MysqlConvertor;
import cloud.easy.utils.DateTimeUtils;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalConfig
 *
 * @author xu honglin
 * @date 2021/12/4 14:03
 */
@Data
public class GlobalConfig {

    private String author;
    private String since;
    private String basePackage;
    private String workplace;

    protected Class<?> entitySuperClass;
    protected Class<?> controllerSuperClass;
    protected Class<?> serviceSuperClass;
    protected Class<?> serviceImplSuperClass;
    protected Class<?> mapperSuperClass;

    /**
     * 字段映射 当前表名.字段=从表名(字段:字段)
     */
    private Map<String, Map<String, String>> fieldMapping = new HashMap<>();

    private DataTypeConvertor typeConvertor = new MysqlConvertor();

    public static GlobalConfig defaultConfig() {
        String projectPath = System.getProperty("user.dir");
        String workplace = projectPath + File.separator + "generate";
        GlobalConfig config = new GlobalConfig();
        config.setBasePackage("cloud.easy");
        config.setAuthor("xu honglin");
        config.setSince(DateTimeUtils.getDate());
        config.setWorkplace(workplace);
        config.setServiceSuperClass(IService.class);
        config.setServiceImplSuperClass(ServiceImpl.class);
        config.setMapperSuperClass(BaseMapper.class);
        config.setControllerSuperClass(BaseController.class);
        return config;
    }

}