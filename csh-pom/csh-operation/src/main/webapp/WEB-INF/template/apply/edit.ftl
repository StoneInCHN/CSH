<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.apply.edit")}</title>
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
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			applyStatus: "required",
			description: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.apply")}&raquo; ${message("asup.apply.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${apply.id}" />
		<input type="hidden" name="email" value="${apply.email}" />
		<input type="hidden" name="nickname" value="${apply.nickname}" />
		<input type="hidden" name="address" value="${apply.address}" />
		<input type="hidden" name="phone" value="${apply.phone}" />
		<input type="hidden" name="createDate" value="${apply.createDate}" />
		<input type="hidden" name="license" value="${apply.license}" />
		<table class="input">
			<tr>
				<th>
					${message("asup.apply.email")}:
				</th>
				<td>
					${apply.email}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.nickname")}:
				</th>
				<td>
					${apply.nickname}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.address")}:
				</th>
				<td>
					${apply.address}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.phone")}:
				</th>
				<td>
					${apply.phone}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.license")}:
				</th>
				<td>
					<a href="${base}/upload/license/${apply.license}" target="1024"><img src="${base}/upload/license/${apply.license}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.apply.license")}"></a>
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.phone")}:
				</th>
				<td>
					${apply.phone}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.apply.applyStatus")}:
				</th>
				<td>
					<select name="applyStatus">
						<option value="">${message("asup.apply.applyStatus.select")}</option>
						<option value="AUDIT_PASSED">${message("asup.apply.applyStatus.AUDIT_PASSED")}</option>
						<option value="AUDIT_FAILED">${message("asup.apply.applyStatus.AUDIT_FAILED")}</option>
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
					<input type="submit" class="button" value="${message("asup.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>