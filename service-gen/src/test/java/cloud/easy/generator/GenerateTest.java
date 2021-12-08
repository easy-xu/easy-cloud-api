package cloud.easy.generator;

import cloud.easy.base.entity.AuthDataEntity;
import cloud.easy.base.entity.BaseEntity;
import cloud.easy.generator.config.GlobalConfig;
import cloud.easy.generator.config.field.FieldConfig;
import cloud.easy.generator.config.field.FieldStyleConfig;
import cloud.easy.generator.config.field.MappingConfig;
import cloud.easy.generator.service.GenerateService;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * ApplicationTest
 *
 * @author xu honglin
 * @date 2021/12/4 12:30
 */
@SpringBootTest
class GenerateTest {
    String javaProject = "C:\\Project\\easy-cloud";
    String reactProject = "C:\\Project\\easy-cloud-cms-ui";

    @Resource
    GenerateService service;

    @Test
    void all() throws IOException, TemplateException {
        job();
        sys();
    }

    @Test
    void sys() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setAdd(false);
        globalConfig.setEdit(false);
        globalConfig.setDelete(false);
        globalConfig.setList(false);
        FieldConfig requestId = new FieldConfig();
        requestId.setStyle(FieldStyleConfig.searchConfig());
        custFiled.put("request_id", requestId);
        FieldConfig userNo = new FieldConfig();
        userNo.setStyle(FieldStyleConfig.searchConfig());
        custFiled.put("user_no", userNo);
        FieldConfig deviceNo = new FieldConfig();
        deviceNo.setStyle(FieldStyleConfig.searchConfig());
        custFiled.put("device_no", deviceNo);
        //sys_api_log
        service.generate(globalConfig, "sys_api_log", custFiled);
        //任务日志表
        service.generate( globalConfig,"sys_option_log", custFiled);
    }

    @Test
    void job() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-job");
        globalConfig.setReactPlace(reactProject);
        //任务配置表
        globalConfig.setEntitySuperClass(AuthDataEntity.class);
        //cron不作为查询条件
        FieldConfig cron = new FieldConfig();
        cron.setStyle(FieldStyleConfig.noSearchConfig());
        custFiled.put("cron", cron);
        service.generate(globalConfig, "job_config", custFiled);
        //任务日志表
        globalConfig.setEntitySuperClass(BaseEntity.class);
        globalConfig.setAdd(false);
        globalConfig.setEdit(false);
        globalConfig.setDelete(false);
        //任务id配置表映射
        FieldConfig jobId = new FieldConfig();
        jobId.setStyle(FieldStyleConfig.searchConfig());
        jobId.setComment("任务");
        jobId.setPageType("select");
        jobId.setTableMapping(new MappingConfig("job_config","id", "name"));
        custFiled.put("job_id", jobId);
        service.generate( globalConfig,"job_log", custFiled);
    }

}