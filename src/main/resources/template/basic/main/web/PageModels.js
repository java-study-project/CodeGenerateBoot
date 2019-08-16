import * as service from '@/services/${simpleName_LXX}';
import uuid from 'node-uuid';

export default {
  namespace: '${simpleName_LXX}',
  state: {
    tokenId: uuid.v4(),
    datas: [],
    total: 0,
    ${simpleName_TF}Data: { deleted: 0, state: 'normal' },
<#list details as detail>
    ${detail.simpleName_TF}List: [],
</#list>
  },

  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(service.query, payload);
      yield put({ type: '_queryList', response });
    },
    *getDetail({ payload }, { call, put }) {
      const response = yield call(service.getDetail, payload.id);
      yield put({ type: '_getDetail', response });
    },
    *del({ payload, callback }, { call }) {
      const response = yield call(service.deleteData, payload.id);
      if (callback) callback(response);
    },
    *dels({ payload, callback }, { call }) {
      const response = yield call(service.bathDeleteDatas, payload.ids);
      if (callback) callback(response);
    },
    *saveData({ payload, callback }, { call, select }) {
      const { openType, saveType, formData } = payload;
      const stateData = yield select(state => state.${simpleName_LXX});
      Object.assign(stateData.${simpleName_TF}Data, formData);
      const saveData = {
        tokenId: stateData.tokenId, // tokenId，防止重复提交
        type: saveType, // 保存类型
        ${simpleName_TF}Data: stateData.${simpleName_TF}Data, // 表头数据
<#list details as detail>
        ${detail.simpleName_TF}List: stateData.${detail.simpleName_TF}List, // ${detail.tableComment}明细数据
</#list>
      };
      if (openType === 'add') {
        const response = yield call(service.addData, saveData);
        if (callback) callback(response);
      } else {
        const response = yield call(service.updateData, saveData);
        if (callback) callback(response);
      }
    },
  },

  reducers: {
    _queryList(state, { response }) {
      return { ...state, datas: response.list, total: response.total };
    },
    _getDetail(state, { response }) {
      return {
        ...state,
        ${simpleName_TF}Data: response.resultMap.${simpleName_TF}Data,
<#list details as detail>
    ${detail.simpleName_TF}List: response.resultMap.${detail.simpleName_TF}List,
</#list>
      };
    },
    clearData() {
      return {
        tokenId: uuid.v4(),
        datas: [],
        total: 0,
        ${simpleName_TF}Data: { deleted: 0, state: 'normal' },
<#list details as detail>
        ${detail.simpleName_TF}List: [],
</#list>
      };
    },
    updateLocalData(state, { payload }) {
      const {
        formData,
        ${simpleName_TF}Data,
<#list details as detail>
        ${detail.simpleName_TF}List,
</#list>
      } = payload;
      const json = {};
      if (${simpleName_TF}Data) {
        const ${simpleName_TF}DataNow = state.${simpleName_TF}Data;
        if (formData) {
          Object.assign(${simpleName_TF}DataNow, formData, ${simpleName_TF}Data);
        } else {
          Object.assign(${simpleName_TF}DataNow, ${simpleName_TF}Data);
        }
        json.${simpleName_TF}Data = ${simpleName_TF}DataNow;
      } else {
        const ${simpleName_TF}DataNow = state.${simpleName_TF}Data;
        Object.assign(${simpleName_TF}DataNow, formData);
        json.${simpleName_TF}Data = ${simpleName_TF}DataNow;
      }
<#list details as detail>
      if (${detail.simpleName_TF}List) {
        json.${detail.simpleName_TF}List = ${detail.simpleName_TF}List;
      }
</#list>
      return { ...state, ...json };
    },
  },
};
