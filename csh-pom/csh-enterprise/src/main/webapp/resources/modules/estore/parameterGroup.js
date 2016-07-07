function deleteParameter(id){
	$('#'+id).remove();
}
var parameterGroup_manager_tool = {
		add:function(){
			$('#addParameterGroup').dialog({
			    title: message("csh.parameterGroup.add"),    
			    width: 700,    
			    height: 550,
			    href:'../parameterGroup/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addParameterGroup_form').form('validate');
						if(validate){
								$.ajax({
									url:"../parameterGroup/add.jhtml",
									type:"post",
									data:$("#addParameterGroup_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addParameterGroup').dialog("close")
											$("#addParameterGroup_form").form("reset");
											$("#parameterGroup-table-list").datagrid('reload');
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
						 $('#addParameterGroup').dialog("close");
						 $("#addParameterGroup_form").form("reset");
					}
			    }],
				onLoad:function(){
					var parameterIndex=0;
					$("#addParameterGroup_productCategory").combotree({    
			    	    url: '../productCategory/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
			    	});
					$('#addParameterGroup-parameterAdd').bind('click', function(){
						var html= '<tr id="addParameterGroup-parameterAddTr'+parameterIndex+'"><td><input name="parameters[' + parameterIndex + '].name" id ="addParameterGroup-parameterName'+parameterIndex+'" type="text"></td>'+
						'<td><input name="parameters[' + parameterIndex + '].order" id ="addParameterGroup-parameterOrder'+parameterIndex+'" type="text"></td>'+
						'<td><input id ="addParameterGroup-parameterDelete'+parameterIndex+'" type="button" value="删除" onclick="deleteParameter(\'addParameterGroup-parameterAddTr'+parameterIndex+'\')"></td> </tr>'
				        $('#addParameterGroup_parameterTable').append(html);
						$('#addParameterGroup-parameterName'+parameterIndex).textbox({
							required:true
						});
						$('#addParameterGroup-parameterOrder'+parameterIndex).numberbox({    
						    min:0    
						});
						$('#addParameterGroup-parameterDelete'+parameterIndex).linkbutton({    
						});
						parameterIndex++;
				    });
				},
			    onClose:function(){
			    	$('#addParameterGroup').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#parameterGroup-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editParameterGroup').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../parameterGroup/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editItemPart_form').form('validate');
						
						if(validate){
							var _data=$("#editParameterGroup_form").serialize();
							$.ajax({
								url:"../parameterGroup/update.jhtml",
								type:"post",
								data:_data,
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editParameterGroup').dialog("close");
										$("#parameterGroup-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editParameterGroup').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	var parameterIndex= $('#editParameterGroupParameterSize').val();
					$("#editParameterGroup_productCategory").combotree({    
			    	    url: '../productCategory/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
			    	});
					$("#editParameterGroup_productCategory").combobox('setValue', $("#editParameterGroup_productCategory").attr('data-value'));
					$('#editParameterGroup-parameterAdd').bind('click', function(){
						var html= '<tr id="editParameterGroup-parameterAddTr'+parameterIndex+'">'+
							'<td>&nbsp;</td>'+
							'<td><input name="parameters[' + parameterIndex + '].name" id ="editParameterGroup-parameterName'+parameterIndex+'" type="text"></td>'+
							'<td><input name="parameters[' + parameterIndex + '].order" id ="editParameterGroup-parameterOrder'+parameterIndex+'" type="text"></td>'+
							'<td><input id ="editParameterGroup-parameterDelete'+parameterIndex+'" type="button" value="删除" onclick="deleteParameter(\'editParameterGroup-parameterAddTr'+parameterIndex+'\')"></td> </tr>'
				        $('#editParameterGroup_parameterTable').append(html);
						$('#editParameterGroup-parameterName'+parameterIndex).textbox({
							required:true
						});
						$('#editParameterGroup-parameterOrder'+parameterIndex).numberbox({    
						    min:0    
						});
						$('#editParameterGroup-parameterDelete'+parameterIndex).linkbutton({    
						});
						parameterIndex++;
				    });
			    },
			    onClose:function(){
			    	$('#editParameterGroup').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('parameterGroup-table-list','../parameterGroup/delete.jhtml');
		}
};

$(function(){
	$("#parameterGroup-table-list").datagrid({
		title:message("csh.parameterGroup.list"),
		fitColumns:true,
		toolbar:"#parameterGroup_manager_tool",
		url:'../parameterGroup/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#parameterGroupDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../parameterGroup/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#parameterGroupDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#parameterGroupDetail').empty();
			    },
			    onLoad:function(){
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.parameterGroup.productCategory"),field:"productCategory",sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return value.name;
					}else{
						return "";
					}
				}},
		      {title:message("csh.parameterGroup.name"),field:"name",sortable:true},
		      {title:message("csh.parameterGroup.order"),field:"order",sortable:true},
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
	
	$("#parameterGroup-search-btn").click(function(){
	  var _queryParams = $("#parameterGroup-search-form").serializeJSON();
	  $('#parameterGroup-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#parameterGroup-table-list").datagrid('reload');
	});
})
