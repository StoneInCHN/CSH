<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantInfo.js"></script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'" style="padding-right:10px">
		<div id="tenantInfo_manager_tool">
			<div class="tool-button">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantInfo_manager_tool.edit();">更新</a>
			</div>
			<div class="tool-filter"></div>
		</div>
		<div>
		<form id="tenantInfoConfig_form" method="post" >
			<table  class="table table-striped"  border="0">
				<input type="hidden" value="${tenantInfo.id}" name="id"/>
				<input type="hidden" id="tenantImageList_input" name="tenantImageList"/>
		 		<tr>
					<th>
						${message("csh.tenantInfo.tenantName")}:
					</th>
					<td>
						<input class="easyui-textbox" disabled="disabled" id="tenantName" value="${tenantInfo.tenantName}" name="tenantName"  maxlength="20" />
					</td>
				
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
				
					<th>
						${message("csh.tenantInfo.area")}:
					</th>
					<td>
						<input type="text"  class="easyui-textbox" disabled="disabled" id="areaId" value="${areaName}" name="areaId"/>
					</td>
				</tr>
				<tr>
					<th>
						${message("csh.tenantInfo.address")}:
					</th>
					<td>
						<input type="text" name="address" value="${tenantInfo.address}" disabled="disabled" class="easyui-textbox" maxlength="20" />
					</td>
				
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
						<!--<input type="text" name="businessTime" value="${tenantInfo.businessTime}" class="easyui-textbox" maxlength="20" />-->
						<!--<input type="text" id="businessTime" onfocus="WdatePicker({dateFmt:'HH:mm'})" class="Wdate" style="width:80px"/>-->
						<div>
					    <input type="text" class="Wdate" readonly="true" id="beginDate" name="businessTimeStart" value="${businessTimeStart}" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate: '#F{$dp.$D(\'endDate\')}'});" />
					   	到
					   	<input type="text" class="Wdate" readonly="true" id="endDate"  name="businessTimeEnd" value="${businessTimeEnd}" onfocus="WdatePicker({dateFmt:'HH:mm',minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
					   	<div>
					</td>
				
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
						<input type="text" name="ownerName" disabled="disabled" value="${tenantInfo.ownerName}" class="easyui-textbox" maxlength="20" />
					</td>
				
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
				
					<th>
						${message("csh.tenantInfo.remark")}:
					</th>
					<td>
						<input type="text" name="remark" value="${tenantInfo.ownerName}" class="easyui-textbox" maxlength="200" />
					</td>
				</tr>
				<tr>
					<th>${message("csh.tenantInfo.tenantImage")}:</th>
		    		<td  colspan="4">
		    			 <div title="图片上传" class="easyui-tooltip">
		    				<div id="tenantImageUploader-edit" class="multiple-uploader">
							    <div  class="queueList">
							       
							        <div class="show-img">
							        <p class="imgWrap img-thumbnail">
							        [#list tenantImageList as tenantImage]
										    <img src="${tenantImage.image}" style ="width:110px;hight:110 px">
										    <button type="button" onclick="tenantInfo_manager_tool.deleteTenantImage(${tenantImage.id})" class="btn btn-primary">删除图片</button>
									[/#list]
									</p>
						        	</div>
						        	 <div  class="placeholder">
							        	<div id="tenantImageFilePicker-edit" ></div>
							        </div>
							    </div>
							    <div class="btns">
							    	<div id="filePicker2_Photos"></div>
							        <div class="uploadBtn state-pedding"></div>
							    </div>
							</div>
		    			</div>
		    		</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</div>




