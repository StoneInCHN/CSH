<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.service.info")}</title>
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
		${message("common.current.position")}：${message("asup.main.service")}&raquo; ${message("asup.service.add")}
	</div>
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.name")}:
				</th>
				<td>
					${service.name}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.intro")}:
				</th>
				<td>
					${service.intro}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.file")}:
				</th>
				<td>
					${service.name}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.price")}:
				</th>
				<td>
					${service.price}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.serviceCategory")}:
				</th>
				<td>
					${service.serviceCategory}
				</td>
			</tr>
			[#if service.serviceStatus =="AUDIT_FAILED"]
				<tr>
					<th>
						<span class="requiredField">*</span>${message("asup.service.description")}:
					</th>
					<td>
						${service.description}
					</td>
				</tr>
			[/#if]
		</table>
</body>
</html>