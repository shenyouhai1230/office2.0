import request from '@/utils/request'

// 查询开发任务表列表
export function listTaskdetailed(query) {
  return request({
    url: '/jobtask/taskdetailed/list',
    method: 'get',
    params: query
  })
}

// 查询开发任务表详细
export function getTaskdetailed(xqid) {
  return request({
    url: '/jobtask/taskdetailed/' + xqid,
    method: 'get'
  })
}

// 新增开发任务表
export function addTaskdetailed(data) {
  return request({
    url: '/jobtask/taskdetailed',
    method: 'post',
    data: data
  })
}

// 修改开发任务表
export function updateTaskdetailed(data) {
  return request({
    url: '/jobtask/taskdetailed',
    method: 'put',
    data: data
  })
}

// 删除开发任务表
export function delTaskdetailed(xqid) {
  return request({
    url: '/jobtask/taskdetailed/' + xqid,
    method: 'delete'
  })
}

// 导出开发任务表
export function exportTaskdetailed(query) {
  return request({
    url: '/jobtask/taskdetailed/export',
    method: 'get',
    params: query
  })
}
// 查询对应开发人员的开发任务总数
export function getQdnameCount(state1,state2) {
  return request({
    url: '/jobtask/taskdetailed/qdnameCount?state1='+state1+'&state2='+state2,
    method: 'get',
    params: state1,state2
  })
}