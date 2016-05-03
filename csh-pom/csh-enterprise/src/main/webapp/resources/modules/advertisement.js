var notification_manager_tool = {
			add:function(){
				$('#addAdvertisement').dialog({
				    title: message("yly.advertisement.add"),    
				    width: 700,    
				    height: 600,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addAdvertisement_form').form('validate');
							if(validate){
								$.ajax({
									url:"../advertisement/add.jhtml",
									type:"post",
									data:$("#addAdvertisement_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addAdvertisement').dialog("close");
											$('#addAdvertisement_form').form('reset');
											$("#advertisement-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addAdvertisement').dialog("close");
							 $('#addAdvertisement_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addAdvertisement_form').show();
				    	var editor = KindEditor.create('#add_notification_content', {
							resizeType : 1,
							width : '400px',
							height:'300px',
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
							allowImageRemote : false,
							showRemote : false,
							allowFileManager: true,
							filePostName: "file",
							uploadJson:  "../../console/file/uploadNotificationPicutre.jhtml",
							urlType:'relative',
							afterBlur:function(){ 
					            this.sync(); 
					        }
						});
				    	editor.sync();
				    }
				});  
				
			},
			edit:function(){
				var _edit_row = $('#advertisement-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editAdvertisement').dialog({    
					title: message("yly.common.edit"),   
				    width: 700,    
				    height: 600,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../advertisement/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editAdvertisement_form').form('validate');
							if(validate){
								$.ajax({
									url:"../advertisement/update.jhtml",
									type:"post",
									data:$("#editAdvertisement_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editAdvertisement').dialog("close");
											$('#editAdvertisement_form').form('reset');
											$("#advertisement-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editAdvertisement').dialog("close");
							 $('#editAdvertisement_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editAdvertisement_form').show();
				    	var editor = KindEditor.create('#edit_advertisement_content', {
							resizeType : 1,
							width : '400px',
							height:'300px',
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
							allowImageRemote : false,
							showRemote : false,
							allowFileManager: true,
							filePostName: "file",
							uploadJson:  "../../console/file/uploadNotificationPicutre.jhtml",
							urlType:'relative',
							afterBlur:function(){ 
					            this.sync(); 
					        }
						});	 
				    	
				    	editor.sync();
				    }
				});
				$('#editAdvertisement_form').show();
			},
			remove:function(){
				listRemove('advertisement-table-list','../advertisement/delete.jhtml');
			}
	};
$(function(){

	$("#advertisement-table-list").datagrid({
		title:message("yly.advertisement.list"),
		fitColumns:true,
		toolbar:"#advertisement_manager_tool",
		url:'../advertisement/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#advertisementDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 600,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../advertisement/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#advertisementDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.advertisement.operator"),field:"operator",width:50,sortable:true},
		      {title:message("yly.common.title"),field:"title",width:50,sortable:true},
		      {title:message("yly.advertisement.publishTime"),field:"publishTime",width:50,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	$("#advertisement-search-btn").click(function(){
	  var _queryParams = $("#advertisement-search-form").serializeJSON();
	  $('#advertisement-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#advertisement-table-list").datagrid('reload');
	})
	
	 
	 
})
