<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("admin.admin.add")}</title>
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
<style type="text/css">
	#selectDistributor_tr{
		display:none;
	}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $isDistributor=$(':input[name="isDistributor"]'); 
	var $selectDistributor_tr = $("#selectDistributor_tr");
	// 表单验证
	$inputForm.validate({
		rules: {
			username: {
				required: true,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "check_username.jhtml",
					cache: false
				}
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			name: "required",
			adminStatus: "required",
			distributorId:"required"
		},
		messages: {
			username: {
				remote: "${message("admin.validate.exist")}"
			},
			password: {
				pattern: "${message("admin.validate.illegal")}"
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.listDistributorAcount")}</a> 
				<span class="divider">/</span> 
				<a href="list4distributor.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.admin.list4distributor")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("admin.admin.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.admin.base4distributor")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save4distributor.jhtml" method="post" class="form-horizontal" role="form">
                     	<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.admin.username")}:
								</th>
								<td>
									<input type="text" name="username" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.admin.password")}:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.admin.rePassword")}:
								</th>
								<td>
									<input type="password" name="rePassword" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.admin.name")}:
								</th>
								<td>
									<input type="text" name="name" class="text" maxlength="200" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.admin.email")}:
								</th>
								<td>
									<input type="text" name="email" class="text" maxlength="200" />
								</td>
							</tr>
							<tr >
								<th>
									<span class="requiredField">*</span>请选择分销商:
								</th>
								<td>
									<span id="distributorName" style="margin-right:30px"></span><button id="selectDistributor"  class="btn btn-default">选择分销商</button>
									<input type="hidden" class="text" id="distributorId" name="distributorId">
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.admin.adminStatus")}:
								</th>
								<td>
									<input type="radio" value="actived" name="adminStatus" checked="checked" />${message("csh.admin.adminStatus.actived")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" value="locked" name="adminStatus" />${message("csh.admin.adminStatus.locked")}
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
									<input type="button" class="button" value="${message("csh.common.back")}" onclick="location.href='list4distributor.jhtml'" />
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
<script type="text/javascript">
		$(function(){
				var $selectDistributor = $("#selectDistributor");
				
				$selectDistributor.click(function(){
					var $operationModal = window.parent.$('#operationModal');
					var $operationModalIframe= window.parent.$('#operationModalIframe');
					$operationModal.find(".modal-title").text("选择分销商");
					$operationModal.modal("show");
					$operationModal.attr("data-ids","&"+$("#listTable input[name='ids']:checked").serialize());
					$operationModalIframe.attr("src","${base}/console/admin/selectDistributor.jhtml");
					$operationModalIframe.css("height",380);
				})
		
		})
	
	</script>
</body>
</html>