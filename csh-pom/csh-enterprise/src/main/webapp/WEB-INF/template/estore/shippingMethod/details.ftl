	<form id="detailsShippingMethod_form" method="post">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.name")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  value="${shippingMethod.name}"  data-options="required:true" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.defaultDeliveryCorp")} :</th>
	    		<td>
	    			<select  class="easyui-combobox" style="width:110px;">
	    				<option>${message("csh.common.please.select")}...</option>
	    				[#list deliveryCorps as deliveryCorp]
	    					<option value="${deliveryCorp.id}" [#if shippingMethod.defaultDeliveryCorp.id == deliveryCorp.id] selected = "selected" [/#if]>${deliveryCorp.name}</option>
	    				[/#list]   
				  	</select> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.firstWeight")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.firstWeight}"   data-options="min:0,precision:0,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.continueWeight")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.continueWeight}"  data-options="min:0,precision:0,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.firstPrice")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.firstPrice}"   data-options="min:0,precision:2,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.continuePrice")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.continuePrice}"   data-options="min:0,precision:2,required:true"/> 
	    		</td>	    			    		
	    	</tr>
			<tr>
				<th>
					${message("csh.icon")}:
				</th>
				<td>
					<a href="${shippingMethod.icon}" target="1024"><img src="${shippingMethod.icon}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.icon")}"></a>
				</td>
			</tr>
	    	<tr>
	    		<th>${message("csh.description")} :</th>
	    		<td>
	    			<input type="text" class="easyui-textbox" value="${shippingMethod.description}" data-options="multiline:true,height:120,width:300" disabled="disabled"/>
	    		</td>	    			    		
	    	</tr>   	
	    </table>
	</form>
