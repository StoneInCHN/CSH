<form id="editDepartment_form" method="post" >
		<input  type="hidden" id="editDeparementId" name="id" value="${department.id}"  />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("csh.department.parent")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="editDepartment_form_parentName" name="parentId" data-value="${department.parent.id}" type="text" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.department.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="editDeparementName" name="name" value="${department.name}" data-options="required:true,delay:3000" invalidMessage="该名称已使用"/>   
	    		</td>
	    	</tr>
	    	
	    </table>
	</form>