package cloud.easy.generator.config;

import cloud.easy.base.entity.AuthDataEntity;
import cloud.easy.generator.config.db.FieldConfig;
import cloud.easy.generator.config.db.TableInfo;
import cloud.easy.generator.config.java.*;
import cloud.easy.generator.config.react.PageConfig;
import cloud.easy.generator.utils.TableUtils;
import lombok.Getter;

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
public class GenerateConfigBuilder {
    private  List<GenerateConfig> configs;
    protected TableInfo tableInfo;
    protected GlobalConfig global;
    protected String model;
    protected String entity;
    protected String code;
    protected String comment;
    private List<FieldConfig> fields;
    private boolean authData = false;

    private GenerateConfigBuilder() {
    }

    public static GenerateConfigBuilder newConfig(TableInfo tableInfo, GlobalConfig globalConfig, Map<String, FieldConfig> custFieldConfig){
        GenerateConfigBuilder builder = new GenerateConfigBuilder();
        builder.configs = new ArrayList<>();
        builder.tableInfo = tableInfo;
        builder.global = globalConfig;
        builder.fields = TableUtils.entityFields(tableInfo, globalConfig, custFieldConfig);
        String tableName = tableInfo.getName();
        builder.model = TableUtils.getModel(tableName);
        builder.entity = TableUtils.table2entity(tableName);
        builder.code = TableUtils.getCode(tableName);
        builder.comment = TableUtils.getComment(tableInfo.getComment());
        return builder;
    }

    public GenerateConfig buildEntity(){
        EntityConfig config = new EntityConfig(global.getBasePackage(), model, entity);
        Class<?> entitySuperClass = global.getEntitySuperClass();
        if (entitySuperClass == AuthDataEntity.class){
            authData = true;
        }
        config.superClass(entitySuperClass);
        for (FieldConfig field: fields){
            config.addImportPackage(field.getPkg());
        }
        configs.add(config);
        return config;
    }
    public GenerateConfig buildController(){
        ControllerConfig config = new ControllerConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getControllerSuperClass());
        configs.add(config);
        return config;
    }
    public GenerateConfig buildService(){
        ServiceConfig config = new ServiceConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getServiceSuperClass());
        configs.add(config);
        return config;
    }
    public GenerateConfig buildServiceImpl(){
        ServiceImplConfig config = new ServiceImplConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getServiceImplSuperClass());
        configs.add(config);
        return config;
    }
    public GenerateConfig buildMapper(){
        MapperConfig config = new MapperConfig(global.getBasePackage(), model, entity);
        config.superClass(global.getMapperSuperClass());
        configs.add(config);
        return config;
    }
    public GenerateConfig buildPage(){
        PageConfig config = new PageConfig(model,code, entity);
        configs.add(config);
        return config;
    }
}
