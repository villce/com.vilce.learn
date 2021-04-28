import request from '../../utils/request'

// 获取角色id为1的菜单栏
export function get1RoleMenu() {
  return request({
    url: '/menu/get1RoleMenu',
    method: 'get'
  })
}

// 获取当前用户的菜单栏
export function getUserMenu() {
  return request({
    url: '/menu/getUserMenu',
    method: 'get'
  })
}
