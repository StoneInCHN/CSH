var deliveryCorp_manager_tool = {
		edit:function(){
			var _edit_row = $('#deliveryCorp-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editDeliveryCorp').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 500,
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../deliveryCorp/details.jhtml?id='+_edit_row.id+'&path=edit',
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editDeliveryCorp_form').form('validate');
						if(validate){
							$.ajax({
								url:"../deliveryCorp/update.jhtml",
								type:"post",
								data:$("#editDeliveryCorp_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editDeliveryCorp').dialog("close");
										$("#deliveryCorp-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editDeliveryCorp').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	
			    }
			});  
		},
		remove:function(){
			listRemove('deliveryCorp-table-list','../deliveryCorp/delete.jhtml');
		}
};

$(function(){	
	//可退货可退款订单列表
	$("#returnsRefunds-table-list").datagrid({
		title:"可退货可退款订单列表",
		fitColumns:true,
		url:'../returnsRefunds/listOrder.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onSelect:function(rowIndex,rowData){},
		onDblClickRow : function (rowIndex, rowData){
			$('#returnsRefundsDetail').dialog({    
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
		      {title:message("csh.order.sn"),field:"sn",width:50,sortable:true},
		      {title:message("csh.order.totalAmount"),field:"totalAmount",width:50,sortable:true},		     
		      {title:message("csh.order.endUser"),field:"endUser",sortable:true,width:50,formatter:function(value,row,index){
		    	  if(value){
		    		  return value.userName;
		    	  }
		      }},
		      {title:message("csh.order.consignee"),field:"consignee",sortable:true,width:50},
		      {title:message("csh.order.shippingStatus"),field:"shippingStatus",sortable:true,width:50,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.shippingStatus."+value);
		    	  }
		      }},
		      {title:message("csh.order.orderStatus"),field:"orderStatus",sortable:true,width:50,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.orderStatus."+value);
		    	  }
		      }},
		      {title:message("csh.order.paymentStatus"),field:"paymentStatus",sortable:true,width:50,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.paymentStatus."+value);
		    	  }
		      }},
		      {title:message("csh.common.createDate"),field:"createDate",sortable:true,width:50,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
		      }},
		      {title:"操作",field:"handle",sortable:true,width:100,formatter: function(value,row,index){
		    	  var returns="退货";
		    	  if(row.shippingStatus == "shipped"){
		    		  returns='<a href="#" onclick="addReturns('+row.id+');">退货</a>';
		    	  }
		    	  var refunds="退款";
		    	  if(row.paymentStatus == "paid"){
		    		  returns='<a href="#" onclick="addRefunds('+row.id+');">退款</a>';
		    	  }
		    	  var viewOrder='<a href="#" onclick="viewOrder('+row.id+');">查看</a>';
				  return returns+"&nbsp;&nbsp;&nbsp;&nbsp;"+refunds+"&nbsp;&nbsp;&nbsp;&nbsp;"+viewOrder;
					
		      }},
		   ]
		]
	});	
	$("#returnsRefunds-search-btn").click(function(){
	  var _queryParams = $("#returnsRefunds-search-form").serializeJSON();
	  $('#returnsRefunds-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#returnsRefunds-table-list").datagrid('reload');
	});

});
function addReturns(id){
	//退货对话框
	$('#addReturns').dialog({
	    title: "退货",    
	    width: 600,    
	    height: 450,
	    iconCls:'icon-mini-add',
	    cache: false, 
	    href:'../returnsRefunds/addReturns.jhtml?orderId='+id,
	    buttons:[{
	    	text:message("csh.common.save"),
	    	iconCls:'icon-save',
			handler:function(){
				var validate = $('#addReturns_form').form('validate');
				if(validate){
						$.ajax({
							url:"../returnsRefunds/saveReturns.jhtml",
							type:"post",
							data:$("#addReturns_form").serialize(),
							beforeSend:function(){
								$.messager.progress({
									text:message("csh.common.saving")
								});
							},
							success:function(result,response,status){
								$.messager.progress('close');
								if(response == "success"){
									$('#addReturns').dialog("close");
									$('#addReturns_form').form("reset");
									$("#returnsRefunds-table-list").datagrid('reload');
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
				 $('#addReturns').dialog("close").form("reset");
			}
	    }],
	    onOpen:function(){}
	});  
}
function addRefunds(id){
	//退款对话框
	$('#addRefunds').dialog({
	    title: "退款",    
	    width: 600,    
	    height: 550,
	    iconCls:'icon-mini-add',
	    cache: false, 
	    href:'../returnsRefunds/addRefunds.jhtml?orderId='+id,
	    buttons:[{
	    	text:message("csh.common.save"),
	    	iconCls:'icon-save',
			handler:function(){
				var validate = $('#addRefunds_form').form('validate');
				if(validate){
						$.ajax({
							url:"../returnsRefunds/saveRefunds.jhtml",
							type:"post",
							data:$("#addRefunds_form").serialize(),
							beforeSend:function(){
								$.messager.progress({
									text:message("csh.common.saving")
								});
							},
							success:function(result,response,status){
								$.messager.progress('close');
								if(response == "success"){
									$('#addRefunds').dialog("close");
									$('#addRefunds_form').form("reset");
									$("#returnsRefunds-table-list").datagrid('reload');
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
				 $('#addRefunds').dialog("close").form("reset");
			}
	    }],
	    onOpen:function(){}
	});  
}
function viewOrder(id){
	//订单及其退货退款详情
	$('#returnsRefundsDetail').dialog({
	    title: "详情",    
	    width: 600,    
	    height: 550,
	    cache: false, 
	    href:'../returnsRefunds/viewOrder.jhtml?orderId='+id,
	    buttons:[{
			text:message("csh.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#returnsRefundsDetail').dialog("close").form("reset");
			}
	    }],
	    onOpen:function(){}
	});  
}