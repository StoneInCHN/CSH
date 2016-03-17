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
					    groupField:'group',
						loadFilter :function(data) {
							var newData=new Array()
							for(var i=0;i<data.length;i++){
								var newDataItem = null;
								newData.name = data[i].name;
								newData.id = data[i].id;
								newData.group=data[i].parent.name;
								newData.push(newData)
							}
							return newData;
							
						},
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
			    width: 700,    
			    height: 450,    
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
					    groupField:'group',
						loadFilter :function(data) {
							var newData=new Array()
							for(var i=0;i<data.length;i++){
								var newDataItem = null;
								newData.name = data[i].name;
								newData.id = data[i].id;
								newData.group=data[i].parent.name;
								newData.push(newData)
							}
							return newData;
						},
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
			    width: 700,    
			    height: 450, 
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
		onLoadSuccess:function(data){
			$("#totalRecord").val(data.total);
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicle.endUser"),field:"endUser",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.userName;
			    	  }
		      	  }},
			   {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(row.endUser != null){
			    		  return  row.endUser.mobileNum;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.plate"),field:"plate",sortable:true},
		      {title:message("csh.vehicle.vehicleBrand"),width:100,field:"vehicleBrand",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(row != null){
			    		  return  row.vehicleBrandDetail.vehicleLine.vehicleBrand.name;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.vehicleLine"),width:100,field:"vehicleLine",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(row != null){
			    		  return  row.vehicleBrandDetail.vehicleLine.name;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.vehicleBrandDetail"),width:100,field:"vehicleBrandDetail",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.vehicleNo"),field:"vehicleNo",sortable:true},
	      	  {title:message("csh.vehicle.dashboardMileage"),field:"dashboardMileage",sortable:true},
	      	  {title:message("csh.vehicle.dashboardBV"),field:"dashboardBV",sortable:true},
		      {title:message("csh.vehicle.device"),field:"deviceNo",width:100,sortable:false},
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
