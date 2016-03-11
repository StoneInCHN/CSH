<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.role.edit")}</title>
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
	var $selectAll = $("#inputForm .selectAll");
	
	
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			authorities: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.role")}&raquo; ${message("asup.role.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${role.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.role.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" value="${role.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.role.description")}:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${role.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.role.systemGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:admin"[#if role.authorities?seq_contains("admin:admin")] checked="checked"[/#if] />${message("asup.role.admin")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:role"[#if role.authorities?seq_contains("admin:role")] checked="checked"[/#if] />${message("asup.role.role")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:apply" [#if role.authorities?seq_contains("admin:apply")] checked="checked"[/#if]/>${message("asup.role.apply")}
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("asup.user.setting")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="setting:account"[#if role.authorities?seq_contains("setting:account")] checked="checked"[/#if] />${message("asup.user.setting")}
						</label>
					</span>
				</td>
			</tr>
					<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.nav.vender")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:vender" [#if role.authorities?seq_contains("admin:vender")] checked="checked"[/#if]/>${message("asup.nav.vender")}
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.nav.service")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:service" [#if role.authorities?seq_contains("admin:service")] checked="checked"[/#if]/>${message("asup.nav.service")}
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.nav.card")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:card" [#if role.authorities?seq_contains("admin:card")] checked="checked"[/#if]/>${message("asup.nav.card")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="vendor:card" [#if role.authorities?seq_contains("vendor:card")] checked="checked"[/#if]/>${message("asup.vendor.card")}
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.nav.comment")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:comment" [#if role.authorities?seq_contains("admin:comment")] checked="checked"[/#if]/>${message("asup.nav.comment")}
						</label>
					</span>
				</td>
			</tr>
			[#if role.isSystem]
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<span class="tips">${message("asup.role.editSystemNotAllowed")}</span>
					</td>
				</tr>
			[/#if]
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
