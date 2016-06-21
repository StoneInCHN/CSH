<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${news.title}</title>
<!-- 优先使用 IE 最新版本和 Chrome -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<!-- 为移动设备添加 viewport -->
<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=no">
<!-- iOS 设备适配代码Star-->
<!-- 添加到主屏后的标题（iOS 6 新增） -->
<meta name="apple-mobile-web-app-title" content="UU商城">
<!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->
<meta name="apple-mobile-web-app-capable" content="yes"/>
<!-- 设置苹果工具栏颜色 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
<meta name="apple-itunes-app" content="app-id=myAppStoreID, affiliate-data=myAffiliateData, app-argument=myURL">
<!-- 忽略页面中的数字识别为电话，忽略email识别 -->
<meta name="format-detection" content="telephone=no"><!--电话数字样式去掉 苹果-->
<link rel="shortcut icon" type="image/x-icon" href="${base}/resources/images/carlife.ico" media="screen" /> 
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<h4 style="text-align:center;margin:30px">${news.title}</h4>
		<p style="text-align:center;margin:30px;font-size:12px;">创建人:${news.userName} 创建时间：${news.createDate}</p>
		<div style="margin-bottom:80px;">${news.content}</div>
	</div>
</body>
</html>