<form id="brandDetail_form" method="post">
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
				 <div title="" class="easyui-tooltip headWarp">
	    				<div id="brandUploader-detail" class="single-uploader">
						    <div  class="queueList filled">
						        <div  class="placeholder element-invisible">
						        	<div id="brandFilePicker-edit" ></div>
						        </div>
						        <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										    <img id ="brandLogo-detail" src="${brand.logo}" style ="width:110px;hight:110 px">
									 </p>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
	    			</div>
			</td>
		</tr>
		<tr>
			<th>${message("csh.brand.introduction")}:</th>
			 <td colspan="5">
				 <div id= "edit_brand_introduction"  
				 style="height:200px;width:300px" name="introduction">${brand.introduction}</div>   
			</td>
		</tr>
	</table>
</form>



