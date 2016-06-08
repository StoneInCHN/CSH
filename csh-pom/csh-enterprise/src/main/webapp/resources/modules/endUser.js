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
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
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
		},
		setBalance:function(){
			var _edit_row = $('#endUser-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#setBalance4EndUser').dialog({
			    title: message("csh.endUser.setBalance"),    
			    width: 260,    
			    height: 150,
			    iconCls:'icon-mini-edit',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#setBalance4EndUser_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../endUser/setBalance.jhtml",
									type:"post",
									data:$("#setBalance4EndUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(result.type == "success"){
											showSuccessMsg(result.content);
											$('#setBalance4EndUser').dialog("close")
											$("#setBalance4EndUser_form").form("reset");
											$("#endUser-table-list").datagrid('reload');
										}else{
											alertInfoMsg(result.content);
										}
									}
								});
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#setBalance4EndUser').dialog("close");
						 $("#setBalance4EndUser_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#setBalance4EndUser_form').show();
			    	$("#setBalance4EndUser_form_endUserId").val(_edit_row.id);
			    }
			})
		}
};

$(function(){
	$("#endUser-table-list").datagrid({
		title:message("csh.endUser.list"),
		fitColumns:true,
		toolbar:"#endUser_manager_tool",
		url:'../endUser/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#endUserDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../endUser/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#endUserDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.endUser.userName"),field:"userName",width:100,sortable:true},
//		      {title:message("csh.endUser.realName"),field:"realName",width:100,sortable:true   },
		      {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true},
		      {title:message("csh.endUser.qq"),field:"qq",width:100,sortable:true},
		      {title:message("csh.endUser.accountBalance"),field:"accountBalance",width:100},
		      {title:message("csh.endUser.accoutStatus"),field:"accoutStatus",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "ACTIVED"){
			    		  return  message("csh.endUser.active");
			    	  }else if (value == "LOCKED"){
			    		  return  message("csh.endUser.locked");
			    	  }
		      	  }  
		      },
//		      {title:message("csh.endUser.loginDate"),field:"loginDate",width:100,sortable:true,formatter: function(value,row,index){
//					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
//				}
//		      },
		   ]
		]
	});

	
	$("#endUser-search-btn").click(function(){
	  var _queryParams = $("#endUser-search-form").serializeJSON();
	  $('#endUser-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#endUser-table-list").datagrid('reload');
	})
})
