<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicle.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/js/LuShu.js"/>
<!--<link rel="stylesheet" type="text/css"href="${base}/resources/css/vehicle.css">-->
<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="vehicle-search-form" class="search-form" >
	    <input type="hidden" name="totalRecord" id="totalRecord"/>
	    	<div class="search-item">
			    <label>设备绑定状态:</label>
			    <select id="bindStatus" class="easyui-combobox" name="bindStatus" style="width:100px;" panelMaxHeight="100px">  
			    	<option value="0" selected="selected">全部</option> 
    			  	<option value="1">已绑定</option>
					<option value="2">未绑定</option>
				</select>
			</div>
	    	<div class="search-item">
			    <label> ${message("csh.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicle.endUser")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.mobile")}:</label>
			    <input type="text" class="easyui-textbox" id="mobileNumSearch" name="mobileNumSearch" validtype="length[0,20]"/>
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
	  	  <button id="vehicle-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="vehicle-table-list"></table>
<div id="vehicle_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.vehicleDailyReport();">车辆每日数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.realTimeCarCondition();">实时车况</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.showAllVehicle();">全地图</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.edit();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="exportExcel('vehicle','vehicle-search-form','totalRecord');">导出</a>
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
	    	<tr >
	    		<th >车型:</th>
	    		<td colspan="3">
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrand-add" />
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleLine-add" />
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrandDetail-add" name="vehicleBrandDetailId"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>绑定设备:</th>
	    		<td>
	    			 <input class="easyui-textbox"  id="addVehicle_deviceInfo" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("csh.common.please.select")}'" />
	    		</td>
	    		<td>	 
	    			 <a href="#" id="common_device_search_btn" class="easyui-linkbutton" onclick="searchDevice('addVehicle_deviceInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>车辆所有者:</th>
	    		<td>
	    			 <input class="easyui-textbox"  id="addVehicle_endUser" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("csh.common.please.select")}'" />
	    		</td>
	    		<td>	 
	    			 <a href="#" id="common_endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicle_endUser')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>

<div id="editVehicle"></div>
<div id="faultCodeDetail"></div>
<div id="vehicleDetail"></div>
<div id="realTimeCarCondition"></div>
<div id="vehicleDailyReport"></div>
<div id="allVehicleStatus"></div>



