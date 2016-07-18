<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.tenantAccount.edit")}</title>
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
	var $editPassword = $("#editPassword");
	var $password = $("#password");
	var $rePassword = $("#rePassword");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			email: {
				required: true,
				email: true
			},
			adminStatus: "required",
			password: {
				minlength: 6
			},
			rePassword: {
				equalTo: "#password"
			}
		},
		messages: {
			password: {
				pattern: "${message("csh.reg.passwordIllegal")}",
				minlength: "${message("csh.password.minlength",6)}"
			},
			repassword:{
					 equalTo:"${message("csh.reg.passwordIllegal")}"
			}
		}
	});
	$editPassword.click(function(){
		$password.parent().parent().show();
		$rePassword.parent().parent().show();
	});
	
});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.tenantAccount")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.tenantAccount.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.tenantAccount.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.tenantAccount.edit")}</div>
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
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.tenantAccount.userName")}:
								</th>
								<td>
									${tenantAccount.userName}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantAccount.realName")}:
								</th>
								<td>
									${tenantAccount.realName}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.tenantAccount.loginDate")}:
								</th>
								<td>
									[#if tenantAccount.loginDate??]
										<span title="${tenantAccount.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${tenantAccount.loginDate}</span>
									[#else]
											-
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantAccount.accoutStatus")}:
								</th>
								<td>
									<select name="accoutStatus" class="text">
										<option value="">${message("csh.tenantAccount.accoutStatus.select")}</option>
										<option value="ACTIVED" [#if tenantAccount.accoutStatus== "ACTIVED" ] selected="selected" [/#if]>${message("csh.tenantAccount.accoutStatus.ACTIVED")}</option>
										<option value="LOCKED" [#if tenantAccount.accoutStatus== "LOCKED" ]selected="selected"[/#if]>${message("csh.tenantAccount.accoutStatus.LOCKED")}</option>
										<option value="DELETE" [#if tenantAccount.accoutStatus== "DELETE" ]selected="selected"[/#if]>${message("csh.tenantAccount.accoutStatus.DELETE")}</option>
									</select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									${message("csh.admin.password")}:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" />
								</td>
							</tr>
							<tr style="display:none">
								<th>
									${message("csh.admin.rePassword")}:
								</th>
								<td>
									<input type="password" name="rePassword" id="rePassword" class="text" maxlength="20" />
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
									<input type="button" id="editPassword" class="button" value="${message("csh.tenantAccount.editPasswordButton")}" />
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