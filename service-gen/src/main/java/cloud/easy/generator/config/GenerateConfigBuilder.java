package cloud.easy.generator.config;

import cloud.easy.generator.config.db.TableInfo;
import cloud.easy.generator.config.field.FieldConfig;
import cloud.easy.generator.config.java.*;
import cloud.easy.generator.config.react.PageConfig;
import cloud.easy.generator.utils.TableUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GenerateConfigBuilder
 *
 * @author xu honglin
 * @date 2021/12/6 10:13
 */
@Getter
@Setter
public class GenerateConfigBuilder {
    private  List<GenerateConfig> configs;
    private TableInfo tableInfo;
    private GlobalConfig global;
    private String model;
    private String entity;
    private String code;
    private String comment;
    private List<FieldConfig> fields;

    private GenerateConfigBuilder() {
    }

    public static GenerateConfigBuilder newConfig(TableInfo tableInfo, GlobalConfig globalConfig, Map<String, FieldConfig> custFieldConfig){
        GenerateConfigBuilder builder = new GenerateConfigBuilder();
        builder.configs = new ArrayList<>();
        builder.tableInfo = tableInfo;
        builder.global = globalConfig;
        builder.fields = new ArrayList<>();
        TableUtils.entityFields(builder.fields, tableInfo, globalConfig, custFieldConfig);
        String tableName = tableInfo.getName();
        builder.model = TableUtils.getModel(tableName);
        builder.entity = TableUtils.getEntity(tableName);
        builder.code = TableUtils.getCode(tableName);
        builder.comment = TableUtils.getComment(tableInfo.getComment());

        return builder;
    }

    public String menuParent(){
        if (global.getMenuParent() != null){
            return global.getMenuParent();
        }
        return model;
    }

    public String menuCode(){
        if (global.getMenuCode() != null){
            return global.getMenuCode();
        }
        return code;
    }

    public GenerateConfig buildEntity(){
        EntityConfig config = new EntityConfig(global.getBasePackage(), model, entity);
        Class<?> entitySuperClass = global.getEntitySuperClass();
        config.superClass(entitySuperClass);
        for (FieldConfig field: fields){
            config.getImportPkg().addAll(field.getImportPkg());
        }
        if (global.isEntity()){
            configs.add(config);
        }
        return config;
    }

    public GenerateConfig buildController(){
        ControllerConfig config = new ControllerConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getControllerSuperClass());
        if (global.isController()){
            configs.add(config);
        }
        return config;
    }
    public GenerateConfig buildService(){
        ServiceConfig config = new ServiceConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getServiceSuperClass());
        if (global.isService()){
            configs.add(config);
        }
        return config;
    }
    public GenerateConfig buildServiceImpl(){
        ServiceImplConfig config = new ServiceImplConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getServiceImplSuperClass());
        if (global.isServiceImpl()){
            configs.add(config);
        }
        return config;
    }
    public GenerateConfig buildMapper(){
        MapperConfig config = new MapperConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getMapperSuperClass());
        if (global.isMapper()){
            configs.add(config);
        }
        return config;
    }
    public GenerateConfig buildPage(){
        PageConfig config = new PageConfig(model,code, entity, menuParent(), menuCode());
        if (global.isPage()){
            configs.add(config);
        }
        return config;
    }
}
