import { FC, useState } from 'react';
import CurdPage, { IFields } from '@/components/CurdPage';
import { useRequest } from 'umi';
import { baseList } from '@/services/base';

const ${page.name}: FC = (props: any) => {

<#list fields as field>
<#if field.tableMapping??>
  const [${field.name}s, set${field.name?cap_first}s] = useState<any>([]);
  const ${field.name}sRequest = useRequest(
    () => baseList('${field.tableMapping.model}', '${field.tableMapping.code}', {}),
    {
      onSuccess: (data) => {
        let ${field.name}s = data.map((item: any) => {
          return { code: item.${field.tableMapping.codeField}, name: item.${field.tableMapping.nameField} };
        });
        set${field.name?cap_first}s(${field.name}s);
      },
    },
  );
</#if>
</#list>

  const fields: IFields = [
    {
      name: '主键',
      code: 'id',
      type: 'number',
      style: {
        search: { display: false },
        table: { display: false },
        add: { hidden: true },
        edit: { hidden: true },
      },
    },
    <#list fields as field>
    {
      name: '${field.comment}',
      code: '${field.name}',
      type: '${field.pageType}',
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
      style: {
        search: { display: ${field.search?string('true', 'false')} },
      },
      rules: [
        <#list field.rules as rule>
        ${rule},
        </#list>
      ]
    },
    </#list>
  ];

  return (
    <CurdPage
        model="${model}"
        entity="${code}"
        pageTitle="${comment!}页面"
        fields={fields}
        isAuthData={${isAuthData?c}} />
  );
};
// @ts-ignore
${page.name}.title = '${comment!}页面';
export default ${page.name};