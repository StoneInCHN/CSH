var maintainReservation_manager_tool = {
		add:function(){
			$('#addMaintainReservation').dialog({
			    title: message("csh.endUser.add"),    
			    width: 600,    
			    height: 450,
			    href:'../maintainReservation/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addMaintainReservation_form').form('validate');
						var plate=$("#vehiclePlate").combobox('getText');
						$('#addMaintainReservation_form').append('<input type="hidden" name="plate" value="'+plate+'"/>')
						if(validate){
								$.ajax({
									url:"../maintainReservation/add.jhtml",
									type:"post",
									data:$("#addMaintainReservation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addMaintainReservation').dialog("close")
											$("#addMaintainReservation_form").form("reset");
											$("#maintainReservation-table-list").datagrid('reload');
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
						 $('#addMaintainReservation').dialog("close");
						 $("#addMaintainReservation_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addMaintainReservation_form').show();
			    },
			    onClose:function(){
			    	$('#addMaintainReservation').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#maintainReservation-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editMaintainReservation').dialog({    
				title: message("csh.common.edit"),     
			    width: 600,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../maintainReservation/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var plate=$("#vehiclePlate").combobox('getText');
						$('#editMaintainReservation_form').append('<input type="hidden" name="plate" value="'+plate+'"/>')
						var validate = $('#editMaintainReservation_form').form('validate');
						if(validate){
							$.ajax({
								url:"../maintainReservation/update.jhtml",
								type:"post",
								data:$("#editMaintainReservation_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editMaintainReservation').dialog("close");
										$("#maintainReservation-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editMaintainReservation').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	$("#vehiclePlate").combobox('clear');
			    	$("#vehiclePlate").combobox({    
				    valueField:'id',    
				    textField:'plate',
				    editable : false,
				    required:true,
				    data:$.parseJSON($("#vehicleMaintainListMap").val()),
				    prompt:message("csh.common.please.select"),
					onLoadSuccess:function(){
					    	$("#vehiclePlate").combobox("setValue",$("#vehiclePlate").attr("data-value"))    	
					},
			    	});
			    },
			    onClose:function(){
			    	$('#editMaintainReservation').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('maintainReservation-table-list','../maintainReservation/delete.jhtml');
		}
};

$(function(){
	$("#maintainReservation-table-list").datagrid({
		title:message("csh.maintainReservation.list"),
		fitColumns:true,
		toolbar:"#maintainReservation_manager_tool",
		url:'../maintainReservation/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#maintainReservationDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../maintainReservation/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#maintainReservationDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#maintainReservationDetail').empty();
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
		      },{title:message("csh.reservation.operate"),field:"button",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row.carServiceRecord.chargeStatus == 'RESERVATION'){
		    			  return '<button class="btn btn-primary btn-maintain-approve" data-value='+row.id+'>确认</button><button class="btn btn-danger btn-maintain-reject" data-value='+row.id+'>拒绝</button>'  
		    		  }else if(row.carServiceRecord.chargeStatus == 'RESERVATION_SUCCESS'){
		    			  return '<button class="btn btn-primary btn-maintain-arrival" data-value='+row.id+'>到店确认</button>'  
		    		  }else{
		    			  return ""
		    		  }
		    	  } 
		      }
		   ]
		],
		onLoadSuccess:function(data){
			$('.btn-maintain-approve').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../maintainReservation/approve.jhtml?id="+id,
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
							$("#maintainReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});

			$('.btn-maintain-reject').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../maintainReservation/reject.jhtml?id="+id,
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
							$("#maintainReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});
			
			$('.btn-maintain-arrival').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../maintainReservation/arrival.jhtml?id="+id,
					type:"get",
					beforeSend:function(){
						$.messager.progress({
							text:message("csh.common.saving")
						});
					},
					success:function(result,response,status){
						$.messager.progress('close');
						if(response == "success"){
							showSuccessMsg("到店");
							$("#maintainReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});
		}
	});

	
	$("#maintainReservation-search-btn").click(function(){
	  var _queryParams = $("#maintainReservation-search-form").serializeJSON();
	  $('#maintainReservation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#maintainReservation-table-list").datagrid('reload');
	})
})
