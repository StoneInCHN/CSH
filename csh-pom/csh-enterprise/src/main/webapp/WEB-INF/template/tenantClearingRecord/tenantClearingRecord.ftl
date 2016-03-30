<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantClearingRecord.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="carService-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.carService.serviceName")}:</label>
			    <input type="text" class="easyui-textbox" id="serviceNameSearch" name="serviceNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carService.serviceCategory")}:</label>
			    <input type="text" class="easyui-textbox" id="serviceCategorySearch" name="serviceCategorySearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carService.serviceStatus")}:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLED',
				      value: '${message("csh.common.enable")}'
				     },{
				      label: 'DISABLED',
				      value: '${message("csh.common.disable")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="serviceStatusSearch" style="width:110px;"/>
			    
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
	  	  <button id="carService-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="tenantClearingRecord-table-list"></table>
<div id="tenantClearingRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="carService_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="carService_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="carService_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addTenantClearingRecord"></div>
<div id="editTenantClearingRecord"></div>
<div id="tenantClearingRecordDetail"></div>




