<script type="text/javascript"  src="${base}/resources/modules/department.js"></script>
<table id="department-table-list"></table>
<div id="addDepartment">
	<form id="addDepartment_form" method="post" class="form-table">
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("csh.department.parent")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="addDepartment_form_parentName" name="parentId" type="text" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.department.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="addDepartmentName" name="name" data-options="required:true,delay:3000" invalidMessage="该名称已使用"/>   
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDepartment"></div>  