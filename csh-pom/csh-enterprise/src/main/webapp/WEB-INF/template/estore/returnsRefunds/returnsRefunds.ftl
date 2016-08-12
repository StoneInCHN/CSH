<script src="${base}/resources/modules/estore/returnsRefunds.js"></script>
<div>
	  <fieldset>
	    <legend>订单搜索</legend>
	    			<form id="returnsRefunds-search-form" class="search-form">
											<div class="search-item">
											   <label>${message("csh.order.sn")}:</label>
											   <input type="text" class="easyui-textbox"  name="orderSnSearch"  validtype="length[0,20]"/>
											</div>		
					</form>
		<div class="search-item">
	  	  <button id="returnsRefunds-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="returnsRefunds-table-list"></table>
<div id="addReturns"></div>
<div id="addRefunds"></div>
<div id="returnsRefundsDetail"></div>




