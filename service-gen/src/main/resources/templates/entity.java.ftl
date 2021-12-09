package ${entity.pkg};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
<#if global.swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
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
<#if global.swagger >
@ApiModel(value = "${entity.name}", description = "${comment!}实体类")
</#if>
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
    <#if global.swagger >
    @ApiModelProperty("${field.comment}")
    </#if>
    <#if field.extend >
    @TableField(exist = false)
    </#if>
    private ${field.type} ${field.name};
</#list>

}
