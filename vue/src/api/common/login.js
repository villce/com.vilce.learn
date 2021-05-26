import {vilceServiceApi} from '@/utils/request'

export function login(data) {
  return vilceServiceApi({
    url: '/login/in',
    method: 'post',
    data
  })
}

// 注册接口
export function register(data) {
  return vilceServiceApi({
    url: '/login/register',
    method: 'post',
    data
  })
}

export function logout() {
  return vilceServiceApi({
    url: '/login/out',
    method: 'get'
  })
}

export function currentUser(username) {
  return vilceServiceApi({
    url: '/login/currentUser/' + username,
    method: 'get'
  })
}
