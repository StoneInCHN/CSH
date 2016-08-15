	<form id="confirmReturn_form" method="post" >
		<input type="hidden" name="returnsId" value="${returns.id}">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.belong.order")}:</th>
	    		<td  width="200">
	    			 ${returns.order.sn}
	    		</td>	   
	    		<th>${message("csh.order")} ${message("csh.createDate")}:</th>
	    		<td>
	    			 ${returns.order.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.returns.returns")}:</th>
	    		<td  width="200">
	    			 ${returns.sn}
	    		</td>	   
	    		<th>${message("csh.returns")} ${message("csh.createDate")}:</th>
	    		<td>
	    			 ${returns.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.returns.status")}:</th>
	    		<td  width="200">
	    			${message("csh.returns.status."+"${returns.returnsStatus}")}
	    		</td>	   
	    		<th>${message("csh.returns.returnAmount")}:</th>
	    		<td>
	    			${message("csh.RMB")}${returns.returnAmount}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingManager.deliveryCorp")} :</th>
	    		<td>
	    		       ${returns.deliveryCorp}	
	    		</td>	  
	    		<th>${message("csh.shippingManager.shippingMethod")}:</th>
	    		<td>
	    				${returns.shippingMethod}	
	    		</td>	  			    		
	    	</tr>	 
	    	<tr>
	    		<th>${message("csh.order.trackingNo")} :</th>
	    		<td>
	    		       ${returns.trackingNo}	
	    		</td>	  
	    		<th>${message("csh.address")} :</th>
	    		<td>
	    				${returns.address}	
	    		</td>	  			    		
	    	</tr>	
	    	<tr>
	    		<th>${message("csh.order.consignee")} :</th>
	    		<td>
	    		       ${returns.shipper}	
	    		</td>	  
	    		<th>${message("csh.zipCode")} :</th>
	    		<td>
	    				${returns.zipCode}	
	    		</td>	  			    		
	    	</tr>	    	    		    	
	    	<tr>
	    		<th>${message("csh.phoneNumber")} :</th>
	    		<td>
	    		       ${returns.phone}	
	    		</td>	  
	    		<th>${message("csh.remark")} :</th>
	    		<td>
	    				${returns.memo}	
	    		</td>	  			    		
	    	</tr>	    		    	
	    </table>
	</form>
	<table  class="easyui-datagrid" id="addReturns-orderItem-table-list"  fitColumns="true" style="width:100%;height:auto;">
		<thead>
			<tr>
				<th field="thumbnail" width="20%"  align="center">${message("csh.shippingManager.returns")}${message("csh.product.thumbnail")}</th>
				<th field="productSn" width="20%"  align="center">${message("csh.shippingManager.returns")}${message("csh.product.sn")}</th>
				<th field="name"  width="50%"  align="center">${message("csh.shippingManager.returns")}${message("csh.order.name")}</th>
				<th field="quantity" width="5%"   align="center">${message("csh.quantity")}</th>
				<th field="price" width="5%"   align="center">${message("csh.price")}</th>
			</tr>
		</thead>		
		   <tbody>   
		   [#list returns.returnsItems as returnsItem]
		   <tr>   
		   		<td><img src="${returnsItem.thumbnail}" title="${returnsItem.sn}" width='40' height='32' style='margin:5px'/></td>
            	<td>${returnsItem.sn}</td>
            	<td>${returnsItem.name}</td>
            	<td>${returnsItem.quantity}</td>   
            	<td>${returnsItem.price}</td>   
          </tr>   
		   [/#list]       
    </tbody>   
   </table>

