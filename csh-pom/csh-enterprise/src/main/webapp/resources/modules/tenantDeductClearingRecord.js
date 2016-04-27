var tenantDeductClearingRecord_manager_tool = {
		applyDeductClearingRecord:function(){
			$('#applyDeductClearingRecord').dialog({
			    title: message("csh.tenantDeductClearingRecord.add"),    
			    width: 700,    
			    height: 550,
			    href:'../tenantDeductClearingRecord/applyDeductClearing.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#applyDeductClearingRecordForm').form('validate');
						var rows = $('#deductClearingCarServiceRecord-table-list').datagrid('getRows');
						if(rows.length == 0){
							$.messager.alert(message("csh.clearingCarServiceRecord.no"));  
							return false;
						}
						var ids = [];
						for(var i =0; i<rows.length;i++){
							ids.push(rows[i].id);
						}
						$('#applyDeductClearingRecordForm').append("<input type='hidden' name='ids' value="+ids+">")
						if(validate){
							$.ajax({
								url:"../tenantDeductClearingRecord/add.jhtml",
								type:"post",
								data:$("#applyDeductClearingRecordForm").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#applyDeductClearingRecord').dialog("close")
										$("#applyDeductClearingRecordForm").form("reset");
										$("#deductClearingCarServiceRecord-table-list").datagrid('reload');
									}else{
										alertErrorMsg();
									}
								}
							});
						}
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#applyDeductClearingRecord').dialog("close");
						 $("#applyDeductClearingRecordForm").form("reset");
					}
			    }],
				onLoad:function(){
					//总金额
					var totalMoney = 0;
					//结算账单清单
					$("#deductClearingCarServiceRecord-table-list").datagrid({
						title:message("csh.carServiceRecord.list"),
						fitColumns:true,
						url:'../carServiceTenantDeductRecord/showCurrentClearingRecord.jhtml',  
						loadMsg:message("csh.common.loading"),
						striped:true,
						columns:[
						   [
						      {title:message("csh.carServiceRecord.serviceName"),field:"carService",sortable:true,
						    	  formatter: function(value,row,index){
						    		  return value.serviceName;
						    	  }},
					    	  {title:message("csh.carServiceRecord.chargeStatus"),width:50,field:"chargeStatus",sortable:true,
						    	  formatter: function(value,row,index){
						    		  if(value=="RESERVATION"){
						    				return message("csh.carServiceRecord.chargeStatus.RESERVATION");
						    			}else if(value == "UNPAID"){
						    				return message("csh.carServiceRecord.chargeStatus.UNPAID");
						    			}else if (value == "PAID"){
						    				return message("csh.carServiceRecord.chargeStatus.PAID");
						    			}else if (value == "RESERVATION_SUCCESS"){
						    				return message("csh.carServiceRecord.chargeStatus.RESERVATION_SUCCESS");
						    			}else if (value == "RESERVATION_FAIL"){
						    				return message("csh.carServiceRecord.chargeStatus.RESERVATION_FAIL");
						    			}else if (value == "FINISH"){
						    				return message("csh.carServiceRecord.chargeStatus.FINISH");
						    			}else if (value == "OVERDUE"){
						    				return message("csh.carServiceRecord.chargeStatus.OVERDUE");
						    			}
						    	  }},
					    	  {title:message("csh.carServiceRecord.endUser"),field:"endUser",sortable:true,
						    	  formatter: function(value,row,index){
						    		  return value.userName;
						    	  }},
					    	  {title:message("csh.carServiceRecord.price"),width:50,field:"deductMoney",sortable:true,
					    		  formatter: function(value,row,index){
					    			  if(value != null){
					    				  totalMoney = totalMoney+value;	
					    			  }
						    		  return value;
						    	  }},  
						      {title:message("csh.carServiceRecord.paymentDate"),field:"paymentDate",width:100,sortable:true,formatter: function(value,row,index){
									return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
								}
						      },
						   ]
						],
						onLoadSuccess:function(){
							$("#amountOfCurrentPeriod").textbox('setValue',totalMoney);
						}
					});
					
				},
			    onClose:function(){
			    	$('#addTenantClearingRecord').empty();
			    }
			});  
		},
		show:function(){
			var _show_row = $('#tenantDeductClearingRecord-table-list').datagrid('getSelected');
			if( _show_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
				$('#tenantClearingRecordDetail').dialog({    
				    title: message("csh.common.detail"),    
				    width: 700,    
				    height: 550, 
				    cache: false,
				    modal: true,
				    href:'../tenantClearingRecord/details.jhtml?id='+_show_row.id,
				    buttons:[{
						text:message("csh.common.close"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#tenantClearingRecordDetail').dialog("close");
						}
				    }],
				    onClose:function(){
				    	$('#tenantClearingRecordDetail').empty();
				    }
				});   
		}
		
};

$(function(){
	
	
	
	$("#tenantDeductClearingRecord-table-list").datagrid({
		title:message("csh.tenantDeductClearingRecord.list"),
		fitColumns:true,
		toolbar:"#tenantDeductClearingRecord_manager_tool",
		url:'../tenantDeductClearingRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.tenantClearingRecord.clearingSn"),field:"clearingSn",sortable:true},
		      {title:message("csh.tenantClearingRecord.amountOfCurrentPeriod"),field:"amountOfCurrentPeriod",width:100,sortable:true},
		      {title:message("csh.tenantClearingRecord.clearingDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#tenantDeductClearingRecord-search-btn").click(function(){
	  var _queryParams = $("#tenantDeductClearingRecord-search-form").serializeJSON();
	  $('#tenantDeductClearingRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#tenantDeductClearingRecord-table-list").datagrid('reload');
	})
})
