<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.vehicleLine.edit")}</title>
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
			code: {
				required: true
			},
			name: {
				required: true
			},
			vehicleBrandId: {
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.vehicleLine")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.vehicleLine.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.vehicleLine.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.vehicleLine.edit")}i</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${vehicleLine.id}" />
						<table class="input tabContent">
							<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleLine.code")}:
								</th>
								<td>
									<input type="text" id="code" name="code" class="text" maxlength="20" value="${vehicleLine.code}" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleLine.icon")}:
								</th>
								<td>
									<a href="${base}/upload/vehicleIcon/${vehicleLine.icon}" target="1024"><img src="${base}/upload/vehicleIcon/${vehicleLine.icon}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.vehicleLine.icon")}"></a>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleLine.icon")}:
								</th>
								<td>
									<input type="file" name="iconFile" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleLine.name")}:
								</th>
								<td>
									<input type="text" name="name" class="text" maxlength="20" value="${vehicleLine.name}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleLine.vehicleBrand")}:
								</th>
								<td>
									<select name="vehicleBrandId">
										<option value="">${message("csh.vehicleLine.vehicleBrand.select")}</option>
										[#list vehicleBrands as vehicleBrand]
											<option value="${vehicleBrand.id}"[#if vehicleLine.vehicleBrand.id == vehicleBrand.id]selected = "selected"[/#if]>${vehicleBrand.code}&nbsp;&nbsp;&nbsp;${vehicleBrand.name}</option>
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleLine.parent")}:
								</th>
								<td>
									<select name="parentId">
										<option value="">${message("csh.vehicleLine.parent.root")}</option>
										[#list vehicleLines as vehicleLine]
											<option value="${vehicleLine.id}"[#if vehicleLine.parent.id == vehicleLine.id]selected = "selected"[/#if]>${vehicleLine.name}</option>
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