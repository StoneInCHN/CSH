<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.service.edit")}</title>
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

	// 表单验证
	$inputForm.validate({
		rules: {
			name:{
				required:true
			},
			intro:{
				required:true
			},
			isTop:{
				required:true
			},
			price:{
				required:true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: 2
				}
				
			},
			category:{
				required:true
			}
		},
		messages: {
			price: {
				decimal:"${message("admin.validate.decimal.fraction")}"
			}
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.service")}&raquo; ${message("asup.service.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${service.id}">
		<input type="hidden" name="imgPath" value="${service.imgPath}">
		<input type="hidden" name="description" value="${service.description}">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" value="${service.name}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.intro")}:
				</th>
				<td>
					<textarea rows="50" rows="6"name="intro" class="text" >${service.intro}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.imgPath")}:
				</th>
				<td>
					<a href="${base}/upload/service/${service.imgPath}" target="1024"><img src="${base}/upload/service/${service.imgPath}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.vendor.image")}"></a>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.file")}:
				</th>
				<td>
					<input type="file" name="file"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.price")}:
				</th>
				<td>
					<input type="text" name="price" class="text" value="${service.price}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.serviceCategory")}:
				</th>
				<td>
					<select name="category" class="text">
							[#list serviceCategory as category]
							<option value="${category.id}" [#if category.id == service.serviceCategory.id]selected="selected"[/#if]>${category.categoryName}</option>
							[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.serviceStatus")}:
				</th>
				<td>
					${message("asup.service.serviceStatus."+service.serviceStatus)}
				</td>
			</tr>
				[#if service.serviceStatus =="AUDIT_FAILED"]
				<tr>
					<th>
						<span class="requiredField">*</span>${message("asup.service.description")}:
					</th>
					<td>
						${service.description}
					</td>
				</tr>
			[/#if]
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}" />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='info.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>