package ${service.pkg};

import ${entity.pkg}.${entity.name};
<#list service.importPackages as pkg>
import ${pkg};
</#list>

/**
 * ${comment!}业务接口
 *
 * @author ${global.author}
 * @since ${global.since}
 */
public interface ${service.name} extends ${service.superClassName}<${entity.name}> {

}

