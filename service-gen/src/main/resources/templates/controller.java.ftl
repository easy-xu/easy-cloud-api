package ${package.Controller};

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/api/${package.ModuleName}/${controllerMappingHyphen?replace('${package.ModuleName}-','')?replace('-','')}")
public class ${table.controllerName} extends BaseController<${table.entityName}, ${table.serviceName}> {

    @Autowired
    public ${table.controllerName}(${table.serviceName} service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody ${table.entityName} entity) {
        return super.queryEntity(entity);
    }

    @Override
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody ${table.entityName} entity) {
        return super.saveEntity(entity);
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody ${table.entityName} entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<${table.entityName}> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody ${table.entityName} entity) {
        return super.listEntity(entity);
    }

}

