package cloud.easy.generator.service;

import cloud.easy.exception.SystemErrorException;
import cloud.easy.generator.config.GenerateConfig;
import cloud.easy.generator.config.GenerateConfigBuilder;
import cloud.easy.generator.config.GlobalConfig;
import cloud.easy.generator.config.db.TableInfo;
import cloud.easy.generator.config.field.FieldConfig;
import cloud.easy.generator.config.java.JavaFileConfig;
import cloud.easy.generator.config.react.PageConfig;
import cloud.easy.utils.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GenerateService
 *
 * @author xu honglin
 * @date 2021/12/4 17:49
 */
@Component
public class GenerateService {

    @Resource
    private TableInfoService tableInfoService;

    private Configuration cfg;

    @PostConstruct
    public void init() {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public void generate(GlobalConfig globalConfig, String tableName) throws IOException, TemplateException {
        generate(globalConfig, tableName, null);
    }

    public void generate(GlobalConfig globalConfig, String tableName, Map<String, FieldConfig> custFieldConfig) throws IOException, TemplateException {
        TableInfo tableInfo = tableInfoService.queryTableInfo(tableName);
        GenerateConfigBuilder configBuilder = GenerateConfigBuilder.newConfig(tableInfo, globalConfig, custFieldConfig);
        generate(configBuilder);
    }

    public void generate(GenerateConfigBuilder configBuilder) throws IOException, TemplateException {
        GlobalConfig global = configBuilder.getGlobal();
        Map<String, Object> root = new HashMap<>();
        root.put("global", global);
        root.put("table", configBuilder.getTableInfo());
        root.put("entity", configBuilder.buildEntity());
        root.put("controller", configBuilder.buildController());
        root.put("service", configBuilder.buildService());
        root.put("serviceImpl", configBuilder.buildServiceImpl());
        root.put("mapper", configBuilder.buildMapper());
        root.put("page", configBuilder.buildPage());
        root.put("fields", configBuilder.getFields());
        root.put("code", configBuilder.getCode());
        root.put("comment", configBuilder.getComment());
        root.put("model", configBuilder.getModel());
        root.put("isAuthData", configBuilder.isAuthData());

        //逐一生成文件
        List<GenerateConfig> configs = configBuilder.getConfigs();
        for (GenerateConfig config : configs) {
            process(global, config, root);
        }
    }

    private void process(GlobalConfig global, GenerateConfig config, Map<String, Object> root) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(config.template());
        String workplace = null;
        if (config instanceof PageConfig && global.getReactPlace() != null) {
            workplace = global.getReactPlace();
        }
        if (config instanceof JavaFileConfig && global.getJavaPlace() != null) {
            workplace = global.getJavaPlace();
        }
        if (workplace == null) {
            throw new SystemErrorException("未指定生成目录");
        }
        String outPath = Paths.get(workplace, config.outPath()).toString();
        File file = new File(outPath);
        FileUtils.makeDirs(file.getParentFile());
        Writer out = new OutputStreamWriter(new FileOutputStream(file));
        temp.process(root, out);
    }
}
