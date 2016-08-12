$(function(){
	$("#order-table-list").datagrid({
		title:message("csh.order.list"),
		fitColumns:true,
		toolbar:"#order_manager_tool",
		url:'../estore/order/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#orderDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../estore/order/details.jhtml?id='+rowData.id+'&sn='+rowData.sn,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#orderDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#orderDetail').empty();
			    },
			    onLoad:function(){
			    	$("#orderStatusConfirmed").unbind();
			    	$("#orderStatusConfirmed").bind("click",function(){
			    		$this = $(this);
			    		$.ajax({
							url:"../estore/order/confirm.jhtml",
							type:"post",
							data:{
								id:$this.attr("data-id"),
								sn:$this.attr("data-sn")
							},
							beforeSend:function(){
								$.messager.progress({
									text:message("csh.common.saving")
								});
							},
							success:function(result,response,status){
								$.messager.progress('close');
								if(response == "success"){
									showSuccessMsg(result.content);
									if(result.type == "success"){
										$("#orderDetailsOrderStatus").text("已确认")
									}
								}else{
									alertErrorMsg();
								}
							}
						});
			    	})
			    }
			});   
		},
		columns:[
		   [
		      /*{field:'ck',checkbox:true},*/
		      {title:message("csh.order.sn"),field:"sn",sortable:true},
		      {title:message("csh.order.amount"),field:"amount",sortable:false},
		      {title:message("csh.order.endUser"),field:"endUser.userName",sortable:true},
		      {title:message("csh.order.consignee"),field:"consignee",sortable:true},
		      {title:message("csh.order.paymentType"),field:"paymentType",sortable:true},
		      {title:message("csh.order.shippingMethodName"),field:"shippingMethodName",sortable:true},
		      {title:message("csh.order.orderStatus"),field:"orderStatus",sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.orderStatus."+value);
		    	  }else{
		    		  return "";
		    	  }
		    	 
				}
		      },
		      {title:message("csh.order.paymentStatus"),field:"paymentStatus",sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.paymentStatus."+value);
		    	  }else{
		    		  return "";
		    	  }
		    	 
				}
		      },
		      {title:message("csh.order.shippingStatus"),field:"shippingStatus",sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return message("csh.order.shippingStatus."+value);
		    	  }else{
		    		  return "";
		    	  }
		    	 
				}
		      },
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
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
	
	$("#order-search-btn").click(function(){
		  var _queryParams = $("#order-search-form").serializeJSON();
		  $('#order-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#order-table-list").datagrid('reload');
	})
	$("#order-reset-btn").click(function(){
		$('#order-search-form')[0].reset()
		  var _queryParams ={};
		  $('#order-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#order-table-list").datagrid('reload');
	})
})
