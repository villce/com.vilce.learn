import request from '../../utils/request'

// 编辑用户信息
export function editUser(data) {
  return request({
    url: '/user/editUser',
    method: 'post',
    data
  })
}

// 根据用户ID获取用户信息
export function findUserById(userId) {
  return request({
    url: '/user/findUser/' + userId,
    method: 'get',
  })
}

// 获取所有用户
export function getAllUsers() {
  return request({
    url: '/user/getAllUsers',
    method: 'get',
  })
}

// 重置用户密码
export function resetPassword(data) {
  return request({
    url: '/user/resetPassword',
    method: 'post',
    data
  })
}

// 更新用户状态
export function updateUserStatus(data) {
  return request({
    url: '/user/updateUserStatus',
    method: 'post',
    data
  })
}
