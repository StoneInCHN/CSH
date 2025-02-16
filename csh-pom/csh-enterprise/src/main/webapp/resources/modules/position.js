$(function(){
		$("#position-table-list").datagrid({
			title:message("csh.position.list"),
			fitColumns:true,
			url:'../position/list.jhtml',  
			pagination: true,
			loadMsg:message("csh.common.loading"),
			striped:true,
			toolbar: [{
				text:message("csh.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addPosition").dialog({    
					    title:message("csh.position.add"),   
					    width: 350,    
					    height: 220,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("csh.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addPosition_form').form('validate');
								if(validate){
									$.ajax({
										url:"../position/add.jhtml",
										type:"post",
										data:$("#addPosition_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addPosition_form').form('reset');
											$('#addPosition').dialog("close");
											$("#position-table-list").datagrid('reload');
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
								};
							}
						},{
							text:message("csh.common.cancel"),
							iconCls:'icon-cancel',
							handler:function(){
								 $('#addPosition').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addPosition_form').show();
						    	$("#addPosition_form_departmentId").combotree({    
						    	    url: '../department/list.jhtml',    
						    	    method:"get",
						    	    animate:true,
						    	    lines:true,
						    	    prompt:message("csh.common.please.select"),
						    	    formatter:function(node){
						    	    	node.text = node.name;
						    			return node.name;
						    		},
						    		onChange:function(newValue, oldValue){
						    			var departmentId=$('#addPosition_form_departmentId').textbox('getValue');
						    			$('#addPositionName').textbox({
						    				validType:"remote['../position/checkUniqueName.jhtml?departmentId="+departmentId+"','name']"
						    			});
						    		}
									
						    	});
						    	var departmentId=$('#addPosition_form_departmentId').textbox('getValue');
				    			$('#addPositionName').textbox({
				    				validType:"remote['../position/checkUniqueName.jhtml?departmentId="+departmentId+"','name']"
				    			});
						},
						onClose:function(){
						    	$('#addPosition_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("csh.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#position-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');    
						return false;
					}
					$("#editPosition").dialog({
						width:300,
						height:200,
						iconCls:'icon-mini-edit',
						title:"部门编辑",
						href:'../position/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("csh.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editPosition_form').form('validate');
								if(validate){
									$.ajax({
										url:"../position/update.jhtml",
										type:"post",
										data:$("#editPosition_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editPosition_form').form('reset');
											$('#editPosition').dialog("close");
											$("#position-table-list").datagrid('reload');
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
								};
							}
						},{
							text:message("csh.common.cancel"),
							iconCls:'icon-cancel',
							handler:function(){
								 $('#editPosition').dialog("close");
							}
						}],
						onLoad:function(){
				    	$("#editPosition_form_departmentId").combotree({    
				    	    url: '../department/list.jhtml',    
				    	    method:"get",
				    	    animate:true,
				    	    lines:true,
				    	    prompt:message("csh.common.please.select"),
				    	    formatter:function(node){
				    	    	node.text = node.name;
				    			return node.name;
				    		},
				    		onLoadSuccess:function(){
								$('#editPosition_form_departmentId').combotree("setValue",$("#editPosition_form_departmentId").attr("data-value"));
							},
							onChange:function(newValue, oldValue){
								var departmentId=$('#editPosition_form_departmentId').textbox('getValue');
				    			var positionId = $('#editPositionId').val();
				    			$('#editPositionName').textbox({
				    				validType:"remote['../position/checkUniqueName.jhtml?departmentId="+departmentId+"&positionId="+positionId+"','name']"
				    			});
							}
				    	});  
				},
					})
				}
			},'-',{
				text:message("csh.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					listRemove('position-table-list','../position/delete.jhtml');
				}
			}],
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("csh.position.name"),field:"name",width:100,sortable:true},
			      {title:message("csh.position.department"),field:"department",width:100,sortable:true,formatter: function(value,row,index){
			    	  if(value){
			    		  return value.name;
			    	  }else{
			    		  return "";
			    	  }
				  }},
			      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
			    	  if(value != null){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
						}else{
							return "";
						}
				  }},
			      {title:message("csh.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
			    	  if(value != null){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
						}else{
							return "";
						}
				  }}
			   ]
			]
	
		});
})
	