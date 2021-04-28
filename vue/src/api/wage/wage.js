import request from '../../utils/request'

// 添加或更新工资条信息
export function addOrUpdateWageOrder(data) {
  return request({
    url: '/wage/addOrUpdateWageOrder',
    method: 'post',
    data
  })
}

// 删除工资条信息
export function deleteWageOrder(id) {
  return request({
    url: '/wage/deleteWageOrder/' + id,
    method: 'get'
  })
}

// 获取所有工资条信息
export function getAllWageOrder() {
  return request({
    url: '/wage/getAllWageOrder',
    method: 'get'
  })
}

