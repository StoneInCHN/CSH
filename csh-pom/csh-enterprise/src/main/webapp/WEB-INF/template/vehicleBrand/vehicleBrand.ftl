<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicleBrand.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicleBrand.search")}</legend>
	    <form id="vehicleBrand-search-form" class="search-form">
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
<table id="vehicleBrand-table-list"></table>
<div id="vehicleBrand_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="vehicleBrand_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicleBrand_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="vehicleBrand_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addVehicleBrand"> 
	<form id="addVehicleBrand_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>品牌名:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="name" id= "name"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>名称首字母:</th>
	    		<td >
	    			 <input class="easyui-textbox" name="code" id= "code" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editVehicle"></div>
<div id="vehicleDetail"></div>




