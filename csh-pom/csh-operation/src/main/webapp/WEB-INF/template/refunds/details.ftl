<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.order.refund")}</title>
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
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.order")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.order.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.order.refund")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.order.refund")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="updateRefunds.jhtml" method="post">
                    	<input type="hidden" name="id" value="${refunds.id}">
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.order.sn")}:
								</th>
								<td>
									${refunds.order.sn}
								</td>
								<th>
									订单${message("csh.common.createDate")}:
								</th>
								<td>
									[#if refunds.order.createDate??]
										<span title="${refunds.order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${refunds.order.createDate}</span>
									[#else]
										-
									[/#if]
								</td>
							</tr>			
						</table>
						<hr>
						<table class="input tabContent">
							<tr>
								<th>
									退货单号:
								</th>
								<td>
									${returns.sn}
								</td>
								<th>
									退货单${message("csh.common.createDate")}:
								</th>
								<td>
									[#if returns.createDate??]
										<span title="${returns.createDate?string("yyyy-MM-dd HH:mm:ss")}">${returns.createDate}</span>
									[#else]
										-
									[/#if]
								</td>
							</tr>		
							<tr>
								<th>退货单状态:</th>
								<td>
										[#if returns.returnsStatus??]${message("csh.returns.status."+returns.returnsStatus)}[/#if]
								</td>
								<th>申请退款金额:</th>
								<td>￥${returns.returnAmount}</td>
							</tr>							
						</table>
						<table class="table table-bordered" style="width:80%;border: 1px solid #ddd;margin:20px auto 30px 5%;">
							<thead>
									<tr>
										<th>退货商品编号</th>
										<th>退货商品名称</th>
										<th>退货数量</th>
									</tr>
							</thead>
							<tbody>
								[#list returnsItems as returnsItem]
									<tr>
										<td>${returnsItem.sn}</td>
										<td>${returnsItem.name}</td>
										<td>${returnsItem.quantity}</td>					
									</tr>
								[/#list]
							</tbody>
						</table>
						<hr>
						<table class="input tabContent">
							<tr>
								<th>退款单号:</th>
								<td>${refunds.sn}</td>
								<th>退款单${message("csh.common.createDate")}:</th>
								<td>
									[#if refunds.createDate??]
										<span title="${refunds.createDate?string("yyyy-MM-dd HH:mm:ss")}">${refunds.createDate}</span>
									[#else]
										-
									[/#if]
								</td>
							</tr>
							<tr>
								<th>方式:</th>
								<td>
											[#if refunds.method??]${message("csh.refunds.method."+refunds.method)}[/#if]
								</td>
								<th>支付方式:</th>
								<td>${refunds.paymentMethod}</td>
							</tr>		
							<tr>
								<th>退款单状态:</th>
								<td>	
											[#if refunds.refundsStatus??]${message("csh.refunds.status."+refunds.refundsStatus)}[/#if]
								</td>
								<th>退款金额:</th>
								<td>￥${refunds.amount}</td>
							</tr>	
							<tr>
					    		<th>${message("csh.order.memo")}:</th>
					    		<td  colspan="3">
					    			${refunds.memo}
					    		</td>	  			    		
					    	</tr>			
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
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