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
						var plate=$("#addRepareVehiclePlate").combobox('getText');
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
						$('#addRepareReservation').empty();
					}
			    }],
			    onOpen:function(){
			    	$('#addRepareReservation_form').show();
			    },
			    onClose:function(){
			    	$('#addRepareReservation').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#repareReservation-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
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
						var validate = $('#editRepareReservation_form').form('validate');
						var plate=$("#editRepareVehiclePlate").combobox('getText');
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
						 $('#editRepareReservation').dialog("close");
					}
			    }],
			    onLoad:function(){
				    	$("#vehiclePlate").combobox({    
					    valueField:'id',    
					    textField:'plate',
					    editable : false,
					    required:true,
					    data:$.parseJSON($("#editRepareVehicleListMap").val()),
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
					    	$("#editRepareVehiclePlate").combobox("setValue",$("#editRepareVehiclePlate").attr("data-value"))    	
					    }
					});
			    },
			    onClose:function(){
			    	$('#editRepareReservation').empty();
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
			    	$('#repareReservationDetail').empty();
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
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      },{title:message("csh.reservation.operate"),field:"button",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row.carServiceRecord.chargeStatus == 'RESERVATION'){
		    			  return '<button class="btn btn-primary btn-repare-approve" data-value='+row.id+'>确认</button><button class="btn btn-danger btn-repare-reject" data-value='+row.id+'>拒绝</button>'  
		    		  }else{
		    			  return ""
		    		  }
		    	  } 
		      }
		   ]
		],
		onLoadSuccess:function(data){
			$('.btn-repare-approve').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../repareReservation/approve.jhtml?id="+id,
					type:"get",
					beforeSend:function(){
						$.messager.progress({
							text:message("csh.common.saving")
						});
					},
					success:function(result,response,status){
						$.messager.progress('close');
						if(response == "success"){
							showSuccessMsg("确认预约");
							$("#repareReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});

			$('.btn-repare-reject').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../repareReservation/reject.jhtml?id="+id,
					type:"get",
					beforeSend:function(){
						$.messager.progress({
							text:message("csh.common.saving")
						});
					},
					success:function(result,response,status){
						$.messager.progress('close');
						if(response == "success"){
							showSuccessMsg("拒绝");
							$("#repareReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});
		
		}
	});

	
	$("#repareReservation-search-btn").click(function(){
	  var _queryParams = $("#repareReservation-search-form").serializeJSON();
	  $('#repareReservation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#repareReservation-table-list").datagrid('reload');
	})
})
