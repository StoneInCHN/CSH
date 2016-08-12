<div class="easyui-tabs" >   
    <div title="订单信息">   
    	<p style="margin:10px">
    		[#if order.orderStatus == "unconfirmed" && order.paymentStatus == "paid" ]
    			<a class="btn btn-primary" href="#" role="button">确认</a>
    		[/#if]
			<!--
			<a class="btn btn-primary" href="#" role="button">发货</a>
			<a class="btn btn-success" href="#" role="button">完成</a>
			<a class="btn btn-info" href="#" role="button">退款</a>
			<a class="btn btn-warning" href="#" role="button">退货</a>
			<a class="btn btn-danger" href="#" role="button">取消</a>
			-->
    	</p>
       	<table class="table table-striped table-bordered table-hover table-condensed">
 			<tr>
 				<th>订单号</th>
 				<td>${order.sn}</td>
 				<th>创建日期</th>
 				<td>${order.createDate}</td>
 			</tr>
 			<tr>
 				<th>订单状态</th>
 				<td>${message("csh.commonEnum.OrderStatus."+order.orderStatus)}</td>
 				<th>支付状态</th>
				<td>${message("csh.commonEnum.PaymentStatus."+order.paymentStatus)}</td>
 			</tr>
 			<tr>
 				<th>配送状态</th>
				<td>${message("csh.commonEnum.ShippingStatus."+order.shippingStatus)}</td>
 				<th>用户名</th>
 				<td>${order.endUser.userName}</td>
 			</tr>
 			<tr>
 				<th>订单金额</th>
 				<td>${order.amount}</td>
 				<th>已付款</th>
 				<td>${order.amountPaid}</td>
 			</tr>
 			<tr>
 				<th>商品重量</th>
 				<td>${order.weight}</td>
 				<th>商品数量</th>
 				<td>${order.quantity}</td>
 			</tr>
 			<tr>
 				<th>运费</th>
 				<td>${order.freight}</td>
 				<th>支付方式</th>
 				<td>${order.paymentType}</td>
 			</tr>
 			<tr>
 				<th>收货人</th>
 				<td>${order.consignee}</td>
 				<th>电话</th>
 				<td>${order.phone}</td>
 			</tr>
 			<tr>
 				<th>地区</th>
 				<td>${order.areaName}</td>
 				<th>地址</th>
 				<td>${order.address}</td>
 			</tr>
 			<tr>
 				<th>附言</th>
 				<td colspan="3">${order.memo}</td>
 			</tr>
 		</table> 
    </div>   
    <div title="商品信息">   
 		<table class="table  table-bordered table-responsive table-condensed table-hover table-striped">
		       <thead>
		       		<tr>
		       			<th>编号</th>
		       			<th>商品名称</th>
		       			<th>数量</th>
		       			<th>价格</th>
		       			<th>小计</th>
		       			<th>状态</th>
		       		</tr>
		       </thead>
		       <tbody>
					[#if order.orderItems?? && order.orderItems?size>0 ]
						[#list order.orderItems as orderItem]
								<tr>
					       			<td>${orderItem.sn}</td>
					       			<td>${orderItem.name}</td>
					       			<td>${orderItem.quantity}</td>
					       			<td>${orderItem.price}</td>
					       			<td>${orderItem.subtotal}</td>
					       			<td>${orderItem.order.orderStatus}</td>
					       		</tr>
						[/#list]
					[/#if]
		       </tbody>         		
		</table>
    </div>      
    <div title="发货信息" >   
        	<table class="table  table-bordered table-responsive table-condensed table-hover table-striped">
		       <thead>
		       		<tr>
		       			<th>编号</th>
		       			<th>配送方式</th>
		       			<th>物流公司</th>
		       			<th>运单号</th>
		       			<th>收货人</th>
		       			<th>创建日期</th>
		       		</tr>
		       </thead>
		       <tbody>
		       		[#if order.shippings?? && order.shippings?size>0 ]
						[#list order.shippings as shipping]
								<tr>
					       			<td>${shipping.sn}</td>
					       			<td>${shipping.shippingMethod}</td>
					       			<td>${shipping.deliveryCorp}</td>
					       			<td>${shipping.trackingNo}</td>
					       			<td>${shipping.consignee}</td>
					       			<td>${shipping.createDate}</td>
					       		</tr>
						[/#list]
					[/#if]
		       </tbody>         		
		</table>
    </div>   
    <div title="退款信息" >   
     	  <table class="table  table-bordered table-responsive table-condensed table-hover table-striped">
		       <thead>
		       		<tr>
		       			<th>编号</th>
		       			<th>支付方式</th>
		       			<th>退款方式</th>
		       			<th>退款金额</th>
		       			<th>退款日期</th>
		       			<th>状态</th>
		       		</tr>
		       </thead>
		       <tbody>
		       		[#if order.refunds?? && order.refunds?size>0 ]
						[#list order.refunds as refund]
								<tr>
					       			<td>${shipping.sn}</td>
					       			<td>${shipping.paymentMethod}</td>
					       			<td>${shipping.method}</td>
					       			<td>${shipping.amount}</td>
					       			<td>${shipping.consignee}</td>
					       			<td>${shipping.createDate}</td>
					       		</tr>
						[/#list]
					[/#if]
		       </tbody>         		
		</table>
    </div>   
    <div title="退货信息" >   
        	<table class="table table-bordered table-responsive table-condensed table-hover table-striped">
		       <thead>
		       		<tr>
		       			<th>编号</th>
		       			<th>配送方式</th>
		       			<th>物流公司</th>
		       			<th>运单号</th>
		       			<th>收货人</th>
		       			<th>创建日期</th>
		       		</tr>
		       </thead>
		       <tbody>
		       		[#if order.returns?? && order.returns?size>0 ]
						[#list order.returns as return]
								<tr>
					       			<td>${return.sn}</td>
					       			<td>${return.shippingMethod}</td>
					       			<td>${return.deliveryCorp}</td>
					       			<td>${return.trackingNo}</td>
					       			<td>${return.order.consignee}</td>
					       			<td>${return.createDate}</td>
					       		</tr>
						[/#list]
					[/#if]
		       </tbody>         		
		</table>
    </div>   
    <div title="订单日志" >   
        	<table class="table table-bordered table-responsive table-condensed table-hover table-striped">
		       <thead>
		       		<tr>
		       			<th>类型</th>
		       			<th>操作员</th>
		       			<th>操作内容</th>
		       			<th>日期</th>
		       		</tr>
		       </thead>
		       <tbody>
		       		[#if order.orderLogs?? && order.orderLogs?size>0 ]
						[#list order.orderLogs as orderLog]
								<tr>
					       			<td>${message("csh.commonEnum.OrderLogType."+orderLog.type)}</td>
					       			<td>${orderLog.operator}</td>
					       			<td>${orderLog.content}</td>
					       			<td>${orderLog.createDate}</td>
					       		</tr>
						[/#list]
					[/#if]
		       </tbody>         		
		</table>
    </div>  
</div>  