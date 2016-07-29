	<form id="addShipping_form" method="post" >
		<input type="hidden" name="orderId" value="${order.id}">
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
	    		<th>配送方式:</th>
	    		<td>
	    			 <select  class="easyui-combobox" name="shippingMethodId" style="width:110px;" data-options="required:true">
	    				[#list shippingMethods as shippingMethod]
	    					<option value="${shippingMethod.id}" [#if order.shippingMethod.id == shippingMethod.id] selected = "selected" [/#if]>${shippingMethod.name}</option>
	    				[/#list]   
				  	</select> 
	    		</td>
				<th>物流公司:</td>
				<td>
	    			 <select  class="easyui-combobox" name="deliveryCorpId" style="width:110px;" data-options="required:true">
	    				[#list deliveryCorps as deliveryCorp]
	    					<option value="${deliveryCorp.id}" [#if order.deliveryCorp.id == deliveryCorp.id] selected = "selected" [/#if]>${deliveryCorp.name}</option>
	    				[/#list]  
				  	</select> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>运单号 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="trackingNo"  data-options="required:true" validtype="length[0,200]"/> 
	    		</td>	  
	    		<th>物流费用 :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text"  name="freight"  data-options="min:0,max:100000,precision:2,required:true"/> 
	    		</td>	  			    		
	    	</tr>	    	
	    	<tr>
	    		<th>收货人 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="consignee" value="${order.consignee}"   data-options="required:true" validtype="length[0,200]"/> 
	    		</td>	  
	    		<th>邮编 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="zipCode" value="${order.zipCode}"  data-options="required:true" validtype="length[0,200]"/>
	    		</td>	  			    		
	    	</tr>	
	    	<tr>
	    		<th>地区 :</th>
	    		<td>
	    			<span class="fieldSet">
	    			 <input type="hidden" id="areaId"  value="${(order.area.id)!}" treePath="${(order.area.treePath)!}"/>
	    			</span>
	    		</td>	  
	    		<th>地址 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="address" value="${order.address}"  data-options="required:true" validtype="length[0,200]"/>
	    		</td>	  			    		
	    	</tr>		    	
	    	<tr>
	    		<th>电话 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="phone" value="${order.phone}"   data-options="required:true" validtype="length[0,200]"/> 
	    		</td>	  
	    		<th>备注 :</th>
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

