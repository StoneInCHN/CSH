<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.appVersion.edit")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			versionName: {
				required: true,
				minlength: 2,
				maxlength: 50,
				remote: {
					url: "checkVersion.jhtml",
					cache: false,
					data:{
						id:${apkVersion.id}
					}
				}
			},
			updateContent: {
				required: true
			},
			file: {
				nameEndWith:".apk"
			},
			versionCode:{
				required: true,
				number:true
			},
			updateContent: {
				required: true
			}
		},
		messages: {
			versionName: {
				remote: "${message("apkVersion.version.validate.exist")}"
			},
			file:{
				nameEndWith:"${message("apkVersion.file.name.error")}"
			}
		}
	});	
});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-android"></i> ${message("csh.main.appVersion")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.appVersion.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.appVersion.edit")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-pencil-square-o"></i>${message("csh.appVersion.edit")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="update.jhtml" method="post" class="form-horizontal" enctype="multipart/form-data" role="form">
                     	<input type="hidden" name="id"  value="${apkVersion.id}" />
                     	<input type="hidden" name="apkPath"  value="${apkVersion.apkPath}" />
                     	<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.appVersion.version")}:
								</th>
								<td>
									<input type="text" id="versionName" value="${apkVersion.versionName}" name="versionName" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.appVersion.versionCode")}:
								</th>
								<td>
									<input type="text" id="versionCode" value="${apkVersion.versionCode}" name="versionCode" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.appVersion.isForced")}:
								</th>
								<td>
									<select name="isForced">
										<option value="true" [#if apkVersion.isForced == true]selected="selected"[/#if]>${message("csh.appVersion.isForced.true")}</option>
										<option value="false" [#if apkVersion.isForced == false] selected="selected"[/#if]>${message("csh.appVersion.isForced.false")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.appVersion.file")}:
								</th>
								<td>
									<input type="file" id="file" name="file" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.appVersion.updateContent")}:
								</th>
								<td>
									<textarea name="updateContent" class="text" maxlength="255">${apkVersion.updateContent}</textarea>
								</td>
							</tr>
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="button" value="${message("csh.common.submit")}" />
									<input type="button" class="button" value="${message("csh.common.back")}" onclick="location.href='list.jhtml'" />
								</td>
							</tr>
						</table>                                     
                     </form>
                  </div>
                </div>
              </div>  
            </div>
          </div>
        </div>
	   </div>
	</div>
	<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
</body>
</html>