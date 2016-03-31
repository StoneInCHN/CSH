<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.vehicleBrandDetail.edit")}</title>
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
			name: {
				required: true
			},
			averageOil: {
				required: true
			},
			oilPerHundred: {
				required: true
			},
			canOBD: {
				required: true
			},
			canGetoil: {
				required: true
			},
			canGetmileage: {
				required: true
			},
			disp: {
				required: true
			},
			status: {
				required: true
			},
			maxbv: {
				required: true
			},
			minbv: {
				required: true
			},
			oilPerHundred: {
				required: true
			},
			tank: {
				required: true
			},
			vehicleLineId: {
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.vehicleBrandDetail")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.vehicleBrandDetail.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.vehicleBrandDetail.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.vehicleBrandDetail.edit")}i</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${vehicleBrandDetail.id}" />
						<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.name")}:
								</th>
								<td>
									<input type="text" name="name" class="text" maxlength="20" value="${vehicleBrandDetail.name}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.averageOil")}:
								</th>
								<td>
									<input type="text" name="averageOil" class="text" maxlength="20" value="${vehicleBrandDetail.averageOil}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.disp")}:
								</th>
								<td>
									<input type="text" name="disp" class="text" maxlength="20" value="${vehicleBrandDetail.disp}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.canGetmileage")}:
								</th>
								<td>
									[#if vehicleBrandDetail.canGetmileage??]
									${message("csh.vehicleBrandDetail.support.yes")}<input type="radio" name="canGetmileage"  maxlength="20" [#if vehicleBrandDetail.canGetmileage?string("yes","no") =="yes"] checked="checked"[/#if]/>
									    ${message("csh.vehicleBrandDetail.support.no")}<input type="radio" name="canGetmileage"  maxlength="20"  [#if vehicleBrandDetail.canGetmileage?string("yes","no") =="no"] checked="checked"[/#if]/>
									[#else]
										${message("csh.vehicleBrandDetail.support.yes")}<input type="radio" name="canGetmileage"  maxlength="20"/>
									    ${message("csh.vehicleBrandDetail.support.no")}<input type="radio" name="canGetmileage"  maxlength="20"/>
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.canGetoil")}:
								</th>
								<td>
									[#if vehicleBrandDetail.canGetmileage??]
									${message("csh.vehicleBrandDetail.support.yes")}<input type="radio" name="canGetoil"  maxlength="20" [#if vehicleBrandDetail.canGetoil?string("yes","no") =="yes"] checked="checked"[/#if]/>
									    ${message("csh.vehicleBrandDetail.support.no")}<input type="radio" name="canGetoil"  maxlength="20"  [#if vehicleBrandDetail.canGetoil?string("yes","no") =="no"] checked="checked"[/#if]/>
									[#else]
										${message("csh.vehicleBrandDetail.support.yes")}<input type="radio" name="canGetoil"  maxlength="20"/>
									    ${message("csh.vehicleBrandDetail.support.no")}<input type="radio" name="canGetoil"  maxlength="20"/>
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.maxbv")}:
								</th>
								<td>
									<input type="text" name="maxbv" class="text" maxlength="20" value="${vehicleBrandDetail.maxbv}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.minbv")}:
								</th>
								<td>
									<input type="text" name="minbv" class="text" maxlength="20" value="${vehicleBrandDetail.minbv}"/>
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.canOBD")}:
								</th>
								<td>
									[#if vehicleBrandDetail.canGetmileage??]
									${message("csh.vehicleBrandDetail.support.yes")}<input type="radio" name="canOBD"  maxlength="20" [#if vehicleBrandDetail.canOBD?string("yes","no") =="yes"] checked="checked"[/#if]/>
									    ${message("csh.vehicleBrandDetail.support.no")}<input type="radio" name="canOBD"  maxlength="20"  [#if vehicleBrandDetail.canOBD?string("yes","no") =="no"] checked="checked"[/#if]/>
									[#else]
										${message("csh.vehicleBrandDetail.support.yes")}<input type="radio" name="canOBD"  maxlength="20"/>
									    ${message("csh.vehicleBrandDetail.support.no")}<input type="radio" name="canOBD"  maxlength="20"/>
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.oilPerHundred")}:
								</th>
								<td>
									<input type="text" name="oilPerHundred" class="text" maxlength="20" value="${vehicleBrandDetail.oilPerHundred}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.tank")}:
								</th>
								<td>
									<input type="text" name="tank" class="text" maxlength="20" value="${vehicleBrandDetail.tank}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.vehicleLine")}:
								</th>
								<td>
									<select name="vehicleLineId">
										<option value="">${message("csh.vehicleBrandDetail.vehicleLine.select")}</option>
										[#list vehicleLines as vehicleLine]
											<option value="${vehicleLine.id}" [#if vehicleBrandDetail.vehicleLine.id == vehicleLine.id]selected="selected"[/#if]>${vehicleLine.code}&nbsp;&nbsp;&nbsp;${vehicleLine.name}</option>
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.status")}:
								</th>
								<td>
									<select name="status">
										<option value="">${message("csh.vehicleBrandDetail.status.select")}</option>
										<option value="ENABLE" [#if vehicleBrandDetail.status == "ENABLE"]selected="selected"[/#if]>${message("csh.vehicleBrandDetail.status.ENABLE")}</option>
										<option value="DISABLE" [#if vehicleBrandDetail.status == "DISABLE"]selected="selected"[/#if]>${message("csh.vehicleBrandDetail.status.DISABLE")}</option>
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