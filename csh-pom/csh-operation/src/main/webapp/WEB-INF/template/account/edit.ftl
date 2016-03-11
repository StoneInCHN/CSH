<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.account.edit")}</title>
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
	var $password = $("#password");
	var $name = $("#name");
	var $submit = $(":submit");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 6
			},
			rePassword: {
				equalTo: "#password"
			},
			nickName: {
				required: true
			}
		},
		messages: {
			password: {
				pattern: "${message("asup.reg.passwordIllegal")}",
				minlength: "${message("asup.password.minlength",6)}"
			},
			repassword:{
					 equalTo:"${message("asup.reg.passwordIllegal")}"
			},
			name:{
				required:"${message("asup.reg.nameRequired")}"
			}
		},
		
		submitHandler:function(form){
			$submit.attr("disabled",true);
			$.ajax({
				url:$inputForm.attr("action"),
				type:"POST",
				data:{
						password:$password.val(),
						name:$name.val()
				},
				dataType:"json",
				cache:false,
				success:function(message){
					$.message(message);
					$submit.attr("disabled",false);
					setTimeout("location.href='accountInfo.jhtml'",1000);
				}
			});
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		 ${message("common.current.position")}：${message("asup.account.setting")}&raquo; ${message("asup.account.settingGroup")}
	</div>
	<form id="inputForm" action="edit.jhtml" method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<input type="hidden" name="userName" value="${user.userName}" />
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
					${message("asup.admin.password")}:
				</th>
				<td>
					<input type="password" id="password" name="password" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.admin.rePassword")}:
				</th>
				<td>
					<input type="password" name="rePassword" id="rePassword" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.admin.name")}:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" value="${admin.name}" maxlength="200" />
				</td>
			</tr>				
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>