import {vilceServiceApi} from '@/utils/request'

// 给图片加图片水印
export function imageToImage(data) {
  return vilceServiceApi({
    url: '/mark/imageToImage',
    method: 'post',
    data
  })
}

// 生成透明水印图片
export function newMarkImg(data) {
  return vilceServiceApi({
    url: '/mark/new',
    method: 'post',
    data
  })
}

// 给图片添加文字水印
export function wordToImage(data) {
  return request({
    url: '/mark/wordToImage',
    method: 'post',
    data
  })
}
