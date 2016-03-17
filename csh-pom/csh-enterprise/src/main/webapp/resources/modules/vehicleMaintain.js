var vehicleMaintain_manager_tool = {
		add:function(){
			$('#addVehicleMaintain').dialog({
			    title: message("csh.vehicleMaintain.add"),    
			    width: 700,    
			    height: 400,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicleMaintain_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../vehicleMaintain/add.jhtml",
									type:"post",
									data:$("#addVehicleMaintain_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicleMaintain').dialog("close")
											$("#addVehicleMaintain_form").form("reset");
											$("#vehicleMaintain-table-list").datagrid('reload');
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
						 $('#addVehicleMaintain').dialog("close");
						 $("#addVehicleMaintain_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addVehicleMaintain_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicleLine-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editVehicleLine').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicleLine/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicleLine_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleLine/update.jhtml",
								type:"post",
								data:$("#editVehicleLine_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicleLine').dialog("close");
										$("#vehicleLine-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicleLine').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
//			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('vehicleLine-table-list','../vehicleLine/delete.jhtml');
		}
};

$(function(){
	$("#vehicleMaintain-table-list").datagrid({
		title:message("csh.vehicleMaintain.list"),
		url:'../vehicleMaintain/list.jhtml', 
		fitColumns:true,
		toolbar:"#vehicleMaintain_manager_tool",
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleMaintainDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../vehicleMaintain/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleMaintainDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicle.plate"),field:"vehicle",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value !=null){
		    			  return value.plate;
		    		  }else{
		    			  return null;
		    		  }
		    			  
		    	  }},
	    	  {title:message("csh.vehicle.endUser"),field:"userName",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row !=null){
		    			  return row.vehicle.endUser.userName;
		    		  }else{
		    			  return null;
		    		  }
		    			  
		    	  }},
	    	  {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row !=null){
		    			  return row.vehicle.endUser.mobileNum;
		    		  }else{
		    			  return null;
		    		  }
		    			  
		    	  }},
	    	  {title:message("csh.vehicle.dashboardMileage"),field:"dashboardMileage",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row !=null){
		    			  return row.vehicle.dashboardMileage;
		    		  }else{
		    			  return null;
		    		  }
		    			  
		    	  }},
		      {title:message("csh.vehicleMaintain.lastMaintainMileage"),field:"lastMaintainMileage",width:100,sortable:true},
		      {title:message("csh.vehicleMaintain.lastMaintainDate"),field:"lastMaintainDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("csh.vehicleMaintain.nextMaintainMileage"),field:"nextMaintainMileage",width:100,sortable:true},
		      {title:message("csh.vehicleMaintain.nextMaintainDate"),field:"nextMaintainDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      }
		   ]
		]
	});

	
	$("#vehicleMaintain-search-btn").click(function(){
	  var _queryParams = $("#vehicleMaintain-search-form").serializeJSON();
	  $('#vehicleMaintain-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicleMaintain-table-list").datagrid('reload');
	});
	
})
