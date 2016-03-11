<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("ausp.message.add")}</title>
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
			messageTitle: "required",
			messageContent: "required",
			messageType: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.message.manage")}&raquo; ${message("asup.message.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.message.title")}:
				</th>
				<td>
					<input type="text" name="messageTitle" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.message.content")}:
				</th>
				<td>
					<textarea  rows="10" cols="20" class="text" name="messageContent"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<span class="requiredField">*</span>${message("asup.message.type")}
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="radio" name="messageType" value="personalMsg"/>${message("asup.message.personalMsg")}
						</label>
						<label>
							<input type="radio" name="messageType" value="systemMsg"/>${message("asup.message.systemMsg")}
						</label>
						<label>
							<input type="radio" name="messageType" value="broadcastMsg"/>${message("asup.message.broadcastMsg")}
						</label>
					</span>
				</td>
			</tr>
			
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}"/>
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>