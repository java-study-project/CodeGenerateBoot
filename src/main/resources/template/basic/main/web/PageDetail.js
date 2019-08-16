/* eslint-disable lines-between-class-members */
import React, { Component } from 'react';
import { goBack } from 'umi/router';
import { connect } from 'dva';
import {
  i18n,
  responseDispose,
  getKeyByRouter,
<#list fields as item><#if item.ComboAble>
  ${item.columnName_TF}Datas,
</#if></#list>
} from '@/components/Format/index';
import BaseDetailPage from '@/components/BaseDetailPage';
<#list details as detail>
import ${detail.simpleName_class} from './${detail.simpleName_class}';
</#list>

const editTypes = ['add', 'update'];
const noAddTypes = ['update', 'show'];

/**
 * ${tableComment}详情
 * auth: ${auth}  ${crateDate}  add
 */
@connect(({ ${simpleName_LXX}, loading }) => ({
  ${simpleName_LXX},
  loading: loading.global,
}))
class ${simpleName_class}Detail extends Component {
  // 页面参数
  pageProps = {
    title: i18n('${simpleName}_${tableName_i18n}'),
    dataFields: [
      <#list fields as item>
      <#if !item.DetailHidden>
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
    ],
    buttons: [
      { name: i18n('SAVE'), onClick: () => this.onSave(), openType: editTypes },
      { name: i18n('RETURN'), type: 'defalut', onClick: () => this.onBack() },
    ],
  };
  // 页面初始化
  constructor(props) {
    super(props);
    const openType = getKeyByRouter(props, 'openType', 'add');
    this.state = { openType };
  }
  // 加载完成初始化数据
  componentDidMount() {
    // 清除redux中本地数据
    this.dispatchModels('${simpleName_LXX}/clearData');
    // 编辑页面，初始化加载合同详情数据
    const ${simpleName_TF}Id = getKeyByRouter(this.props, '${simpleName_TF}Id');
    if (${simpleName_TF}Id) {
      this.dispatchModels('${simpleName_LXX}/getDetail', { id: ${simpleName_TF}Id });
    }
  }
  // 返回列表
  onBack = () => {
    goBack();
  };
  // 保存详情,调用后台
  onSave = (saveType = 'save') => {
    this.detail.validate(formData => {
      const { openType } = this.state;
      const payload = { openType, saveType, formData };
      this.dispatchModels('${simpleName_LXX}/saveData', payload, this.opSuccess);
    });
  };
  // 修改本地数据
  updateLocalData = (params = {}) => {
    const formData = this.detail.getFieldsValue();
    this.dispatchModels('${simpleName_LXX}/updateLocalData', { ...params, formData });
  };
  // 操作成功则，跳转到列表页面
  opSuccess = response => {
    responseDispose(response, {
      success: () => {
        this.onBack();
      },
    });
  };
  // 转发到models
  dispatchModels(type, payload, callback) {
    const { dispatch } = this.props;
    dispatch({ type, payload, callback });
  }

  render() {
    const { openType } = this.state;
    const { ${simpleName_LXX} } = this.props;
    const dictData = {
      <#list fields as item>
      <#if item.ComboAble>
      ${item.columnName_TF}: ${item.columnName_TF}Datas,
      </#if>
      </#list>
    };
    return (
      <BaseDetailPage
        ref={detail => {
          this.detail = detail;
        }}
        colSpan={24}
        data={${simpleName_LXX}.${simpleName_TF}Data}
        openType={openType}
        dictData={dictData}
        {...this.pageProps}
      >
        <#list details as detail>
        <${detail.simpleName_class}
          title={i18n('${detail.simpleName}_${detail.tableName_i18n}')}
          dataName="${detail.simpleName_TF}List"
          dataList={${simpleName_LXX}.${detail.simpleName_TF}List}
        />
        </#list>
      </BaseDetailPage>
    );
  }
}
export default ${simpleName_class}Detail;
