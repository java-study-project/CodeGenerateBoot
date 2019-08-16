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
  deletedDatas,
} from '@/components/Format/index';

const authCode = '/rule/';
const authAdd = authCode + 'Add.arz';
const authDelete = authCode + 'Delete.arz';
const authUpdate = authCode + 'Update.arz';
const authShow = authCode + 'Show.arz';

/**
 * 规则定义表列表
 * auth: wulonghuai  2019年08月16日  add
 */
@connect(({ rule, loading }) => ({
  rule,
  loading: loading.global,
  pageSize: 10,
  permission: [authAdd, authDelete, authUpdate, authShow],
}))
class Rule extends PureComponent {
  // 页面参数
  pageProps = {
    title: i18n('rule_Core_Dataauth_Rule_Scf'),
    searchFields: [
      {
        type: 'Select',
        name: 'deleted',
        label: i18n('COLUMN_Deleted'),
        dict: 'deleted',
      }, // 是否删除
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
      {
        dataIndex: 'ruleName',
        title: i18n('rule_Rule_Name'),
      }, // 规则名称
      {
        dataIndex: 'ruleDesc',
        title: i18n('rule_Rule_Desc'),
      }, // 规则描述
      {
        dataIndex: 'opTime',
        title: i18n('COLUMN_Op_Time'),
        format: 'hhMMss',
      }, // 修改时间
      {
        dataIndex: 'opUserName',
        title: i18n('COLUMN_Op_User_Name'),
      }, // 修改用户名
      {
        dataIndex: 'deleted',
        title: i18n('COLUMN_Deleted'),
        dict: 'deleted',
      }, // 是否删除
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
    dispatch({ type: 'rule/query', payload: queryParams });
  };
  // 新增记录
  onAdd = () => {
    push('/app/dataauth/rule/add');
  };
  // 修改记录
  onModify = id => {
    push('/app/dataauth/rule/update/' + id);
  };
  // 查看记录
  onShow = id => {
    push('/app/dataauth/rule/show/' + id);
  };
  // 删除记录
  onDelete = id => {
    const { dispatch } = this.props;
    dispatch({ type: 'rule/del', payload: { id }, callback: this.opSuccess });
  };
  // 批量删除记录
  onBathDelete = ids => {
    const { dispatch } = this.props;
    dispatch({ type: 'rule/dels', payload: { ids }, callback: this.opSuccess });
  };
  // 操作成功则，重新查询数据
  opSuccess = response => {
    responseDispose(response, { success: () => this.list.serach() });
  };
  render() {
    const { rule, pageSize, loading, permission } = this.props;
    const dictData = {
	    deleted: deletedDatas,
	  };
    return (
      <BaseListPage
        ref={list => {
          this.list = list;
        }}
        datas={rule.datas}
        total={rule.total}
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
export default Rule;
