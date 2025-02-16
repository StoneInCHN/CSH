	<div class="easyui-tabs">  
		<div title="${message("csh.order")}${message("csh.details")}" >
	    	<table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.order.sn")}:</th>
	    		<td  width="200">
	    			 ${order.sn}
	    		</td>	   
	    		<th>${message("csh.createDate")}:</th>
	    		<td>
	    			 ${order.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
    		<tr>
	    		<th>${message("csh.order")}${message("csh.status")}:</th>
	    		<td>
	    			 ${message("csh.commonEnum.OrderStatus."+order.orderStatus)}
	    		</td>	   
	    		<th  align="right">${message("csh.operator")}:</th>
	    		<td>
	    			 ${order.operator.userName}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.order.paymentStatus")}:</th>
	    		<td>
	    			 ${message("csh.commonEnum.PaymentStatus."+order.paymentStatus)}
	    		</td>	   
	    		<th>${message("csh.order.paymentType")}:</th>
	    		<td>
	    			 ${message("csh.carServiceRecord.paymentType."+"${order.paymentType}")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.order.shippingStatus")}:</th>
	    		<td>
	    			 ${message("csh.commonEnum.ShippingStatus."+order.shippingStatus)}
	    		</td> 	
	    		<th>${message("csh.shippingManager.shippingMethod")}:</th>
	    		<td>
	    			 ${order.shippingMethod.name}
	    		</td>			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.order.consignee")} :</th>
	    		<td>
	    			 ${order.consignee} 
	    		</td>	  
	    		<th>${message("csh.zipCode")} :</th>
	    		<td>
	    			 ${order.zipCode}
	    		</td>	  			    		
	    	</tr>	
	    	<tr>
	    		<th>${message("csh.tenantInfo.area")} :</th>
	    		<td>
	    			${(order.area.id)!}
	    		</td>	  
	    		<th>${message("csh.address")} :</th>
	    		<td>
	    			 ${order.address}
	    		</td>	  			    		
	    	</tr>		    	
	    	<tr>
	    		<th>${message("csh.phoneNumber")}:</th>
	    		<td>
	    			 ${order.phone}
	    		</td>	  
	    		<th>${message("csh.remark")} :</th>
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
		<div title="${message("csh.shipping")}" >
			[#list shippings as shipping]
			<table class="table table-striped"  border="0">
			[#if shippings?size > 1]<tr align="center"><th colspan="4">${shipping_index + 1}</th></tr>[/#if]
	    	<tr>
	    		<th>${message("csh.shipping.sn")}:</th>
	    		<td  width="200">
	    			 ${shipping.sn}
	    		</td>	   
	    		<th>${message("csh.createDate")}:</th>
	    		<td>
	    			 ${shipping.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingManager.shippingMethod")}:</th>
	    		<td>
	    			 ${shipping.shippingMethod}
	    		</td>	   
	    		<th>${message("csh.shippingManager.deliveryCorp")}:</th>
	    		<td>
	    			 ${order.deliveryCorp}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingManager.deliveryCorp")}${message("csh.url")}:</th>
	    		<td>
	    			 ${shipping.deliveryCorpUrl}
	    		</td>	   
	    		<th>${message("csh.shippingManager.deliveryCorp")}${message("csh.code")}:</th>
	    		<td>
	    			 ${order.deliveryCorpCode}
	    		</td> 			    		
	    	</tr>	 
	    	<tr>
	    		<th>${message("csh.order.trackingNo")}:</th>
	    		<td>
	    			 ${shipping.trackingNo}
	    		</td>	   
	    		<th>${message("csh.order.freight")}:</th>
	    		<td>
	    			 ${shipping.freight}
	    		</td> 			    		
	    	</tr>	  
	    	<tr>
	    		<th>${message("csh.order.consignee")}:</th>
	    		<td>
	    			 ${shipping.consignee}
	    		</td>	   
	    		<th>${message("csh.tenantInfo.area")}:</th>
	    		<td>
	    			 ${shipping.area}
	    		</td> 			    		
	    	</tr>	
	    	<tr>
	    		<th>${message("csh.address")}:</th>
	    		<td>
	    			 ${shipping.address}
	    		</td>	   
	    		<th>${message("csh.zipCode")}:</th>
	    		<td>
	    			 ${shipping.zipCode}
	    		</td> 			    		
	    	</tr>	   
	    	<tr>
	    		<th>${message("csh.phoneNumber")}:</th>
	    		<td>
	    			 ${shipping.phone}
	    		</td>	   
	    		<th>${message("csh.operator")}:</th>
	    		<td>
	    			 ${shipping.operator}
	    		</td> 			    		
	    	</tr>	 
	    	<tr>
	    		<th>${message("csh.remark")} :</th>
	    		<td  colspan="3">
	    			<input type="text" class="easyui-textbox" value="${shipping.memo}" data-options="multiline:true,height:80,width:440" disabled="disabled"/>
	    		</td>	  			    		
	    	</tr>			    	  	   	
	    	</table>	
		    <table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
			 <thead>
				<tr>
					<th field="productSn" width="25%"  align="center">${message("csh.product.sn")}</th>
					<th field="name"  width="25%"  align="center">${message("csh.product.name")}</th>
					<th field="quantity" width="25%"   align="center">${message("csh.quantity")}</th>
					<th field="price" width="25%"   align="center">${message("csh.shipping.sn")}</th>
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
	   		<p><hr><hr>
			[/#list] 
	    </div>
		<div title="${message("csh.returns")}" >
	    	[#list returns as return]
			<table class="table table-striped"  border="0">
			[#if returns?size > 1]<tr align="center"><th colspan="4">${return_index + 1}</th></tr>[/#if]
	    	<tr>
	    		<th>${message("csh.returns.returns")}:</th>
	    		<td  width="200">
	    			 ${return.sn}
	    		</td>	   
	    		<th>${message("csh.createDate")}:</th>
	    		<td>
	    			 ${return.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingManager.shippingMethod")}:</th>
	    		<td>
	    			 ${return.shippingMethod}
	    		</td>	   
	    		<th>${message("csh.shippingManager.deliveryCorp")}:</th>
	    		<td>
	    			 ${return.deliveryCorp}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.order.trackingNo")}:</th>
	    		<td>
	    			 ${return.trackingNo}
	    		</td>	   
	    		<th>${message("csh.order.freight")}:</th>
	    		<td>
	    			 ${return.freight}
	    		</td> 			    		
	    	</tr>	  
	    	<tr>
	    		<th>${message("csh.order.consignee")}:</th>
	    		<td>
	    			 ${return.shipper}
	    		</td>	   
	    		<th>${message("csh.tenantInfo.area")}:</th>
	    		<td>
	    			 ${return.area}
	    		</td> 			    		
	    	</tr>	
	    	<tr>
	    		<th>${message("csh.address")}:</th>
	    		<td>
	    			 ${return.address}
	    		</td>	   
	    		<th>${message("csh.zipCode")}:</th>
	    		<td>
	    			 ${return.zipCode}
	    		</td> 			    		
	    	</tr>	   
	    	<tr>
	    		<th>${message("csh.phoneNumber")}:</th>
	    		<td>
	    			 ${return.phone}
	    		</td>	   
	    		<th>${message("csh.operator")}:</th>
	    		<td>
	    			 ${return.operator}
	    		</td> 			    		
	    	</tr>	 
	    	<tr>
	    		<th>${message("csh.remark")} :</th>
	    		<td  colspan="3">
	    			<input type="text" class="easyui-textbox" value="${return.memo}" data-options="multiline:true,height:80,width:440" disabled="disabled"/>
	    		</td>	  			    		
	    	</tr>			   
	    	</table>
	    	<table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
			 <thead>
				<tr>
					<th field="productSn" width="25%"  align="center">${message("csh.product.sn")}</th>
					<th field="name"  width="25%"  align="center">${message("csh.product.name")}</th>
					<th field="quantity" width="25%"   align="center">${message("csh.quantity")}</th>
					<th field="price" width="25%"   align="center">${message("csh.returns.returns")}</th>
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
	   		<p><hr><hr>
	    	[/#list]
	    </div>
		<div title="${message("csh.refunds.refunds")}" >
		    	[#list refunds as refund]
				<table class="table table-striped"  border="0">
				[#if refunds?size > 1]<tr align="center"><th colspan="4">${refund_index + 1}</th></tr>[/#if]
		    	<tr>
		    		<th>${message("csh.returns.returns")} :</th>
		    		<td  width="200">
		    			 ${refund.sn}
		    		</td>	   
		    		<th>${message("csh.createDate")}:</th>
		    		<td>
		    			 ${refund.createDate?string("yyyy-MM-dd HH:mm:ss")}
		    		</td> 			    		
		    	</tr>
		    	<tr>
		    		<th>${message("csh.order.paymentType")}:</th>
		    		<td>
		    			 ${refund.paymentMethod}
		    		</td>	   
		    		<th>${message("csh.returns.refundAmount")}:</th>
		    		<td>
		    			 ${refund.amount}
		    		</td> 	 			    		
		    	</tr>
		    	<tr>
		    		<th>${message("csh.payee")}:</th>
		    		<td>
		    			 ${refund.payee}
		    		</td>	   
		    		<th>${message("csh.operator")}:</th>
		    		<td>
		    			 ${refund.operator}
		    		</td> 			    		
		    	</tr>	
		    	<tr>
		    		<th>${message("csh.remark")} :</th>
		    		<td  colspan="3">
		    			<input type="text" class="easyui-textbox" value="${refund.memo}" data-options="multiline:true,height:80,width:440" disabled="disabled"/>
		    		</td>	  			    		
		    	</tr>			    	
		    	</table>
		    	[/#list]
	    </div>
	    <div title="${message("csh.action")}${message("csh.log")}" >
			   <table  class="easyui-datagrid"   fitColumns="true" style="width:100%;height:auto;">
				<thead>
					<tr>
						<th field="productSn" width="25%"  align="center">${message("csh.createDate")}</th>
						<th field="name"  width="40%"  align="center">${message("csh.action")}</th>
						<th field="quantity" width="10%"   align="center">${message("csh.operator")}</th>
						<th field="price" width="25%"   align="center">${message("csh.remark")}</th>
					</tr>
				</thead>		
				   <tbody>   
				   [#list orderLogs as orderLog]
				   <tr>   
		            	<td>${orderLog.createDate}</td>
		            	<td>${message("csh.commonEnum.OrderLogType."+"${orderLog.type}")}</td>
		            	<td>${orderLog.operator}</td>   
		            	<td>${orderLog.content}</td>   
		          </tr>   
				   [/#list]       
		    		</tbody>   
		   		</table>
   		</div>
    </div>