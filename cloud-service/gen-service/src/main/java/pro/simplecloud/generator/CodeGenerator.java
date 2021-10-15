package pro.simplecloud.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import pro.simplecloud.entity.BaseEntity;
import pro.simplecloud.entity.PrimaryDataEntity;

import java.util.ArrayList;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

/**
 * MyBatisPlus 官方示例修改
 */
public class CodeGenerator {


    public static void main(String[] args) {
        generate("api", false, "api_log");

        generate("file", true, "file_master");
        generate("file", false, "file_content");

        generate("cms", true, "cms_user", "cms_menu", "cms_role", "cms_auth", "cms_option");
        generate("cms", false, "cms_user_role", "cms_role_auth","cms_auth_menu", "cms_auth_option" );

        //generate("quna", true, "quna_config_questionnaire", "quna_config_question", "quna_config_option", "quna_answer_questionnaire", "quna_answer_question", "quna_config_result", "quna_config_result_score", "quna_config_result_description", "quna_answer_result");
    }

    private static void generate(String module, boolean primaryData, String... table) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/cloud-module/module-datasource/src/main/java");
        gc.setAuthor("Generator");
        gc.setOpen(false);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/simple-cloud?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(module);
        pc.setParent("pro.simplecloud");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        mpg.setTemplate(templateConfig);

        //配置freemarker engine
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(table);
        strategy.setControllerMappingHyphenStyle(true);
        //设置父类
        if (primaryData) {
            strategy.setSuperEntityClass(PrimaryDataEntity.class);
            strategy.setSuperEntityColumns("id","deleted","group_id", "own_mode", "other_mode", "group_mode", "create_by", "create_time", "update_by", "update_time");
        } else {
            strategy.setSuperEntityClass(BaseEntity.class);
            strategy.setSuperEntityColumns("id", "create_by", "create_time", "update_by", "update_time");
        }

        //自动充填
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("createTime", INSERT));
        tableFills.add(new TableFill("createBy", INSERT));
        tableFills.add(new TableFill("updateTime", INSERT_UPDATE));
        tableFills.add(new TableFill("updateBy", INSERT_UPDATE));
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
