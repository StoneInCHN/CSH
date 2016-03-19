<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/app.js"></script>
<form id="app_form" method="post">   
	<table class="table table-striped"  border="0">
		    <tr>
	    		<th>${message("csh.app.appTitleName")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${app.appTitleName}"  id= "appTitleName"  data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td >
	    			 <input type="button" value="保存"  id= "appSave" />
	    		</td>
	    	</tr>
	    </table>
</form>



