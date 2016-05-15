<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.coupon.edit")}</title>
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
			isEnabled: {
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.coupon")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.coupon.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.coupon.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.coupon.edit")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post">
						<input type="hidden" name="id" value="${coupon.id}" />
						<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.remark")}:
								</th>
								<td>
									<input type="text" name="remark" value="${coupon.remark}" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.amount")}:
								</th>
								<td>
									<!-- <input type="text" name="amount" value="${coupon.amount}" class="text" maxlength="20" /> -->
									${coupon.amount}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.overDueType")}:
								</th>
								<td>
									<!--<select id="overDueType" name="overDueType">
										<option value="BYDAY" [#if coupon.overDueType== "BYDAY" ]selected="selected"[/#if]>${message("csh.commonEnum.CouponOverDueType.BYDAY")}</option>
										<option value="BYDATE" [#if coupon.overDueType== "BYDATE" ]selected="selected"[/#if]>${message("csh.commonEnum.CouponOverDueType.BYDATE")}</option>
									</select> -->
									[#if coupon.overDueType== "BYDAY" ]${message("csh.commonEnum.CouponOverDueType.BYDAY")}[/#if]
									[#if coupon.overDueType== "BYDATE" ]${message("csh.commonEnum.CouponOverDueType.BYDATE")}[/#if]
								</td>
							</tr>
							[#if coupon.overDueType== "BYDAY" ]
							<tr >
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.overDueDay")}:
								</th>
								<td>
									<input type="text" id="overDueDay" name="overDueDay" value="${coupon.overDueDay}" class="text" maxlength="20" />
								</td>
							</tr>
							[/#if]
							[#if coupon.overDueType== "BYDATE" ]
							<tr >
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.overDueTime")}:
								</th>
								<td>
									<input type="text" id="overDueTime" name="overDueTime" value="${coupon.overDueTime}" class="text Wdate" onfocus="WdatePicker();" readonly maxlength="20" />
								</td>
							</tr>
							[/#if]
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.deadlineTime")}:
								</th>
								<td>
									<input type="text" name="deadlineTime" class="text Wdate" value="${coupon.deadlineTime}" onfocus="WdatePicker();" readonly maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.counts")}:
								</th>
								<td>
									<input type="text" name="counts" class="text" maxlength="20" value="${coupon.counts}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.remainNum")}:
								</th>
								<td>
									<input type="text" name="remainNum" value="${coupon.remainNum}" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.coupon.isEnabled")}:
								</th>
								<td>
									<select name="isEnabled">
										<option value="true" [#if coupon.isEnabled == true]selected="selected"[/#if]>${message("csh.commonEnum.Status.ENABLE")}</option>
										<option value="false" [#if coupon.isEnabled == false] selected="selected"[/#if]>${message("csh.commonEnum.Status.DISABLE")}</option>
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