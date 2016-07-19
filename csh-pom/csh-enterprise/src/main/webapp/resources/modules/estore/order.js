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
			    href:'../estore/order/details.jhtml?id='+rowData.id,
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
			    	
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.order.sn"),field:"sn",sortable:true},
		      {title:message("csh.order.orderStatus"),field:"orderStatus",sortable:true},
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
})
