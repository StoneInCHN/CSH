<script src="${base}/resources/modules/estore/deliveryCorp.js"></script>
<div>
	  <fieldset>
	    <legend>${message("csh.search")}</legend>
	    <form id="deliveryCorp-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.deliveryCorp.name")}:</label>
			    <input type="text" class="easyui-textbox"  name="nameSearch" validtype="length[0,20]"/>
			</div>			
		</form>
		<div class="search-item">
	  	  <button id="deliveryCorp-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="deliveryCorp_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="deliveryCorp_manager_tool.add();">${message("csh.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="deliveryCorp_manager_tool.edit();">${message("csh.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="deliveryCorp_manager_tool.remove();">${message("csh.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<table id="deliveryCorp-table-list"></table>
<div id="addDeliveryCorp"> 
	<form id="addDeliveryCorp_form" method="post" class="form-table">  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.deliveryCorp.name")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="name" data-options="required:true" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.deliveryCorp.url")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="url" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.deliveryCorp.code")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="code"/>   
	    		</td>	    			    		
	    	</tr>   	
	    </table>
	</form>
</div>
<div id="editDeliveryCorp"></div>
<div id="deliveryCorpDetail"></div>




