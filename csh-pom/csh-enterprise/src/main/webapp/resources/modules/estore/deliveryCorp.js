var deliveryCorp_manager_tool = {
		add:function(){
			$('#addDeliveryCorp').dialog({
			    title: message("csh.common.add"),    
			    width: 400,    
			    height: 450,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addDeliveryCorp_form').form('validate');
						if(validate){
								$.ajax({
									url:"../deliveryCorp/add.jhtml",
									type:"post",
									data:$("#addDeliveryCorp_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											$('#addDeliveryCorp').dialog("close");
											$('#addDeliveryCorp_form').form("reset");
											$("#deliveryCorp-table-list").datagrid('reload');
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
						 $('#addDeliveryCorp').dialog("close").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addDeliveryCorp_form').show();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#deliveryCorp-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editDeliveryCorp').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 500,
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../deliveryCorp/details.jhtml?id='+_edit_row.id+'&path=edit',
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editDeliveryCorp_form').form('validate');
						if(validate){
							$.ajax({
								url:"../deliveryCorp/update.jhtml",
								type:"post",
								data:$("#editDeliveryCorp_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editDeliveryCorp').dialog("close");
										$("#deliveryCorp-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editDeliveryCorp').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	
			    }
			});  
		},
		remove:function(){
			listRemove('deliveryCorp-table-list','../deliveryCorp/delete.jhtml');
		}
};

$(function(){	
	$("#deliveryCorp-table-list").datagrid({
		title:message("csh.deliveryCorp.list"),
		fitColumns:true,
		toolbar:"#deliveryCorp_manager_tool",
		url:'../deliveryCorp/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#deliveryCorpDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 450,
			    cache: false,
			    modal: true,
			    href:'../deliveryCorp/details.jhtml?id='+rowData.id+'&path=details',
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#deliveryCorpDetail').dialog("close");
					}
			    }],
			    onLoad:function(){		
			    	$("#detailsDeliveryCorp_form").find("input").attr("disabled","disabled");
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.deliveryCorp.name"),field:"name",width:100,sortable:true},
		      {title:message("csh.deliveryCorp.url"),field:"url",width:100,sortable:true},		     
		      {title:message("csh.deliveryCorp.code"),field:"code",width:100,sortable:true},	
		   ]
		]
	});	
	$("#deliveryCorp-search-btn").click(function(){
	  var _queryParams = $("#deliveryCorp-search-form").serializeJSON();
	  $('#deliveryCorp-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#deliveryCorp-table-list").datagrid('reload');
	});

});
