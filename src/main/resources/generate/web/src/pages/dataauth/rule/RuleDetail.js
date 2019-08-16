/* eslint-disable lines-between-class-members */
import React, { Component } from 'react';
import { goBack } from 'umi/router';
import { connect } from 'dva';
import {
  i18n,
  responseDispose,
  getKeyByRouter,
  deletedDatas,
} from '@/components/Format/index';
import BaseDetailPage from '@/components/BaseDetailPage';
import RuleMapper from './RuleMapper';

const editTypes = ['add', 'update'];
const noAddTypes = ['update', 'show'];

/**
 * 规则定义表详情
 * auth: wulonghuai  2019年08月16日  add
 */
@connect(({ rule, loading }) => ({
  rule,
  loading: loading.global,
}))
class RuleDetail extends Component {
  // 页面参数
  pageProps = {
    title: i18n('rule_Core_Dataauth_Rule_Scf'),
    dataFields: [
      {
        type: 'Input',
        name: 'ruleName',
        label: i18n('rule_Rule_Name'),
        enabledType: editTypes,
        rules: ['max32'],
      }, // 规则名称
      {
        type: 'Input',
        name: 'ruleDesc',
        label: i18n('rule_Rule_Desc'),
        enabledType: editTypes,
        rules: ['max32'],
      }, // 规则描述
      {
        type: 'Select',
        name: 'deleted',
        label: i18n('COLUMN_Deleted'),
        dict: 'deleted',
        disabled: true,
      }, // 是否删除
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
    this.dispatchModels('rule/clearData');
    // 编辑页面，初始化加载合同详情数据
    const ruleId = getKeyByRouter(this.props, 'ruleId');
    if (ruleId) {
      this.dispatchModels('rule/getDetail', { id: ruleId });
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
      this.dispatchModels('rule/saveData', payload, this.opSuccess);
    });
  };
  // 修改本地数据
  updateLocalData = (params = {}) => {
    const formData = this.detail.getFieldsValue();
    this.dispatchModels('rule/updateLocalData', { ...params, formData });
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
    const { rule } = this.props;
    const dictData = {
      deleted: deletedDatas,
    };
    return (
      <BaseDetailPage
        ref={detail => {
          this.detail = detail;
        }}
        colSpan={24}
        data={rule.ruleData}
        openType={openType}
        dictData={dictData}
        {...this.pageProps}
      >
        <RuleMapper
          title={i18n('ruleMapper_Core_Dataauth_Rule_Mapper_Scf')}
          dataName="ruleMapperList"
          dataList={rule.ruleMapperList}
        />
      </BaseDetailPage>
    );
  }
}
export default RuleDetail;
