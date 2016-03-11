<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.apply.title")}</title>
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="${base}/resources/images/car.ico" media="screen" /> 
<style>
.container .stance{
		height: 200px;
}
.container .message{
	margin:0px auto;
	width:300px;
	font-size:20px;
	color:#CD8500;
}
.container .message a{
	color:#8B6508;
}
</style>
</head>
<body>
	<div class="header">
            <img class="logo" src="${base}/resources/images/logo.gif" alt="车管家"/>
    </div>
    <div class="warp">
	    <div class="container">
	    	<div class="stance"></div>
	    	<div class="message">
	    		[#if content]
	    			${message("apply.submit.success")}
	    		[#else]
	    			${message("apply.submit.failure")}<a href="javascript:history.go(-1);">返回</a>
	    		[/#if]
	    		
	    	</div>
	    </div>
	 </div>
    <div class="footer-warp">
        <div class="copy-warp">
            <div class="copy">版权所有©2014<span>Copyright © 2014 </span></div>
        </div>
    </div>
</body>
</html>