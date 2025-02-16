var tenantClearingRecord_manager_tool = {
		applyClearingRecord:function(){
			$('#applyClearingRecord').dialog({
			    title: message("csh.tenantClearingRecord.add"),    
			    width: 800,    
			    height: 450,
			    href:'../tenantClearingRecord/applyClearing.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addTenantClearingRecord_form').form('validate');
						var rows = $('#clearingCarServiceRecord-table-list').datagrid('getRows');
						if(rows.length == 0){
							$.messager.alert("提示",message("csh.clearingCarServiceRecord.no"),'warning');  
							return false;
						}
						var ids = [];
						for(var i =0; i<rows.length;i++){
							ids.push(rows[i].id);
						}
						$('#applyClearingRecordForm').append("<input type='hidden' name='ids' value="+ids+">")
						if(validate){
							$.ajax({
								url:"../tenantClearingRecord/add.jhtml",
								type:"post",
								data:$("#applyClearingRecordForm").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#applyClearingRecord').dialog("close")
										$("#applyClearingRecordForm").form("reset");
										$("#tenantClearingRecord-table-list").datagrid('reload');
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
						 $('#applyClearingRecord').dialog("close");
						 $("#applyClearingRecordForm").form("reset");
					}
			    }],
				onLoad:function(){
					//总金额
					var totalMoney = 0;
					//总提成金额
					var totalDeductMoney = 0;
					//结算账单清单
					$("#clearingCarServiceRecord-table-list").datagrid({
						title:message("csh.carServiceRecord.list"),
						fitColumns:true,
						url:'../carServiceRecord/showCurrentClearingRecord.jhtml',  
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
						    	  
						    	  {title:message("csh.carServiceRecord.paymentType"),width:50,field:"paymentType",sortable:true,
							    	  formatter: function(value,row,index){
							    		  if(value == "ALIPAY"){
							    			  totalMoney = totalMoney+row.price;
							    			  totalDeductMoney = totalDeductMoney+row.price;
							    			  return "支付宝"
							    		  }else if(value == "WECHAT"){
							    			  totalMoney = totalMoney+row.price;
							    			  totalDeductMoney = totalDeductMoney+row.price;
							    			  return "微信"
							    		  }else if(value == "WALLET"){
							    			  totalMoney = totalMoney+row.price;
							    			  totalDeductMoney = totalDeductMoney+row.price;
							    			  return "钱包"
							    		  } else if(value == "DIRECTREDPACKAGE") {
							    			  //直接支付加上红包支付，结算金额为优惠后金额
							    			  totalMoney = totalMoney+row.price;
							    	          totalDeductMoney = totalDeductMoney+row.price;
							    	          return "直接支付红包混合"
							    		  }
							    		  else if(value == "COUPON"){
							    			  if(row.couponSource == 'OPERATION'){
							    				  totalMoney = totalMoney+row.price;
							    				  totalDeductMoney = totalDeductMoney+row.discountPrice;
							    			  }else if(row.couponSource == 'ENTERPRISE'){
							    				  totalMoney = totalMoney+row.discountPrice;
							    				  totalDeductMoney = totalDeductMoney+row.price;
							    			  }
							    			  return "优惠券";
							    		  }else if(value == "COUPONREDPACKAGE"){
							    			  debugger;
							    			  if(row.couponSource == 'OPERATION'){
							    				  totalMoney = totalMoney+row.price-row.redPackageUsage;
							    				  totalDeductMoney = totalDeductMoney+row.discountPrice+row.redPackageUsage;
							    			  }else if(row.couponSource == 'ENTERPRISE'){
							    				  totalMoney = totalMoney+row.discountPrice;
							    				  totalDeductMoney = totalDeductMoney+row.price;
							    			  }
							    			  return "优惠券红包";
							    		  }else if(value == "OFFLINEBALLANCEREDPACKAGE"){
						    				  totalMoney = totalMoney+row.discountPrice-row.offlineBalance;
						    				  totalDeductMoney = totalDeductMoney+row.discountPrice-row.offlineBalance;
							    			  return "线下钱包红包";
							    		  }else if(value == "WASHCOUPON"){
//							    			  totalMoney = totalMoney+row.offlineBalance;
							    			  return "洗车券";
							    		  }else if(value == "OFFLINEBALLANCE"){
							    			  totalMoney = totalMoney+(row.price-row.offlineBalance);
							    			  totalDeductMoney = totalDeductMoney+(row.price-row.offlineBalance);
							    			  return "线下余额";
							    		  }else if(value == "MIXCOUPONOFFLINE"){
							    			  if(row.couponSource == 'OPERATION'){
							    				  totalMoney = totalMoney+(row.price-row.offlineBalance);
							    				  totalDeductMoney = totalDeductMoney+(row.discountPrice-row.offlineBalance);
							    			  }else if(row.couponSource == 'ENTERPRISE'){
							    				  totalMoney = totalMoney+(row.discountPrice-row.offlineBalance);
							    				  totalDeductMoney = totalDeductMoney+(row.price-row.offlineBalance);
							    			  }
							    			  return "线下钱包优惠券混合";
							    		  }else if(value == "MIXCOUPONOFFLINE"){
							    			  if(row.couponSource == 'OPERATION'){
							    				  totalMoney = totalMoney+(row.price-row.offlineBalance);
							    				  totalDeductMoney = totalDeductMoney+(row.discountPrice-row.offlineBalance);
							    			  }else if(row.couponSource == 'ENTERPRISE'){
							    				  totalMoney = totalMoney+(row.discountPrice-row.offlineBalance);
							    				  totalDeductMoney = totalDeductMoney+(row.price-row.offlineBalance);
							    			  }
							    			  return "线下钱包优惠券混合";
							    		  }else if(value == "MIXCOUPONOFFLINEREDPACKAGE"){
							    			  if(row.couponSource == 'OPERATION'){
							    				  totalMoney = totalMoney+(row.price-row.offlineBalance-row.redPackageUsage);
							    				  totalDeductMoney = totalDeductMoney+(row.discountPrice-row.offlineBalance+row.redPackageUsage);
							    			  }else if(row.couponSource == 'ENTERPRISE'){
							    				  totalMoney = totalMoney+(row.discountPrice-row.offlineBalance);
							    				  totalDeductMoney = totalDeductMoney+(row.price-row.offlineBalance);
							    			  }
							    			  return "线下钱包优惠券红包混合";
							    		  }
							    	  }},
					    	  {title:message("csh.carServiceRecord.endUser"),field:"endUser",sortable:true,
						    	  formatter: function(value,row,index){
						    		  return value.userName;
						    	  }},
					    	  {title:message("csh.carServiceRecord.price"),width:50,field:"price",sortable:true},
						      {title:message("csh.carServiceRecord.discountPrice"),width:50,field:"discountPrice",sortable:true},
						      {title:message("csh.carServiceRecord.offlineBalance"),width:50,field:"offlineBalance",sortable:true},
						      {title:message("csh.carServiceRecord.systemType"),width:50,field:"couponSource",sortable:true,
					    		  formatter: function(value,row,index){
					    			  if(value != null && value =='OPERATION'){
					    				  return "平台优惠券"
					    			  }else if(value != null && value =='ENTERPRISE'){
					    				  return "店铺优惠券"
					    			  }
						    		  return value;
						      }},
						      {title:message("csh.carServiceRecord.paymentDate"),field:"paymentDate",width:100,sortable:true,formatter: function(value,row,index){
						    	  if(value != null){
										return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
									}else{
										return "";
									}
								}
						      },
						   ]
						],
						onLoadSuccess:function(){
							$("#amountOfCurrentPeriod").textbox('setValue',totalMoney);
							var rate = $('#platformRate').val();
							$("#amountRealIncome").textbox('setValue',(totalMoney-math.mul(totalDeductMoney,parseFloat(rate))));
							$("#platformRate").textbox('setText',Number(rate).toPercent());
						}
					});
					
				},
			    onClose:function(){
			    	$('#addTenantClearingRecord').empty();
			    }
			});  
		},
		show:function(){
			var _show_row = $('#tenantClearingRecord-table-list').datagrid('getSelected');
			if( _show_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
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
	
	
	
	$("#tenantClearingRecord-table-list").datagrid({
		title:message("csh.tenantClearingRecord.list"),
		fitColumns:true,
		toolbar:"#tenantClearingRecord_manager_tool",
		url:'../tenantClearingRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.tenantClearingRecord.clearingSn"),field:"clearingSn",sortable:true},
		      {title:message("csh.tenantClearingRecord.amountOfCurrentPeriod"),field:"amountOfCurrentPeriod",width:100,sortable:true},
		      {title:message("csh.tenantClearingRecord.amountRealIncome"),field:"amountRealIncome",width:100,sortable:true},
		      {title:message("csh.tenantClearingRecord.clearingDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      },
		   ]
		]
	});

	
	$("#tenantClearingRecord-search-btn").click(function(){
	  var _queryParams = $("#tenantClearingRecord-search-form").serializeJSON();
	  $('#tenantClearingRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#tenantClearingRecord-table-list").datagrid('reload');
	})
})
