import {vilceServiceApi} from '@/utils/request'

// 添加或更新角色、权限
export function addOrUpdateRole(data) {
  return vilceServiceApi({
    url: '/role/addOrUpdateRole',
    method: 'post',
    data
  })
}

// 获取所有权限信息
export function getAllPermissions() {
  return vilceServiceApi({
    url: '/role/getAllPermissions',
    method: 'get'
  })
}

// 获取所有角色权限菜单栏
export function listAllRolesInfo() {
  return vilceServiceApi({
    url: '/role/listAllRolesInfo',
    method: 'get'
  })
}

// 更新角色菜单信息
export function updateRoleMenu(data) {
  return vilceServiceApi({
    url: '/role/updateRoleMenu',
    method: 'post',
    data
  })
}

// 更新角色状态
export function updateRoleStatus(data) {
  return vilceServiceApi({
    url: '/role/updateRoleStatus',
    method: 'post',
    data
  })
}


