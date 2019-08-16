import request from '@/utils/request';

// 获取资助人详情
export async function getDetail(id) {
  return request('/api/coredataauthrulescf/getDetail', {
    method: 'POST',
    body: { id },
  });
}
// 新增
export async function addData(saveData) {
  return request('/api/coredataauthrulescf/add', {
    method: 'POST',
    body: {
      tokenId: saveData.tokenId, // tokenId，防止重复提交
      saveData: JSON.stringify(saveData),
    },
  });
}
// 修改
export async function updateData(saveData) {
  return request('/api/coredataauthrulescf/update', {
    method: 'POST',
    body: {
      tokenId: saveData.tokenId, // tokenId，防止重复提交
      saveData: JSON.stringify(saveData),
    },
  });
}
// 删除
export async function deleteData(id) {
  return request('/api/coredataauthrulescf/dels', {
    method: 'POST',
    body: { ids: JSON.stringify([id]) },
  });
}
// 批量删除
export async function bathDeleteDatas(ids) {
  return request('/api/coredataauthrulescf/dels', {
    method: 'POST',
    body: { ids: JSON.stringify(ids) },
  });
}
// 查询
export async function query(params) {
  return request('/api/coredataauthrulescf/query', {
    method: 'POST',
    body: params,
  });
}
