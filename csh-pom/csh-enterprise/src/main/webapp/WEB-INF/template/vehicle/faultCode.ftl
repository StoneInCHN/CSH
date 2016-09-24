<form id="vehicleDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>故障代码:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${faultCode.code}" disabled="disabled"/>
	    		</td>
	    		<th>范畴:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${faultCode.scope}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>中文定义:</th>
	    		<td >
	    			 <input class="easyui-textbox" multiline="true" value="${faultCode.defineCh}" style="height:80px;" disabled="disabled"/>
	    		</td>
	    		<th>英文定义:</th>
	    		<td >
	    			 <input  class="easyui-textbox" multiline="true" value="${faultCode.defineEn}" style="height:80px;" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>背景知识:</th>
	    		<td colspan='5'>
	    			 <textarea class="easyui-textbox" multiline="true" disabled="disabled" style="height:150px;width:450px">${faultCode.info}</textarea>
	    		</td>
	    	</tr>
	    </table>
</form>



