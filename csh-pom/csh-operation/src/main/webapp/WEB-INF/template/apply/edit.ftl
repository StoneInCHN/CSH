<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.apply.edit")}</title>
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
	var $submit = $("#submit");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			notes: "required",
			applyStatus: "required"
		},
		submitHandler:function(form){
			$submit.attr("disabled",true);
			$.ajax({
				url:$inputForm.attr("action"),
				type:"POST",
				data:$inputForm.serialize(),
				dataType:"json",
				cache:false,
				success:function(message){
					$.message(message);
					$submit.attr("disabled",false);
					setTimeout("location.href='details.jhtml?id=${apply.id}'",1000);
				}
			});
		}
	});

});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.apply")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.apply.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.apply.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.apply.base")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post">
						<input type="hidden" name="id" value="${tenantAccount.id}" />
						<table class="input">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantAccount.tenantID")}:
								</th>
								<td>
									<input type="password" realOnly class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantAccount.username")}:
								</th>
								<td>
									<input type="text" realOnly class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantAccount.accoutStatus")}:
								</th>
								<td>
									<input type="password" name="accoutStatus" />
									<select name="accoutStatus">
										<option value="">${message("csh.tenantAccount.accoutStatus.select")}</option>
										<option value="ACTIVED" [#if admin.adminStatus== "ACTIVED" ]checked="checked"[/#if]>${message("csh.tenantAccount.accoutStatus.ACTIVED")}</option>
										<option value="LOCKED" [#if admin.adminStatus== "LOCKED" ]checked="checked"[/#if]>${message("csh.tenantAccount.accoutStatus.LOCKED")}</option>
										<option value="DELETE" [#if admin.adminStatus== "DELETE" ]checked="checked"[/#if]>${message("csh.tenantAccount.accoutStatus.DELETE")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantAccount.realName")}:
								</th>
								<td>
									<input type="text" name="realName" class="text" maxlength="200" value-"${tenantAccount.realName}"/>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" id="submit" class="button" value="${message("csh.common.submit")}" />
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