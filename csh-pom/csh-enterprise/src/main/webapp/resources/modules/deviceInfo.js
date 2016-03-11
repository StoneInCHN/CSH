var endUser_manager_tool = {
		add:function(){
			$('#addEndUser').dialog({
			    title: message("csh.endUser.add"),    
			    width: 400,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addEndUser_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../endUser/add.jhtml",
									type:"post",
									data:$("#addEndUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addEndUser').dialog("close")
											$("#addEndUser_form").form("reset");
											$("#endUser-table-list").datagrid('reload');
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
						 $('#addEndUser').dialog("close");
						 $("#addEndUser_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addEndUser_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#endUser-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editEndUser').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../endUser/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#endUser_form').form('validate');
						if(validate){
							$.ajax({
								url:"../endUser/update.jhtml",
								type:"post",
								data:$("#editEndUser_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editEndUser').dialog("close");
										$("#endUser-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editEndUser').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('endUser-table-list','../endUser/delete.jhtml');
		}
};

$(function(){
	$("#deviceInfo-table-list").datagrid({
		title:message("csh.deviceInfo.list"),
		fitColumns:true,
		toolbar:"#deviceInfo_manager_tool",
		url:'../deviceInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#deviceInfoDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../deviceInfo/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#deviceInfoDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.deviceInfo.deviceNO"),field:"deviceNo",width:100,sortable:true},
		      {title:message("csh.deviceInfo.deviceType"),field:"type",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }
		      	  }},
		      {title:message("csh.deviceInfo.deviceStatus"),field:"deviceStatus",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "INITED"){
			    		  return  message("csh.deviceInfo.deviceStatus.INITED");
			    	  }else if (value = "SENDOUT"){
			    		  return  message("csh.deviceInfo.deviceStatus.SENDOUT");
			    	  }else if (value = "STORAGEOUT"){
			    		  return  message("csh.deviceInfo.deviceStatus.STORAGEOUT");
			    	  }else if (value = "BINDED"){
			    		  return  message("csh.deviceInfo.deviceStatus.BINDED");
			    	  }else if (value = "REFUNDED"){
			    		  return  message("csh.deviceInfo.deviceStatus.REFUNDED");
			    	  }
		      	  }  
		      },
		      {title:message("csh.deviceInfo.bindTime"),field:"bindTime",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#deviceInfo-search-btn").click(function(){
	  var _queryParams = $("#deviceInfo-search-form").serializeJSON();
	  $('#deviceInfo-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#deviceInfo-table-list").datagrid('reload');
	})
})
