<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.card.cardReview")}</title>
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
			status: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.card.manage")}&raquo; ${message("asup.card.cardReview")}
	</div>
	<form id="inputForm" action="submit.jhtml" method="post">
		<input type="hidden" name="id" value="${card.id}" />
		<table class="input">
			<tr>
				<th>
					${message("asup.card.imagePath")}:
				</th>
				<td>
					<a href="${base}/upload/card/${card.imagePath}" target="1024"><img src="${base}/upload/card/${card.imagePath}" style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.card.imagePath")}"/></a>
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.createDate")}:
				</th>
				<td>
					${card.createDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.servicename")}:
				</th>
				<td>
					${card.service.name}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.vendorname")}:
				</th>
				<td>
					${card.service.vendor.vendorName}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.startDate")}:
				</th>
				<td>
					${card.effictiveFrom?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.endDate")}:
				</th>
				<td>
					${card.effictiveTo?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.cardDescription")}:
				</th>
				<td>
					${card.cardDescription}
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.expire")}:
				</th>
				<td>
					[#if card.isExpire]
						${message("asup.card.expire.inactive")}
					[#else]
						${message("asup.card.expire.active")}
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.card.status")}:
				</th>
				<td>
					<select class="selectField" id="status" name="status">
						<option value="supply" [#if card.status == 'supply']selected="selected"[/#if]>${message("asup.card.status.supply")}</option>
						<option value="approvedPass" [#if card.status == 'approvedPass']selected="selected"[/#if]>${message("asup.card.status.approvedPass")}</option>
						<option value="approvedFailed" [#if card.status == 'approvedFailed']selected="selected"[/#if]>${message("asup.card.status.approvedFailed")}</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("asup.card.review.remark")}:
				</th>
				<td>
					<textarea  rows="6" cols="15" class="text" name="remark">
						${card.remark}
					</textarea>
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