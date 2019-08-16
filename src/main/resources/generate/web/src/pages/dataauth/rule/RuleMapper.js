/* eslint-disable lines-between-class-members */
import React, { Component } from 'react';
import {
  i18n,
  createALink,
  createConfirm,
} from '@/components/Format/index';
import DetailTableItemDemo from '@/components/BaseDetailPage/DetailTableItemDemo';

const editTypes = ['add', 'update'];
/**
 * 规则数据权限Mapper明细表明细
 * auth: wulonghuai  2019年08月16日  add
 */
export default class RuleMapper extends Component {
  // 页面参数
  pageProps = {
    title: i18n('ruleMapper_Core_Dataauth_Rule_Mapper_Scf'),
    buttons: [
      { name: i18n('ADD'), onClick: () => this.detail.handleAdd(), openType: editTypes },
    ],
    fields: [
      {
        type: 'Input',
        name: 'ruleName',
        label: i18n('ruleMapper_Rule_Name'),
        enabledType: editTypes,
        rules: ['max32'],
      }, // 规则名称
      {
        type: 'Input',
        name: 'dimensionTypeId',
        label: i18n('ruleMapper_Dimension_Type_Id'),
        enabledType: editTypes,
        rules: ['max64'],
      }, // 维度类别id
      {
        type: 'Input',
        name: 'dimensionType',
        label: i18n('ruleMapper_Dimension_Type'),
        enabledType: editTypes,
        rules: ['max64'],
      }, // 维度类别
      {
        type: 'Input',
        name: 'dimensionTypeDesc',
        label: i18n('ruleMapper_Dimension_Type_Desc'),
        enabledType: editTypes,
        rules: ['max64'],
      }, // 维度描述
      {
        type: 'Input',
        name: 'tablePrefix',
        label: i18n('ruleMapper_Table_Prefix'),
        enabledType: editTypes,
        rules: ['max64'],
      }, // 表名前缀
      {
        type: 'Input',
        name: 'goalFieldNo',
        label: i18n('ruleMapper_Goal_Field_No'),
        enabledType: editTypes,
        rules: ['max32'],
      }, // 目标字段名称
      {
        type: 'Input',
        name: 'sourceFieldNo',
        label: i18n('ruleMapper_Source_Field_No'),
        enabledType: editTypes,
        rules: ['max32'],
      }, // 来源字段名称
      {
        type: 'InputNumber',
        name: 'isEnable',
        label: i18n('ruleMapper_Is_Enable'),
        enabledType: editTypes,
      }, // 是否启用
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
