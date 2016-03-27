var vehicleInsurance_manager_tool = {
		add:function(){
			$('#addVehicleInsurance').dialog({
			    title: message("csh.vehicleInsurance.add"),   
			    href:'../vehicleInsurance/add.jhtml',
			    width: 750,    
			    height: 600,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicleInsurance_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../vehicleInsurance/add.jhtml",
									type:"post",
									data:$("#addVehicleInsurance_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicleInsurance').dialog("close")
											$("#addVehicleInsurance_form").form("reset");
											$("#vehicleInsurance-table-list").datagrid('reload');
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
						 $('#addVehicleInsurance').dialog("close");
						 $("#addVehicleInsurance_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addVehicleInsurance_form').show();
			    	
			    },
			    onClose:function(){
			    	$('#addVehicleInsurance').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicleInsurance-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editVehicleInsurance').dialog({    
				title: message("csh.common.edit"),     
			    width: 650,    
			    height: 400,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicleInsurance/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicleInsurance_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleInsurance/update.jhtml",
								type:"post",
								data:$("#editVehicleInsurance_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicleInsurance').dialog("close");
										$("#vehicleInsurance-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						$('#editVehicleInsurance').dialog("close");
						 $("#editVehicleInsurance_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#vehiclePlate").combobox({    
					    valueField:'id',    
					    textField:'plate',
					    editable : false,
					    required:true,
					    data:$.parseJSON($("#vehicleListMap").val()),
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
					    	$("#vehiclePlate").combobox("setValue",$("#vehiclePlate").attr("data-value"))    	
					    }
					});
			    	
			    },
			    onClose:function(){
			    	$('#editVehicleInsurance').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('vehicleInsurance-table-list','../vehicleInsurance/delete.jhtml');
		}
};

$(function(){
	$("#vehicleInsurance-table-list").datagrid({
		title:message("csh.vehicleMaintain.list"),
		url:'../vehicleInsurance/list.jhtml', 
		fitColumns:true,
		toolbar:"#vehicleInsurance_manager_tool",
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleInsuranceDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 650,    
			    height: 400, 
			    cache: false,
			    modal: true,
			    href:'../vehicleInsurance/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleInsuranceDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#vehicleInsuranceDetail').empty();
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicleInsurance.userName"),field:"userName",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row !=null){
		    			  return row.endUser.userName;
		    		  }else{
		    			  return null;
		    		  }
		    			  
		    	  }},
		    	  {title:message("csh.vehicleInsurance.mobile"),field:"mobileNum",width:100,sortable:true,
			    	  formatter:function(value,row,index){
			    		  if(row !=null){
			    			  return row.endUser.mobileNum;
			    		  }else{
			    			  return null;
			    		  }
			    			  
			    	  }},
		      {title:message("csh.vehicle.plate"),field:"vehiclePlate",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row !=null){
		    			  return row.vehicle.plate;
		    		  }else{
		    			  return null;
		    		  }
		    			  
		    	  }},
		      {title:message("csh.vehicleInsurance.isOwned"),field:"isOwned",width:100,sortable:true,
		    		  formatter:function(value,row,index){
			    		  if(value == true){
			    			  return message("csh.common.yes");
			    		  }else{
			    			  return message("csh.common.no");
			    		  }
			    			  
			    	  }
		      },
		      {title:message("csh.vehicleInsurance.isLoaned"),field:"isLoaned",width:100,sortable:true,
	    		  formatter:function(value,row,index){
		    		  if(value == true){
		    			  return message("csh.common.yes");
		    		  }else{
		    			  return message("csh.common.no");
		    		  }
		    			  
		    	  }
	      },
		      {title:message("csh.vehicleInsurance.startDate"),field:"insuranceStartDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("csh.vehicleInsurance.endDate"),field:"insuranceEndDate",width:100,sortable:true,formatter: function(value,row,index){
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
