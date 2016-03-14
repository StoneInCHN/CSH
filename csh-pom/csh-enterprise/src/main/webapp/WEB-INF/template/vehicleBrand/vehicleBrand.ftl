<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicleBrand.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicleBrand.search")}</legend>
	    <form id="vehicleBrand-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.vehicleBrand.name")}:</label>
			    <input type="text" class="easyui-textbox" id="nameSearch" name="nameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.vehicleBrand.code")}:</label>
			    <input type="text" class="easyui-textbox" id="codeSearch" name="codeSearch" validtype="length[0,20]"/>
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
	  	  <button id="vehicleBrand-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
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
	    		<th>${message("csh.vehicleBrand.name")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="name" id= "name"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleBrand.code")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="code" id= "code" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editVehicleBrand"></div>
<div id="vehicleBrandDetail"></div>




