<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantUser.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("csh.tenantUser.search")}</legend>
	    <form id="tenantUser-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.tenantUser.search.realName")}:</label>
			    <input type="text" class="easyui-textbox" id="realName" name="realNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.tenantUser.search.department")}:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserDepartment-search" name="departmentSearchId" data-options="prompt:'${message("csh.common.please.select")}'"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.tenantUser.search.position")}:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserPosition-search" name="positionSearchId"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.tenantUser.staffStatus")}:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '${message("csh.tenantUser.staffStatus.inService")}'
				     },{
				      label: 'OUTSERVICE',
				      value: '${message("csh.tenantUser.staffStatus.outService")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="staffStatusSearch" style="width:110px;"/>
			    
			</div>
		</form>
		<div class="search-item">
	  	  <button id="tenantUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="tenantUser-table-list"></table>
<div id="tenantUser_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantUser_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="tenantUser_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="tenantUser_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addTenantUser"> 
	<form id="addTenantUser_form" method="post" class="form-table">  
	<input type="hidden" id="addTenantUser_form_file_input" name="photo"> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.tenantUser.realName")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "realName" name="realName" data-options="required:true" />   
	    		</td>
	    		<th>${message("csh.tenantUser.photo")}:</th>
	    		<td  rowspan="6">
	    			<div title="头像上传" class="easyui-tooltip headWarp">
	    				<div id="tenantUserUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="tenantUserFilePicker-add" ></div>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
	    			</div>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.gender")}:</th>
				  
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'MALE',
				      value: '${message("csh.gender.male")}'
				     },{
				      label: 'FEMALE',
				      value: '${message("csh.gender.female")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="gender" style="width:110px;" required=true/>
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.email")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="email" data-options="required:true" />
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.staffID")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="staffID" data-options="required:true" validtype="length[0,30];"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.staffStatus")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '${message("csh.tenantUser.staffStatus.inService")}'
				     },{
				      label: 'OUTSERVICE',
				      value: '${message("csh.tenantUser.staffStatus.outService")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="staffStatus" style="width:110px;" required=true/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.address")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="address" data-options="required:true" validtype="length[0,200]" />   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.phoneNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="telephone" />   
	    		</td>
	    	
	    		<th>${message("csh.mobile")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="mobile" data-options="required:true" validtype="mobile"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.department")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="tenantUserDepartment-add" name="departmentId" />   
	    		</td>
	    	
	    		<th>${message("csh.tenantUser.position")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" type="text" id="tenantUserPosition-add" name="positionId" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.hireDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="hireDate"  data-options="required:true,editable:false"/>   
	    		</td>
	    		<th>${message("csh.tenantUser.age")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="age" data-options="required:true" validType="max[200]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.IDCard")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="IDCard" data-options="required:true" validtype="idcard;length[0,30]"/>
	    		</td>
	    		<th>${message("csh.tenantUser.workingYear")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="workingYear" data-options="required:true" validType="max[200]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.birthDay")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="birthDay"  data-options="required:true,editable:false"/>
	    		</td>
	    		<th>${message("csh.tenantUser.zipCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="zipCode" data-options="required:true" validtype="length[0,20]"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editTenantUser"></div>
<div id="tenantUserDetail"></div>




