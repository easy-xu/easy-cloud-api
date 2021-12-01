import { FC, useState } from 'react';
import CurdPage, { IFields } from '@/components/CurdPage';

const ${entity}: FC = (props: any) => {
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

    <#list table.fields as field>
    {
      name: '${field.comment}',
      code: '${field.propertyName}',
      <#if field.propertyType == 'String'>
      type: 'string',
      </#if>
      <#if field.propertyType == 'Long'>
      type: 'number',
      </#if>
      style: {
          search: { display: false },
      },
    },
    </#list>

  ];

  return (
    <CurdPage
        model="${package.ModuleName}"
        entity="${controllerMappingHyphen?replace('${package.ModuleName}-','')?replace('-','')}"
        pageTitle="${table.comment!?replace('表','页面')}"
        fields={fields}
        isAuthData={${isAuthData?c}} />
  );
};
// @ts-ignore
Option.title = '${table.comment!?replace('表','页面')}';
export default ${entity};