<script type="text/javascript"  src="${base}/resources/modules/estore/order.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<fieldset>
		    <legend>${message("csh.order.search")}</legend>
		    <form id="order-search-form" class="search-form">
		    	<div class="search-item">
				    <label> ${message("csh.order.sn")}:</label>
				    <input type="text" class="easyui-textbox" id="orderSnSearch" name="sn" validtype="length[0,20]"/>
				</div>
				<div class="search-item">
				    <label>开始时间:</label>
				    <input type="text" class="Wdate" id="orderBeginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'orderEndDate\')}'});" />
				</div>
				<div class="search-item">
				    <label>结束时间:</label>
				   	<input type="text" class="Wdate" id="orderEndDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'orderBeginDate\')}'});"/>
				</div>
				<div class="search-item">
				    <label> ${message("csh.order.orderStatus")}:</label>
					<input type="text" class="easyui-combobox" id="orderOrderStatusSearch" name="orderStatus" data-options="
						 valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'unconfirmed',
					      value: '${message("csh.commonEnum.OrderStatus.unconfirmed")}'
					     },{
					      label: 'confirmed',
					      value: '${message("csh.commonEnum.OrderStatus.confirmed")}'
					     },{
					      label: 'completed',
					      value: '${message("csh.commonEnum.OrderStatus.completed")}'
					     },{
					      label: 'cancelled',
					      value: '${message("csh.commonEnum.OrderStatus.cancelled")}'
					     },{
					      label: 'failure',
					      value: '${message("csh.commonEnum.OrderStatus.failure")}'
					     }],
						prompt:'${message("csh.common.please.select")}'"/>
				</div>
			</form>
			<div class="search-item">
		  	  <button id="order-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
			  <button id="order-reset-btn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">重置</button>
		    </div>
		  </fieldset>
<table id="order-table-list"></table>
<div id="order_manager_tool">
	<div class="tool-button">
	</div>
	<div class="tool-filter">
	</div>
</div>
<div id="orderDetail">