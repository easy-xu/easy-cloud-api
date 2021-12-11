package ${controller.pkg};

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
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

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

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
    @Option(value = "${comment!}详情", menuCode = "${menuCode}", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

</#if>
<#if global.query >
    @Override
    @Option(value = "${comment!}查询", menuCode = "${menuCode}", optionCode = "query", optionLog = false)
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody ${entity.name} entity) {
        return super.queryEntity(entity);
    }

</#if>
<#if global.add>
    @Option(value = "${comment!}新增", menuCode = "${menuCode}", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody ${entity.name} entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

</#if>
<#if global.edit>
    @Option(value = "${comment!}更新", menuCode = "${menuCode}", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody ${entity.name} entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

</#if>
<#if global.delete >
    @Override
    @Option(value = "${comment!}删除", menuCode = "${menuCode}", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

</#if>
<#if global.pageList >
    @Override
    @Option(value = "${comment!}分页查询", menuCode = "${menuCode}", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<${entity.name}> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

</#if>
<#if global.list >
    @Override
    @Option(value = "${comment!}列表查询", menuCode = "${menuCode}", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody ${entity.name} entity) {
        return super.listEntity(entity);
    }

</#if>
<#if global.count >
    @Override
    @Option(value = "${comment!}计数", menuCode = "${menuCode}", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody ${entity.name} entity) {
        return super.countEntity(entity);
    }

</#if>
}

