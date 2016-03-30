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
			    onLoad:function(){
			    	$('#addVehicleInsurance_form').show();
			    	
			    	$('#addIDphotoFile').change(function(){
			    		var fd = new FormData($("#addVehicleInsurance_form"));
			    		fd.append("file", $('#addIDphotoFile')[0].files[0]);
			    		fd.append("imageType","IDPHOTO");
			    		$.ajax({
					    	  url: "../file/uploadPhoto.jhtml",
					    	  type: "POST",
					    	  data: fd,
					    	  processData: false,  // 告诉jQuery不要去处理发送的数据
					    	  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
					    	  success:function(result,response,status){
					    		  if(result.type = "success"){
					    			  $("#addIDphotoFileValue").val(result.content);
					    			  $("#addIDphotoImg").attr('src',result.content);
					    		  }
					    	  }
					    	});
			    	});
			    	$('#addDrivingLicensePhoto').change(function(){
			    		var fd = new FormData($("#addVehicleInsurance_form"));
			    		fd.append("file", $('#addDrivingLicensePhoto')[0].files[0]);
			    		fd.append("imageType","DRIVINGLICENSEPHOTO");
			    		$.ajax({
					    	  url: "../file/uploadPhoto.jhtml",
					    	  type: "POST",
					    	  data: fd,
					    	  processData: false,  // 告诉jQuery不要去处理发送的数据
					    	  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
					    	  success:function(result,response,status){
					    		  if(result.type = "success"){
					    			  $("#addDrivingLicensePhotoValue").val(result.content);
					    			  $("#addDrivingLicensePhotoImg").attr('src',result.content);
					    		  }
					    	  }
					    	});
			    	});
			    	$('#addDriverLicensePhoto').change(function(){
			    		var fd = new FormData($("#addVehicleInsurance_form"));
			    		fd.append("file", $('#addDriverLicensePhoto')[0].files[0]);
			    		fd.append("imageType","DRIVERLICENSEPHOTO");
			    		$.ajax({
					    	  url: "../file/uploadPhoto.jhtml",
					    	  type: "POST",
					    	  data: fd,
					    	  processData: false,  // 告诉jQuery不要去处理发送的数据
					    	  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
					    	  success:function(result,response,status){
					    		  if(result.type = "success"){
					    			  $("#addDriverLicensePhotoValue").val(result.content);
					    			  $("#addDriverLicensePhotoImg").attr('src',result.content); 
					    		  }
					    	  }
					    	});
			    	});
			    	
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
						var validate = $('#editVehicleInsurancen_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleInsurance/update.jhtml",
								type:"post",
								data:$("#editVehicleInsurancen_form").serialize(),
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
			    	$("#editInsuranceVehiclePlate").combobox({    
					    valueField:'id',    
					    textField:'plate',
					    editable : false,
					    required:true,
					    data:$.parseJSON($("#vehicleListMap").val()),
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
					    	$("#editInsuranceVehiclePlate").combobox("setValue",$("#editInsuranceVehiclePlate").attr("data-value"))    	
					    }
					});
			    	$('#editIDphotoFile').change(function(){
			    		var fd = new FormData($("#editVehicleInsurancen_form"));
			    		fd.append("file", $('#editIDphotoFile')[0].files[0]);
			    		
			    		$.ajax({
					    	  url: "../file/uploadProfilePhoto.jhtml",
					    	  type: "POST",
					    	  data: fd,
					    	  processData: false,  // 告诉jQuery不要去处理发送的数据
					    	  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
					    	  success:function(result,response,status){
					    		  if(result.type = "success"){
					    			  $("#editIDphotoFileValue").val(result.content);
					    			  $("#editIDphotoImg").attr('src',result.content);
					    		  }
					    	  }
					    	});
			    	});
			    	$('#editDrivingLicensePhoto').change(function(){
			    		var fd = new FormData($("#editVehicleInsurancen_form"));
			    		fd.append("file", $('#editDrivingLicensePhoto')[0].files[0]);
			    		
			    		$.ajax({
					    	  url: "../file/uploadProfilePhoto.jhtml",
					    	  type: "POST",
					    	  data: fd,
					    	  processData: false,  // 告诉jQuery不要去处理发送的数据
					    	  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
					    	  success:function(result,response,status){
					    		  if(result.type = "success"){
					    			  $("#editDrivingLicensePhotoValue").val(result.content);
					    			  $("#editDrivingLicensePhotoImg").attr('src',result.content);
					    		  }
					    	  }
					    	});
			    	});
			    	$('#editDriverLicensePhoto').change(function(){
			    		var fd = new FormData($("#editVehicleInsurancen_form"));
			    		fd.append("file", $('#editDriverLicensePhoto')[0].files[0]);
			    		
			    		$.ajax({
					    	  url: "../file/uploadProfilePhoto.jhtml",
					    	  type: "POST",
					    	  data: fd,
					    	  processData: false,  // 告诉jQuery不要去处理发送的数据
					    	  contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
					    	  success:function(result,response,status){
					    		  if(result.type = "success"){
					    			  $("#editDriverLicensePhotoValue").val(result.content);
					    			  $("#editDriverLicensePhotoImg").attr('src',result.content); 
					    		  }
					    	  }
					    	});
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
			    		  }else if(value == false){
			    			  return message("csh.common.no");
			    		  }
			    			  
			    	  }
		      },
		      {title:message("csh.vehicleInsurance.isLoaned"),field:"isLoaned",width:100,sortable:true,
	    		  formatter:function(value,row,index){
		    		  if(value == true){
		    			  return message("csh.common.yes");
		    		  }else if(value == false){
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

	
	$("#vehicleInsurance-search-btn").click(function(){
	  var _queryParams = $("#vehicleInsurance-search-form").serializeJSON();
	  $('#vehicleInsurance-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicleInsurance-table-list").datagrid('reload');
	});
	
})
