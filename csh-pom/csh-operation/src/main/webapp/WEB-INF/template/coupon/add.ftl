<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.coupon.add")}</title>
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
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $overDueType = $("#overDueType");
	var $overDueDay = $("#overDueDay");
	var $overDueTime = $("#overDueTime");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			remark: {
				required: true
			},
			amount:{
				required: true,
				number:true,
				min:1
			},
			counts:{
				required: true,
				number:true,
				min:1
			},
			overDueType:{
				required: true
			},
			isEnabled: {
				required: true
			}
		}	
	});
	
	$overDueType.change(function(){
		if("BYDAY" == $(this).val()){
			$overDueDay.parent().parent().show();
			$overDueDay.rules("add",{
				 required: true,
				 number:true,
				min:1
			});
			$overDueTime.parent().parent().hide();
			$overDueTime.rules("remove");  
		}else if("BYDATE" == $(this).val()){
			$overDueTime.parent().parent().show();
			$overDueTime.rules("add",{
				 required: true
			});
			$overDueDay.rules("remove");  
			$overDueDay.parent().parent().hide();
		}else{
			$overDueDay.parent().parent().hide();
			$overDueTime.parent().parent().hide();
			$overDueDay.rules("remove"); 
			$overDueTime.rules("remove");  
		}
		
	})
	
});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.coupon")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.coupon.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("csh.coupon.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.coupon.add")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form">
                     	<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.remark")}:
								</th>
								<td>
									<input type="text" name="remark" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.amount")}:
								</th>
								<td>
									<input type="text" name="amount" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.overDueType")}:
								</th>
								<td>
									<select id="overDueType" name="overDueType">
										<option value="">请选择优惠券过期方式</option>
										<option value="BYDAY">${message("csh.commonEnum.CouponOverDueType.BYDAY")}</option>
										<option value="BYDATE">${message("csh.commonEnum.CouponOverDueType.BYDATE")}</option>
									</select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.overDueDay")}:
								</th>
								<td>
									<input type="text" id="overDueDay" name="overDueDay" class="text" maxlength="20" />
								</td>
							</tr>
							<tr style="display:none">
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.overDueTime")}:
								</th>
								<td>
									<input type="text" id="overDueTime" name="overDueTime" class="text Wdate" onfocus="WdatePicker();" readonly maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.deadlineTime")}:
								</th>
								<td>
									<input type="text" name="deadlineTime" class="text Wdate" onfocus="WdatePicker();" readonly maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.counts")}:
								</th>
								<td>
									<input type="text" name="counts" class="text" maxlength="20" />
								</td>
							</tr>
						<!--	<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.remainNum")}:
								</th>
								<td>
									<input type="text" name="remainNum" class="text" maxlength="20" />
								</td>
							</tr> -->
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.isEnabled")}:
								</th>
								<td>
									<select name="isEnabled">
										<option value="">${message("csh.commonEnum.status.select")}</option>
										<option value="true">${message("csh.commonEnum.Status.ENABLE")}</option>
										<option value="false">${message("csh.commonEnum.Status.DISABLE")}</option>
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