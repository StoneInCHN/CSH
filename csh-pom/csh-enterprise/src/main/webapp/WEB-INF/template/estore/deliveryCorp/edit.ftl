	<form id="editDeliveryCorp_form" method="post">  
	<input type="hidden"  name="id" value="${deliveryCorp.id}">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.deliveryCorp.name")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="name"  value="${deliveryCorp.name}"  data-options="required:true" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.deliveryCorp.url")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"   value="${deliveryCorp.url}" name="url" />   
	    		</td>	    			    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.deliveryCorp.code")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  value="${deliveryCorp.code}" name="code"/>   
	    		</td>	    			    		
	    	</tr>   	
	    </table>
	</form>