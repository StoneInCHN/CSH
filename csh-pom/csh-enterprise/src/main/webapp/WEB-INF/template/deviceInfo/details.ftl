<form id="deviceInfoDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.deviceInfo.deviceNO")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value="${deviceInfo.deviceNo}" name="deviceNo" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>	
	    		<th>${message("csh.deviceInfo.simNO")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="simNo" disabled="disabled" value="${deviceInfo.simNo}" id= "simNo"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr class="search-item">
			    <th> ${message("csh.deviceInfo.bindStatus")}:</th>
			    <td>
			    <input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'BINDED',
				      value: '${message("csh.deviceInfo.bindStatus.BINDED")}'
				      [#if deviceInfo.bindStatus == "BINDED"]
				      ,selected: true
				      [/#if]
				     },{
				      label: 'UNBINDED',
				      value: '${message("csh.deviceInfo.bindStatus.UNBINDED")}'
				      [#if deviceInfo.bindStatus == "UNBINDED"]
				      ,selected: true
				      [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  style="width:110px;"/></td>
			</tr>
	    	<tr>
	    		<th>里程:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${realTimeCarCondition.mileAge}"   data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>运行时间:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.engineRuntime}"  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>平均油耗:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.averageOil}" disabled="disabled"/>
	    		</td>
	    		<th>速度:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.averageOil}"  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >经度:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.lon}" disabled="disabled"/>
	    			
	    		</td>
	    		<th >纬度:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.lat}" disabled="disabled"/>
	    			
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>方位角:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${realTimeCarCondition.azimuth}"  disabled="disabled"/>
	    		</td>
	    		<th>是否启动:</th>
	    		<td >
	    		<input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'BINDED',
				      value: '${message("csh.common.yes")}'
				      [#if realTimeCarCondition.acc == "1"]
				      ,selected: true
				      [/#if]
				     },{
				      label: 'UNBINDED',
				      value: '${message("csh.common.no")}'
				      [#if realTimeCarCondition.acc == "0"]
				      ,selected: true
				      [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  style="width:110px;"/></td>
	    		</td>
	    	</tr>
	    </table>
</form>



