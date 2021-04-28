import request from '../../utils/request'

// 登录接口
export function login(data) {
  return request({
    url: '/login/in',
    method: 'post',
    data
  })
}

// 注册接口
export function register(data) {
  return request({
    url: '/login/register',
    method: 'post',
    data
  })
}

// 认证接口
export function authentication() {
  return request({
    url: '/login/authentication',
    method: 'get'
  })
}

export function currentUser(username) {
  return request({
    url: '/login/currentUser',
    method: 'get',
    params: { 'username': username }
  })
}

// 退出接口
export function logout() {
  return request({
    url: '/login/out',
    method: 'get'
  })
}
