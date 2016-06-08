<script src="${base}/resources/modules/carWashingCouponEndUser.js"></script>
<script src="${base}/resources/modules/carWashingCoupon.js"></script>
<table id="carWashingCoupon-table-list"></table>
<div id="carWashingCoupon_manager_tool">
	<div class="tool-button">
		[#if  !isCreate] <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="carWashingCoupon_manager_tool.add();">添加</a> [/#if]
		<!--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="carWashingCoupon_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="carWashingCoupon_manager_tool.remove();">删除</a>-->
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addCarWashingCoupon">
	<form id="addCarWashingCoupon_form" method="post" class="form-table">  
	   <table class="table table-striped">
	    	<tr>
	    		<th>${message("csh.carWashingCoupon.couponName")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="couponName" data-options="required:true" /> 
	    		</td>
	    	</tr>
			<tr>
	    		<th>${message("csh.carWashingCoupon.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editCarWashingCoupon"></div> 
<div id="carWashingCouponEndUserContainer" style="display:none;">
		<table id="carWashingCouponEndUser-table-list"></table>
		<div id="carWashingCouponEndUser_manager_tool">
			<div class="tool-button">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="carWashingCouponEndUser_manager_tool.add();">添加</a>
			</div>
			<div class="tool-filter"></div>
		</div> 
		<div id="addCarWashingCouponEndUser">
			<form id="addCarWashingCouponEndUser_form" method="post" class="form-table">  
				<input  type="hidden" id="addCarWashingCouponEndUser_form_id" name="id" data-options="required:true" />
			   <table class="table table-striped">
			    	<tr>
			    		<th>${message("csh.carWashingCoupon.couponName")}:</th>
			    		<td>
			    			  <input  type="hidden" id="addCarWashingCouponEndUser_form_endUserID" name="endUserID" data-options="required:true" />
			    			  <input class="easyui-textbox" id="addCarWashingCouponEndUser_form_endUser" type="text" name="count" data-options="required:true,editable:false" />
			    			  <a class="btn" id="addCarWashingCouponEndUser_selectEndUser">选择用户</a>
			    		</td>
			    	</tr>
			    	<tr>
			    		<th>${message("csh.carWashingCoupon.counts")}:</th>
			    		<td>
			    			  <input type="text" class="easyui-numberspinner" name="counts" data-options="required:true,min:1,editable:false" />
			    		</td>
			    	</tr>
			    </table>
			</form>
		</div>
</div> 