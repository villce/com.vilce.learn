import { parseTime } from "./index";

const ServerConfig = {
  local: 'http://localhost:8006/api',
  dev: 'http://10.10.181.121:8125',
  test: 'http://10.10.86.229:8128',
  prod: 'http://180.163.42.201:38116',
  production: 'http://180.163.42.198:8116'
}

export function getBasicUrl() {
  if (
    location.href.indexOf('10.10.181.121') > -1
  ) {
    return ServerConfig.dev
  } else if (location.href.indexOf('10.10.86.229') > -1) {
    return ServerConfig.test
  } else if (location.href.indexOf('wgtradetest.eastmoney.com') > -1) {
    return ServerConfig.prod
  } else if (location.href.indexOf('180.163.42.164') > -1) {
    return ServerConfig.production
  } else if (location.href.indexOf('localhost') > -1) {
    return ServerConfig.local
  }
}

export function downloadImage(imgSrc) {
  //实例化一个img对象
  const img = new Image();
  //设置img的图片路径
  img.src = imgSrc;
  //设置跨域解决
  img.setAttribute('crossOrigin', 'Anonymous');
  //img加载完后处理
  img.onload = function () {
    //创建一个canvas对象
    const canvas = document.createElement('canvas')
    //把图片的宽度设为canves的宽度
    canvas.width = img.width
    //把图片的高度设为canves的高度
    canvas.height = img.height
    //创建一个2d画布
    const ctx = canvas.getContext('2d')
    // 将img中的内容画到画布上
    ctx.drawImage(img, 0, 0, canvas.width, canvas.height)
    // 将画布内容转换为base64
    let base64 = canvas.toDataURL()
    // 创建a链接
    const a = document.createElement('a')
    a.href = base64
    a.download = parseTime(new Date(), '{y}{m}{d}{h}{i}{s}')
    // 触发a链接点击事件，浏览器开始下载文件
    a.click()
  }
}
