import request from '../../utils/request'

// 添加或更新角色、权限
export function addOrUpdateRole(data) {
  return request({
    url: '/role/addOrUpdateRole',
    method: 'post',
    data
  })
}

// 获取所有权限信息
export function getAllPermissions() {
  return request({
    url: '/role/getAllPermissions',
    method: 'get'
  })
}

// 获取所有角色权限菜单栏
export function listAllRolesInfo() {
  return request({
    url: '/role/listAllRolesInfo',
    method: 'get'
  })
}

// 更新角色菜单信息
export function updateRoleMenu(data) {
  return request({
    url: '/role/updateRoleMenu',
    method: 'post',
    data
  })
}

// 更新角色状态
export function updateRoleStatus(data) {
  return request({
    url: '/role/updateRoleStatus',
    method: 'post',
    data
  })
}


