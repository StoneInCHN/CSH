<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicleMaintain.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicleMaintain.search")}</legend>
	    <form id="vehicleMaintain-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicleBrand.code")}:</label>
			    <input type="text" class="easyui-textbox" id="codeSearch" name="codeSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> 下次保养时间:</label>
			    <input type="text" class="Wdate" id="nextMaintainDateStart" name="nextMaintainDateStart"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'nextMaintainDateEnd\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="nextMaintainDateEnd"  name="nextMaintainDateEnd" onclick="WdatePicker({minDate: '#F{$dp.$D(\'nextMaintainDateStart\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="vehicleMaintain-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="vehicleMaintain-table-list"></table>
<div id="vehicleMaintain_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="vehicleMaintain_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicleMaintain_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="vehicleMaintain_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addVehicleMaintain"> 
	<form id="addVehicleMaintain_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.vehicleMaintain.name")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="name" id= "name"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleMaintain.code")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="code" id= "code" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editVehicleBrand"></div>
<div id="vehicleBrandDetail"></div>




