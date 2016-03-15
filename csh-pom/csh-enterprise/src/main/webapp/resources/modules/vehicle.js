var vehicle_manager_tool = {
		add:function(){
			$('#addVehicle').dialog({
			    title: message("csh.vehicle.add"),    
			    width: 600,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicle_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../vehicle/add.jhtml",
									type:"post",
									data:$("#addVehicle_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicle').dialog("close")
											$("#addVehicle_form").form("reset");
											$("#vehicle-table-list").datagrid('reload');
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
						 $('#addVehicle').dialog("close");
						 $("#addVehicle_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$("#vehicleSelectVehicleBrand-add").combobox({
			    		url: '../vehicleBrand/findAllVehicleBrand.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    formatter: function(row){
							var opts = $(this).combobox('options');
							return row[opts.textField];
						},
						onSelect: function(rec){    
				            var url = '../vehicleLine/findVehicleLineByBrand.jhtml?vehicleBrandId='+rec.id;    
				            $('#vehicleSelectVehicleLine-add').combobox('clear');
				            $('#vehicleSelectVehicleLine-add').combobox('reload', url);    
				        }

					});
			    	$("#vehicleSelectVehicleLine-add").combobox({
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
						onSelect: function(rec){ 
				            var url = '../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml?vehicleLineId='+rec.id;    
				            $('#vehicleSelectVehicleBrandDetail-add').combobox('clear');
				            $('#vehicleSelectVehicleBrandDetail-add').combobox('reload', url);    
				        }

					});
			    	$("#vehicleSelectVehicleBrandDetail-add").combobox({
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$('#addVehicle_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editVehicle').dialog({    
				title: message("csh.common.edit"),     
			    width: 600,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicle/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicle_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicle/update.jhtml",
								type:"post",
								data:$("#editVehicle_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicle').dialog("close");
										$("#vehicle-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicle').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$("#vehicleSelectVehicleBrand-edit").combobox({
			    		url: '../vehicleBrand/findAllVehicleBrand.jhtml',
					    valueField:'id',
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    formatter: function(row){
							var opts = $(this).combobox('options');
							return row[opts.textField];
						},
						onSelect: function(rec){    
				            var url = '../vehicleLine/findVehicleLineByBrand.jhtml?vehicleBrandId='+rec.id;
				            $('#vehicleSelectVehicleLine-edit').combobox('clear');
				            $('#vehicleSelectVehicleLine-edit').combobox('reload', url);    
				        },
						onChange:function(newValue, oldValue){
							var url = '../vehicleLine/findVehicleLineByBrand.jhtml?vehicleBrandId='+newValue;
				            $('#vehicleSelectVehicleLine-edit').combobox('clear');
				            $('#vehicleSelectVehicleLine-edit').combobox('reload', url);
						}

					});
			    	$("#vehicleSelectVehicleLine-edit").combobox({
			    		url : '../vehicleLine/findVehicleLineByBrand.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
						onSelect: function(rec){ 
				            var url = '../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml?vehicleLineId='+rec.id;
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('clear');
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('reload', url);    
				        },
				        onChange:function(newValue, oldValue){
				        	var url = '../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml?vehicleLineId='+newValue;
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('clear');
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('reload', url);    
						}

					});
			    	$("#vehicleSelectVehicleBrandDetail-edit").combobox({
			    		url:'../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$('#editVehicle_form').show();
			    	$("#vehicleSelectVehicleBrand-edit").combobox("setValue",$("#vehicleSelectVehicleBrand-edit").attr("data-value"));
			    	$("#vehicleSelectVehicleLine-edit").combobox("setValue",$("#vehicleSelectVehicleLine-edit").attr("data-value"));
			    	$("#vehicleSelectVehicleBrandDetail-edit").combobox("setValue",$("#vehicleSelectVehicleBrandDetail-edit").attr("data-value"));
			    }
			});  
		},
		remove:function(){
			listRemove('vehicle-table-list','../vehicle/delete.jhtml');
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
			    width: 600,    
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
			    }],
			    onLoad:function(){
			    	$("#vehicleSelectVehicleBrand-detail").combobox({
			    		url: '../vehicleBrand/findAllVehicleBrand.jhtml',
					    valueField:'id',
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					});
			    	$("#vehicleSelectVehicleLine-detail").combobox({
			    		url : '../vehicleLine/findVehicleLineByBrand.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$("#vehicleSelectVehicleBrandDetail-detail").combobox({
			    		url:'../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$('#editVehicle_form').show();
			    	$("#vehicleSelectVehicleBrand-detail").combobox("setValue",$("#vehicleSelectVehicleBrand-detail").attr("data-value"));
			    	$("#vehicleSelectVehicleLine-detail").combobox("setValue",$("#vehicleSelectVehicleLine-detail").attr("data-value"));
			    	$("#vehicleSelectVehicleBrandDetail-detail").combobox("setValue",$("#vehicleSelectVehicleBrandDetail-detail").attr("data-value"));
			    }
			    
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicle.plate"),field:"plate",width:100,sortable:true},
		      {title:message("csh.vehicle.vehicleBrandDetail"),field:"vehicleBrandDetail",width:100,sortable:true,
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
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
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
