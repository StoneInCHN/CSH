<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicleInsurance.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="vehicleInsurance-search-form" class="search-form">
			<div class="search-item">
			    <label> ${message("csh.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicleInsurance.userName")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicleInsurance.mobile")}:</label>
			    <input type="text" class="easyui-textbox" id="mobileNumSearch" name="mobileNumSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> 保险到期时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="vehicleInsuranceEndDateStrart"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="vehicleInsuranceEndDateStrartEnd" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="vehicleInsurance-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	    
	  </fieldset>
</div>
<table id="vehicleInsurance-table-list"></table>
<div id="vehicleInsurance_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="vehicleInsurance_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicleInsurance_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="vehicleInsurance_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addVehicleInsurance"></div>
<div id="editVehicleInsurance"></div>
<div id="vehicleInsuranceDetail"></div>




