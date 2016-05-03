<form id="advertisementDetail_form" method="post">
	<table class="table table-striped"  border="0">
	   <tr>
    		<th>${message("csh.advertisement.advName")}:</th>
    		<td>
    			 <input class="easyui-textbox" name="advName" value="${advertisement.advName}" disabled = "disabled" id= "advName" data-options="required:true" />
    		</td>
    		<th>${message("csh.advertisement.status")}:</th>
    		<td>
    			  <input class="easyui-combobox" disabled = "disabled" data-options="
			     valueField: 'label',
			     textField: 'value',
			     data: [{
			      label: 'ENABLE',
			      value: '${message("csh.common.enable")}'
			      [#if advertisement.status = 'ENABLE']
			      	, selected:true
			      [/#if]
			     },{
			      label: 'DISABLE',
			      value: '${message("csh.common.disable")}'
			      [#if advertisement.status = 'DISABLE']
			      	, selected:true
			      [/#if]
			     }],
			     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="status" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.advertisement.advContentLink")}:</th>
    		<td colspan="3">
    			 <input class="easyui-textbox" name="advContentLink" value="${advertisement.advContentLink}" disabled = "disabled" id= "advContentLink" data-options="required:true" />
    		</td>
    	</tr>
    	<tr>
			<th>${message("csh.advertisement.advImage")}:</th>
			<td colspan="3">
				  <img id ="advertisementImgPath-edit" src="${base}/${advertisement.advImageUrl}" disabled = "disabled" style ="width:110px;hight:110 px">
			</td>
		</tr>
		<tr>
			<th>${message("csh.advertisement.remark")}:</th>
			<td colspan="3">
				 <input type="text" class="easyui-textbox" name="remark" disabled = "disabled" value="${advertisement.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:350" />   
			</td>
		</tr>
</form>
