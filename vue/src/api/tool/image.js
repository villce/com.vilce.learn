import {vilceServiceApi} from '@/utils/request'

// 更改图片背景颜色
export function changBg(data) {
  return vilceServiceApi({
    url: '/image/changBg',
    method: 'post',
    data
  })
}

// 上传图片
export function coversUpload(data) {
  return vilceServiceApi({
    url: '/image/coversUpload',
    method: 'post',
    data
  })
}

// 删除指定图片
export function deleteImage(imageName) {
  return request({
    url: '/image/deleteImage',
    method: 'get',
    params: { imageName }
  })
}

// 伪上传图片
export function toolUpload(data) {
  return request({
    url: '/image/toolUpload',
    method: 'post',
    data
  })
}
