package ${entity.pkg};

import com.baomidou.mybatisplus.annotation.TableName;
<#if global.swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Data;
<#if entity.superClassName?? >
import lombok.EqualsAndHashCode;
</#if>
<#list entity.importPkg as pkg>
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
<#list fields as field>
  <#if field.entityRules??>
    <#list field.entityRules as rule>
      <#if rule?starts_with("@UniqueField") >
${rule}
      </#if>
    </#list>
  </#if>
</#list>
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
    <#if field.entityRules??>
      <#list field.entityRules as rule>
        <#if !rule?starts_with("@UniqueField") >
    ${rule}
      </#if>
        </#list>
    </#if>
    <#if global.swagger >
    @ApiModelProperty("${field.comment}")
    </#if>
    <#if field.extend >
    @TableField(exist = false)
    </#if>
    private ${field.type} ${field.name};
</#list>

}
