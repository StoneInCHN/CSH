<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/repareReservation.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="repareReservation-search-form" class="search-form">
	    <input type="hidden" name="totalRecord" id="totalRecord"/>
	    	<div class="search-item">
			    <label> ${message("csh.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.reservation.userName")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> 预约时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="repareReservation-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="repareReservation-table-list"></table>
<div id="repareReservation_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="repareReservation_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="repareReservation_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="repareReservation_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addTenantAccount"> 
	<form id="addTenantAccount_form" method="post" class="form-table">
	<input type="hidden" name="tenantUserID" id="addTenantAccountUserID">
	<input type="hidden" name="roleID" id="addTenantAccountRoleID">  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.tenantAccount.staffID")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="staffID" id= "staffID"   data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantAccount.userName")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="userName" id= "userName"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantAccount.password")}:</th>
	    		<td colspan='2'>
	    			 <input type="password" class="easyui-textbox" name="password" id= "password"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantAccount.accoutStatus")}:</th>
	    		<td colspan='2'>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("csh.tenantAccount.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("csh.tenantAccount.accoutStatus.locked")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="accoutStatus" style="width:110px;"/>
	    		</td>
	    		</tr>
	    	<tr>
	    		<th>${message("csh.tenantAccount.tenantUser")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="addTenantAccountUser" id= "addTenantAccountUser"  />
	    		</td>
	    		<td>
	    			<a href="#" id="tenant_user_search_btn" class="easyui-linkbutton" onclick="searchTenantUser('addTenantAccountUser')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    	<th>${message("csh.tenantAccount.role")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="addTenantAccountRole" id= "addTenantAccountRole"  />
	    		</td>
	    		<td>
	    			<a href="#" id="role_search_btn" class="easyui-linkbutton" onclick="searchRoles('addTenantAccountRole')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editTenantAccount"></div>
<div id="tenantAccountDetail"></div>




