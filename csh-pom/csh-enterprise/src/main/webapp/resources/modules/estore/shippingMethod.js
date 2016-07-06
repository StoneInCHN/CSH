var shippingMethod_manager_tool = {
		add:function(){
			$('#addShippingMethod').dialog({
			    title: message("csh.common.add"),    
			    width: 500,    
			    height: 510,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addShippingMethod_form').form('validate');
						if(validate){
								//jquery2.0以下版本不支持ajax提交文件（dataForm方式也不行），暂时用表单提交实现
								$("#addShippingMethod_form").attr("action","../shippingMethod/add.jhtml");
								$("#addShippingMethod_form").submit();
								$.messager.progress({
									text:message("csh.common.saving")
								});
								$("#ifm").load(function(){//获取iframe中的内容
									var body = $(window.frames['ifm'].document.body);
									var data = eval('(' + body[0].textContent + ')');  
									$.messager.progress('close');
									 if(data.type =="success"){
											$('#addShippingMethod').dialog("close");
											$('#addShippingMethod_form').form("reset");
											$("#shippingMethod-table-list").datagrid('reload');
											showSuccessMsg(data.content);
											$.messager.alert(message("csh.common.prompt"),data.content,'info');
									 }else{
										 alertErrorMsg();
									 }
							});
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addShippingMethod').dialog("close").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addShippingMethod_form').show();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#shippingMethod-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editShippingMethod').dialog({    
				title: message("csh.common.edit"),     
			    width: 500,    
			    height: 600,
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../shippingMethod/details.jhtml?id='+_edit_row.id+'&path=edit',
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editShippingMethod_form').form('validate');
						if(validate){
							//jquery2.0以下版本不支持ajax提交文件（dataForm方式也不行），暂时用表单提交实现
							$("#editShippingMethod_form").attr("action","../shippingMethod/update.jhtml");
							$("#editShippingMethod_form").submit();
							$.messager.progress({
								text:message("csh.common.saving")
							});
							$("#editIfm").load(function(){//获取iframe中的内容
								var body = $(window.frames['editIfm'].document.body);
								var data = eval('(' + body[0].textContent + ')');  
								$.messager.progress('close');
								 if(data.type =="success"){
										$('#editShippingMethod').dialog("close");
										$('#editShippingMethod_form').form("reset");
										$("#shippingMethod-table-list").datagrid('reload');
										showSuccessMsg(data.content);
										$.messager.alert(message("csh.common.prompt"),data.content,'info');
								 }else{
									 alertErrorMsg();
								 }
						});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editShippingMethod').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	
			    }
			});  
		},
		remove:function(){
			listRemove('shippingMethod-table-list','../shippingMethod/delete.jhtml');
		}
};

$(function(){	
	$("#shippingMethod-table-list").datagrid({
		title:message("csh.shippingMethod.list"),
		fitColumns:true,
		toolbar:"#shippingMethod_manager_tool",
		url:'../shippingMethod/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#shippingMethodDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 500,    
			    height: 570,
			    cache: false,
			    modal: true,
			    href:'../shippingMethod/details.jhtml?id='+rowData.id+'&path=details',
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#shippingMethodDetail').dialog("close");
					}
			    }],
			    onLoad:function(){		
			    	$("#detailsShippingMethod_form").find("input").attr("disabled","disabled");
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.name"),field:"name",width:100,sortable:true},
		      {title:message("csh.shippingMethod.firstWeight"),field:"firstWeight",width:50,sortable:true},		
		      {title:message("csh.shippingMethod.continueWeight"),field:"continueWeight",width:50,sortable:true},	
		      {title:message("csh.shippingMethod.firstPrice"),field:"firstPrice",width:50,sortable:true},		
		      {title:message("csh.shippingMethod.continuePrice"),field:"continuePrice",width:50,sortable:true},	
		      {title:message("csh.icon"),field:"icon",width:50,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  "<img src='"+value+"' title='"+row.icon+"' width='40' height='32' style='margin:5px'/>";
		    	  }
		      }},	
		      {title:message("csh.description"),field:"description",width:150,sortable:true},
		   ]
		]
	});	
	$("#shippingMethod-search-btn").click(function(){
	  var _queryParams = $("#shippingMethod-search-form").serializeJSON();
	  $('#shippingMethod-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#shippingMethod-table-list").datagrid('reload');
	});

});
