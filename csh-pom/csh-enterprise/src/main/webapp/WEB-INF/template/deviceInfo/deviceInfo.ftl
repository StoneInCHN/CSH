<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/deviceInfo.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.deviceInfo.search")}</legend>
	    <form id="deviceInfo-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.deviceInfo.deviceNO")}:</label>
			    <input type="text" class="easyui-textbox" id="deviceNoSearch" name="deviceNoSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.device.device.deviceTpye")}:</label>
			     <input class="easyui-combobox" data-options="
				     valueField: 'name',
				     textField: 'name',
				     method:'get',  
				     url:'../deviceType/findAllDeviceType.jhtml',
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" name="deviceTpyeSearch" id="deviceTpyeSearch" style="width:110px;"/>
			</div>
			
			<div class="search-item">
			    <label> ${message("csh.device.deviceStatus")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'BINDED',
				      value: '${message("csh.deviceInfo.bindStatus.BINDED")}'
				     },{
				      label: 'UNBINDED',
				      value: '${message("csh.deviceInfo.bindStatus.UNBINDED")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="bindStatusSearch" id="bindStatusSearch" style="width:110px;"/>
			</div>
			
		</form>
		<div class="search-item">
	  	  <button id="deviceInfo-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="deviceInfo-table-list"></table>
<div id="deviceInfo_manager_tool">
	<div class="tool-button">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="deviceInfo_manager_tool.bind();">绑定</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="deviceInfo_manager_tool.unBind();">解绑</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id = "bindDevice">
	<form id="bindDevice_form" method="post" class="form-table">
	 	<input type="hidden" name="vehicleId" id="bindDevice_vehicleID"/>    
		    <table class="table table-striped"  border="0">
		    	<tr>
		    		<th>${message("csh.vehicle.plate")}:</th>
		    		<td colspan="2">
		    			 <input type="text" class="easyui-textbox" name="vehicleName" id= "bindDevice_vehicle"  data-options="required:true" disabled="disabled"/>
		    			<a href="#" id="device_search_btn" class="easyui-linkbutton" onclick="searchVehicle('bindDevice_vehicle',true)" iconCls="icon-search" plain=true"></a>
		    		</td>
		    	</tr>
		    </table>
	</form>
</div>





