import {vilceServiceApi} from '@/utils/request'

// 编辑用户信息
export function editUser(data) {
  return vilceServiceApi({
    url: '/user/editUser',
    method: 'post',
    data
  })
}

// 根据用户ID获取用户信息
export function findUserById(userId) {
  return vilceServiceApi({
    url: '/user/findUser/' + userId,
    method: 'get',
  })
}

// 获取所有用户
export function getAllUsers() {
  return vilceServiceApi({
    url: '/user/getAllUsers',
    method: 'get',
  })
}

// 重置用户密码
export function resetPassword(data) {
  return vilceServiceApi({
    url: '/user/resetPassword',
    method: 'post',
    data
  })
}

// 更新用户状态
export function updateUserStatus(data) {
  return vilceServiceApi({
    url: '/user/updateUserStatus',
    method: 'post',
    data
  })
}
