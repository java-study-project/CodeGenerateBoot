/* eslint-disable lines-between-class-members */
import React, { Component } from 'react';
import {
  i18n,
  createALink,
  createConfirm,
<#list fields as item>
<#if item.ComboAble>
<#if item.columnName!=refId && !item.DetailHidden && !item.ChildHidden && !item.OpTimeAble>
  ${item.columnName_TF}Datas,
</#if></#if></#list>
} from '@/components/Format/index';
import DetailTableItemDemo from '@/components/BaseDetailPage/DetailTableItemDemo';

const editTypes = ['add', 'update'];
/**
 * ${tableComment}明细
 * auth: ${auth}  ${crateDate}  add
 */
export default class ${simpleName_class} extends Component {
  // 页面参数
  pageProps = {
    title: i18n('${simpleName}_${tableName_i18n}'),
    buttons: [
      { name: i18n('ADD'), onClick: () => this.detail.handleAdd(), openType: editTypes },
    ],
    fields: [
      <#list fields as item>
      <#if item.columnName!=refId && !item.DetailHidden && !item.ChildHidden && !item.OpTimeAble>
      <#if item.ComboAble>
      {
        type: 'Select',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        dict: '${item.columnName_TF}',
        <#if item.DisableAble>
        disabled: true,
        <#else>
        enabledType: editTypes,
        <#if item.isNullable=='NO'>
        rules: ['required'],
        </#if>
        </#if>
      }, // ${item.columnComment}
      <#elseif item.dataType_java=='Date'>
      {
        type: 'DatePicker',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        <#if item.OpTimeAble>
        disabled: true,
        openType: noAddTypes,
        <#else>
        enabledType: editTypes,
        <#if item.isNullable=='NO'>
        rules: ['required'],
        </#if>
        </#if>
      }, // ${item.columnComment}
      <#elseif item.dataType_isNum>
      {
        type: 'InputNumber',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        <#if item.DisableAble>
        disabled: true,
        <#else>
        enabledType: editTypes,
        <#if item.isNullable=='NO'>
        rules: ['required'],
        </#if>
        </#if>
      }, // ${item.columnComment}
      <#else>
      {
        type: 'Input',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        <#if item.DisableAble>
        disabled: true,
        <#else>
        enabledType: editTypes,
        <#if item.isNullable=='NO'>
        rules: ['max${item.characterMaximumLength}', 'required'],
        <#else>
        rules: ['max${item.characterMaximumLength}'],
        </#if>
        </#if>
      }, // ${item.columnComment}
      </#if>
      </#if>
      </#list>
      {
        label: i18n('OPERATING'),
        isNotDetail: true,
        render: (text, record) => {
          const { openType } = this.props;
          if (editTypes.indexOf(openType) !== -1) {
            const editBut = createALink({
              label: i18n('EDIT'),
              onClick: () => this.detail.handleUpdate(record),
            });
            const delBut = createConfirm({
              label: i18n('DELETE'),
              title: i18n('DELETE_CONFIRM'),
              onConfirm: () => this.detail.handleDelete(record.id),
            });
            return (
              <div>
                {editBut}
                &nbsp;&nbsp;
                {delBut}
              </div>
            );
          }
          return '';
        },
      },
    ],
  };
  render() {
    const dictData = {
      <#list fields as item>
      <#if item.ComboAble>
      <#if item.columnName!=refId && !item.DetailHidden && !item.ChildHidden && !item.OpTimeAble>
      ${item.columnName_TF}: ${item.columnName_TF}Datas,
      </#if></#if></#list>
    };
    return (
      <div>
        <DetailTableItemDemo
          {...this.props}
          {...this.pageProps}
          dictData={dictData}
          ref={detail => {
            this.detail = detail;
          }}
        />
      </div>
    );
  }
}
