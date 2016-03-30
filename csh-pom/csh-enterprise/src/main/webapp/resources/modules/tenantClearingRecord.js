var tenantClearingRecord_manager_tool = {
		add:function(){
			$('#addTenantClearingRecord').dialog({
			    title: message("csh.tenantClearingRecord.add"),    
			    width: 700,    
			    height: 550,
			    href:'../tenantClearingRecord/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addTenantClearingRecord_form').form('validate');
						if(validate){
							$.ajax({
								url:"../carService/add.jhtml",
								type:"post",
								data:$("#addCarService_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#addTenantClearingRecord').dialog("close")
										$("#addTenantClearingRecord_form").form("reset");
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
						 $('#addTenantClearingRecord').dialog("close");
						 $("#addTenantClearingRecord_form").form("reset");
					}
			    }],
				onLoad:function(){},
			    onClose:function(){
			    	$('#addTenantClearingRecord').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#tenantClearingRecord-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editTenantClearingRecord').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../tenantClearingRecord/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editTenantClearingRecord_form').form('validate');
						if(validate){
							$.ajax({
								url:"../tenantClearingRecord/update.jhtml",
								type:"post",
								data:$("#editTenantClearingRecord_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editTenantClearingRecord').dialog("close");
										$("#tenantClearingRecord-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editTenantClearingRecord').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	$("#editCarServiceCategory").combobox({    
					    valueField:'id',
					    textField:'categoryName',
					    url:"../serviceCategory/findAllServiceCategory.jhtml",
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
						},
			    	});
			    },
			    onClose:function(){
			    	$('#editTenantClearingRecord').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('tenantClearingRecord-table-list','../tenantClearingRecord/delete.jhtml');
		}
};

$(function(){
	
	
	
	$("#tenantClearingRecord-table-list").datagrid({
		title:message("csh.tenantClearingRecord.list"),
		fitColumns:true,
		toolbar:"#carService_manager_tool",
		url:'../tenantClearingRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#tenantClearingRecordDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../tenantClearingRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#tenantClearingRecordDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#tenantClearingRecordDetail').empty();
			    },
			    onLoad:function(){
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.carService.clearingSn"),field:"clearingSn",sortable:true},
		      {title:message("csh.tenantClearingRecord.periodBeginDate"),field:"periodBeginDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		      {title:message("csh.tenantClearingRecord.periodEndDate"),field:"periodEndDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		      {title:message("csh.tenantClearingRecord.clearingDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
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
