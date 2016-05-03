<form id="editAdvertisement_form" method="post" stype="margin-top: 10px">   
	 <input type="hidden" name="id" id="id" value="${advertisement.id}">   
	    	<table class="table table-striped"  border="0">
		    	<tr>
		    		<th>${message("csh.advertisement.advName")}:</th>
		    		<td>
		    			 <input class="easyui-textbox" name="advName" value="${advertisement.advName}" id= "advName" data-options="required:true" />
		    		</td>
		    		<th>${message("csh.advertisement.status")}:</th>
		    		<td>
		    			  <input class="easyui-combobox" data-options="
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
		    		<td colspan="4">
		    			 <input class="easyui-textbox" name="advContentLink" value="${advertisement.advContentLink}" id= "advContentLink" data-options="required:true" />
		    		</td>
		    	</tr>
		    	<tr rowspan="4">
	    		
	    		<th>${message("csh.advertisement.advImage")}:</th>
	    		<td  colspan="4">
	    			 <div title="图片上传" class="easyui-tooltip headWarp">
	    				<div id="advertisementUploader-edit" class="single-uploader">
						    <div  class="queueList filled">
						        <div  class="placeholder element-invisible">
						        	<div id="advertisementFilePicker-edit" ></div>
						        </div>
						        <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										    <img id ="advertisementImgPath-edit" src="${base}/${advertisement.advImageUrl}" style ="width:110px;hight:110 px">
									 </p>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
						<div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						        <div id="advertisementFilePicker-edit2" class="margin-left-40">选择文件</div>
						        <div class="btn btn-info savePhoto margin-left-40" style="display:none">保存头像</div>
						    </div>
	    			</div>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.advertisement.remark")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="remark" value="${advertisement.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />   
	    		</td>
	    	</tr>
	    </table>
</form>



