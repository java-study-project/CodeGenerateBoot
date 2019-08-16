import * as service from '@/services/rule';
import uuid from 'node-uuid';

export default {
  namespace: 'rule',
  state: {
    tokenId: uuid.v4(),
    datas: [],
    total: 0,
    ruleData: { deleted: 0, state: 'normal' },
    ruleMapperList: [],
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
      const stateData = yield select(state => state.rule);
      Object.assign(stateData.ruleData, formData);
      const saveData = {
        tokenId: stateData.tokenId, // tokenId，防止重复提交
        type: saveType, // 保存类型
        ruleData: stateData.ruleData, // 表头数据
        ruleMapperList: stateData.ruleMapperList, // 规则数据权限Mapper明细表明细数据
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
        ruleData: response.resultMap.ruleData,
    ruleMapperList: response.resultMap.ruleMapperList,
      };
    },
    clearData() {
      return {
        tokenId: uuid.v4(),
        datas: [],
        total: 0,
        ruleData: { deleted: 0, state: 'normal' },
        ruleMapperList: [],
      };
    },
    updateLocalData(state, { payload }) {
      const {
        formData,
        ruleData,
        ruleMapperList,
      } = payload;
      const json = {};
      if (ruleData) {
        const ruleDataNow = state.ruleData;
        if (formData) {
          Object.assign(ruleDataNow, formData, ruleData);
        } else {
          Object.assign(ruleDataNow, ruleData);
        }
        json.ruleData = ruleDataNow;
      } else {
        const ruleDataNow = state.ruleData;
        Object.assign(ruleDataNow, formData);
        json.ruleData = ruleDataNow;
      }
      if (ruleMapperList) {
        json.ruleMapperList = ruleMapperList;
      }
      return { ...state, ...json };
    },
  },
};
