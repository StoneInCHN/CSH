<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("csh.role.edit")}</title>
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
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
.authorities th a{
	color:#666;
	font-weight:bold;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");
	
	
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name:{
				required: true
			
			},
			authorities: "required",
			description:{
				maxlength:200
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
				<a><i class="fa fa-user"></i> ${message("csh.main.role")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.role.list")}</a>
				<span class="divider">/</span> 
				<a class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.role.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.role.edit")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                  		<form id="inputForm" action="update.jhtml" method="post">
							<input type="hidden" name="id" value="${role.id}" />
							<input type="hidden" id="isSystem" value="${role.isSystem}" />
							<table class="input">
								<tr>
									<th>
										<span class="requiredField">*</span>${message("csh.role.name")}:
									</th>
									<td>
										<input type="text" name="name" class="text" value="${role.name}" maxlength="200" />
									</td>
								</tr>
								<tr>
									<th>
										${message("csh.role.description")}:
									</th>
									<td>
										<textarea  name="description" class="text" maxlength="200">${role.description}</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
								<tr class="authorities">
									<th>
										<a href="javascript:;" class="selectAll" title="${message("csh.role.selectAll")}">${message("csh.role.systemGroup")}</a>
									</th>
									<td>
										<span class="fieldSet">
											<label>
												<input type="checkbox" name="authorities" value="admin:account" [#if role.authorities?seq_contains("admin:account")] checked="checked"[/#if]/><span>${message("csh.account.settingGroup")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:tenantInfo4distributor" [#if role.authorities?seq_contains("admin:tenantInfo4distributor")] checked="checked"[/#if] /><span>${message("csh.main.tenantInfo")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:clearingRecord4distributor" [#if role.authorities?seq_contains("admin:clearingRecord4distributor")] checked="checked"[/#if] /><span>${message("csh.main.distributorDeductClearingRecord")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:deviceProvide4distributor" [#if role.authorities?seq_contains("admin:deviceProvide4distributor")] checked="checked"[/#if]/><span>${message("csh.main.deviceInfo.list4distributor")}</span>
											</label>
										</span>
									</td>
								</tr>
								[#if role.isSystem]
									<tr>
										<th>
											&nbsp;
										</th>
										<td>
											<span class="tips">${message("csh.role.editSystemNotAllowed")}</span>
										</td>
									</tr>
								[/#if]
								<tr>
									<th>
										&nbsp;
									</th>
									<td>
										<input type="submit" class="button" value="${message("csh.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
										<input type="button" id="backBtn" class="button" value="${message("csh.common.back")}" onclick="location.href='list.jhtml'" />
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
<script type="text/javascript" >
	$(function(){
		var $isSystem =  $("#isSystem");
		if($isSystem.val() =="true"){
			$(':input').attr("disabled","disabled");	
			$("#backBtn").attr("disabled",false);
		}
	})
</script>
</body>
</html>
