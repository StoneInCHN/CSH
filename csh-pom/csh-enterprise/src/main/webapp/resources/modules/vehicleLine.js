var vehicleLine_manager_tool = {
		add:function(){
			$('#addVehicleLine').dialog({
			    title: message("csh.vehicleLine.add"),    
			    width: 350,    
			    height: 250,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicleLine_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../vehicleLine/add.jhtml",
									type:"post",
									data:$("#addVehicleLine_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicleLine').dialog("close")
											$("#addVehicleLine_form").form("reset");
											$("#vehicleLine-table-list").datagrid('reload');
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
						 $('#addVehicleLine').dialog("close");
						 $("#addVehicleLine_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addVehicleLine_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicleLine-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editVehicleLine').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicleLine/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicleLine_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleLine/update.jhtml",
								type:"post",
								data:$("#editVehicleLine_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicleLine').dialog("close");
										$("#vehicleLine-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicleLine').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
//			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('vehicleLine-table-list','../vehicleLine/delete.jhtml');
		}
};

$(function(){
	$("#vehicleLine-table-list").datagrid({
		title:message("csh.vehicleLine.list"),
		url:'../vehicleLine/list.jhtml', 
		fitColumns:true,
		toolbar:"#vehicleLine_manager_tool",
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleLineDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../vehicleLine/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleLineDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicleLine.name"),field:"name",width:100,sortable:true},
		      {title:message("csh.vehicleLine.code"),field:"code",width:100,sortable:true},
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      }
		   ]
		]
	});

	
	$("#vehicleLine-search-btn").click(function(){
	  var _queryParams = $("#vehicleLine-search-form").serializeJSON();
	  $('#vehicleLine-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicleLine-table-list").datagrid('reload');
	});
	
})
