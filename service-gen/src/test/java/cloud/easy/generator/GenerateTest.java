package cloud.easy.generator;

import cloud.easy.base.entity.AuthEntity;
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
        cms_menu();
        cms_option();
        cms_group();
        cms_auth();
        cms_auth_option();
        cms_auth_menu();

    }
    @Test
    void cms_auth_option() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setPage(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_auth_option", custFiled);
    }
    void cms_auth_menu() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setPage(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_auth_menu", custFiled);
    }

    @Test
    void cms_option() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("manage");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        //备注字段列表不显示
        FieldConfig remark = new FieldConfig();
        remark.setStyle(FieldStyleConfig.detailOnlyConfig());
        custFiled.put("remark", remark);
        service.generate(globalConfig, "cms_option", custFiled);
    }

    @Test
    void cms_group() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("manage");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        //备注字段列表不显示
        FieldConfig remark = new FieldConfig();
        remark.setStyle(FieldStyleConfig.detailOnlyConfig());
        custFiled.put("remark", remark);
        service.generate(globalConfig, "cms_group", custFiled);
    }

    @Test
    void cms_menu() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("manage");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        //备注字段列表不显示
        FieldConfig remark = new FieldConfig();
        remark.setStyle(FieldStyleConfig.detailOnlyConfig());
        custFiled.put("remark", remark);
        //父菜单,表映射，下拉菜单
        FieldConfig parentFolder = new FieldConfig();
        parentFolder.setTableMapping(new MappingConfig("cms_menu", "id", "name"));
        parentFolder.setComment("父菜单");
        parentFolder.setPageType("select");
        custFiled.put("parent_id", parentFolder);
        FieldConfig menuType = new FieldConfig();
        menuType.setType("MenuTypeEnum");
        menuType.setPkg("cloud.easy.cms.enums.MenuTypeEnum");
        custFiled.put("type", menuType);
        //页面手动修改，增加图标
        globalConfig.setPage(false);
        service.generate(globalConfig, "cms_menu", custFiled);
    }

    @Test
    void cms_auth() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("manage");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        //备注字段列表不显示
        FieldConfig remark = new FieldConfig();
        remark.setStyle(FieldStyleConfig.detailOnlyConfig());
        custFiled.put("remark", remark);
        //权限菜单，表映射，菜单树
        FieldConfig menuTree = new FieldConfig();
        menuTree.setName("menuIds");
        menuTree.setComment("权限菜单");
        menuTree.setPkg("java.util.List");
        menuTree.setType("List<Long>");
        menuTree.setPageType("tree");
        menuTree.setStyle(FieldStyleConfig.detailOnlyConfig());
        menuTree.setTableMapping(new MappingConfig("cms_menu", "id", "name"));
        menuTree.setExtend(true);
        custFiled.put("menuIds", menuTree);
        //权限操作，表映射，多选框
        FieldConfig optionCheck = new FieldConfig();
        optionCheck.setName("optionIds");
        optionCheck.setComment("权限操作");
        menuTree.setPkg("java.util.List");
        optionCheck.setType("List<Long>");
        optionCheck.setPageType("checks");
        optionCheck.setStyle(FieldStyleConfig.detailOnlyConfig());
        optionCheck.setTableMapping(new MappingConfig("cms_option", "id", "name"));
        optionCheck.setExtend(true);
        custFiled.put("optionIds", optionCheck);
        //后端多表保存手动修改
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_auth", custFiled);
    }

    @Test
    void sys() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("system");
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
        //sys_option_log
        service.generate( globalConfig,"sys_option_log", custFiled);
    }

    @Test
    void job() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-job");
        globalConfig.setReactPlace(reactProject);
        //任务配置表
        globalConfig.setEntitySuperClass(AuthEntity.class);
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