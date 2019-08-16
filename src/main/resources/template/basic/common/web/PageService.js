import request from '@/utils/request';

// 获取资助人详情
export async function getDetail(id) {
  return request('/api/${tableName_LXX}/getDetail', {
    method: 'POST',
    body: { id },
  });
}
// 新增
export async function addData(saveData) {
  return request('/api/${tableName_LXX}/add', {
    method: 'POST',
    body: {
      tokenId: saveData.tokenId, // tokenId，防止重复提交
      saveData: JSON.stringify(saveData),
    },
  });
}
// 修改
export async function updateData(saveData) {
  return request('/api/${tableName_LXX}/update', {
    method: 'POST',
    body: {
      tokenId: saveData.tokenId, // tokenId，防止重复提交
      saveData: JSON.stringify(saveData),
    },
  });
}
// 删除
export async function deleteData(id) {
  return request('/api/${tableName_LXX}/dels', {
    method: 'POST',
    body: { ids: JSON.stringify([id]) },
  });
}
// 批量删除
export async function bathDeleteDatas(ids) {
  return request('/api/${tableName_LXX}/dels', {
    method: 'POST',
    body: { ids: JSON.stringify(ids) },
  });
}
// 查询
export async function query(params) {
  return request('/api/${tableName_LXX}/query', {
    method: 'POST',
    body: params,
  });
}
