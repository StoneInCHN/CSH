<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.admin.edit")}</title>
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<style type="text/css">
	html{
		padding-left: 20px;
		padding-right: 15px;
	}
.roles label {
	width: 150px;
	display: block;
	float: left;
	padding-right: 5px;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			password: {
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			name: "required",
			roleIds: "required",
			adminStatus: "required"
		},
		messages: {
			password: {
				pattern: "${message("admin.validate.illegal")}"
			}
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("admin.main.admin")}&raquo; ${message("admin.admin.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${admin.id}" />
		<input type="hidden" name="username" value="${admin.username}" />
		<input type="hidden" name="email" value="${admin.email}" />
		<div class="bar">
			<a href="javascript:;" id="refreshButtonFirst" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("asup.common.refresh")}
			</a>
		</div>
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.admin.base")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					${message("asup.admin.username")}:
				</th>
				<td>
					${admin.username}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.admin.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" value="${admin.name}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.admin.email")}:
				</th>
				<td>
					${admin.email}
				</td>
			</tr>
			<tr class="roles">
				<th>
					<span class="requiredField">*</span>${message("asup.admin.roles")}:
				</th>
				<td>
					<span class="fieldSet">
						[#list roles as role]
							<label>
								<input type="checkbox" name="roleIds" value="${role.id}"[#if admin.roles?seq_contains(role)] checked="checked"[/#if] />${role.name}
							</label>
						[/#list]
					</span>
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.admin.adminStatus")}:
				</th>
				<td>
					<input type="radio" value="actived" name="adminStatus" [#if admin.adminStatus== "actived" ]checked="checked"[/#if] />${message("asup.admin.adminStatus.actived")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" value="locked" name="adminStatus" [#if admin.adminStatus== "locked" ]checked="checked"[/#if] />${message("asup.admin.adminStatus.locked")}
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}" />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>