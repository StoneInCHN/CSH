<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.order.details")}</title>
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.order")}</a> 
				<span class="divider">/</span> 
				<a href="#" onclick="history.go(-1)"><i class="fa fa-list"></i>${message("csh.order.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.order.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.order.details")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.order.sn")}:
								</th>
								<td>
									${order.sn}
								</td>
							</tr>
                     		<tr>
								<th>
									${message("csh.common.createDate")}:
								</th>
								<td>
									[#if order.createDate??]
										<span title="${order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${order.createDate}</span>
									[#else]
										-
									[/#if]
								</td>
							</tr>												
							<tr>
								<th>
									${message("csh.order.orderStatus")}:
								</th>
								<td>
									[#if order.orderStatus??]
										${message("csh.order.orderStatus."+order.orderStatus)}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.order.paymentStatus")}:
								</th>
								<td>
									[#if order.paymentStatus??]
										${message("csh.order.paymentStatus."+order.paymentStatus)}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.order.shippingStatus")}:
								</th>
								<td>
									[#if order.shippingStatus??]
										${message("csh.order.shippingStatus."+order.shippingStatus)}
									[/#if]
								</td>
							</tr>							
							<tr>
								<th>
									${message("csh.order.afterSalesStatus")}:
								</th>
								<td>
									[#if order.afterSalesStatus??]
										${message("csh.order.afterSalesStatus."+order.afterSalesStatus)}
									[#else]
										-
									[/#if]
								</td>
							</tr>							
							<tr>
								<th>
									${message("csh.order.tenantName")}:
								</th>
								<td>
									${tenantName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.order.consignee")}:
								</th>
								<td>
									${order.consignee}
								</td>
							</tr>							
							<tr>
								<th>
									${message("csh.order.phone")}:
								</th>
								<td>
									${order.phone} 
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.order.operator")}:
								</th>
								<td>
									${order.operator.userName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.order.paymentType")}:
								</th>
								<td>
									[#if order.paymentType??]
										${message("csh.order.paymentType."+order.paymentType)}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.order.shippingMethod")}:
								</th>
								<td>
									[#if order.shippingMethod??]
										${order.shippingMethod.name}
									[/#if]
								</td>
							</tr>		
							<tr>
								<th>
									${message("csh.order.zipCode")}:
								</th>
								<td>
									${order.zipCode}
								</td>
							</tr>	
							<tr>
								<th>
									${message("csh.order.address")}:
								</th>
								<td>
									${order.address}
								</td>
							</tr>	
							<tr>
								<th>
									${message("csh.order.memo")}:
								</th>
								<td>
									${order.memo}
								</td>
							</tr>	
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="button" class="button" value="${message("csh.common.back")}" onclick="history.go(-1)" />
								</td>
							</tr>
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