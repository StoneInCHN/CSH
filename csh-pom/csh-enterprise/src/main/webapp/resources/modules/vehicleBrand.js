var vehicle_manager_tool = {
		add:function(){
			$('#addVehicleBrand').dialog({
			    title: message("csh.vehicleBrand.add"),    
			    width: 700,    
			    height: 550,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicleBrand_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../vehicleBrand/add.jhtml",
									type:"post",
									data:$("#addVehicleBrand_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicleBrand').dialog("close")
											$("#addVehicleBrand_form").form("reset");
											$("#vehicleBrand-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addVehicleBrand').dialog("close");
						 $("#addVehicleBrand_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addVehicleBrand_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicleBrand-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editVehicleBrand').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicleBrand/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicleBrand_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleBrand/update.jhtml",
								type:"post",
								data:$("#editVehicleBrand_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicleBrand').dialog("close");
										$("#vehicleBrand-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicleBrand').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
//			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('vehicleBrand-table-list','../vehicleBrand/delete.jhtml');
		}
};

$(function(){
	$("#vehicleBrand-table-list").datagrid({
		title:message("csh.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicleBrand_manager_tool",
		url:'../vehicleBrand/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleBrandDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../vehicleBrand/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleBrandDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicleBrand.plate"),field:"plate",width:100,sortable:true},
		      {title:message("csh.vehicle.vehicleBrand"),field:"vehicleBrand",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }
		      	  }},
	      	  {title:message("csh.vehicle.endUser"),field:"endUser",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.userName;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.device"),field:"device",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.deviceNo;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.status"),field:"status",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "ENABLE"){
			    		  return  message("csh.common.enable");
			    	  }else if (value = "DISABLE"){
			    		  return  message("csh.common.disable");
			    	  }
		      	  }  
		      },
		      {title:message("csh.vehicle.bindTime"),field:"bindTime",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#vehicleBrand-search-btn").click(function(){
	  var _queryParams = $("#vehicleBrand-search-form").serializeJSON();
	  $('#vehicleBrand-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicleBrand-table-list").datagrid('reload');
	})
})
