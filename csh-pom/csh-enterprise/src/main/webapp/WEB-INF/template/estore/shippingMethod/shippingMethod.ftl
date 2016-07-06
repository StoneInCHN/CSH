<script src="${base}/resources/modules/estore/shippingMethod.js"></script>
<div>
	  <fieldset>
	    <legend>${message("csh.search")}</legend>
	    <form id="shippingMethod-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.name")}:</label>
			    <input type="text" class="easyui-textbox"  name="nameSearch" validtype="length[0,20]"/>
			</div>			
		</form>
		<div class="search-item">
	  	  <button id="shippingMethod-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="shippingMethod_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="shippingMethod_manager_tool.add();">${message("csh.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="shippingMethod_manager_tool.edit();">${message("csh.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="shippingMethod_manager_tool.remove();">${message("csh.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<table id="shippingMethod-table-list"></table>
<div id="addShippingMethod"> 
	<form id="addShippingMethod_form" method="post" class="form-table" enctype="multipart/form-data" target="ifm">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.name")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="name" data-options="required:true" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.defaultDeliveryCorp")} :</th>
	    		<td>
	    			<select  class="easyui-combobox" name="deliveryCorpId" style="width:110px;">
	    				<option>${message("csh.common.please.select")}...</option>
	    				[#list deliveryCorps as deliveryCorp]
	    					<option value="${deliveryCorp.id}" >${deliveryCorp.name}</option>
	    				[/#list]   
				  	</select> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.firstWeight")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="firstWeight"  data-options="min:0,precision:0,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.continueWeight")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="continueWeight"  data-options="min:0,precision:0,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.firstPrice")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="firstPrice"  data-options="min:0,precision:2,required:true"/> 
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.shippingMethod.continuePrice")} :</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="continuePrice"  data-options="min:0,precision:2,required:true"/> 
	    		</td>	    			    		
	    	</tr>
		    <tr>
	    		<th>${message("csh.icon")} :</th>
	    		<td>
	    			 <input type="file" name="iconImage"  style="width:250px"/>
	    		</td>	    			    		
	    	</tr>    	
	    	<tr>
	    		<th>${message("csh.description")} :</th>
	    		<td>
	    			<input type="text" class="easyui-textbox" name= "description" data-options="multiline:true,height:120,width:300" />
	    		</td>	    			    		
	    	</tr>   	
	    </table>
	</form>
	<iframe id='ifm' name='ifm' style="display:none"/> 
</div>
<div id="editShippingMethod"></div>
<div id="shippingMethodDetail"></div>




