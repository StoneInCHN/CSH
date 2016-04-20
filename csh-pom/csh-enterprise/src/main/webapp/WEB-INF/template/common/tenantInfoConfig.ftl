 <form id="tenantInfoConfig_form" method="post" >
	<table  class="table table-striped"  border="0">
		<input type="hidden" value="${tenantInfo.id}" name="id"/>
                     		<tr>
								<th>
									${message("csh.tenantInfo.tenantName")}:
								</th>
								<td>
									<input class="easyui-textbox" id="tenantName" value="${tenantInfo.tenantName}" name="tenantName"  maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.contactPerson")}:
								</th>
								<td>
									<input type="text" name="contactPerson" value="${tenantInfo.contactPerson}" class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.contactPhone")}:
								</th>
								<td>
									<input type="text" name="contactPhone" value="${tenantInfo.contactPhone}" class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.area")}:
								</th>
								<td>
									<input type="easyui-textbox" disabled="disabled" id="areaId" value="${areaName}" name="areaId"/>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.address")}:
								</th>
								<td>
									<input type="text" name="address" value="${tenantInfo.address}"  class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<!--<tr>
								<th>${message("csh.apply.pisotion.point")}:</th>
								<td>
									<input type="text" class="easyui-textbox" style="width:100px" readonly id="longitude" name="longitude" />
									<input type="text" class="easyui-textbox" style="width:100px" readonly  id="latitude" name="latitude" />
									<button type="button" class="btn btn-info " id="selectPositionPoint">
									  ${message("csh.apply.select.pisotion.point")}
									</button>
								</td>
							</tr>
							-->
							<tr>
								<th>
									${message("csh.tenantInfo.email")}:
								</th>
								<td>
									<input type="text" name="email" value="${tenantInfo.email}" class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.businessTime")}:
								</th>
								<td>
									<input type="text" name="businessTime" value="${tenantInfo.businessTime}" class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.description")}:
								</th>
								<td>
									<input type="text" name="description" value="${tenantInfo.description}" class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.ownerName")}:
								</th>
								<td>
									<input type="text" name="ownerName" value="${tenantInfo.ownerName}" class="easyui-textbox" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.versionConfig")}:
								</th>
								<td>
									<input type="text" name="ownerName" disabled="disabled" value="${tenantInfo.versionConfig.versionName}" class="easyui-textbox"/>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.accoutStatus.select")}:
								</th>
								<td>
				    			 <input class="easyui-combobox" disabled="disabled" data-options="
							     valueField: 'label',
							     textField: 'value',
							     data: [{
							      label: 'ACTIVED',
							      value: '${message("csh.tenantInfo.accoutStatus.ACTIVED")}'
							      [#if tenantInfo.accountStatus == 'ACTIVED']
							      	,selected:true
							      [/#if]
							     },{
							      label: 'LOCKED',
							      value: '${message("csh.tenantInfo.accoutStatus.LOCKED")}'
							      [#if tenantInfo.accountStatus == 'LOCKED']
							      	, selected:true
							      [/#if]
							     },{
							      label: 'DELETE',
							      value: '${message("csh.tenantInfo.accoutStatus.DELETE")}'
							      [#if tenantInfo.accountStatus == 'DELETE']
							      	, selected:true
							      [/#if]
							     }]"/>
				    		</td>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.tenantInfo.remark")}:
								</th>
								<td>
									<input type="text" name="remark" value="${tenantInfo.ownerName}" class="easyui-textbox" maxlength="200" />
								</td>
							</tr>
						</table>
</form>




