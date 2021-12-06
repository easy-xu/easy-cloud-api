package ${controller.pkg};

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${entity.pkg}.${entity.name};
import ${service.pkg}.${service.name};
<#list controller.importPackages as pkg>
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
public class ${controller.name} extends ${controller.superClassName}<${entity.name}, ${service.name}> {

    @Autowired
    public ${controller.name}(${service.name} service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody ${entity.name} entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("${comment!}保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody ${entity.name} entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("${comment!}删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody ${entity.name} entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<${entity.name}> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody ${entity.name} entity) {
        return super.listEntity(entity);
    }

}

