import request from '@/utils/request'

// 查询任务总数列表
export function listTaskbasic(query) {
  return request({
    url: '/jobtask/taskbasic/list',
    method: 'get',
    params: query
  })
}

// 查询任务总数详细
export function getTaskbasic(id) {
  return request({
    url: '/jobtask/taskbasic/' + id,
    method: 'get'
  })
}

// 新增任务总数
export function addTaskbasic(data) {
  return request({
    url: '/jobtask/taskbasic',
    method: 'post',
    data: data
  })
}

// 修改任务总数
export function updateTaskbasic(data) {
  return request({
    url: '/jobtask/taskbasic',
    method: 'put',
    data: data
  })
}

// 删除任务总数
export function delTaskbasic(id) {
  return request({
    url: '/jobtask/taskbasic/' + id,
    method: 'delete'
  })
}

// 导出任务总数
export function exportTaskbasic(query) {
  return request({
    url: '/jobtask/taskbasic/export',
    method: 'get',
    params: query
  })
}