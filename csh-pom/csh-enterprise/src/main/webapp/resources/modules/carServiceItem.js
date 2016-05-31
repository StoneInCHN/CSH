var carServiceItem_manager_tool = {
		add:function(){
			$('#addCarServiceItem').dialog({
			    title: message("csh.carServiceItem.add"),    
			    width: 700,    
			    height: 550,
			    href:'../carServiceItem/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addCarServiceItem_form').form('validate');
						if(validate){
							var _selected_row = $('#carService_carServiceItem-table-list').datagrid('getSelected');
							var _data=$("#addCarServiceItem_form").serialize()+"&carServiceId="+_selected_row.id;
							debugger;
								$.ajax({
									url:"../carServiceItem/add.jhtml",
									type:"post",
									data:_data,
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addCarServiceItem').dialog("close")
											$("#addCarServiceItem_form").form("reset");
											$("#carServiceItem-table-list").datagrid('reload');
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
						 $('#addCarServiceItem').dialog("close");
						 $("#addCarServiceItem_form").form("reset");
					}
			    }],
				onLoad:function(){
				},
			    onClose:function(){
			    	$('#addCarServiceItem').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#carServiceItem-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editCarServiceItem').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../carServiceItem/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editCarServiceItem_form').form('validate');
						if(validate){
							$.ajax({
								url:"../carServiceItem/update.jhtml",
								type:"post",
								data:$("#editCarServiceItem_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editCarServiceItem').dialog("close");
										$("#carServiceItem-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editCarServiceItem').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	
			    },
			    onClose:function(){
			    	$('#editCarServiceItem').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('carServiceItem-table-list','../carServiceItem/delete.jhtml');
		}
};

$(function(){
	$("#carService_carServiceItem-table-list").datagrid({
		title:message("csh.carService.list"),
		fitColumns:true,
		toolbar:"#carService_manager_tool",
		url:'../carService/list.jhtml',  
		pagination:true,
		singleSelect:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		columns:[
		   [
		      {title:message("csh.carService.serviceName"),field:"serviceName",sortable:true},
		      {title:message("csh.carService.serviceCategory"),field:"serviceCategory",width:50,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value != null){
		    			  return value.categoryName;
		    		  }else{
		    			  return "";
		    		  }
		    	  }},
		      {title:message("csh.carService.serviceStatus"),field:"serviceStatus",width:50,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value =="ENABLED"){
		    			  return message("csh.carService.serviceStatus.ENABLED")
		    		  }else if(value =="DISABLED"){
		    			  return message("csh.carService.serviceStatus.DISABLED")
		    		  }
		    	  }},
		      {title:message("csh.common.createDate"),field:"createDate",width:120,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      }
		      
		   ]
		],
		onSelect:function(rowIndex, rowData){
			  $('#carServiceItem-table-list').datagrid('load',{
				  carServiceId: rowData.id
				});
	      }
	});	
	$("#carServiceItem-table-list").datagrid({
		title:message("csh.carServiceItem.list"),
		fitColumns:true,
		toolbar:"#carServiceItem_manager_tool",
		url:'../carServiceItem/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#carServiceItemDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../carServiceItem/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#carServiceItemDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#carServiceItemDetail').empty();
			    },
			    onLoad:function(){
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.carServiceItem.serviceItemName"),field:"serviceItemName",sortable:true},
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

	
	$("#carServiceItem-search-btn").click(function(){
	  var _queryParams = $("#carServiceItem-search-form").serializeJSON();
	  $('#carServiceItem-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#carServiceItem-table-list").datagrid('reload');
	})
})
