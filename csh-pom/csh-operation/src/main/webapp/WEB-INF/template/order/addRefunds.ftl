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
$().ready(function() {

	var $inputForm = $("#inputForm");	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			account: {
				required: true
			},		
			payee: {
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
                    <form id="inputForm" action="saveRefunds.jhtml" method="post">
                    	<input type="hidden" name="orderId" value="${order.id}">
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.order.sn")}:
								</th>
								<td>
									${order.sn}
								</td>
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
								<td  colspan="3">
									${order.memo}
								</td>
							</tr>	
							<tr>
								<th>${message("csh.refunds.bank")}:</th>
								<td>
									   <input type="text" name="bank" class="text" maxlength="20" />
								</td>
								<th><span class="requiredField">*</span>${message("csh.refunds.account")}:</th>
								<td>
								       <input type="text" name="account" class="text" maxlength="20" />
								</td>
							</tr>	
					    	<tr>
								<th><span class="requiredField">*</span>${message("csh.refunds.amount")}:</th>
								<td>
									   <input type="text" name="amount" class="text"  value="${order.amountPaid}"  readonly="readonly"/>
								</td>
								<th><span class="requiredField">*</span>${message("csh.refunds.payee")}:</th>
								<td>
										<input type="text" name="payee" class="text" maxlength="20" />
								</td>
							</tr>		    	
					    	<tr>
					    		<th>${message("csh.refunds.method")}:</th>
					    		<td> 
								  	<select name="method">
										[#list refundsMethods as refundsMethod]
										<option value="${refundsMethod}">${message("csh.refunds.method." + refundsMethod)}</option>
										[/#list]
									</select>
					    		</td>
								<th>${message("csh.refunds.paymentMethod")}:</td>
								<td>   				
									   <input class="easyui-combobox" data-options="
										valueField: 'label',
										textField: 'value',
										data: [{
											label: 'ALIPAY',
											value: '支付宝'
										},{
											label: 'WECHAT',
											value: ' 微信'
										},{
											label: 'WALLET',
											value: '钱包余额'
										}],
										prompt:'${message("jlr.common.please.select")}',panelMaxHeight:100"   value="paymentType" style="width:110px;" readonly='readonly'/>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>${message("csh.order.memo")} :</th>
					    		<td  colspan="3">
					    				<input type="text" name="memo" class="text" maxlength="200" />
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