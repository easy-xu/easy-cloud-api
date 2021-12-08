import { FC, useState } from 'react';
import CurdPage, { IFields } from '@/components/CurdPage';
<#if entity.superClassName?? >
import ${entity.superClassName}Page from '@/components/${entity.superClassName}Page';
</#if>
import { useRequest } from 'umi';
import { baseList } from '@/services/base';

const ${page.name}: FC = (props: any) => {

<#list fields as field>
<#if field.tableMapping??>
  const [${field.name}s, set${field.name?cap_first}s] = useState<any>([]);
  const ${field.name}sRequest = useRequest(() => baseList('${field.tableMapping.model}', '${field.tableMapping.code}', {}), {
      onSuccess: (data) => {
        let ${field.name}s = data.map((item: any) => {
          return { code: item.${field.tableMapping.codeField}, name: item.${field.tableMapping.nameField} };
        });
        set${field.name?cap_first}s(${field.name}s);
      }});
</#if>
</#list>

  const fields: IFields = [
    <#list fields as field>
    {
      subPage: '${field.subPage}',
      name: '${field.comment}',
      code: '${field.name}',
      type: '${field.pageType}',
      <#if field.initial??>
      initial: ${field.initial},
      </#if>
      <#if field.pageType == "select">
        <#if field.tableMapping??>
      select: ${field.name}s,
        </#if>
        <#if field.codeMapping??>
      select: [
        <#list field.codeMapping?keys as key>
        { code: '${key}', name: '${field.codeMapping["${key}"]}' },
        </#list>
      ],
        </#if>
      </#if>
      <#if field.style??>
      style: ${field.style},
      </#if>
      <#if field.rules??>
      rules: [
        <#list field.rules as rule>
        ${rule},
        </#list>
      ]
      </#if>
    },
    </#list>
  ];

  return (
    <#if entity.superClassName?? >
    <${entity.superClassName}Page
    <#else>
    <CurdPage
    </#if>
        model="${model}"
        entity="${code}"
        pageTitle="${comment!}页面"
        fields={fields}
        option={[${global.add?string('"add",', '')} ${global.edit?string('"edit",', '')} ${global.delete?string('"delete",', '')}]}
    />
  );
};
// @ts-ignore
${page.name}.title = '${comment!}页面';
export default ${page.name};