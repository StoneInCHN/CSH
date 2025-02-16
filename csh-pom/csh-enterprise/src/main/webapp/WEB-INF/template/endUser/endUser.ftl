<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/endUser.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.form.js"></script>
<div>
	  <fieldset>
	    <legend>${message("csh.endUser.search")}</legend>
	    <form id="endUser-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.endUser.userName")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.endUser.staffStatus")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("csh.endUser.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("csh.endUser.accoutStatus.locked")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="accountStatusSearch" id="accountStatusSearch" style="width:110px;"/>
			</div>
			
		</form>
		<div class="search-item">
	  	  <button id="endUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="endUser-table-list"></table>
<div id="endUser_manager_tool">
	<div class="tool-button">
		<!--
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="endUser_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="endUser_manager_tool.edit();">修改</a>
		-->
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="endUser_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="endUser_manager_tool.setBalance();">设置余额</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="endUser_manager_tool.import();">批量导入</a>
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
<div id="importEndUserDialog">
	<form id="endUserImport_form" method="post" class="form-table">   
    <table class="table table-striped"  border="0">
    	<tr>
    		<th>${message("csh.endUser.excel")}:</th>
    		<td>
    			 <input class="easyui-filebox" style="width:300px" name="filePath"/>
    		</td>
    	</tr>
    </table>
</form>
</div>
<div id="setBalance4EndUser" >
	<form id="setBalance4EndUser_form" method="post" class="form-table">
		<input type="hidden" name="endUserId" id="setBalance4EndUser_form_endUserId">
		 <table class="table table-striped"  border="0">
		<tr>
	    		<th>${message("csh.accountBalance.balance")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox" name="balance" data-options="required:true,min:0,precision:2"/>
	    		</td>
	    </tr>
	</table>
	</form>
</div>



