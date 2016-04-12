<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.clearingRecord.details")}</title>
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
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.clearingRecord")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.clearingRecord.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.clearingRecord.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.clearingRecord.details")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
							<tr>
								<td>${message("csh.clearingRecord.clearingSn")}</td>
								<td>
									${clearingRecord.clearingSn}
								</td>
								<td>${message("csh.clearingRecord.clearingStatus")}</td>
								<td>
									[#if clearingRecord.clearingStatus??]
										${message("csh.commonEnum.ClearingStatus."+clearingRecord.clearingStatus)}
									[#else]
										-
									[/#if]	
								</td>
								<td>${message("csh.clearingRecord.amountOfCurrentPeriod")}</td>
								<td>
									${clearingRecord.amountOfCurrentPeriod}
								</td>
							</tr>
							<tr>
								<td>${message("csh.clearingRecord.reduce")}</td>
								<td>
									${clearingRecord.reduce}
								</td>
								<td>${message("csh.clearingRecord.periodBeginDate")}</td>
								<td>
									[#if clearingRecord.periodBeginDate??]
										${clearingRecord.periodBeginDate}
									[#else]
										-
									[/#if]
								</td>
								<td>${message("csh.clearingRecord.periodEndDate")}</td>
								<td>
									[#if clearingRecord.periodEndDate??]
										${clearingRecord.periodEndDate}
									[#else]
										-
									[/#if]
								</td>
							</tr>
								<tr>
								<td>${message("csh.clearingRecord.comments")}</td>
								<td>
									${clearingRecord.comments}
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
									[#list clearingRecord.carServiceRecords as carServiceRecord]
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
</body>
</html>