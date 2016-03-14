<form id="editVehicleBrand_form" method="post">   
		<input type="hidden" name="id" value= "${vehicleBrand.id}"/>
	    <table class="table table-striped"  border="0">
	    	<table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.vehicleBrand.name")}:</th>
	    		<td>
	    			 <input  class="easyui-textbox" value="${vehicleBrand.name}" name="name" id= "name"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleBrand.code")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"value="${vehicleBrand.code}" name="code" id= "code" />
	    		</td>
	    	</tr>
	    </table>
</form>



