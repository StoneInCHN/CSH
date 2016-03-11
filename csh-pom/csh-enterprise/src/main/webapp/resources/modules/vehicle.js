var endUser_manager_tool = {
		add:function(){
			$('#addEndUser').dialog({
			    title: message("csh.endUser.add"),    
			    width: 400,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addEndUser_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../endUser/add.jhtml",
									type:"post",
									data:$("#addEndUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addEndUser').dialog("close")
											$("#addEndUser_form").form("reset");
											$("#endUser-table-list").datagrid('reload');
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
						 $('#addEndUser').dialog("close");
						 $("#addEndUser_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addEndUser_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#endUser-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editEndUser').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../endUser/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#endUser_form').form('validate');
						if(validate){
							$.ajax({
								url:"../endUser/update.jhtml",
								type:"post",
								data:$("#editEndUser_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editEndUser').dialog("close");
										$("#endUser-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editEndUser').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('endUser-table-list','../endUser/delete.jhtml');
		}
};

$(function(){
	$("#vehicle-table-list").datagrid({
		title:message("csh.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicle_manager_tool",
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../vehicle/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicle.plate"),field:"plate",width:100,sortable:true},
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

	
	$("#vehicle-search-btn").click(function(){
	  var _queryParams = $("#vehicle-search-form").serializeJSON();
	  $('#vehicle-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicle-table-list").datagrid('reload');
	})
})
