	<form id="addShipping_form" method="post" >
		<input type="hidden" name="orderId" value="${order.id}">
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
	    		<th>${message("csh.shippingManager.shippingMethod")}:</th>
	    		<td>
	    			 <select  class="easyui-combobox" name="shippingMethodId" style="width:110px;" data-options="required:true">
	    				[#list shippingMethods as shippingMethod]
	    					<option value="${shippingMethod.id}" [#if order.shippingMethod.id == shippingMethod.id] selected = "selected" [/#if]>${shippingMethod.name}</option>
	    				[/#list]   
				  	</select> 
	    		</td>
				<th>${message("csh.shippingManager.deliveryCorp")}:</td>
				<td>
	    			 <select  class="easyui-combobox" name="deliveryCorpId" style="width:110px;" data-options="required:true">
	    				[#list deliveryCorps as deliveryCorp]
	    					<option value="${deliveryCorp.id}" [#if order.deliveryCorp.id == deliveryCorp.id] selected = "selected" [/#if]>${deliveryCorp.name}</option>
	    				[/#list]  
				  	</select> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.order.trackingNo")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="trackingNo"  data-options="required:true" validtype="length[0,20]"/> 
	    		</td>	  
	    		<th>${message("csh.order.freight")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text"  name="freight"  data-options="min:0,max:100000,precision:2,required:true"/> 
	    		</td>	  			    		
	    	</tr>	    	
	    	<tr>
	    		<th>${message("csh.order.consignee")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="consignee" value="${order.consignee}"   data-options="required:true" validtype="length[0,200]"/> 
	    		</td>	  
	    		<th>${message("csh.zipCode")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="zipCode" value="${order.zipCode}"  data-options="required:true" validtype="length[0,200]"/>
	    		</td>	  			    		
	    	</tr>	
	    	<tr>
	    		<th>${message("csh.tenantInfo.area")} :</th>
	    		<td>
	    			<input class="easyui-textbox" type="text"  name="area" value="${order.areaName}"  data-options="required:true" validtype="length[0,200]"/>
	    		</td>	  
	    		<th>${message("csh.address")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="address" value="${order.address}"  data-options="required:true" validtype="length[0,200]"/>
	    		</td>	  			    		
	    	</tr>		    	
	    	<tr>
	    		<th>${message("csh.phoneNumber")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="phone" value="${order.phone}"   data-options="required:true" validtype="length[0,200]"/> 
	    		</td>	  
	    		<th>${message("csh.remark")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="memo" value="${order.memo}"  data-options="required:true" validtype="length[0,200]"/>
	    		</td>	  			    		
	    	</tr>		    	
	    </table>
	</form>
	<table  class="easyui-datagrid" id="addShipping-orderItem-table-list"  fitColumns="true" style="width:100%;height:auto;">
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

