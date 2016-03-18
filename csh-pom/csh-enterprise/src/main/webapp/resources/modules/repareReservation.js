var repareReservation_manager_tool = {
		add:function(){
			$('#addRepareReservation').dialog({
			    title: message("csh.endUser.add"),    
			    width: 600,    
			    height: 450,
			    href:'../repareReservation/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addRepareReservation_form').form('validate');
						var plate=$("#vehiclePlate").combobox('getText');
						$('#addRepareReservation_form').append('<input type="hidden" name="plate" value="'+plate+'"/>')
						if(validate){
								$.ajax({
									url:"../repareReservation/add.jhtml",
									type:"post",
									data:$("#addRepareReservation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addRepareReservation').dialog("close")
											$("#addRepareReservation_form").form("reset");
											$("#repareReservation-table-list").datagrid('reload');
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
						 $('#addRepareReservation').dialog("close");
						 $("#addRepareReservation_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addRepareReservation_form').show();
			    },
			    onClose:function(){
			    	$('#addVehicleMaintain').dialog("clear");
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#repareReservation-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editRepareReservation').dialog({    
				title: message("csh.common.edit"),     
			    width: 600,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../repareReservation/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#repareReservation_form').form('validate');
						var plate=$("#vehiclePlate").combobox('getText');
						$('#editRepareReservation_form').append('<input type="hidden" name="plate" value="'+plate+'"/>')
						if(validate){
							$.ajax({
								url:"../repareReservation/update.jhtml",
								type:"post",
								data:$("#editRepareReservation_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editRepareReservation').dialog("close");
										$("#repareReservation-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editRepareReservation').dialog("close").form("reset");
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
			    	$('#addVehicleMaintain').dialog("clear");
			    }
			});  
		},
		remove:function(){
			listRemove('repareReservation-table-list','../repareReservation/delete.jhtml');
		}
};

$(function(){
	$("#repareReservation-table-list").datagrid({
		title:message("csh.repareReservation.list"),
		fitColumns:true,
		toolbar:"#repareReservation_manager_tool",
		url:'../repareReservation/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#repareReservationDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../repareReservation/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#repareReservationDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#addVehicleMaintain').dialog("clear");
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.reservation.userName"),field:"endUser",sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value != null){
		    			  return value.userName;
		    		  }else{
		    			  return null;
		    		  }
		    		  
		    	  }},
	    	  {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row != null){
		    			  return row.endUser.mobileNum;
		    		  }else{
		    			  return null;
		    		  }
		    		  
		    	  }},
		      {title:message("csh.reservation.plate"),field:"plate",width:100,sortable:true},
		      {title:message("csh.reservation.vehicleBrand"),field:"vehicleBrand",width:100,sortable:true},
		      {title:message("csh.reservation.reservationInfoFrom"),field:"reservationInfoFrom",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value =="APP"){
		    			  return message("csh.reservation.reservationInfoFrom.app")
		    		  }else if(value =="CALL"){
		    			  return message("csh.reservation.reservationInfoFrom.call")
		    		  }
		    	  }},
		      {title:message("csh.reservation.reservationDate"),field:"reservationDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#repareReservation-search-btn").click(function(){
	  var _queryParams = $("#repareReservation-search-form").serializeJSON();
	  $('#repareReservation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#repareReservation-table-list").datagrid('reload');
	})
})
