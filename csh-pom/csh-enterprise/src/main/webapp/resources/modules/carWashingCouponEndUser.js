var carWashingCouponEndUser_manager_tool = {
		add:function(){
			$('#addCarWashingCouponEndUser').dialog({    
			    title: message("csh.carWashingCoupon.add"),  
			    width: 370,    
			    height: 270,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addCarWashingCouponEndUser_form').form('validate');
						if(validate){
							$.ajax({
								url:"../carWashingCouponEndUser/add.jhtml",
								type:"post",
								data:$("#addCarWashingCouponEndUser_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(result.type == "success"){
										showSuccessMsg(result.content);
										$('#addCarWashingCouponEndUser_form').form('reset');
										$('#addCarWashingCouponEndUser').dialog("close");
										$("#carWashingCouponEndUser-table-list").datagrid('reload');
									}else{
										alertInfoMsg(result.content);
									}
									
								},
								error:function (XMLHttpRequest, textStatus, errorThrown) {
									$.messager.progress('close');
									alertErrorMsg();
								}
							});
						}
						else{
							alert("validate fail");
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addCarWashingCouponEndUser_form').hide();
						 $('#addCarWashingCouponEndUser').dialog("close");
					}
			    }],
			    onOpen:function(){
			    	$('#addCarWashingCouponEndUser_form').show();
			    	$("#addCarWashingCouponEndUser_selectEndUser").click(function(){
			    		searchEndUser("addCarWashingCouponEndUser_form_endUser");
			    	})
			    }
			
			});   
		}
}

function carWashingCouponEndUserLoad(id){
	$("#carWashingCouponEndUser-table-list").datagrid({
		title:message("csh.carWashingCouponEndUser.list"),
		//fit:true,
		fitColumns:true,
		toolbar:"#carWashingCouponEndUser_manager_tool",
		url:'../carWashingCouponEndUser/list.jhtml?id='+id,  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		columns:[
		   [
/*		      {field:'ck',checkbox:true},*/
		      {title:message("csh.carWashingCoupon.couponName"),field:"couponName",sortable:true,formatter: function(value,row,index){
		    	  return "洗车券"
				}},
		      {title:message("csh.carWashingCoupon.expireDate"),field:"expireDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd");
					}else{
						return "";
					}}
		      },
		      {title:message("csh.carWashingCoupon.endUser"),field:"endUser",sortable:true,formatter: function(value,row,index){
		    	  if(value && value.userName){
						return value.userName;
					}else{
						return "";
					}
				}},
		      {title:message("csh.carWashingCoupon.isUsed"),field:"isUsed",sortable:true,formatter: function(value,row,index){
		    	  if(value){
						return "已使用";
					}else{
						return "未使用";
					}
				}},
		      {title:message("csh.carWashingCoupon.remark"),field:"remark",sortable:true},
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      },
		   ]
		]
	});
}
