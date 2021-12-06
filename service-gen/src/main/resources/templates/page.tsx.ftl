import { FC, useState } from 'react';
import CurdPage, { IFields } from '@/components/CurdPage';

const ${page.name}: FC = (props: any) => {
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
      select: [
        <#list field.codeMapping?keys as key>
        { code: '${key}', name: '${field.codeMapping["${key}"]}' },
        </#list>
      ],
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