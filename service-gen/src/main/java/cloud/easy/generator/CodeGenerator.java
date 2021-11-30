package cloud.easy.generator;


import cloud.easy.base.entity.BaseEntity;
import cloud.easy.base.entity.PrimaryDataEntity;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * MyBatisPlus 官方示例修改
 */
public class CodeGenerator {


    public static void main(String[] args) {
        generate("sys", false, "sys_api_log", "sys_option_log");

        //generate("file", true, "file_master");
        //generate("file", false, "file_content");

        //generate("cms", true, "cms_user", "cms_menu", "cms_role", "cms_auth", "cms_option","cms_group");
        //generate("cms", false, "cms_user_group","cms_user_role", "cms_role_auth","cms_auth_menu", "cms_auth_option" );

        //generate("quna", true, "quna_config_questionnaire", "quna_config_question", "quna_config_option", "quna_answer_questionnaire", "quna_answer_question", "quna_config_result", "quna_config_result_score", "quna_config_result_description", "quna_answer_result");

        //generate("kl", true, "kl_knowledge_node", "kl_knowledge_content");

    }

    private static void generate(String module, boolean primaryData, String... table) {
        String projectPath = System.getProperty("user.dir");
        String workPath = projectPath + "/generate";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/cloud?useUnicode=true&useSSL=false&characterEncoding=utf8", "root", "root")
                .globalConfig(gc ->
                        gc.author("Mybatis Plus")
                                .outputDir(workPath + "/java")
                                .fileOverride()
                                .disableOpenDir()
                                .enableSwagger())
                .packageConfig(pc ->
                        pc.moduleName(module)
                                .parent("cloud.easy")
                                .pathInfo(Collections.singletonMap(OutputFile.other, workPath + "/react")))
                .templateConfig(tc ->
                        tc.disable(TemplateType.XML)
                                .controller("/templates/controller.java"))
                .strategyConfig(sc -> {
                    sc.addInclude(table);
                    Entity.Builder entity = sc.entityBuilder();
                    entity.naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok();
                    //设置父类
                    if (primaryData) {
                        entity.superClass(PrimaryDataEntity.class);
                        entity.addSuperEntityColumns("id", "deleted", "group_id", "own_mode", "other_mode", "group_mode", "create_by", "create_time", "update_by", "update_time");
                    } else {
                        entity.superClass(BaseEntity.class);
                        entity.addSuperEntityColumns("id", "create_by", "create_time", "update_by", "update_time");
                    }
                })

                .injectionConfig(ic -> {
                    ic.beforeOutputFile((tableInfo, configMap) -> {
                        Map<String, String> fileMap = new HashMap<>();
                        String mappingHyphen = (String) configMap.get("controllerMappingHyphen");
                        String fileName = mappingHyphen.substring(mappingHyphen.indexOf("-") + 1).replace("-","") + ".tsx";
                        fileMap.put(fileName, "/templates/page.tsx.ftl");
                        ic.customFile(fileMap);
                    });
                })
                .templateEngine(new CustTemplateEngine())
                .execute();

    }

}
