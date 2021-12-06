package ${mapper.pkg};

import ${entity.pkg}.${entity.name};
import org.apache.ibatis.annotations.Mapper;
<#list mapper.importPackages as pkg>
import ${pkg};
</#list>

/**
 * ${comment!}查询类
 *
 * @author ${global.author}
 * @since ${global.since}
 */
@Mapper
public interface ${mapper.name} extends ${mapper.superClassName}<${entity.name}> {

}
