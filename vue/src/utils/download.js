import { parseTime } from "./index";

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

export function downloadByUrl(url, name) {
  let image = new Image()
  image.setAttribute('crossOrigin', 'anonymous')
  image.src = url
  // 解决跨域 Canvas 污染问题
  image.setAttribute("crossOrigin", "anonymous");
  image.onload = function() {
    var canvas = document.createElement("canvas");
    canvas.width = image.width;
    canvas.height = image.height;
    var context = canvas.getContext("2d");
    context.drawImage(image, 0, 0, image.width, image.height);
    var url = canvas.toDataURL("image/png"); //得到图片的base64编码数据

    var a = document.createElement("a"); // 生成一个a元素
    var event = new MouseEvent("click"); // 创建一个单击事件
    a.download = name || "photo"; // 设置图片名称
    a.href = url; // 将生成的URL设置为a.href属性
    a.dispatchEvent(event); // 触发a的单击事件
  };
}
