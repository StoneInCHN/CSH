<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.comment.info")}</title>
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<style type="text/css">
	html{
		padding-left: 20px;
		padding-right: 15px;
	}
	
</style>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.comment")}&raquo; ${message("asup.comment.info")}
	</div>
		<table class="input">
			<tr>
				<th>
					${message("asup.common.createDate")}:
				</th>
				<td>
					${comment.createDate?string("yyyy-MM-dd HH:mm:ss")}">${comment.createDate}
				</td>
			</tr>
			
			<tr>
				<th>
					${message("asup.service.name")}:
				</th>
				<td>
					${comment.service.name}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.comment.contents")}:
				</th>
				<td>
					${comment.contents}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.comment.user")}:
				</th>
				<td>
					${comment.user.userName}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.comment.rate")}:
				</th>
				<td>
					${comment.rate}
				</td>
			</tr>
		</table>
</body>
</html>