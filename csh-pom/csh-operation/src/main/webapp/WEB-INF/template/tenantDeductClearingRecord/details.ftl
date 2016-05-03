<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.tenantDeductClearingRecord.details")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>

</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.tenantDeductClearingRecord")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.tenantDeductClearingRecord.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.tenantDeductClearingRecord.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.tenantDeductClearingRecord.details")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
							[#if tenantDeductClearingRecord.clearingStatus?? && tenantDeductClearingRecord.clearingStatus ="UNPAID"]
							<tr>
								<button class="btn btn-default pull-right" id="changeStatus">修改结算状态</button>
							</tr>
							[/#if]	
							<tr>
								<th>${message("csh.tenantDeductClearingRecord.clearingSn")}:</th>
								<td>
									${tenantDeductClearingRecord.clearingSn}
								</td>
								<th>${message("csh.tenantDeductClearingRecord.clearingStatus")}:</th>
								<td id="clearingStatus">
									[#if tenantDeductClearingRecord.clearingStatus??]
										${message("csh.commonEnum.ClearingStatus."+tenantDeductClearingRecord.clearingStatus)}
									[#else]
										-
									[/#if]	
								</td>
								<th>${message("csh.tenantDeductClearingRecord.amountOfCurrentPeriod")}:</th>
								<td>
									${tenantDeductClearingRecord.amountOfCurrentPeriod}
								</td>
							</tr>
						</table>
						<hr/>
						<table id="listTable" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>
										${message("csh.carServiceRecord.recordNo")}
									</th>
									<th>
										${message("csh.carServiceRecord.carService")}
									</th>
									<th>
										${message("csh.carServiceRecord.endUser")}
									</th>
									<th>
										${message("csh.carServiceRecord.paymentType")}
									</th>
									<th>
										${message("csh.carServiceRecord.paymentDate")}
									</th>
									<th>
										${message("csh.carServiceRecord.chargeStatus")}
									</th>
									<th>
										${message("csh.carServiceRecord.price")}
									</th>
												
									<th>
										${message("csh.carServiceRecord.tenantName")}
									</th>
									<th>
										${message("csh.carServiceRecord.clearingDate")}
									</th>
												
									</tr>
								</thead>
								<tbody>
									[#list tenantDeductClearingRecord.carServiceTenantDeductRecords as carServiceRecord]
									<tr>
										<td>
											${carServiceRecord.recordNo}
										</td>
									<td>
										[#if carServiceRecord.carService??]
											${carServiceRecord.carService.serviceName}
										[#else]
											-
										[/#if]
									</td>
									<td>
										[#if carServiceRecord.endUser??]
											${carServiceRecord.endUser.userName}
										[#else]
											-
										[/#if]
									</td>
									<td>
										[#if carServiceRecord.paymentType??]
											${message("csh.commonEnum.paymentType."+carServiceRecord.paymentType)}
										[#else]
											-
										[/#if]
									</td>
									<td>
										[#if carServiceRecord.paymentDate??]
											<span title="${carServiceRecord.paymentDate?string("yyyy-MM-dd HH:mm:ss")}">${carServiceRecord.paymentDate}</span>
										[#else]
											-
										[/#if]
									</td>
									<td>
										[#if carServiceRecord.chargeStatus??]
											${message("csh.commonEnum.ChargeStatus."+carServiceRecord.chargeStatus)}
										[#else]
											-
										[/#if]
									</td>
									<td>
										${carServiceRecord.price}
									</td>
									<td>
										${carServiceRecord.tenantName}
									</td>
									<td>
										[#if carServiceRecord.clearingDate??]
										<span title="${carServiceRecord.clearingDate?string("yyyy-MM-dd HH:mm:ss")}">${carServiceRecord.clearingDate}</span>
										[#else]
											-
										[/#if]
									</td>
												
								</tr>
							</tbody>
							[/#list]
						</table>
                  </div>
                </div>
              </div>  
            </div>
          </div>
        </div>
	   </div>
	</div>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
[#if tenantDeductClearingRecord.clearingStatus?? && tenantDeductClearingRecord.clearingStatus ="UNPAID"]
<script type="text/javascript">
	var $changeStatus =$("#changeStatus");
	$changeStatus.click(function(){
		 $.messager.model = { 
	        ok:{ text: "确定", classed: 'btn-default' },
	        cancel: { text: "取消", classed: 'btn-error' }
	      };
	      $.messager.confirm("修改结算状态", "确定要将该结算单的状态从 [未付款] 改成 [已付款] 吗?? ", function() { 
	        $.ajax({
			   type: "POST",
			   url: "changeStatus.jhtml",
			   data:{
			   		id:${tenantDeductClearingRecord.id}
			   },
			   success: function(result){
			     if(result.type == "success"){
			     	$("#clearingStatus").html("已付款")
			     }
			      $.messager.popup(result.content);
			   }
			});
	      });
	})
</script>
[/#if]	
</body>
</html>