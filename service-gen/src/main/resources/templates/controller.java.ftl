package ${controller.pkg};

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
<#if global.swagger >
import io.swagger.annotations.Api;
</#if>
import ${entity.pkg}.${entity.name};
import ${service.pkg}.${service.name};
<#list controller.importPkg as pkg>
import ${pkg};
</#list>

/**
 * ${comment!}控制器
 *
 * @author ${global.author}
 * @since ${global.since}
 */
@RestController
@RequestMapping("/api/${controller.model}/${code}")
<#if global.swagger >
@Api(tags = "${comment!}接口")
</#if>
public class ${controller.name} extends ${controller.superClassName}<${entity.name}, ${service.name}> {

    @Autowired
    public ${controller.name}(${service.name} service) {
        super(service);
    }

<#if global.get >
    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

</#if>
<#if global.query >
    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody ${entity.name} entity) {
        return super.queryEntity(entity);
    }

</#if>
<#if global.add || global.edit>
    @Override
    @OptionLog("${comment!}保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@Validated @RequestBody ${entity.name} entity) {
        return super.saveEntity(entity);
    }

</#if>
<#if global.delete >
    @Override
    @OptionLog("${comment!}删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

</#if>
<#if global.pageList >
    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<${entity.name}> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

</#if>
<#if global.list >
    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody ${entity.name} entity) {
        return super.listEntity(entity);
    }

</#if>
<#if global.count >
    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody ${entity.name} entity) {
        return super.countEntity(entity);
    }

</#if>
}

