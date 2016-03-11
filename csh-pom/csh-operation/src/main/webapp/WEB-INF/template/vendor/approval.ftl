<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.vendor.add")}</title>
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
<script>
$(function(){
	var $inputForm = $("#inputForm");

	// 表单验证
	$inputForm.validate({
		rules: {
			vendorStatus: "required"
		}
	});
})
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.vendor")}&raquo; ${message("asup.vendor.approval")}
	</div>
	<form id="inputForm" action="statusUpdate.jhtml" method="post" >
		<input type="hidden" name="id" value="${vendor.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorName")}:
				</th>
				<td>
					${vendor.vendorName}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorIntro")}:
				</th>
				<td>
					${vendor.vendorIntro}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorPhone")}:
				</th>
				<td>
					${vendor.vendorPhone}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorAddress")}:
				</th>
				<td>
					${vendor.vendorAddress}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorImagePath")}:
				</th>
				<td>
					<a href="${base}/upload/vendor/${vendor.vendorImagePath}" target="1024"><img src="${base}/upload/vendor/${vendor.vendorImagePath}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.vendor.image")}"></a>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorType")}:
				</th>
				<td>
					${message("asup.vendor.vendorType."+vendor.vendorType)}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorStatus")}:
				</th>
				<td>
					<select name="vendorStatus">
						<option value="">${message("asup.vendor.vendorStatus.select")}</option>
						<option value="AUDIT_PASSED">${message("asup.vendor.vendorStatus.AUDIT_PASSED")}</option>
						<option value="AUDIT_FAILED">${message("asup.vendor.vendorStatus.AUDIT_FAILED")}</option>
					</select>
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