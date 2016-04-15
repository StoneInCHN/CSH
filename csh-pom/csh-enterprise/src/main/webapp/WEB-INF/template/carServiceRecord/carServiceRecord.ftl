<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/carServiceRecord.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="carServiceRecord-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.carService.serviceName")}:</label>
			    <input type="text" class="easyui-textbox" style="width:80px" id="serviceNameSearch" name="serviceNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carServiceRecord.endUser")}:</label>
			    <input type="text" class="easyui-textbox" style="width:80px" name="endUserSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carService.serviceCategory")}:</label>
			    <input type="text" class="easyui-textbox" style="width:80px" id="serviceCategorySearch" name="serviceCategorySearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carServiceRecord.paymentType")}:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ALIPAY',
				      value: '${message("csh.carServiceRecord.paymentType.ALIPAY")}'
				     },{
				      label: 'WECHAT',
				      value: '${message("csh.carServiceRecord.paymentType.WECHAT")}'
				     },{
				      label: 'WALLET',
				      value: '${message("csh.carServiceRecord.paymentType.WALLET")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="paymentTypeSearch" style="width:110px;"/>
			    
			</div>
			<div class="search-item">
			    <label> ${message("csh.carServiceRecord.chargeStatus")}:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'RESERVATION',
				      value: '${message("csh.carServiceRecord.chargeStatus.RESERVATION")}'
				     },{
				      label: 'RESERVATION_SUCCESS',
				      value: '${message("csh.carServiceRecord.chargeStatus.RESERVATION_SUCCESS")}'
				     },{
				      label: 'RESERVATION_FAIL',
				      value: '${message("csh.carServiceRecord.chargeStatus.RESERVATION_FAIL")}'
				     },{
				      label: 'UNPAID',
				      value: '${message("csh.carServiceRecord.chargeStatus.UNPAID")}'
				     },{
				      label: 'FINISH',
				      value: '${message("csh.carServiceRecord.chargeStatus.FINISH")}'
				     },{
				      label: 'OVERDUE',
				      value: '${message("csh.carServiceRecord.chargeStatus.OVERDUE")}'
				     },{
				      label: 'PAID',
				      value: '${message("csh.carServiceRecord.chargeStatus.PAID")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="chargeStatusSearch" style="width:110px;"/>
			    
			</div>
			<div class="search-item">
			    <label> 创建时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="carServiceRecord-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="carServiceRecord-table-list"></table>
<div id="carServiceRecord_manager_tool">
	<div class="tool-button">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="carServiceRecord_manager_tool.edit();">修改</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="editCarServiceRecord"></div>
<div id="carServiceRecordDetail"></div>




