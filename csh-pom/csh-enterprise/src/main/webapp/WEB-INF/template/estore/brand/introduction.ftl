<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>品牌详情</title>
<!-- 优先使用 IE 最新版本和 Chrome -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<!-- 为移动设备添加 viewport -->
<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<!-- iOS 设备适配代码Star-->
<!-- 添加到主屏后的标题（iOS 6 新增） -->
<meta name="apple-mobile-web-app-title" content="车生活">
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
<style type="text/css">
	ul,li,p,span,a{padding:0;margin:0}
	body{
		max-width:768px;
		margin:0 auto 20px auto;
		padding:0 20px;
	
	}
	.top{
		text-align:center;
		height:40px;
		line-height:30px;
		padding:5px;
		font-size:18px;
		border-bottom: 1px solid #E4E4E4;
	}
	.top .category{float:left;height:30px;color:#333}
	.top .time{height:30px;color:#999;font-size:18px;}
	.top .hits{float:right;height:30px;color:#999}
	.comFrame{margin-top:20px;color:#343434;}
	.comFrame h3{border-bottom:1px solid #e6eaed;color:#000;font-weight:400;font-size:18px;line-height:3;}
	.comFrame ul{list-style:none}
	.comFrame li{position:relative;padding:10px 0 10px 50px;border-bottom:1px solid #e6eaed;}
	.comFrame li.hot{background:url(../images/tag_hot.png) right top no-repeat;}
	.comFrame li.hot span.flo{display:none;}
	.comFrame li .head{position:absolute;top:10px;left:-5px;width:50px;height:50px;border-radius: 10px;}
	.comFrame li p{font-size:14px;line-height:1.6;}
	.comFrame li p.re{padding:0;font-size:16px;line-height:1.6;}
	.comFrame li p.nameCon{padding-bottom:15px;line-height:12px;}
	.comFrame li p.nameCon span{display:block;line-height:20px}
	span.time{font-size:10px;}
</style>
</head>
<body>
	<p class="top">
	<span class="time">${brand.createDate}</span> 
	</p>
	<div>
		<h3 style="text-align:center;margin:30px">${brand.name}</h3>
		<div style="margin-bottom:80px;">${brand.introduction}</div>
	</div>
</body>
</html>