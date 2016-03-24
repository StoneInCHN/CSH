<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.tenantInfo.add")}</title>
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
<script type="text/javascript" src="${base}/resources/js/jquery.lSelect.js"></script>
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
			tenantName: {
				required: true
			},
			contactPerson: {
				required: true
			},
			accoutStatus: {
				required: true
			},
			contactPhone: "required"
		},
		messages: {
			tenantName: {
				remote: "${message("username.validate.exist")}"
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.tenantInfo")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.tenantInfo.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("csh.tenantInfo.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.tenantInfo.add")}</div>
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
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.tenantName")}:
								</th>
								<td>
									<input type="text" id="tenantName" name="tenantName" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.contactPerson")}:
								</th>
								<td>
									<input type="text" name="contactPerson" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.contactPhone")}:
								</th>
								<td>
									<input type="text" name="contactPhone" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.area")}:
								</th>
								<td>
									<input type="hidden" id="areaId"  name="areaId"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.address")}:
								</th>
								<td>
									<input type="text" name="address" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.email")}:
								</th>
								<td>
									<input type="text" name="email" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.businessTime")}:
								</th>
								<td>
									<input type="text" name="businessTime" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.description")}:
								</th>
								<td>
									<input type="text" name="description" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.ownerName")}:
								</th>
								<td>
									<input type="text" name="ownerName" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.accoutStatus.select")}:
								</th>
								<td>
									<select name="accoutStatus" class="text">
										<option value="">${message("csh.tenantInfo.accoutStatus.select")}</option>
										<option value="ACTIVED">${message("csh.tenantInfo.accoutStatus.ACTIVED")}</option>
										<option value="LOCKED">${message("csh.tenantInfo.accoutStatus.LOCKED")}</option>
										<option value="DELETE">${message("csh.tenantInfo.accoutStatus.DELETE")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantInfo.remark")}:
								</th>
								<td>
									<input type="text" name="remark" class="text" maxlength="200" />
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