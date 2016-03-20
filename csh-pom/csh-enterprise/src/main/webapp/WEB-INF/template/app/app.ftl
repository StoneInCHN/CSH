<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/app.js"></script>
<form id="app_form" method="post">
	<input type="hidden" name="id" value="${app.id}"/>  
	<input type="hidden" name="tenantID" value="${app.tenantID}"/>
	<input type="hidden" name="createDate" value="${app.createDate}"/>
	<table class="table-striped"  border="0" style="margin-top: 80px;margin-left: 80px;">
		    <tr>
	    		<th  width="80px">${message("csh.app.appTitleName")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${app.appTitleName}" name="appTitleName"  id= "appTitleName"  data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="2" style="text-align: center;">
	    			 <input type="button"  class="btn btn-primary" value="${message('csh.save')}" id= "appSave"  style="margin-top:20px"/>
	    		</td>
	    	</tr>
	    </table>
</form>


