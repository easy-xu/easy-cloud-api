package ${serviceImpl.pkg};

import ${entity.pkg}.${entity.name};
import ${mapper.pkg}.${mapper.name};
import ${service.pkg}.${service.name};
import org.springframework.stereotype.Service;
<#list serviceImpl.importPkg as pkg>
import ${pkg};
</#list>

/**
 * ${comment!}业务实现类
 *
 * @author ${global.author}
 * @since ${global.since}
 */
@Service
public class ${serviceImpl.name} extends ${serviceImpl.superClassName}<${mapper.name}, ${entity.name}> implements ${service.name} {

}

