	<form id="editShippingMethod_form" method="post" enctype="multipart/form-data" target="editIfm">
	<input type="hidden" value="${shippingMethod.id}" name="id">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.name")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  value="${shippingMethod.name}" name="name" data-options="required:true" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.defaultDeliveryCorp")} :</th>
	    		<td>
	    			<select  class="easyui-combobox" name="deliveryCorpId" style="width:110px;">
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
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.firstWeight}" name="firstWeight"  data-options="min:0,precision:0,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.continueWeight")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.continueWeight}" name="continueWeight"  data-options="min:0,precision:0,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.firstPrice")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.firstPrice}" name="firstPrice"  data-options="min:0,precision:2,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.continuePrice")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${shippingMethod.continuePrice}" name="continuePrice"  data-options="min:0,precision:2,required:true"/> 
	    		</td>	    			    		
	    	</tr>
			<tr>
				<th>
					${message("csh.icon")}:
				</th>
				<td>
					<a href="${shippingMethod.icon}" target="1024"><img src="${shippingMethod.icon}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.icon")}"></a>
					<input type="hidden" value="${shippingMethod.icon}" name="icon">
				</td>
			</tr>
		    <tr>
	    		<th>${message("csh.change.icon")} :</th>
	    		<td>
	    			 <input type="file"  name="iconImage"  style="width:250px"/>
	    		</td>	    			    		
	    	</tr>    	
	    	<tr>
	    		<th>${message("csh.description")} :</th>
	    		<td>
	    			<input type="text" class="easyui-textbox" value="${shippingMethod.description}" name= "description" data-options="multiline:true,height:120,width:300" />
	    		</td>	    			    		
	    	</tr>   	
	    </table>
	</form>
	<iframe id='editIfm' name='editIfm' style="display:none"/> 