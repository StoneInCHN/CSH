var brand_manager_tool = {
		add:function(){
			$('#addBrand').dialog({
			    title: message("csh.brand.add"),    
			    width: 850,    
			    height: 700,
			    href:'../brand/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.brand.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addBrand_form').form('validate');
						var _ids = [];
						if(validate){
							$.ajax({
								url:"../brand/add.jhtml",
								type:"post",
								data:$("#addBrand_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#addBrand').dialog("close")
										$("#addBrand_form").form("reset");
										$("#brand-table-list").datagrid('reload');
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
						 $('#addBrand').dialog("close");
						 $("#addBrand_form").form("reset");
					}
			    }],
				onLoad:function(){
					
					var editor = KindEditor.create('#add_brand_remark', {
						resizeType : 1,
						width : '400px',
						height:'400px',
						allowPreviewEmoticons : false,
						items: [
								"source", "|", "undo", "redo", "|", "preview", "print", "template", "cut", "copy", "paste",
								"plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright",
								"justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript",
								"superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/",
								"formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold",
								"italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "multiimage",
								"table", "hr", "emoticons",  "pagebreak",
								"anchor", "link", "unlink"
							],
						allowImageRemote : true,
						showRemote : false,
						allowFileManager: false,
						filePostName: "file",
						formatUploadUrl: false,
						uploadJson:  "../../console/file/kindEditorUploadPicutre.jhtml?imageType=PRODUCTBRANDIMAGE",
						urlType:'relative',
						afterBlur:function(){ 
				            this.sync(); 
				        }
					});	 
			    	
			    	editor.sync();
				},
			    onClose:function(){
			    	$("#brandUploader-add .uploadBtn").trigger("clearFileQuene");
			    	$('#addBrand').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#brand-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editBrand').dialog({    
				title: message("csh.common.edit"),     
			    width: 850,    
			    height: 700,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../brand/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editBrand_form').form('validate');
						if(validate){
								$.ajax({
									url:"../coupon/update.jhtml",
									type:"post",
									data:$("#editBrand_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editBrand').dialog("close");
											$("#brand-table-list").datagrid('reload');
									}
								});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editBrand').dialog("close")
					}
			    }],
			    onLoad:function(){

					
					var editor = KindEditor.create('#edit_brand_remark', {
						resizeType : 1,
						width : '400px',
						height:'400px',
						allowPreviewEmoticons : false,
						items: [
								"source", "|", "undo", "redo", "|", "preview", "print", "template", "cut", "copy", "paste",
								"plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright",
								"justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript",
								"superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/",
								"formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold",
								"italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "multiimage",
								"table", "hr", "emoticons",  "pagebreak",
								"anchor", "link", "unlink"
							],
						allowImageRemote : true,
						showRemote : false,
						allowFileManager: false,
						filePostName: "file",
						formatUploadUrl: false,
						uploadJson:  "../../console/file/kindEditorUploadPicutre.jhtml?imageType=PRODUCTBRANDIMAGE",
						urlType:'relative',
						afterBlur:function(){ 
				            this.sync(); 
				        }
					});	 
			    	
			    	editor.sync();
			    },
			    onClose:function(){
			    	$('#editBrand').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('brand-table-list','../brand/delete.jhtml');
		}
};

$(function(){
	
	$("#brand-table-list").datagrid({
		title:message("csh.brand.list"),
		fitColumns:true,
		toolbar:"#brand_manager_tool",
		url:'../brand/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#brandDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../brand/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#brandDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#brandDetail').empty();
			    },
			    onLoad:function(){
			    	
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.brand.name"),field:"name",sortable:true},
		      {title:message("csh.brand.type"),field:"type",sortable:true},
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

	
	$("#coupon-search-btn").click(function(){
	  var _queryParams = $("#coupon-search-form").serializeJSON();
	  $('#coupon-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#coupon-table-list").datagrid('reload');
	});
})
