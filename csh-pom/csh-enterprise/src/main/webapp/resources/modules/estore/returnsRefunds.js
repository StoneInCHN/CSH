

$(function(){	
	//退货单列表
	$("#returnsRefunds-table-list").datagrid({
		title:message("csh.returns.returns")+message("csh.common.list"),
		fitColumns:true,
		url:'../returnsRefunds/listReturns.jhtml',  
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
		      {field:'ck',checkbox:true},
		      {title:message("csh.returns.returns")+message("csh.common.sn"),field:"sn",width:50,sortable:true},
		      {title:message("csh.shipping.shippingMethod"),field:"shippingMethod",width:50,sortable:true},		     
		      {title:message("csh.shipping.trackingNo"),field:"trackingNo",width:50,sortable:true},	
		      {title:message("csh.order.shipper"),field:"shipper",width:40,sortable:true},	
		      {title:message("csh.common.area"),field:"area",width:50,sortable:true},	
		      {title:message("csh.common.phonenumber"),field:"phone",width:50,sortable:true},	
		      {title:message("csh.returns.returnAmount"),field:"returnAmount",width:40,sortable:true},	
		      {title:message("csh.returns.returns")+message("csh.common.status"),field:"returnsStatus",width:40,sortable:true,formatter:function(value,row,index){
		    	  if(value){
		    		  return message("csh.returns.status."+value);
		    	  }
		      }},
		      {title:message("csh.belong.order"),field:"order",width:50,sortable:true,formatter:function(value,row,index){
		    	  if(value !=null && value.sn != null){
		    		  return value.sn;
		    	  }else{
		    		  return "-";
		    	  }
		    		  
		      }},
		      {title:message("csh.common.action"),field:"handle",sortable:true,width:100,align:"center",formatter: function(value,row,index){
		    	  var approved='<div class="linkbtn_grey">'+message("csh.common.approved")+message("csh.returns")+'</div>';
		    	  if(row.returnsStatus == "applyReturn"){
		    		  approved='<div class="linkbtn_green" onclick="approvedReturn('+row.id+');">'+message("csh.common.approved")+message("csh.returns")+'</div>';
		    	  }
		    	  var confirm='<div class="linkbtn_grey">'+message("csh.common.confirm")+message("csh.returns")+'</div>';
		    	  if(row.returnsStatus == "approvedReturn"){
		    		  confirm='<div class="linkbtn_green" onclick="confirmReturn('+row.id+');">'+message("csh.common.confirm")+message("csh.returns")+'</div>';
		    	  }
		    	  var viewOrder='<div class="linkbtn_green" onclick="viewOrder('+row.order.id+');">'+message("csh.order")+message("csh.common.detail")+'</div>';
				  return "<table><tr><td>"+approved+"</td><td>"+confirm+"</td><td>"+viewOrder+"</td></tr></table>";
		      }},
		   ]
		],
		onLoadSuccess:function(result){
	    }
	});	
	$("#returnsRefunds-search-btn").click(function(){
	  var _queryParams = $("#returnsRefunds-search-form").serializeJSON();
	  $('#returnsRefunds-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#returnsRefunds-table-list").datagrid('reload');
	});

});
function approvedReturn(id){
	//批准退货对话框
	$('#approvedReturn').dialog({
	    title: message("csh.common.approved")+message("csh.returns"),    
	    width: 800,    
	    height: 450,
	    iconCls:'icon-mini-add',
	    cache: false, 
	    href:'../returnsRefunds/approvedReturn.jhtml?returnsId='+id,
	    buttons:[{
	    	text:message("csh.common.approved"),
	    	iconCls:'icon-save',
			handler:function(){
				var validate = $('#approvedReturn_form').form('validate');
				if(validate){
						$.ajax({
							url:"../returnsRefunds/updateApprovedReturn.jhtml",
							type:"post",
							data:$("#approvedReturn_form").serialize(),
							beforeSend:function(){
								$.messager.progress({
									text:message("csh.common.saving")
								});
							},
							success:function(result,response,status){
								$.messager.progress('close');
								if(response == "success"){
									$('#approvedReturn').dialog("close");
									$('#approvedReturn_form').form("reset");
									$("#returnsRefunds-table-list").datagrid('reload');
									showSuccessMsg(result.content);
									$.messager.alert(message("csh.common.prompt"),result.content,'info');
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
				 $('#approvedReturn').dialog("close").form("reset");
			}
	    }],
	    onOpen:function(){}
	});  
}
//确认收到退货对话框
function confirmReturn(id){
		$('#confirmReturn').dialog({
		    title: message("csh.common.confirm")+message("csh.common.received")+message("csh.returns"),    
		    width: 800,    
		    height: 450,
		    iconCls:'icon-mini-add',
		    cache: false, 
		    href:'../returnsRefunds/confirmReturn.jhtml?returnsId='+id,
		    buttons:[{
		    	text:message("csh.confirm.returns.approved.refunds"),
		    	iconCls:'icon-save',
				handler:function(){
					var validate = $('#confirmReturn_form').form('validate');
					if(validate){
							$.ajax({
								url:"../returnsRefunds/confirmApprovedRefunds.jhtml",
								type:"post",
								data:$("#confirmReturn_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										$('#confirmReturn').dialog("close");
										$('#confirmReturn_form').form("reset");
										$("#returnsRefunds-table-list").datagrid('reload');
										showSuccessMsg(result.content);
										$.messager.alert(message("csh.common.prompt"),result.content,'info');
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
					 $('#confirmReturn').dialog("close").form("reset");
				}
		    }],
		    onOpen:function(){}
		});  
}

//订单及其退货退款详情
function viewOrder(id){
	$('#returnsRefundsDetail').dialog({
	    title:message("csh.order")+message("csh.common.detail"),    
	    width: 800,    
	    height: 600,
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