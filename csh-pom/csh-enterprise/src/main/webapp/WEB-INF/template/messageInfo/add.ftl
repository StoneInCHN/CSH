<form id="addMessageInfo_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.messageInfo.title")}:</th>
    		<td colspan="2">
    			 <input  class="easyui-textbox" name="messageTitle" id= "title" />
    		</td>
    		<th>${message("csh.messageInfo.type")}:</th>
    		<td >
    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'PERSONALMSG',
				      value: '${message("csh.messageInfo.type.PERSONALMSG")}'
				     },{
				      label: 'NEWSMSG',
				      value: '${message("csh.messageInfo.type.NEWSMSG")}'
				     },{
				      label: 'PROMOTION',
				      value: '${message("csh.messageInfo.type.PROMOTION")}'}],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="messageType" style="width:110px;"/>
    		</td>
    	</tr>
    	<tr>
    		<th >筛选用户:</th>
    		<td>
    			 <input  class="easyui-textbox" id= "endUserFilter" />
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<select name="from" id="endUserMultiselect" class="js-multiselect form-control" size="8" multiple="multiple">
				</select>
    		</td>
    		<td>
    			<button type="button" id="js_right_All_1" class="btn btn-block btn-primary"><i class="glyphicon glyphicon-forward"></i></button>
				<button type="button" id="js_right_Selected_1" class="btn btn-block btn-primary"><i class="glyphicon glyphicon-chevron-right"></i></button>
				<button type="button" id="js_left_Selected_1" class="btn btn-block btn-primary"><i class="glyphicon glyphicon-chevron-left"></i></button>
				<button type="button" id="js_left_All_1" class="btn btn-block btn-primary"><i class="glyphicon glyphicon-backward"></i></button>
    		</td>
    		<td colspan="2">
    			<select id="endUserMultiselect_to" class="form-control" size="8" multiple="multiple"></select>
    		</td>
    	</tr>
    	<tr>
    		<th >内容:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" name="messageContent" id= "messageContent"  data-options="multiline:true,height:120,width:500"/>
    		</td>
    	</tr>
    </table>
	</form>