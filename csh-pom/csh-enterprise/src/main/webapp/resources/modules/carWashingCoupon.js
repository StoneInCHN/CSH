var carWashingCoupon_manager_tool = {
		add:function(){
			$('#addCarWashingCoupon').dialog({    
			    title: message("csh.carWashingCoupon.add"),  
			    width: 370,    
			    height: 270,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addCarWashingCoupon_form').form('validate');
						if(validate){
							$.ajax({
								url:"../carWashingCoupon/add.jhtml",
								type:"post",
								data:$("#addCarWashingCoupon_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#addCarWashingCoupon_form').form('reset');
									$('#addCarWashingCoupon').dialog("close");
									$("#carWashingCoupon-table-list").datagrid('reload');
									
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
						 $('#addCarWashingCoupon_form').hide();
						 $('#addCarWashingCoupon').dialog("close");
					}
			    }],
			    onOpen:function(){
			    	$('#addCarWashingCoupon_form').show();
			    }
			
			});   
		},
		edit:function(){
			var _edit_row = $('#carWashingCoupon-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.notice"),message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editCarWashingCoupon').dialog({
			    title: message("csh.common.edit"),     
			    width: 370,    
			    height: 270,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../carWashingCoupon/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editCarWashingCoupon_form').form('validate');
						if(validate){
							$.ajax({
								url:"../carWashingCoupon/update.jhtml",
								type:"post",
								data:$("#editCarWashingCoupon_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#editCarWashingCoupon').dialog("close");
									$("#carWashingCoupon-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editCarWashingCoupon').dialog("close");
					}
			    }]
			});  
			$('#editCarWashingCoupon_form').show();
		},
		remove:function(){
			listRemove('carWashingCoupon-table-list','../carWashingCoupon/delete.jhtml');
		}
}

$(function(){
	
	$("#carWashingCoupon-table-list").datagrid({
		title:message("csh.carWashingCoupon.list"),
		//fit:true,
		fitColumns:true,
		toolbar:"#carWashingCoupon_manager_tool",
		url:'../carWashingCoupon/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.carWashingCoupon.couponName"),field:"couponName",sortable:true},
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
		],
		onDblClickRow:function(rowIndex, rowData){
			var $carWashingCouponEndUserContainer = $("#carWashingCouponEndUserContainer");
			$carWashingCouponEndUserContainer.show();
			carWashingCouponEndUserLoad(rowData.id);
			$("#addCarWashingCouponEndUser_form_id").val(rowData.id);
		}
	});

})




