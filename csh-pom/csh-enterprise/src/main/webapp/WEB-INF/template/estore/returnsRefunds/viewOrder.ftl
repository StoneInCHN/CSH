	<div id="viewOrderTab" data-options="tabPosition:'top',headerWidth:100" class="easyui-tabs">  
		<div title="订单详情" style="padding:10px;text-align: centor;">
	    	<table class="table table-striped"  border="0">
	    	<tr>
	    		<th>订单编号:</th>
	    		<td  width="200">
	    			 ${order.sn}
	    		</td>	   
	    		<th>创建时间:</th>
	    		<td>
	    			 ${order.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>订单状态:</th>
	    		<td>
	    			 ${order.orderStatus}
	    		</td>	   
	    		<th  align="right">操作员:</th>
	    		<td>
	    			 ${order.operator.userName}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>支付状态:</th>
	    		<td>
	    			 ${order.paymentStatus}
	    		</td>	   
	    		<th>支付方式:</th>
	    		<td>
	    			 ${order.paymentType}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>配送状态:</th>
	    		<td>
	    			 ${order.shippingStatus}
	    		</td> 	
	    		<th>配送方式:</th>
	    		<td>
	    			 ${order.shippingMethod}
	    		</td>			    		
	    	</tr>
	    	<tr>
	    		<th>收&nbsp;货&nbsp;人 :</th>
	    		<td>
	    			 ${order.consignee} 
	    		</td>	  
	    		<th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编 :</th>
	    		<td>
	    			 ${order.zipCode}
	    		</td>	  			    		
	    	</tr>	
	    	<tr>
	    		<th>地 区 :</th>
	    		<td>
	    			${(order.area.id)!}
	    		</td>	  
	    		<th>地 址 :</th>
	    		<td>
	    			 ${order.address}
	    		</td>	  			    		
	    	</tr>		    	
	    	<tr>
	    		<th>电 话 :</th>
	    		<td>
	    			 ${order.phone}
	    		</td>	  
	    		<th>备 注 :</th>
	    		<td>
	    			 ${order.memo}
	    		</td>	  			    		
	    	</tr>		    	
	    </table>
	    <table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
		<thead>
			<tr>
				<th field="productSn" width="25%"  align="center">${message("csh.product.sn")}</th>
				<th field="name"  width="55%"  align="center">${message("csh.order.name")}</th>
				<th field="quantity" width="10%"   align="center">${message("csh.quantity")}</th>
				<th field="price" width="10%"   align="center">${message("csh.price")}</th>
			</tr>
		</thead>		
		   <tbody>   
		   [#list order.orderItems as orderItem]
		   <tr>   
            	<td>${orderItem.sn}</td>
            	<td>${orderItem.name}</td>
            	<td>${orderItem.quantity}</td>   
            	<td>${orderItem.price}</td>   
          </tr>   
		   [/#list]       
    		</tbody>   
   		</table>
	    </div>
		<div title="发货单" style="padding:10px;text-align: centor;">
			[#list shippings as shipping]
			<table class="table table-striped"  border="0">
	    	<tr>
	    		<th>发货单编号:</th>
	    		<td  width="200">
	    			 ${shipping.sn}
	    		</td>	   
	    		<th>创建时间:</th>
	    		<td>
	    			 ${shipping.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>配送方式:</th>
	    		<td>
	    			 ${shipping.shippingMethod}
	    		</td>	   
	    		<th>物流公司:</th>
	    		<td>
	    			 ${order.deliveryCorp}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>物流公司网址:</th>
	    		<td>
	    			 ${shipping.deliveryCorpUrl}
	    		</td>	   
	    		<th>物流公司代码:</th>
	    		<td>
	    			 ${order.deliveryCorpCode}
	    		</td> 			    		
	    	</tr>	 
	    	<tr>
	    		<th>运单号:</th>
	    		<td>
	    			 ${shipping.trackingNo}
	    		</td>	   
	    		<th>物流费用:</th>
	    		<td>
	    			 ${shipping.freight}
	    		</td> 			    		
	    	</tr>	  
	    	<tr>
	    		<th>收货人:</th>
	    		<td>
	    			 ${shipping.consignee}
	    		</td>	   
	    		<th>地区:</th>
	    		<td>
	    			 ${shipping.area}
	    		</td> 			    		
	    	</tr>	
	    	<tr>
	    		<th>地址:</th>
	    		<td>
	    			 ${shipping.address}
	    		</td>	   
	    		<th>邮编:</th>
	    		<td>
	    			 ${shipping.zipCode}
	    		</td> 			    		
	    	</tr>	   
	    	<tr>
	    		<th>电话:</th>
	    		<td>
	    			 ${shipping.phone}
	    		</td>	   
	    		<th>操作员:</th>
	    		<td>
	    			 ${shipping.operator}
	    		</td> 			    		
	    	</tr>	 
	    	<tr>
	    		<th>备注 :</th>
	    		<td  colspan="3">
	    			<input type="text" class="easyui-textbox" value="${shipping.memo}" data-options="multiline:true,height:80,width:440" disabled="disabled"/>
	    		</td>	  			    		
	    	</tr>			    	  	   	
	    	</table>	
		    <table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
			 <thead>
				<tr>
					<th field="productSn" width="25%"  align="center">商品编号</th>
					<th field="name"  width="25%"  align="center">商品名称</th>
					<th field="quantity" width="25%"   align="center">数量</th>
					<th field="price" width="25%"   align="center">发货单编号</th>
				</tr>
			</thead>		
			   <tbody>   
			   [#list shipping.shippingItems as shippingItem]
			   <tr>   
	            	<td>${shippingItem.sn}</td>
	            	<td>${shippingItem.name}</td>
	            	<td>${shippingItem.quantity}</td>   
	            	<td>${shippingItem.shipping.sn}</td>   
	          </tr>   
			   [/#list]       
	    		</tbody>   
	   		</table>   
	   		[#break /]	
			[/#list] 
	    </div>
		<div title="退货单" style="padding:10px;text-align: centor;">
	    	[#list returns as return]
			<table class="table table-striped"  border="0">
	    	<tr>
	    		<th>退货单编号:</th>
	    		<td  width="200">
	    			 ${return.sn}
	    		</td>	   
	    		<th>创建时间:</th>
	    		<td>
	    			 ${return.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>配送方式:</th>
	    		<td>
	    			 ${return.shippingMethod}
	    		</td>	   
	    		<th>物流公司:</th>
	    		<td>
	    			 ${return.deliveryCorp}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>运单号:</th>
	    		<td>
	    			 ${return.trackingNo}
	    		</td>	   
	    		<th>物流费用:</th>
	    		<td>
	    			 ${return.freight}
	    		</td> 			    		
	    	</tr>	  
	    	<tr>
	    		<th>发货人:</th>
	    		<td>
	    			 ${return.shipper}
	    		</td>	   
	    		<th>地区:</th>
	    		<td>
	    			 ${return.area}
	    		</td> 			    		
	    	</tr>	
	    	<tr>
	    		<th>地址:</th>
	    		<td>
	    			 ${return.address}
	    		</td>	   
	    		<th>邮编:</th>
	    		<td>
	    			 ${return.zipCode}
	    		</td> 			    		
	    	</tr>	   
	    	<tr>
	    		<th>电话:</th>
	    		<td>
	    			 ${return.phone}
	    		</td>	   
	    		<th>操作员:</th>
	    		<td>
	    			 ${return.operator}
	    		</td> 			    		
	    	</tr>	 
	    	<tr>
	    		<th>备注 :</th>
	    		<td  colspan="3">
	    			<input type="text" class="easyui-textbox" value="${return.memo}" data-options="multiline:true,height:80,width:440" disabled="disabled"/>
	    		</td>	  			    		
	    	</tr>			   
	    	</table>
	    	<table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
			 <thead>
				<tr>
					<th field="productSn" width="25%"  align="center">商品编号</th>
					<th field="name"  width="25%"  align="center">商品名称</th>
					<th field="quantity" width="25%"   align="center">数量</th>
					<th field="price" width="25%"   align="center">发货单编号</th>
				</tr>
			</thead>		
			   <tbody>   
			   [#list return.returnsItems as returnsItem]
			   <tr>   
	            	<td>${returnsItem.sn}</td>
	            	<td>${returnsItem.name}</td>
	            	<td>${returnsItem.quantity}</td>   
	            	<td>${returnsItem.returns.sn}</td>   
	          </tr>   
			   [/#list]       
	    		</tbody>   
	   		</table>   	
	    	[/#list]
	    </div>
		<div title="退款单" style="padding:10px;text-align: centor;">
		    	[#list refunds as refund]
				<table class="table table-striped"  border="0">
		    	<tr>
		    		<th>退款单编号:</th>
		    		<td  width="200">
		    			 ${refund.sn}
		    		</td>	   
		    		<th>创建时间:</th>
		    		<td>
		    			 ${refund.createDate?string("yyyy-MM-dd HH:mm:ss")}
		    		</td> 			    		
		    	</tr>
		    	<tr>
		    		<th>支付方式:</th>
		    		<td>
		    			 ${refund.paymentMethod}
		    		</td>	   
		    		<th>退款银行:</th>
		    		<td>
		    			 ${refund.bank}
		    		</td> 			    		
		    	</tr>
		    	<tr>
		    		<th>退款账号:</th>
		    		<td>
		    			 ${refund.account}
		    		</td>	   
		    		<th>退款金额:</th>
		    		<td>
		    			 ${refund.amount}
		    		</td> 			    		
		    	</tr>	    	
		    	<tr>
		    		<th>收款人:</th>
		    		<td>
		    			 ${refund.payee}
		    		</td>	   
		    		<th>操作员:</th>
		    		<td>
		    			 ${refund.operator}
		    		</td> 			    		
		    	</tr>	
		    	<tr>
		    		<th>备注 :</th>
		    		<td  colspan="3">
		    			<input type="text" class="easyui-textbox" value="${refund.memo}" data-options="multiline:true,height:80,width:440" disabled="disabled"/>
		    		</td>	  			    		
		    	</tr>			    	
		    	</table>
		    	[/#list]
	    </div>
	    <div title="操作日志" style="padding:10px;text-align: centor;">
			   <table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
				<thead>
					<tr>
						<th field="productSn" width="25%"  align="center">时间</th>
						<th field="name"  width="25%"  align="center">操作</th>
						<th field="quantity" width="25%"   align="center">操作员</th>
						<th field="price" width="25%"   align="center">备注</th>
					</tr>
				</thead>		
				   <tbody>   
				   [#list orderLogs as orderLog]
				   <tr>   
		            	<td>${orderLog.createDate}</td>
		            	<td>${orderLog.type}</td>
		            	<td>${orderLog.operator}</td>   
		            	<td>${orderLog.content}</td>   
		          </tr>   
				   [/#list]       
		    		</tbody>   
		   		</table>
   		</div>
    </div>