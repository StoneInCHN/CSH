var brand_manager_tool = {
		add:function(){
			$('#addBrand').dialog({
			    title: message("csh.brand.add"),    
			    width: 750,    
			    height: 500,
			    href:'../brand/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addBrand_form').form('validate');
						var $photoLi = $("#brandUploader-add ul.filelist li");
						if(validate){
							if($photoLi.length >0){
								$("#brandUploader-add .uploadBtn").trigger("upload");
							}else{
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
					//头像上传
			     	var options ={
			     			createOption:{
			     				pick: {
					                 id: '#brandFilePicker-add',
					                 label: '',
					                 multiple :false
					             },
					             dnd: '#brandUploader-add .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             //缩略图
					             thumb:{
					            	    width: 110,
					            	    height: 110,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					              },
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../file/uploadPhoto.jhtml',
					             fileNumLimit: 1,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
					             //图片裁剪
					             compress:{
					            	 width: 110,
					            	 height: 110,
					            	 // 图片质量，只有type为`image/jpeg`的时候才有效。
					            	 quality: 90,
					            	 // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
					            	 allowMagnify: false,
					            	 // 是否允许裁剪。
					            	 crop: false,
					            	 // 是否保留头部meta信息。
					            	 preserveHeaders: true,
					            	 // 如果发现压缩后文件大小比原来还大，则使用原来图片
					            	 // 此属性可能会影响图片自动纠正功能
					            	 noCompressIfLarger: false,
					            	 // 单位字节，如果图片大小小于此值，不会采用压缩。
					            	 compressSize: 0
					             }
			     			},
			     			//包裹上传组件的div id
			     			warp :"addBrand_form",
			     			uploadBeforeSend:function(object, data, headers){
			     				 data.imageType ='PRODUCTBRANDIMAGE';
			     			},
			     			uploadSuccess:function(file, response){
			     				//将返回的图片路径放到隐藏的input中，用于表单保存
			     				$("#addBrand_form_file_input").val(response.content);
			     				$.ajax({
									url:"../brand/add.jhtml",
									type:"post",
									data:$("#addBrand_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addBrand').dialog("close")
										$("#addBrand_form").form("reset");
										$("#brand-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
			     			}
			     	};
			     	
			     	singleUpload(options);
					var editor = KindEditor.create('#add_brand_introduction', {
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
			    width: 750,    
			    height: 600,    
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

			    	var editOptions ={
			     			createOption:{
			     				pick: {
					                 id: '#brandFilePicker-edit',
					                 innerHTML :'',
					                 multiple :true
					             },
					             dnd: '#brandUploader-edit .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             thumb:{
					            	    width: 150,
					            	    height: 150,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					            	},
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../brand/uploadLogo.jhtml',
					             fileNumLimit: 100,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
					             compress:{
					            	 width: 110,
					            	    height: 110,
					            	    // 图片质量，只有type为`image/jpeg`的时候才有效。
					            	    quality: 90,
					            	    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
					            	    allowMagnify: false,
					            	    // 是否允许裁剪。
					            	    crop: false,
					            	    // 是否保留头部meta信息。
					            	    preserveHeaders: true,
					            	    // 如果发现压缩后文件大小比原来还大，则使用原来图片
					            	    // 此属性可能会影响图片自动纠正功能
					            	    noCompressIfLarger: false,
					            	    // 单位字节，如果图片大小小于此值，不会采用压缩。
					            	    compressSize: 0
					             }
			     			},
			     			warp :"editBrand_form",
			     			uploadImageType:"edit",
			     			addButton:{
			     				id: '#brandFilePicker-edit2',
			     				innerHTML: '替换头像'
			     			},
			     			uploadBeforeSend:function(object, data, headers){
			     				 data.brandId=$("#editBrand_form").find("input[name='id']").val();;
			     			}
			     	};
			     	singleUpload(editOptions);
			     	$("#editBrand_form").find(".savePhoto").on("click",function(){
			     		$.messager.confirm('确认','头像保存后将直接修改当前用户的头像，确认要上传吗？',function(res){    
			     		    if (res){
			     		    	$("#brandUploader-edit .uploadBtn").trigger("upload");
			     		    }    
			     		}); 
			     		//alert("保存头像");
			     	});
			     	
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
			    	
//			    	editor.sync();
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
			    height: 450, 
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
		      {title:message("csh.brand.url"),field:"url",sortable:true},
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
