package ${entity.pkg};

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
<#if entity.superClassName?? >
import lombok.EqualsAndHashCode;
</#if>
<#list entity.importPackages as pkg>
import ${pkg};
</#list>

/**
 * ${comment!}实体类
 *
 * @author ${global.author}
 * @since ${global.since}
 */
@Data
@TableName("${table.name}")
@ApiModel(value = "${entity.name}对象", description = "${table.name}")
<#if entity.superClassName?? >
@EqualsAndHashCode(callSuper = true)
public class ${entity.name} extends ${entity.superClassName} {
<#else>
public class ${entity.name} {
</#if>

    private static final long serialVersionUID = 1L;

<#list fields as field>
    /**
     * ${field.comment}
     */
    private ${field.type} ${field.name};
</#list>

}
