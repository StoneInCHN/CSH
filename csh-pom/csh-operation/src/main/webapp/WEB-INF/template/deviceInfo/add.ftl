<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.deviceInfo.add")}</title>
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
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			deviceNo: {
				required: true
			},
			simNo: {
				required: true
			},
			deviceStatus: {
				required: true
			},
			bindStatus: {
				required: true
			},
			typeId: {
				required: true
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.deviceInfo")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.deviceInfo.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("csh.deviceInfo.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.deviceInfo.add")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form">
                     	<table class="input tabContent">
							<!--
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.bindTime")}:
								</th>
								<td>
									<input type="text" name="bindTime" class="text Wdate" id="bindTime"  onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.unBindTime")}:
								</th>
								<td>
									<input type="text" name="unBindTime" class="text Wdate" maxlength="20" onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
								</td>
							</tr>
							-->
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.deviceNo")}:
								</th>
								<td>
									<input type="text" name="deviceNo" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.simNo")}:
								</th>
								<td>
									<input type="text" name="simNo" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.deviceStatus")}:
								</th>
								<td>
									<select name="deviceStatus">
										<option value="">${message("csh.deviceInfo.deviceStatus.select")}</option>
										<option value="INITED">${message("csh.deviceInfo.deviceStatus.INITED")}</option>
										<option value="SENDOUT">${message("csh.deviceInfo.deviceStatus.SENDOUT")}</option>
										<option value="STORAGEOUT">${message("csh.deviceInfo.deviceStatus.STORAGEOUT")}</option>
										<option value="BINDED">${message("csh.deviceInfo.deviceStatus.BINDED")}</option>
										<option value="REFUNDED">${message("csh.deviceInfo.deviceStatus.REFUNDED")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.bindStatus")}:
								</th>
								<td>
									<select name="bindStatus">
										<option value="">${message("csh.deviceInfo.bindStatus.select")}</option>
										<option value="BINDED">${message("csh.deviceInfo.bindStatus.BINDED")}</option>
										<option value="UNBINDED">${message("csh.deviceInfo.bindStatus.UNBINDED")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.deviceInfo.type")}:
								</th>
								<td>
									<select name="typeId">
										<option value="">${message("csh.deviceInfo.type.select")}</option>
										[#list types as type]
										<option value="${type.id}">${type.name}</option>
										[/#list]
									</select>
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