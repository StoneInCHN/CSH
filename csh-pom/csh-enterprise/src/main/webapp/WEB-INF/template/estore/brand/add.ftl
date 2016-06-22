<form id="addBrand_form" method="post" >
	<input type="hidden" id="addBrand_form_file_input" name="logo">
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.brand.name")}:</th>
    		<td>
    			 <input  class="easyui-textbox" name="name"  data-options="required:true" validtype="length[0,200]"/>
    		</td>
    		<th >${message("csh.brand.url")}:</th>
    		<td colspan="4">
    			<input  class="easyui-textbox" name="url" validtype="length[0,200]"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.brand.logo")}:</th>
    		<td  colspan="4">
    			 <div title="图片上传" class="easyui-tooltip headWarp">
	    				<div id="brandUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="brandFilePicker-add" ></div>
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
    			 <textarea id= "add_brand_introduction"  
    			 style="height:200px;width:300px" name="introduction"></textarea>   
    		</td>
    	</tr>
    </table>
</form>

