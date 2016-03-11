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
<script type="text/javascript" src="${base}/resources/datePicker/WdatePicker.js"></script>
<style type="text/css">
	html{
		padding-left: 20px;
		padding-right: 15px;
	}
	
	#positionIframe{
		width:99%;
		height:470px;
	}
	
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");

	// 表单验证
	$inputForm.validate({
		rules: {
			effictiveFrom: "required",
			effictiveTo: "required",
			file: "required",
			cardDescription: "required",
			serviceId: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.vendor.card")}&raquo; ${message("asup.card.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<table class="input">
			
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.card.upload")}:
				</th>
				<td>
					<input type="file" name="file"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.card.startDate")}:
				</th>
				<td>
					<input type="text" style="width:140px;" id="effictiveFrom" name="effictiveFrom" class="text Wdate" value="" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'effictiveTo\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.card.endDate")}:
				</th>
				<td>
					<input type="text" style="width:140px;" id="effictiveTo" name="effictiveTo" class="text Wdate" value="" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'effictiveFrom\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.card.service")}:
				</th>
				<td>
					<select name="serviceId">
						<option value="">${message("asup.card.service.select")}</option>
						[#list vendorServices as vendorservice]
							<option value="${vendorservice.id}">${vendorservice.name}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.card.cardDescription")}:
				</th>
				<td>
					<textarea  rows="6" cols="15" class="text" name="cardDescription">
					</textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}" />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='vendor.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	<script>
	$(function(){
	 var $positionUpload = $("#positionUpload");
	 $positionUpload.click(function(){
	 	 $.dialog({
				title: "${message("asup.vendor.position")}",
				content:'<iframe id="positionIframe" name="positionIframe" src="simulator.jhtml" frameborder="0" ></iframe>',
				width: 1000,
				height:520,
				top:10,
				modal: true,
				cancel:null,
				ok:null,
				close:true
			});
	 });
	});
	</script>
	
</body>
</html>