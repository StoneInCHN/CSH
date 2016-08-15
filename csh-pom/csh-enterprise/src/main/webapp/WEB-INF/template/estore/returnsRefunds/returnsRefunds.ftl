<script src="${base}/resources/modules/estore/returnsRefunds.js"></script>
<link rel="stylesheet" type="text/css"href="${base}/resources/css/order.css" >
<div>
	  <fieldset>
	    <legend>${message("csh.returns")}${message("csh.search")}</legend>
	    			<form id="returnsRefunds-search-form" class="search-form">
											<div class="search-item">
											   <label>${message("csh.returns")}:</label>
											   <input type="text" class="easyui-textbox"  name="returnsSnSearch"  validtype="length[0,20]"/>
											</div>		
					</form>
		<div class="search-item">
	  	  <button id="returnsRefunds-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
	  
</div>
<table id="returnsRefunds-table-list"></table>
<div id="approvedReturn"></div>	
<div id="confirmReturn"></div>
<div id="returnsRefundsDetail"></div>




