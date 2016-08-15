var shipping_manager_tool = {
		addShipping:function(){
			var _edit_row = $('#shipping-order-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.order.select.addShippingRow"),'warning');  
				return false;
			}
			$('#addShipping').dialog({
			    title: message("csh.shipping"),    
			    width: 600,    
			    height: 450,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    href:'../shipping/addShipping.jhtml?orderId='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addShipping_form').form('validate');
						if(validate){
								$.ajax({
									url:"../shipping/saveShipping.jhtml",
									type:"post",
									data:$("#addShipping_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											$('#addShipping').dialog("close");
											$('#addShipping_form').form("reset");
											$("#shipping-order-table-list").datagrid('reload');
											$("#shipping-table-list").datagrid('reload');
											showSuccessMsg(result.content);
											//$.messager.alert(message("csh.common.prompt"),result.content,'info');
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
						 $('#addShipping').dialog("close").form("reset");
					}
			    }],
			    onOpen:function(){}

			});  
		},
		edit:function(){
			var _edit_row = $('#shipping-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editShipping').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 500,
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../shipping/details.jhtml?id='+_edit_row.id+'&path=edit',
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editShipping_form').form('validate');
						if(validate){
							$.ajax({
								url:"../shipping/update.jhtml",
								type:"post",
								data:$("#editShipping_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editShipping').dialog("close");
										$("#shipping-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editShipping').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	
			    }
			});  
		},
		remove:function(){
			listRemove('shipping-table-list','../shipping/delete.jhtml');
		}
};

$(function(){	
	//发货单列表
	$("#shipping-table-list").datagrid({
		title:message("csh.shipping.list"),
		fitColumns:true,
		toolbar:"#shipping_manager_tool",
		url:'../shipping/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			var shippingItemData = {"total":0,"rows":[]};
			var shippingItems = rowData.shippingItems;
			if(shippingItems.length > 0){
				for(var i = 0; i < shippingItems.length; i++){
					shippingItemData.total += 1;
					shippingItemData.rows.push({
						shippingSn:rowData.sn,
						productSn:shippingItems[i].sn,
						name:shippingItems[i].name,
						quantity:shippingItems[i].quantity,
					});
					$('#shippingItem-table-list').datagrid('loadData', shippingItemData);
				}
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			$('#shippingDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 450,
			    cache: false,
			    modal: true,
			    href:'../shipping/details.jhtml?id='+rowData.id+'&path=details',
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#shippingDetail').dialog("close");
					}
			    }],
			    onLoad:function(){		
			    	$("#detailsShipping_form").find("input").attr("disabled","disabled");
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},		      
		      {title:message("csh.shipping.sn"),field:"sn",sortable:true},
		      {title:message("csh.shipping.shippingMethod"),field:"shippingMethod",sortable:true},		     
		      {title:message("csh.shipping.deliveryCorp"),field:"deliveryCorp",sortable:true},
		      {title:message("csh.shipping.freight"),field:"freight",sortable:true},
		      {title:message("csh.shipping.trackingNo"),field:"trackingNo",sortable:true},
		      {title:message("csh.shipping.consignee"),field:"consignee",sortable:true},
		      {title:message("csh.common.phonenumber"),field:"phone",sortable:true},
		      {title:message("csh.common.createDate"),field:"createDate",sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
		      }},
		   ]
		]
	});	
	$("#shipping-search-btn").click(function(){
	  var _queryParams = $("#shipping-search-form").serializeJSON();
	  $('#shipping-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#shipping-table-list").datagrid('reload');
	});
//发货项列表
	$('#shippingItem-table-list').datagrid({
		singleSelect:true
	});
	
//未发货订单列表
	$("#shipping-order-table-list").datagrid({
		title:message("csh.shipping.order.list"),
		fitColumns:true,
		toolbar:"#shipping-order_manager_tool",
		url:'../shipping/listUnshippedOrder.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			var orderItemData = {"total":0,"rows":[]};
			var orderItems = rowData.orderItems;
			if(orderItems.length > 0){
				for(var i = 0; i < orderItems.length; i++){
					orderItemData.total += 1;
					orderItemData.rows.push({
						orderSn:rowData.sn,
						productSn:orderItems[i].sn,
						name:orderItems[i].name,
						quantity:orderItems[i].quantity,
						price:orderItems[i].price,
					});
					$('#shipping-orderItem-table-list').datagrid('loadData', orderItemData);
				}
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			$('#shippingOrderDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 450,
			    cache: false,
			    modal: true,
			    href:'../order/details.jhtml?id='+rowData.id+'&path=details',
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#shippingOrderDetail').dialog("close");
					}
			    }],
			    onLoad:function(){		
			    	$("#detailsShipping_form").find("input").attr("disabled","disabled");
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},		      
		      {title:message("csh.order.sn"),field:"sn",sortable:true},
		      {title:message("csh.order.amountPaid"),field:"amountPaid",sortable:true},		     
		      {title:message("csh.order.endUser"),field:"endUser",sortable:true,formatter:function(value,row,index){
		    	  if(value !=null && value.userName != null){
		    		  return value.userName;
		    	  }else{
		    		  return "-";
		    	  }
		      }},
		      {title:message("csh.order.shippingStatus"),field:"shippingStatus",sortable:true,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.shippingStatus."+value);
		    	  }
		      }},
		      {title:message("csh.order.orderStatus"),field:"orderStatus",sortable:true,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.orderStatus."+value);
		    	  }
		      }},
		      {title:message("csh.order.paymentStatus"),field:"paymentStatus",sortable:true,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.paymentStatus."+value);
		    	  }
		      }},
		      {title:message("csh.common.createDate"),field:"createDate",sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
		      }},
		   ]
		]
	});	
	$("#shipping-order-search-btn").click(function(){
	  var _queryParams = $("#shipping-order-search-form").serializeJSON();
	  $('#shipping-order-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#shipping-order-table-list").datagrid('reload');
	});
//订单项列表
	$('#shipping-orderItem-table-list').datagrid({
		singleSelect:true
	});
});

