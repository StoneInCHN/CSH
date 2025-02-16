<form id="tenantUserDetail_form" method="post">   
	 <input type="hidden" id="addTenantUser_form_file_input" name="photo"> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.tenantUser.realName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" disabled="disabled" id = "realName" name="realName" data-options="required:true" value="${tenantUser.realName}" />   
	    		</td>
	    		<th>${message("csh.tenantUser.photo")}:</th>
	    		<td  rowspan="6">
	    				<p class="imgWrap img-thumbnail">
						     <img src="${tenantUser.photo}" style ="width:110px;hight:110 px">
						</p>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.gender")}:</th>
				  <td>
	    	 	<select id="gender" class="easyui-combobox" disabled="disabled" name="gender" disabled="disabled" style="width:50px;">   
    			  	<option value="MALE" [#if tenantUser.gender =="MALE"] selected="selected" [/#if]>${message("csh.gender.male")}</option>
					<option value="FEMALE" [#if tenantUser.gender =="FEMALE"] selected="selected" [/#if]>${message("csh.gender.female")}</option>
				  </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.email")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="email" data-options="required:true" value="${tenantUser.email}"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.staffID")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="staffID" data-options="required:true" value="${tenantUser.staffID}"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.staffStatus")}:</th>
	    		<td>
	    			<select id="gender" class="easyui-combobox" name="staffStatus" disabled="disabled" style="width:80px;">   
    			  		<option value="INSERVICE" [#if tenantUser.staffStatus =="INSERVICE"] selected="selected" [/#if]>${message("csh.tenantUser.staffStatus.inService")}</option>
						<option value="OUTSERVICE" [#if tenantUser.staffStatus =="OUTSERVICE"] selected="selected" [/#if]>${message("csh.tenantUser.staffStatus.outService")}</option>
				  </select>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.address")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="address" data-options="required:true" value="${tenantUser.address}"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.phoneNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="telephone" data-options="required:true" value="${tenantUser.telephone}"/>   
	    		</td>
	    	
	    		<th>${message("csh.mobile")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  disabled="disabled" type="text" name="mobile" data-options="required:true" value="${tenantUser.mobile}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.department")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" disabled="disabled" id="tenantUserDepartment-detail" name="departmentId" data-options="prompt:'${message("csh.common.please.select")}'" value="${tenantUser.department.name}"/>   
	    		</td>
	    	
	    		<th>${message("csh.tenantUser.position")}:</th>
	    		<td>
	    			 <input class="easyui-combobox"  disabled="disabled" type="text" id="tenantUserPosition-detail" name="positionId" id="position" data-options="prompt:'${message("csh.common.please.select")}'" value="${tenantUser.position.name}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.hireDate")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="hireDate" readonly="readonly" value="${tenantUser.hireDate}"/>   
	    		</td>
	    		<th>${message("csh.tenantUser.age")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="age" data-options="required:true" value="${tenantUser.age}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.IDCard")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="IDCard" data-options="required:true" value="${tenantUser.IDCard}"/>
	    		</td>
	    		<th>${message("csh.tenantUser.workingYear")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="workingYear" data-options="required:true" value="${tenantUser.workingYear}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.tenantUser.birthDay")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="birthDay" name="birthDay" readonly="readonly" value="${tenantUser.birthDay}"/>
	    		</td>
	    		<th>${message("csh.tenantUser.zipCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="zipCode" data-options="required:true" value="${tenantUser.zipCode}"/>
	    		</td>
	    	</tr>
	    </table>
</form>



