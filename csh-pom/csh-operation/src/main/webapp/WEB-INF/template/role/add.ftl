<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.role.add")}</title>
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
		${message("common.current.position")}：${message("asup.main.role")}&raquo; ${message("asup.role.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.role.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.role.description")}:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.main.systemNav")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:admin" />${message("asup.role.admin")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:role" />${message("asup.role.role")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:apply" />${message("asup.role.apply")}
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("asup.role.selectAll")}">${message("asup.user.setting")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="setting:account" />${message("asup.user.setting")}
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
							<input type="checkbox" name="authorities" value="admin:vender" />${message("asup.nav.vender")}
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
							<input type="checkbox" name="authorities" value="admin:service" />${message("asup.nav.service")}
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
							<input type="checkbox" name="authorities" value="admin:card" />${message("asup.nav.card")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="vendor:card" />${message("asup.vendor.card")}
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
							<input type="checkbox" name="authorities" value="admin:comment" />${message("asup.nav.comment")}
						</label>
					</span>
				</td>
			</tr>
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
