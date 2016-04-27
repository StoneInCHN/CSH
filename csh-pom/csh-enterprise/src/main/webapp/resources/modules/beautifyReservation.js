var beautifyReservation_manager_tool = {
		add:function(){
			$('#addBeautifyReservation').dialog({
			    title: message("csh.endUser.add"),    
			    width: 600,    
			    height: 450,
			    href:'../beautifyReservation/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addBeautifyReservation_form').form('validate');
						var plate=$("#vehiclePlate").combobox('getText');
						$('#addBeautifyReservation_form').append('<input type="hidden" name="plate" value="'+plate+'"/>')
						if(validate){
								$.ajax({
									url:"../beautifyReservation/add.jhtml",
									type:"post",
									data:$("#addBeautifyReservation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addBeautifyReservation').dialog("close")
											$("#addBeautifyReservation_form").form("reset");
											$("#beautifyReservation-table-list").datagrid('reload');
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
						$('#addBeautifyReservation').empty();
					}
			    }],
			    onOpen:function(){
			    	$('#addRepareReservation_form').show();
			    },
			    onClose:function(){
			    	$('#addBeautifyReservation').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#beautifyReservation-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editBeautifyReservation').dialog({    
				title: message("csh.common.edit"),     
			    width: 600,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../beautifyReservation/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#beautifyReservation_form').form('validate');
						var plate=$("#vehiclePlate").combobox('getText');
						$('#editBeautifyReservation_form').append('<input type="hidden" name="plate" value="'+plate+'"/>')
						if(validate){
							$.ajax({
								url:"../beautifyReservation/update.jhtml",
								type:"post",
								data:$("#editBeautifyReservation_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editBeautifyReservation').dialog("close");
										$("#beautifyReservation-table-list").datagrid('reload');
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
					    data:$.parseJSON($("#vehicleListMap").val()),
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
					    	$("#vehiclePlate").combobox("setValue",$("#vehiclePlate").attr("data-value"))    	
					    }
					});
			    },
			    onClose:function(){
			    	$('#editRepareReservation').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('beautifyReservation-table-list','../beautifyReservation/delete.jhtml');
		}
};

$(function(){
	$("#beautifyReservation-table-list").datagrid({
		title:message("csh.beautifyReservation.list"),
		fitColumns:true,
		toolbar:"#beautifyReservation_manager_tool",
		url:'../beautifyReservation/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#beautifyReservationDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../beautifyReservation/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#beautifyReservationDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#beautifyReservationDetail').empty();
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
		    			  return '<button class="btn btn-primary btn-beautify-approve" data-value='+row.id+'>确认</button><button class="btn btn-danger btn-beautify-reject" data-value='+row.id+'>拒绝</button>'  
		    		  }else if(row.carServiceRecord.chargeStatus == 'RESERVATION_SUCCESS'){
		    			  return '<button class="btn btn-primary btn-beautify-arrival" data-value='+row.id+'>到店确认</button>'  
		    		  }else{
		    			  return ""
		    		  }
		    	  } 
		      }
		   ]
		],
		onLoadSuccess:function(data){
			$('.btn-beautify-approve').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../beautifyReservation/approve.jhtml?id="+id,
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
							$("#beautifyReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});

			$('.btn-beautify-reject').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../beautifyReservation/reject.jhtml?id="+id,
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
							$("#beautifyReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});
			$('.btn-beautify-arrival').click(function(){
				var $this=$(this);
				var id = $this.attr('data-value');
				
				$.ajax({
					url:"../beautifyReservation/arrival.jhtml?id="+id,
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
							$("#beautifyReservation-table-list").datagrid('reload');
						}else{
							alertErrorMsg();
						}
					}
				});
			});
		}
	});

	
	$("#beautifyReservation-search-btn").click(function(){
	  var _queryParams = $("#beautifyReservation-search-form").serializeJSON();
	  $('#beautifyReservation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#beautifyReservation-table-list").datagrid('reload');
	})
})
