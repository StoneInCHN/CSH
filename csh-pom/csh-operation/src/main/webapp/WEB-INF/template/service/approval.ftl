<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.service.approval")}</title>
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
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");

	// 表单验证
	$inputForm.validate({
		rules: {
			serviceStatus: "required",
			description:"required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.service")}&raquo; ${message("asup.service.approval")}
	</div>
	<form id="inputForm" action="statusUpdate.jhtml" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${service.id}">
		<table class="input">
			<tr>
				<th>
					${message("asup.service.name")}:
				</th>
				<td>
					${service.name}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.service.intro")}:
				</th>
				<td>
					${service.intro}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.service.file")}:
				</th>
				<td>
					<a href="${base}/upload/service/${service.imgPath}" target="1024"><img src="${base}/upload/service/${service.imgPath}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.vendor.image")}"></a>
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.service.price")}:
				</th>
				<td>
					${service.price}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.service.serviceCategory")}:
				</th>
				<td>
					${service.serviceCategory.categoryName}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.serviceStatus.select")}:
				</th>
				<td>
					<select name="serviceStatus">
						<option value="AUDIT_PASSED">${message("asup.service.serviceStatus.AUDIT_PASSED")}</option>
						<option value="AUDIT_FAILED">${message("asup.service.serviceStatus.AUDIT_FAILED")}</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.description")}:
				</th>
				<td>
					<textarea  name="description" rows="6" cols="60">${apply.description}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}" />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='info.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>