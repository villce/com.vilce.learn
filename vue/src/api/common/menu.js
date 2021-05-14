import {vilceServiceApi} from '@/utils/request'

// 获取角色id为1的菜单栏
export function get1RoleMenu() {
  return vilceServiceApi({
    url: '/menu/get1RoleMenu',
    method: 'get'
  })
}

// 获取当前用户的菜单栏
export function getUserMenu() {
  return vilceServiceApi({
    url: '/menu/getUserMenu',
    method: 'get'
  })
}
