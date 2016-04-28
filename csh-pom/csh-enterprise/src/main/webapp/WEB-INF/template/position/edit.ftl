<form id="editPosition_form" method="post" >
		<input  type="hidden" id="editPositionId" name="id" value="${position.id}"  />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("csh.position.department")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="editPosition_form_departmentId" name="departmentId" data-value="${position.department.id}"  />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.position.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="editPositionName" name="name" value="${position.name}" data-options="required:true,delay:3000" invalidMessage="${message("csh.common.nameAleradyUsed")}"/>   
	    		</td>
	    	</tr>
	    </table>
	</form>