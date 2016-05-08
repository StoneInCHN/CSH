<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantClearingRecord.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.tenantClearingRecord.search")}</legend>
	    <form id="tenantClearingRecord-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.tenantClearingRecord.clearingSn")}:</label>
			    <input type="text" class="easyui-textbox" id="clearingSnSearch" name="clearingSnSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> 结算时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="tenantClearingRecord-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="tenantClearingRecord-table-list"></table>
<div id="tenantClearingRecord_manager_tool">
	<div class="tool-button">
		[#if allowClearing]
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantClearingRecord_manager_tool.applyClearingRecord();">申请结算</a>
		[/#if]
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="tenantClearingRecord_manager_tool.show();">查看详情</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="applyClearingRecord"></div>
<div id="editTenantClearingRecord"></div>
<div id="tenantClearingRecordDetail"></div>




