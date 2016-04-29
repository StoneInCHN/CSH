<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.insurance.edit")}</title>
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
			name: {
				required: true
			},
			phoneNo:{
				required :true,
				isMobile:true
			},
			areaId:{
				required:true
			},
			address:{
				required:true
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.insurance")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.insurance.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.insurance.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.insurance.edit")}i</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post">
						<input type="hidden" name="id" value="${insuranceCompany.id}" />
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.insurance.parent")}:
								</th>
								<td>
									<select name="parentId">
										<option value="">${message("csh.insurance.root")}</option>
										[#list categorys as category]
											[#if category != insuranceCompany && !children?seq_contains(category)]
												<option value="${category.id}"[#if category == insuranceCompany.parent] selected="selected"[/#if]>
													[#if category.grade != 0]
														[#list 1..category.grade as i]
															&nbsp;&nbsp;
														[/#list]
													[/#if]
													${category.name}
												</option>
											[/#if]
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.insurance.name")}:
								</th>
								<td>
									<input type="text" id="name" name="name" value="${insuranceCompany.name}" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.insurance.phoneNo")}:
								</th>
								<td>
									<input type="text" name="phoneNo" value="${insuranceCompany.phoneNo}"  class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.insurance.area")}:
								</th>
								<td>
									<input type="hidden" id="areaId"  value="${(insuranceCompany.area.id)!}"  name="areaId" treePath="${(insuranceCompany.area.treePath)!}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.insurance.address")}:
								</th>
								<td>
									<input type="text" name="address" value="${insuranceCompany.address}"  class="text" maxlength="20" />
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