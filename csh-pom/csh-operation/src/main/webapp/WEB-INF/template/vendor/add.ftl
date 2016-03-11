<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.vendor.add")}</title>
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
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
	var $areaId = $("#areaId");
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/console/common/area.jhtml"
	});

	// 表单验证
	$inputForm.validate({
		rules: {
			vendorName: "required",
			vendorPhone: "required",
			vendorAddress: "required",
			file: "required",
			vendorType: "required",
			vendorIntro: "required",
			longitude:"required",
			latitude:"required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.vendor")}&raquo; ${message("asup.vendor.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorName")}:
				</th>
				<td>
					<input type="text" name="vendorName" class="text" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorIntro")}:
				</th>
				<td>
					<textarea rows="50" rows="6"name="vendorIntro" class="text" maxlength="200"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorPhone")}:
				</th>
				<td>
					<input type="text" name="vendorPhone" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorArea")}:
				</th>
				<td>
					<input type="hidden" id="areaId" name="areaId"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorAddress")}:
				</th>
				<td>
					<input type="text" name="vendorAddress" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorImagePath")}:
				</th>
				<td>
					<input type="file" name="file"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.vendorType")}:
				</th>
				<td>
					<select name="vendorType">
						<option value="">${message("asup.vendor.vendorType.select")}</option>
						<option value="WASHING">${message("asup.vendor.vendorType.washing")}</option>
						<option value="COSMETOLOGY">${message("asup.vendor.vendorType.cosmetology")}</option>
						<option value="UPKEEP">${message("asup.vendor.vendorType.upkeep")}</option>
						<option value="REFUEL">${message("asup.vendor.vendorType.refuel")}</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.vendor.position")}:
				</th>
				<td>
					${message("pisotion.point.longitude")}:<input type="text" readonly id="longitude" name="longitude" />
					${message("pisotion.point.latitude")}:<input type="text" readonly id="latitude" name="latitude" />
					<input type="button" id="positionUpload" value="${message("asup.vendor.position")}"/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" id="submitButton" class="button" value="${message("asup.common.submit")}" />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	<script>
	$(function(){
	 var $positionUpload = $("#positionUpload");
	 $positionUpload.click(function(){
	 	
	 	 $dialog =  $.dialog({
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