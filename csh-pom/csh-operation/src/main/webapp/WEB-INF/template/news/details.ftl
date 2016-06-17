<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${news.title}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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