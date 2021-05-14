import {vilceServiceApi} from '@/utils/request'

// 上传私密图片
export function uploadSecretImg(data) {
  return vilceServiceApi({
    url: '/secret/coversUpload',
    method: 'post',
    data
  })
}

// 删除图片模块
export function deleteModules(modulesId) {
  return vilceServiceApi({
    url: '/secret/deleteModules/' + modulesId,
    method: 'get'
  })
}

// 编辑图片模块
export function editModules(data) {
  return vilceServiceApi({
    url: '/secret/editModules',
    method: 'post',
    data
  })
}

// 根据ID查询模块
export function findModules(modulesId) {
  return vilceServiceApi({
    url: '/secret/findModules/' + modulesId,
    method: 'get'
  })
}

// 分页获取图片模块
export function getModules(pageIndex, pageSize) {
  return vilceServiceApi({
    url: '/secret/getModules/' + pageIndex + '/' + pageSize,
    method: 'get'
  })
}

// 获取最新图片模块
export function getNewModules(pageSize) {
  return vilceServiceApi({
    url: '/secret/getNewModules/' + pageSize,
    method: 'get'
  })
}

// 获取最新图片模块
export function timeLineGetModules(pageIndex, pageSize) {
  return vilceServiceApi({
    url: '/secret/timeLineGetModules/' + pageIndex + '/' + pageSize,
    method: 'get'
  })
}
