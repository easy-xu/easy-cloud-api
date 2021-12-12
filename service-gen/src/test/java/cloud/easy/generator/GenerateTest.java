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
import java.util.List;

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
        cms_role();
        cms_role_auth();
        cms_user();
        cms_user_role();
        cms_user_group();
        sys_code_map();

    }
    @Test
    void sys_code_map() throws IOException, TemplateException {
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("data");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        service.generate(globalConfig, "sys_code_map");
    }

    @Test
    void cms_user_role() throws IOException, TemplateException {
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_user_role");
    }

    @Test
    void cms_user_group() throws IOException, TemplateException {
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_user_group");
    }

    @Test
    void cms_user() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setServiceImpl(false);
        globalConfig.setMenuParent("manage");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        //备注字段列表不显示
        FieldConfig remark = new FieldConfig();
        remark.setStyle(FieldStyleConfig.detailOnlyConfig());
        custFiled.put("remark", remark);
        //角色多选，表映射，多选
        FieldConfig roleIds = new FieldConfig();
        roleIds.setName("roleIds");
        roleIds.setComment("角色");
        roleIds.getImportPkg().add(List.class.getCanonicalName());
        roleIds.setType("List<Long>");
        roleIds.setPageType("checks");
        roleIds.setStyle(FieldStyleConfig.detailOnlyConfig());
        roleIds.setTableMapping(new MappingConfig("cms_role", "id", "name"));
        roleIds.setExtend(true);
        custFiled.put("roleIds", roleIds);
        //分组多选，表映射，多选
        FieldConfig groupIds = new FieldConfig();
        groupIds.setName("groupIds");
        groupIds.setComment("分组");
        groupIds.getImportPkg().add(List.class.getCanonicalName());
        groupIds.setType("List<Long>");
        groupIds.setPageType("select");
        groupIds.setStyle(FieldStyleConfig.detailOnlyConfig());
        groupIds.setTableMapping(new MappingConfig("cms_group", "id", "name"));
        groupIds.setExtend(true);
        custFiled.put("groupIds", groupIds);
        //手机号自定义格式
        FieldConfig phone = new FieldConfig();
        phone.setRegexp("^(13[0-9]|14[5|7]|15[0-9]|18[0-9])\\\\d{8}$");
        custFiled.put("phone", phone);
        //邮箱自定义格式
        FieldConfig email = new FieldConfig();
        email.setRegexp("^(13[0-9]|14[5|7]|15[0-9]|18[0-9])\\\\d{8}$");
        custFiled.put("email", email);
        service.generate(globalConfig, "cms_user", custFiled);
    }

    @Test
    void cms_role_auth() throws IOException, TemplateException {
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_role_auth");
    }

    @Test
    void cms_role() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setServiceImpl(false);
        globalConfig.setMenuParent("manage");
        globalConfig.setEntitySuperClass(AuthEntity.class);
        //备注字段列表不显示
        FieldConfig remark = new FieldConfig();
        remark.setStyle(FieldStyleConfig.detailOnlyConfig());
        custFiled.put("remark", remark);
        //权限菜单，表映射，菜单树
        FieldConfig authCheck = new FieldConfig();
        authCheck.setName("authIds");
        authCheck.setComment("权限");
        authCheck.getImportPkg().add(List.class.getCanonicalName());
        authCheck.setType("List<Long>");
        authCheck.setPageType("checks");
        authCheck.setStyle(FieldStyleConfig.detailOnlyConfig());
        authCheck.setTableMapping(new MappingConfig("cms_auth", "id", "name"));
        authCheck.setExtend(true);
        custFiled.put("authIds", authCheck);
        service.generate(globalConfig, "cms_role", custFiled);
    }

    @Test
    void cms_auth_option() throws IOException, TemplateException {
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_auth_option");
    }
    void cms_auth_menu() throws IOException, TemplateException {
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setSwagger(false);
        globalConfig.setController(false);
        service.generate(globalConfig, "cms_auth_menu");
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
        globalConfig.setServiceImpl(false);
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
        menuType.getImportPkg().add("cloud.easy.cms.enums.MenuTypeEnum");
        custFiled.put("type", menuType);
        service.generate(globalConfig, "cms_menu", custFiled);
    }

    @Test
    void cms_auth() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setServiceImpl(false);
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
        menuTree.getImportPkg().add(List.class.getCanonicalName());
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
        menuTree.getImportPkg().add(List.class.getCanonicalName());
        optionCheck.setType("List<Long>");
        optionCheck.setPageType("checks");
        optionCheck.setStyle(FieldStyleConfig.detailOnlyConfig());
        optionCheck.setTableMapping(new MappingConfig("cms_option", "id", "name"));
        optionCheck.setExtend(true);
        custFiled.put("optionIds", optionCheck);
        service.generate(globalConfig, "cms_auth", custFiled);
    }

    @Test
    void sys() throws IOException, TemplateException {
        HashMap<String, FieldConfig> custFiled = new HashMap<>();
        GlobalConfig globalConfig = GlobalConfig.defaultConfig();
        globalConfig.setJavaPlace(javaProject + "\\service-cms");
        globalConfig.setReactPlace(reactProject);
        globalConfig.setMenuParent("query");
        globalConfig.setAdd(false);
        globalConfig.setEdit(false);
        globalConfig.setDelete(false);
        globalConfig.setList(false);
        globalConfig.setCount(false);
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