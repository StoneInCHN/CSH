<form id="editBrand_form" method="post">   
	<input type="hidden" name="id" value="${brand.id}">
	<table class="table table-striped"  border="0">
	    <tr>
			<th>${message("csh.brand.name")}:</th>
			<td>
				 <input  class="easyui-textbox" value="${brand.name}" name="name"  data-options="required:true" validtype="length[0,200]"/>
			</td>
			<th >${message("csh.brand.url")}:</th>
			<td colspan="4">
				<input  class="easyui-textbox" value="${brand.url}" name="url" validtype="length[0,200]"/>
			</td>
		</tr>
		<tr>
			<th>${message("csh.brand.logo")}:</th>
			<td  colspan="4">
				 <div title="图片上传" class="easyui-tooltip headWarp">
	    				<div id="brandUploader-edit" class="single-uploader">
						    <div  class="queueList filled">
						         <div  class="placeholder element-invisible">
						        	<div id="brandFilePicker-edit" ></div>
						        </div>
						 
							    <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										 <img id ="brandLogo-edit" src="${brand.logo}" style ="width:80px;hight:80 px"/>
									</p>
							    </div>
							</div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
						<div class="btns">
					        <div class="uploadBtn state-pedding"></div>
					        <div id="brandFilePicker-edit2" class="margin-left-20">选择文件</div>
					        <div class="btn btn-info savePhoto margin-left-20" style="display:none">保存图片</div>
					    </div>
	    			</div>
			</td>
		</tr>
		<tr>
			<th>${message("csh.brand.introduction")}:</th>
			 <td colspan="5">
				 <textarea id= "edit_brand_introduction"  
				 style="height:200px;width:300px" name="introduction"></textarea>   
			</td>
		</tr>
	</table>
</form>


