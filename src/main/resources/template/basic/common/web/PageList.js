/* eslint-disable lines-between-class-members */
import React, { PureComponent } from 'react';
import { push } from 'umi/router';
import { connect } from 'dva';
import BaseListPage from '@/components/BaseListPage';
import {
  i18n,
  responseDispose,
  createALink,
  createConfirm,
<#list fields as item><#if item.ComboAble>
  ${item.columnName_TF}Datas,
</#if></#list>
} from '@/components/Format/index';

const authCode = '/${simpleName_TF}/';
const authAdd = authCode + 'Add.arz';
const authDelete = authCode + 'Delete.arz';
const authUpdate = authCode + 'Update.arz';
const authShow = authCode + 'Show.arz';

/**
 * ${tableComment}列表
 * auth: ${auth}  ${crateDate}  add
 */
@connect(({ ${simpleName_LXX}, loading }) => ({
  ${simpleName_LXX},
  loading: loading.global,
  pageSize: 10,
  permission: [authAdd, authDelete, authUpdate, authShow],
}))
class ${simpleName_class} extends PureComponent {
  // 页面参数
  pageProps = {
    title: i18n('${simpleName}_${tableName_i18n}'),
    searchFields: [
<#list fields as item>
<#if item.QueryAble>
	<#if item.ComboAble>
      {
        type: 'Select',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        dict: '${item.columnName_TF}',
      }, // ${item.columnComment}
	<#elseif item.dataType_java=='Date'>
      <#if item.QueryAbleType=="range">
      {
        type: 'DatePicker',
        name: '${item.columnName_TF}Before',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}') + '开始',
      }, // ${item.columnComment}开始
      {
        type: 'DatePicker',
        name: '${item.columnName_TF}After',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}') + '结束',
      }, // ${item.columnComment}结束
      <#else>
      {
        type: 'DatePicker',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
      }, // ${item.columnComment}
      </#if>
	<#else> 
      {
        type: 'Input',
        name: '${item.columnName_TF}',
        label: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
      }, // ${item.columnComment}
	</#if>
</#if>
</#list>
    ],
    buttons: [
      { name: i18n('ADD'), authUrl: authAdd, onClick: () => this.onAdd() },
      {
        name: i18n('DELETE'),
        authUrl: authDelete,
        onClick: ({ selectedRowKeys }) => this.onBathDelete(selectedRowKeys),
        checkDisabled: ({ selectedRowKeys }) => selectedRowKeys.length === 0,
      },
    ],
    tableColumns: [
<#list fields as item>
<#if !item.ListHidden>
<#if item.ComboAble>
      {
        dataIndex: '${item.columnName_TF}',
        title: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        dict: '${item.columnName_TF}',
      }, // ${item.columnComment}
<#elseif item.dataType_java=='Date'>
      {
        dataIndex: '${item.columnName_TF}',
        title: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
        format: <#if item.dataType=='DATE'>'yyyyMMdd'<#else>'hhMMss'</#if>,
      }, // ${item.columnComment}
<#else>
      {
        dataIndex: '${item.columnName_TF}',
        title: i18n('<#if item.i18NCommonAble>${i18nCloumnKey}<#else>${simpleName}</#if>_${item.columnName_i18n}'),
      }, // ${item.columnComment}
</#if>
</#if>
</#list>
      {
        title: i18n('OPERATING'),
        render: (text, record) => {
          const { permission } = this.props;
          const showBut = createALink(
            { label: i18n('SHOW'), onClick: () => this.onShow(record.id) },
            permission,
            authShow
          );
          const editBut = createALink(
            { label: i18n('EDIT'), onClick: () => this.onModify(record.id) },
            permission,
            authUpdate
          );
          const delBut = createConfirm(
            {
              title: i18n('DELETE_CONFIRM'),
              label: i18n('DELETE'),
              onConfirm: () => this.onDelete(record.id),
            },
            permission,
            authDelete
          );
          if (record.deleted === 1) {
            return <div>{showBut}</div>;
          }
          return (
            <div>
              {showBut} &nbsp;&nbsp;
              {editBut} &nbsp;&nbsp;
              {delBut}
            </div>
          );
        },
      },
    ],
  };
  // 加载完成初始化数据
  componentDidMount() {
    this.fetch();
  }
  // 查询数据
  fetch = (params = {}) => {
    const { dispatch, pageSize } = this.props;
    const queryParams = Object.assign({ limit: pageSize }, params);
<#list fields as item>
<#if item.QueryAble && item.dataType_java=='Date'>
<#if item.QueryAbleType=="range">
    if (queryParams.${item.columnName_TF}Before) queryParams.${item.columnName_TF}Before = queryParams.${item.columnName_TF}Before.format('YYYY-MM-DD');
    if (queryParams.${item.columnName_TF}After) queryParams.${item.columnName_TF}After = queryParams.${item.columnName_TF}After.format('YYYY-MM-DD');
<#else>
    if (queryParams.${item.columnName_TF}) queryParams.${item.columnName_TF} = queryParams.${item.columnName_TF}.format('YYYY-MM-DD');
</#if>
</#if>
</#list>
    dispatch({ type: '${simpleName_LXX}/query', payload: queryParams });
  };
  // 新增记录
  onAdd = () => {
    push('/app/${module}/${simpleName_LXX}/add');
  };
  // 修改记录
  onModify = id => {
    push('/app/${module}/${simpleName_LXX}/update/' + id);
  };
  // 查看记录
  onShow = id => {
    push('/app/${module}/${simpleName_LXX}/show/' + id);
  };
  // 删除记录
  onDelete = id => {
    const { dispatch } = this.props;
    dispatch({ type: '${simpleName_LXX}/del', payload: { id }, callback: this.opSuccess });
  };
  // 批量删除记录
  onBathDelete = ids => {
    const { dispatch } = this.props;
    dispatch({ type: '${simpleName_LXX}/dels', payload: { ids }, callback: this.opSuccess });
  };
  // 操作成功则，重新查询数据
  opSuccess = response => {
    responseDispose(response, { success: () => this.list.serach() });
  };
  render() {
    const { ${simpleName_LXX}, pageSize, loading, permission } = this.props;
    const dictData = {
      <#list fields as item>
      <#if item.ComboAble>
	    ${item.columnName_TF}: ${item.columnName_TF}Datas,
      </#if>
      </#list>
	  };
    return (
      <BaseListPage
        ref={list => {
          this.list = list;
        }}
        datas={${simpleName_LXX}.datas}
        total={${simpleName_LXX}.total}
        pageSize={pageSize}
        loading={loading}
        fetch={this.fetch}
        dictData={dictData}
        permission={permission}
        {...this.pageProps}
      />
    );
  }
}
export default ${simpleName_class};
