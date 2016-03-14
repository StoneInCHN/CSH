<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicle.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="vehicle-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicle.vehiclebrand")}:</label>
			    <input type="text" class="easyui-textbox" id="vehicleBrandSearch" name="vehicleBrandSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicle.status")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLE',
				      value: '${message("csh.common.enable")}'
				     },{
				      label: 'DISABLE',
				      value: '${message("csh.common.disable")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="statusSearch" id="status" style="width:110px;"/>
			</div>
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="vehicle-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="vehicle-table-list"></table>
<div id="vehicle_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="vehicle_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="vehicle_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addVehicle"> 
	<form id="addVehicle_form" method="post" class="form-table">
	<input type="hidden" name="endUserID" id="addVehicle_endUserID">
	<input type="hidden" name="deviceInfoID" id="addVehicle_deviceInfoID">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>车牌号:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="plate" id= "plate"  data-options="required:true"/>
	    		</td>
	    		<th>车辆颜色:</th>
	    		<td >
	    			 <input class="easyui-textbox" name="color" id= "color" />
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>车型:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addPrescription_elderlyInfo" panelHeight="150px" data-options="editable:false" />
	    		</td>
	    		<td>	 
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addPrescription_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>绑定设备:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addVehicle_deviceInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    		</td>
	    		<td>	 
	    			 <a href="#" id="common_device_search_btn" class="easyui-linkbutton" onclick="searchDevice('addVehicle_deviceInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>车辆所有者:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" id="addVehicle_endUser" panelHeight="150px" data-options="required:true,editable:false" />
	    		</td>
	    		<td>	 
	    			 <a href="#" id="common_endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicle_endUser')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicle.status")}:</th>
	    		<td colspan='2'>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLE',
				      value: '${message("csh.common.enable")}'
				     },{
				      label: 'DISABLE',
				      value: '${message("csh.common.disable")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="status" id="status" style="width:110px;"/>
	    		</td>
	    		</tr>
	    	<tr>
	    </table>
	</form>
</div>
<div id="editVehicle"></div>
<div id="vehicleDetail"></div>




