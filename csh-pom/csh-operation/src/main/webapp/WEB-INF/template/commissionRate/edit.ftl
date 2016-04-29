<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.commissionRate.edit")}</title>
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
			distributorRate: {
				required: true
			},
			platformRate: {
				required: true
			},
			tenantRate: {
				required: true
			},
			rateFormOtherTenant: {
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
				<a ><i class="fa fa-android"></i> ${message("csh.main.commissionRate")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.commissionRate.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.commissionRate.edit")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-pencil-square-o"></i>${message("csh.commissionRate.edit")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="update.jhtml" method="post" class="form-horizontal" enctype="multipart/form-data" role="form">
                     	<input type="hidden" name="id"  value="${commissionRate.id}" />
                     	<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.commissionRate.distributorRate")}:
								</th>
								<td>
									<input type="text" id="distributorRate" value="${commissionRate.distributorRate}" name="distributorRate" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.commissionRate.platformRate")}:
								</th>
								<td>
									<input type="text" id="platformRate" value="${commissionRate.platformRate}" name="platformRate" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.commissionRate.tenantRate")}:
								</th>
								<td>
									<input type="text" id="tenantRate" value="${commissionRate.tenantRate}" name="tenantRate" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.commissionRate.rateFormOtherTenant")}:
								</th>
								<td>
									<input type="text" id="rateFormOtherTenant" value="${commissionRate.rateFormOtherTenant}" name="rateFormOtherTenant" class="text" maxlength="20" />
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