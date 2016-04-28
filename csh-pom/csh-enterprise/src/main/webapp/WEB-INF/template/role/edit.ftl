<form id="editRole_form" method="post">  
		<input value="${role.id}" type="hidden" name="id" />
		<input value="${role.tenantID}" type="hidden" name="tenantID" />
	   <table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("csh.role.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${role.name}" name="name" data-options="required:true,delay:3000" validType="length[0,150],remote['../role/uniqueFieldCheck.jhtml?filedName=name&id=${role.id}','value']" invalidMessage="${message("csh.common.nameAleradyUsed")}"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.role.description")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" value="${role.description}" name="description" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    </table>
</form>

