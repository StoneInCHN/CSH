		var editIndex = undefined;
		function endEditing(id){
			if (editIndex == undefined){return true}
			if ($('#'+id).datagrid('validateRow', editIndex)){
				$('#'+id).datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index,id){
			if (editIndex != index){
				if (endEditing(id)){
					$('#'+id).datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#'+id).datagrid('selectRow', editIndex);
				}
			}
		}
		function append(id){
			if (endEditing(id)){
				$('#'+id).datagrid('appendRow',{
					order:"",
					name:""
				});
				editIndex =$('#'+id).datagrid('getRows').length-1;
				$('#'+id).datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(id){
			if (editIndex == undefined){return}
			$('#'+id).datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(id){
			if (endEditing(id)){
				$('#'+id).datagrid('acceptChanges');
			}
		}
		function reject(id){
			$('#'+id).datagrid('rejectChanges');
			editIndex = undefined;
		}
		function edit(id){
			var selectIndex=$('#'+id).datagrid('getRowIndex',$('#'+id).datagrid('getSelected'));
			if(selectIndex != -1)
				onClickRow(selectIndex,id);
		}
		function getChanges(id){
			var rows = $('#'+id).datagrid('getChanges');
			alert(rows.length+' rows are changed!');
		}
var productCategory_manager_tool = {
		add:function(){
			var editIndex = undefined;
			function endEditing(){
				if (editIndex == undefined){return true}
				if ($('#productCategoryAdd-parameterGroup').datagrid('validateRow', editIndex)){
					var ed = $('#productCategoryAdd-parameterGroup').datagrid('getEditor', {index:editIndex,field:'productid'});
					var productname = $(ed.target).combobox('getText');
					$('#productCategoryAdd-parameterGroup').datagrid('getRows')[editIndex]['productname'] = productname;
					$('#productCategoryAdd-parameterGroup').datagrid('endEdit', editIndex);
					editIndex = undefined;
					return true;
				} else {
					return false;
				}
			}
			$('#addProductCategory').dialog({
			    title: message("csh.productCategory.add"),    
			    width: 650,    
			    height: 500,
			    href:'../productCategory/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addProductCategory_form').form('validate');
						if(validate){
								$.ajax({
									url:"../productCategory/add.jhtml",
									type:"post",
									data:$("#addProductCategory_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addProductCategory').dialog("close")
											$("#addProductCategory_form").form("reset");
											$("#productCategory-table-list").datagrid('reload');
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
						 $('#addProductCategory').dialog("close");
						 $("#addProductCategory_form").form("reset");
					}
			    }],
				onLoad:function(){
					$("#addProductCategory_parent").combotree({    
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
					
					$('#productCategoryAdd-parameterGroup').datagrid({
						toolbar: '#productCategoryAdd-parameterGroup-tb',
						iconCls: 'icon-edit',
						singleSelect:true,
						columns:[[
						    {field:'name',title:'参数名',width:75,editor:{
	                            type:'textbox',
	                            options:{
	                                validType:'length[0,200]',
	                                required:true
	                            }
	                        }},  
					        {field:'order',title:'序号',width:75,editor:{
	                            type:'numberbox'
	                        }} 
					        
					    ]],
						onSelect:function(rowIndex, rowData){
							$('#productCategoryAdd-parameter').datagrid({
								width:150,
							    columns:[[
							        {field:'name',title:'参数名',width:75,editor:'textbox'},
							        {field:'order',title:'序号',width:75,editor:'textbox'}    
							    ]]
							});
						},
						onEndEdit:function(rowIndex, rowData, changes){
							$('#productCategoryAdd-parameterGroup-tb-append').show();
							$('#productCategoryAdd-parameterGroup-tb-edit').show();
							$('#productCategoryAdd-parameterGroup-tb-remove').hide();
							$('#productCategoryAdd-parameterGroup-tb-save').hide();
							$('#productCategoryAdd-parameterGroup-tb-repeal').hide();
						},
						onCancelEdit:function(){
							$('#productCategoryAdd-parameterGroup-tb-append').show();
							$('#productCategoryAdd-parameterGroup-tb-edit').show();
							$('#productCategoryAdd-parameterGroup-tb-remove').hide();
							$('#productCategoryAdd-parameterGroup-tb-save').hide();
							$('#productCategoryAdd-parameterGroup-tb-repeal').hide();
						},
						onBeginEdit:function(){
							$('#productCategoryAdd-parameterGroup-tb-append').hide();
							$('#productCategoryAdd-parameterGroup-tb-edit').hide();
							$('#productCategoryAdd-parameterGroup-tb-remove').show();
							$('#productCategoryAdd-parameterGroup-tb-save').show();
							$('#productCategoryAdd-parameterGroup-tb-repeal').show();
						}
					});
					 
				},
			    onClose:function(){
			    	$('#addProductCategory').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#productCategory-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editProductCategory').dialog({    
				title: message("csh.common.edit"),     
			    width: 750,    
			    height: 600,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../productCategory/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#productCategory_form').form('validate');
						if(validate){
								$.ajax({
									url:"../productCategory/update.jhtml",
									type:"post",
									data:$("#editProductCategory_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editProductCategory').dialog("close");
											$("#productCategory-table-list").datagrid('reload');
									}
								});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editProductCategory').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	$("#editProductCategory_parent").combotree({    
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
			    	$("#editProductCategory_parent").combotree('setValue',$("#editProductCategory_parent").attr('data-value'));
			    },
			    onClose:function(){
			    	$('#editProductCategory').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('productCategory-table-list','../productCategory/delete.jhtml');
		}
};

$(function(){
	
	$("#productCategory-table-list").datagrid({
		title:message("csh.productCategory.list"),
		fitColumns:true,
		toolbar:"#productCategory_manager_tool",
		url:'../productCategory/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#productCategoryDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../productCategory/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#productCategoryDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#productCategoryDetail').empty();
			    },
			    onLoad:function(){

			    	$("#productCategoryDetail_parent").combotree({    
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
			    	$("#productCategoryDetail_parent").combotree('setValue',$("#productCategoryDetail_parent").attr('data-value'));
			    
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.productCategory.name"),field:"name",sortable:true},
		      {title:message("csh.productCategory.grade"),field:"grade",sortable:true},
//		      {title:message("csh.productCategory.parent"),field:"parent",sortable:true,formatter: function(value,row,index){
//		    	  if(value != null){
//						return value.name;
//					}else{
//						return "";
//					}
//				}
//		      },
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

	
	$("#productCategory-search-btn").click(function(){
	  var _queryParams = $("#productCategory-search-form").serializeJSON();
	  $('#productCategory-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#productCategory-table-list").datagrid('reload');
	});
	
})
