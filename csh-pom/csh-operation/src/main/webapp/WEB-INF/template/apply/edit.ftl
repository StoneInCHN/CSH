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
			applyStatus: "required",
			versionConfig:"required",
			distributorId:"required"
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
						<input type="hidden" name="id" value="${apply.id}" />
						<table class="input">
							<tr>
								<th>
									${message("csh.apply.tenantName")}:
								</th>
								<td>
									${apply.tenantName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.contactPerson")}:
								</th>
								<td>
									${apply.contactPerson}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.contactPhone")}:
								</th>
								<td>
									${apply.contactPhone}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.address")}:
								</th>
								<td>
									${apply.address}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.license")}:
								</th>
								<td>
									<a href="${base}/upload/license/${apply.license}" target="1024"><img src="${base}/upload/license/${apply.license}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.apply.license")}"></a>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.photo")}:
								</th>
								<td>
									<a href="${base}/upload/license/${apply.photo}" target="1024"><img src="${base}/upload/license/${apply.photo}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.apply.photo")}"></a>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.area")}:
								</th>
								<td>
									${apply.area}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.distributor")}:
								</th>
								<td>
									<input type="hidden" class="text" id="distributorId" name="distributorId"><span id="distributorName" style="margin-right:30px"></span><button id="selectDistributor"  class="btn btn-default">选择分销商</button>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.versionConfig")}:
								</th>
								<td>
									<select name="versionConfig">
										<option value="">${message("csh.apply.versionConfig.select")}</option>
										[#list versions as version]
										<option value="${version.id}">${version.versionName}</option>
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.applyStatus")}:
								</th>
								<td>
									<select name="applyStatus">
										<option value="">${message("csh.apply.applyStatus.select")}</option>
										<option value="AUDIT_PASSED">${message("csh.apply.applyStatus.AUDIT_PASSED")}</option>
										<option value="AUDIT_FAILED">${message("csh.apply.applyStatus.AUDIT_FAILED")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.notes")}:
								</th>
								<td>
									<textarea  name="notes" rows="6" cols="60">${apply.notes}</textarea>
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
	<script type="text/javascript">
		$(function(){
				var $selectDistributor = $("#selectDistributor");
				
				$selectDistributor.click(function(){
					var $deviceBinding = window.parent.$('#operationModal');
					var $operationModalIframe= window.parent.$('#operationModalIframe');
					$deviceBinding.find(".modal-title").text("选择分销商");
					$deviceBinding.modal("show");
					$deviceBinding.attr("data-ids","&"+$("#listTable input[name='ids']:checked").serialize());
					$operationModalIframe.attr("src","${base}/console/apply/selectDistributor.jhtml");
					$operationModalIframe.css("height",380);
				})
		
		})
	
	</script>

</body>
</html>