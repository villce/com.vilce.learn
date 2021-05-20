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
export function imageUpload(data) {
  return vilceServiceApi({
    url: '/image/imageUpload',
    method: 'post',
    data
  })
}

// 删除指定图片
export function deleteImage(imageName) {
  return vilceServiceApi({
    url: '/image/deleteImage',
    method: 'get',
    params: { imageName }
  })
}

// 伪上传图片
export function toolUpload(data) {
  return vilceServiceApi({
    url: '/image/toolUpload',
    method: 'post',
    data
  })
}

// 批量压缩图片
export function compress(data) {
  return vilceServiceApi({
    url: '/image/compress',
    method: 'post',
    data
  })
}
