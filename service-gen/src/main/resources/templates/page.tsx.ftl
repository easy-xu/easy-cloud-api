import { FC, useState } from 'react';
import CurdPage, { IFields } from '@/components/CurdPage';
<#if entity.superClassName?? >
import ${entity.superClassName}Page from '@/components/${entity.superClassName}Page';
</#if>
import { useRequest } from 'umi';
import { baseList, baseTree } from '@/services/base';
import { toTreeData, toListData } from '@/utils/baseUtil';

const ${page.name}: FC = (props: any) => {

<#list fields as field>
<#if field.tableMapping?? && field.pageType !='tree'>
  const [${field.name}, set${field.name?cap_first}] = useState<any>([]);
  const ${field.name}Request = useRequest(() => baseList('${field.tableMapping.model}', '${field.tableMapping.code}', {}), {
      onSuccess: (data) => {
          set${field.name?cap_first}(toListData(data, '${field.tableMapping.codeField}', '${field.tableMapping.nameField}'));
      }});
</#if>
<#if field.tableMapping?? && field.pageType =='tree'>
  const [${field.name}, set${field.name?cap_first}] = useState<any>([]);
  const ${field.name}Request = useRequest(() => baseTree('${field.tableMapping.model}', '${field.tableMapping.code}', {}), {
      onSuccess: (data) => {
          set${field.name?cap_first}(toTreeData(data, '${field.tableMapping.codeField}', '${field.tableMapping.nameField}', 'children'));
      }});
</#if>
</#list>

  const fields: IFields = [
    <#list fields as field>
    {
      <#if field.subPage??>
      subPage: '${field.subPage}',
      </#if>
      name: '${field.comment}',
      code: '${field.name}',
      type: '${field.pageType}',
      <#if field.initial??>
      initial: ${field.initial},
      </#if>
      <#if field.pageType == "select" || field.pageType == "tree" || field.pageType == "checks">
        <#if field.tableMapping??>
      ${field.pageType}: ${field.name},
        </#if>
        <#if field.codeMapping??>
      ${field.pageType}: [
        <#list field.codeMapping?keys as key>
        { code: '${key}', name: '${field.codeMapping["${key}"]}' },
        </#list>
      ],
        </#if>
      </#if>
      <#if field.style??>
      style: ${field.style},
      </#if>
      <#if field.pageRules??>
      rules: [
        <#list field.pageRules as rule>
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