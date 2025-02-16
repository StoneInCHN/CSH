var role_manager_tool = {
		auth:function(){		
			//授权树形列表
			var _edit_row = $('#role-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.notice"),message("csh.common.select.editRow"));  
				return false;
			}
			$('#role-dialog-auth').dialog({    
			    title: message("csh.role.auth.manange"),    
			    width: 450,    
			    height: 500,    
			    closed: false,    
			    cache: false,    
			    modal: true ,
			    onOpen:function(){
			    	$('#roleTreeAuth').tree({
			    		url:'../role/listAuth.jhtml?id='+_edit_row.id,  
			    		cache:false,
			    	    animate:true,
			    	    lines:true
			    	});
			    	$('#roleTreeAuth').tree('collapseAll');

			    },
				buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
							var selectedList = $('#roleTreeAuth').tree('getChecked', ['checked','indeterminate']);
							var _ids = [];
							for(var i=0; i< selectedList.length; i++){
								_ids[i] = selectedList[i].id;
							}
							$.ajax({
								url:"../role/addAuth.jhtml",
								type:"post",
								traditional : true,
								data:{
									"id":_edit_row.id,
									"authIds": _ids
								},
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#role-dialog-auth').dialog("close");
									$('#roleTreeAuth').tree("reload");
								},
								error:function (XMLHttpRequest, textStatus, errorThrown) {
									$.messager.progress('close');
									alertErrorMsg();
								}
							});
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#role-dialog-auth').dialog("close");
					}
			    }]
			}); 
			
		},
		add:function(){		
			$('#addrole').dialog({    
			    title: message("csh.role.add"),    
			    width: 370,    
			    height: 270,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addrole_form').form('validate');
						if(validate){
							$.ajax({
								url:"../role/add.jhtml",
								type:"post",
								data:$("#addrole_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#addrole_form').form('reset');
									$('#addrole').dialog("close");
									$("#role-table-list").datagrid('reload');
									
								},
								error:function (XMLHttpRequest, textStatus, errorThrown) {
									$.messager.progress('close');
									alertErrorMsg();
								}
							});
						}
						else{
							alert("validate fail");
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addrole').dialog("close");
					}
			    }]
			});  
			 $('#addrole_form').show();
		},
		edit:function(){
			var _edit_row = $('#role-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.notice"),message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editRole').dialog({
			    title: message("csh.common.edit"),     
			    width: 370,    
			    height: 270,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../role/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editRole_form').form('validate');
						if(validate){
							$.ajax({
								url:"../role/update.jhtml",
								type:"post",
								data:$("#editRole_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#editRole').dialog("close");
									$("#role-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editRole').dialog("close");
					}
			    }]
			});  
			$('#editRole_form').show();
		},
		remove:function(){
			var _edit_row = $('#role-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.notice"),message("csh.common.select.deleteRow"));  
				return false;
			}
			listRemove('role-table-list','../role/delete.jhtml');
		}
}

$(function(){
	
	$("#role-table-list").datagrid({
		title:message("csh.role.record"),
		fitColumns:true,
		toolbar:"#role_manager_tool",
		url:'../role/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#roleDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 370,    
			    height: 270, 
			    cache: false,   
			    href:'../role/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#roleDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.role.name"),field:"name",width:20,align:'center',formatter:function(value,row,index){
		    	  return row.name;
		      }},
		      {title:message("csh.role.description"),field:"description",width:80,align:'center',formatter:function(value,row,index){
		    	  return row.description;
		      }},
		      {title:message("csh.role.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
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
	
	
	$("#role_search_btn").click(function(){
	  var _queryParams = $("#role_search_form").serializeJSON();
	  $('#role-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#role-table-list").datagrid('reload');
	})
	
	$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }
	})
	
	
	
})

