<form id="addMessageInfo_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.messageInfo.title")}:</th>
    		<td >
    			 <input  class="easyui-textbox" disabled="disabled" name="messageTitle" value="${messageInfo.messageTitle}" id= "title" />
    		</td>
    		<th>${message("csh.messageInfo.type")}:</th>
    		<td >
    			 <input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'PERSONALMSG',
				      value: '${message("csh.messageInfo.type.PERSONALMSG")}'
				      [#if messageInfo.messageType == 'PERSONALMSG']
				      ,selected:true
				      [/#if]
				     },{
				      label: 'NEWSMSG',
				      value: '${message("csh.messageInfo.type.NEWSMSG")}'
				      [#if messageInfo.messageType == 'NEWSMSG']
				      ,selected:true
				      [/#if]
				     },{
				      label: 'PROMOTION',
				      value: '${message("csh.messageInfo.type.PROMOTION")}'}
				      [#if messageInfo.messageType == 'PROMOTION']
				      ,selected:true
				      [/#if]],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="messageType" style="width:110px;"/>
    		</td>
    	</tr>
    	<tr>
    		<th >发送用户:</th>
    		<td colspan="5">
    			<select name="from"  disabled="disabled" id="endUserSmsMultiselect" class="js-multiselect form-control" size="8" multiple="multiple">
    				[#list messageInfo.msgUser as user]
	    				<option>${user.endUser.userName} ${user.endUser.mobileNum}</option>
	    			[/#list]
				</select>
    		</td>
    	</tr>
    	<tr>
    		<th >内容:</th>
    		<td colspan="5">
    			 <input disabled="disabled" class="easyui-textbox" name="messageContent" value="${messageInfo.messageContent}" id= "messageContent"  data-options="multiline:true,height:120,width:500"/>
    		</td>
    	</tr>
    </table>
	</form>