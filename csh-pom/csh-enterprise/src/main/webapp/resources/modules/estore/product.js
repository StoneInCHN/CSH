var deleteImageIdList = new Array();
var product_manager_tool = {
		add:function(){
			$('#addProduct').dialog({
			    title: message("csh.product.add"),    
			    width: 1024,    
			    height: 600,
			    href:'../product/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addProduct_form').form('validate');
						var $photoLi = $("#productImageUploader-add ul.filelist li");
						if(validate){
							if($photoLi.length >0){
								$("#productImageUploader-add .uploadBtn").trigger("upload");
							}else{
								if(validate){
									var requestParameters = $('#addProductProductParameter').propertygrid('getData').rows;
									var request ="";
									for(var i=0;i<requestParameters.length;i++){
										request = request +'&propertyGridResponses['+i+'].name='+requestParameters[i].name+
												'&propertyGridResponses['+i+'].value='+requestParameters[i].value+
												'&propertyGridResponses['+i+'].id='+requestParameters[i].id;
									}
									$.ajax({
										url:"../product/add.jhtml",
										type:"post",
										data:$("#addProduct_form").serialize()+request,
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											if(response == "success"){
												showSuccessMsg(result.content);
												$('#addProduct').dialog("close")
												$("#addProduct_form").form("reset");
												$("#product-table-list").datagrid('reload');
											}else{
												alertErrorMsg();
											}
										}
									});
								}
							}
						}
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addProduct').dialog("close");
						 $("#addProduct_form").form("reset");
					}
			    }],
				onLoad:function(){
					$("#addProduct_parent").combotree({    
			    	    url: '../product/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
			    	});

					//图片上传
			     	var options ={
			     			createOption:{
			     				pick: {
					                 id: '#productImageFilePicker-add',
					                 label: '',
					                 multiple :false
					             },
					             dnd: '#productImageUploader-add .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             //缩略图
					             thumb:{
					            	    width: 300,
					            	    height: 300,
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
					            	 width: 300,
					            	 height: 300,
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
			     			warp :"addProductImageWarp",
			     			uploadBeforeSend:function(object, data, headers){
			     				 data.imageType ='PRODUCTIMAGE';
			     			},
			     			uploadSuccess:function(file, response){
			     				var $photoLi = $("#productImageListUploader-add ul.filelist li");
			     				if($photoLi.length>0){
			     					$("#addProductImage_form_file_input").val(response.content);
			     					$("#productImageListUploader-add .uploadBtn").trigger("upload");
			     				}else{
				     				//将返回的图片路径放到隐藏的input中，用于表单保存
				     				$("#addProductImage_form_file_input").val(response.content);
				     				var requestParameters = $('#addProductProductParameter').propertygrid('getData').rows;
									var request ="";
									for(var i=0;i<requestParameters.length;i++){
										request = request +'&propertyGridResponses['+i+'].name='+requestParameters[i].name+
												'&propertyGridResponses['+i+'].value='+requestParameters[i].value+
												'&propertyGridResponses['+i+'].id='+requestParameters[i].id;
									}
									$.ajax({
										url:"../product/add.jhtml",
										type:"post",
										data:$("#addProduct_form").serialize()+request,
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											if(response == "success"){
												showSuccessMsg(result.content);
												$('#addProduct').dialog("close")
												$("#addProduct_form").form("reset");
												$("#product-table-list").datagrid('reload');
											}else{
												alertErrorMsg();
											}
										}
									});
			     				}
			     			}
			     	};
			     	
			     	singleUpload(options);
			     	
			     	
					var editor = KindEditor.create('#add_product_introduction', {
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
						uploadJson:  "../../console/file/kindEditorUploadPicutre.jhtml?imageType=PRODUCTIMAGE",
						urlType:'',
						afterBlur:function(){ 
				            this.sync(); 
				        }
					});	 
			    	editor.sync();
			    	$("#addProduct_productCategory").combotree({    
			    	    url: '../productCategory/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
			    		onChange:function(newValue, oldValue){
			    			$('#addProductProductParameter').propertygrid({    
					    	    url: '../parameterGroup/findAll.jhtml', 
					    	    method:"get",
					    	    showGroup: true,
					    	    showHeader:false,
					    	    scrollbarSize: 0,
					    	    columns:[[
									{field:'name',width:100,sortable:true},
									{field:'value',width:100,resizable:false},
									{field:'id',width:100,resizable:false,hidden:true}
					    	    ]],
					    	    onBeforeLoad:function(param){
									param.productCategoryId = newValue;
								},
					    	});
			    		}
			    	});
			    	
			    	
			    	$("#addProduct_brand").combobox({    
			    	    url: '../brand/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    valueField:'id',
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
			    	});
			    	$("#addProductTab").tabs({    
			    		onSelect:function(title){    
			    	        if(title == "商品图片展示列表"){
			    	        	//图片上传
						     	var multipleOptions ={
						     			createOption:{
						     				pick: {
						    	                 id: '#productImageListFilePicker-add',
						    	                 label: '选择图片',
						    	                 multiple :true
						    	             },
						    	             dnd: '#productImageListUploader-add .queueList',
						    	             accept: {
						    	                 title: 'Images',
						    	                 extensions: 'gif,jpg,jpeg,bmp,png',
						    	                 mimeTypes: 'image/*'
						    	             },
						    	             //缩略图
						    	             thumb:{
						    	            	    width: 300,
						    	            	    height: 300,
						    	            	    quality: 90,
						    	            	    allowMagnify: false,
						    	            	    crop: false,
						    	            	    type: 'image/jpeg'
						    	              },
						    	             // swf文件路径
						    	             swf: BASE_URL + '/js/Uploader.swf',
						    	             disableGlobalDnd: true,
						    	             server: '../file/uploadPhoto.jhtml',
//						    	             fileNumLimit: 4-$('img[class="preview"]').length,
						    	             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
						    	             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
						    	             //图片裁剪
						    	             compress:{
						    	            	 width: 300,
						    	            	 height: 300,
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
						     			warp :"addProductImageListWarp",
						     			fileType:"PRODUCTIMAGELIST",
						     			method:"save",
						     			filePicker_replace :"filePicker_replace_Photos",
						     			filePicker2 :"filePicker2_productImageList",
						     			uploadBeforeSend:function(object, data, headers){
						     				 data.imageType ='PRODUCTIMAGELIST';
						     			},
						     			uploadSuccess:function(file, response){
						     				photoUrlList.push(response.content);
						     			},
						     			uploadFinished:function(){}
						     	};
						     	
						     	multipleUpload(multipleOptions);
			    	        }   
			    	    }  
			    	});
				},
			    onClose:function(){
			    	$("#productImageUploader-add .uploadBtn").trigger("clearFileQuene");
			    	$('#addProduct').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#product-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editProduct').dialog({    
				title: message("csh.common.edit"),     
			    width: 1024,    
			    height: 600,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../product/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						
						$("#deleteImageIdList").val(deleteImageIdList.join(","));
						var validate = $('#product_form').form('validate');
						if(validate){
							var $photoLi = $("#productImageListUploader-edit ul.filelist li");
							if($photoLi.length >0){
								$("#productImageListUploader-edit .uploadBtn").trigger("upload");
							}else{
								
								var requestParameters = $('#editProductProductParameter').propertygrid('getData').rows;
								var request ="";
								for(var i=0;i<requestParameters.length;i++){
									request = request +'&propertyGridResponses['+i+'].name='+requestParameters[i].name+
											'&propertyGridResponses['+i+'].value='+requestParameters[i].value+
											'&propertyGridResponses['+i+'].id='+requestParameters[i].id;
								}
								$.ajax({
									url:"../product/update.jhtml",
									type:"post",
									data:$("#editProduct_form").serialize()+request,
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editProduct').dialog("close");
											$("#product-table-list").datagrid('reload');
									}
								});
							}
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editProduct').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	$("#editProduct_productCategory").combotree({    
			    	    url: '../productCategory/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
						onLoadSuccess:function(){
							$('#editProduct_productCategory').combotree("setValue",$("#editProduct_productCategory").attr("data-value"));
							$('#editProductProductParameter').propertygrid({    
	    			    	    url: '../product/getCurrentParameter.jhtml', 
	    			    	    method:"get",
	    			    	    showGroup: true,
	    			    	    showHeader:false,
	    			    	    scrollbarSize: 0,
	    			    	    columns:[[
	    							{field:'name',width:100,sortable:true},
	    							{field:'value',width:100,resizable:false},
	    							{field:'id',width:100,resizable:false,hidden:true}
	    			    	    ]],
	    			    	    onBeforeLoad:function(param){
	    							param.productId = $('#editProductId').val();
	    						},
	    						onLoadSuccess:function(){
	    	    				}
	    			    	});
						},
						onSelect:function(node){
			    			$('#editProductProductParameter').propertygrid({    
					    	    url: '../parameterGroup/findAll.jhtml', 
					    	    method:"get",
					    	    showGroup: true,
					    	    showHeader:false,
					    	    scrollbarSize: 0,
					    	    columns:[[
									{field:'name',width:100,sortable:true},
									{field:'value',width:100,resizable:false},
									{field:'id',width:100,resizable:false,hidden:true}
					    	    ]],
					    	    onBeforeLoad:function(param){
									param.productCategoryId = node.id;
								},
			    				onLoadSuccess:function(){
			    					
			    				}
					    	});
			    		}
			    	});

					
				
	    			
	    		
			    	
			    	$("#editProduct_brand").combobox({    
			    	    url: '../brand/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    valueField:'id',    
			    	    textField:'name',
			    	    prompt:message("csh.common.please.select"),
			    		onLoadSuccess:function(){
			    			$('#editProduct_brand').combobox("setValue",$("#editProduct_brand").attr("data-value"));
			    		}
			    	});
			    	
			    	var editor = KindEditor.create('#edit_product_introduction', {
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
						uploadJson:  "../../console/file/kindEditorUploadPicutre.jhtml?imageType=PRODUCTIMAGE",
						urlType:'',
						afterBlur:function(){ 
				            this.sync(); 
				        }
					});	 
			    	editor.sync();
			    	
			    	var editOptions ={
			     			createOption:{
			     				pick: {
					                 id: '#productImageFilePicker-edit',
					                 innerHTML :'',
					                 multiple :true
					             },
					             dnd: '#productImageUploader-edit .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             thumb:{
					            	    width: 300,
					            	    height: 300,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					            	},
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../product/uploadImage.jhtml',
					             fileNumLimit: 1,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
					             compress:{
					            	 width: 300,
					            	    height: 300,
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
			     			warp :"editProductImageWarp",
			     			uploadImageType:"edit",
			     			addButton:{
			     				id: '#productImageFilePicker-edit2',
			     				innerHTML: '替换图片'
			     			},
			     			uploadBeforeSend:function(object, data, headers){
			     				 data.productId=$("#editProduct_form").find("input[name='id']").val();;
			     			}
			     	};
			     	singleUpload(editOptions);
			     	$("#editProduct_form").find(".savePhoto").on("click",function(){
			     		$.messager.confirm('确认','图片保存后将直接修改当前Logo，确认要上传吗？',function(res){    
			     		    if (res){
			     		    	$("#productImageUploader-edit .uploadBtn").trigger("upload");
			     		    }    
			     		}); 
			     		//alert("保存图片");
			     	});
			     	$("#editProductTab").tabs({    
			    		onSelect:function(title){    
			    	        if(title == "商品图片展示列表"){
			    	        	//图片上传
						     	var multipleOptions ={
						     			createOption:{
						     				pick: {
						    	                 id: '#productImageListFilePicker-edit',
						    	                 label: '继续添加',
						    	                 multiple :true
						    	             },
						    	             dnd: '#productImageListUploader-edit .queueList',
						    	             accept: {
						    	                 title: 'Images',
						    	                 extensions: 'gif,jpg,jpeg,bmp,png',
						    	                 mimeTypes: 'image/*'
						    	             },
						    	             //缩略图
						    	             thumb:{
						    	            	    width: 300,
						    	            	    height: 300,
						    	            	    quality: 90,
						    	            	    allowMagnify: false,
						    	            	    crop: false,
						    	            	    type: 'image/jpeg'
						    	              },
						    	             // swf文件路径
						    	             swf: BASE_URL + '/js/Uploader.swf',
						    	             disableGlobalDnd: true,
						    	             server: '../file/uploadPhoto.jhtml',
//						    	             fileNumLimit: 4-$('img[class="preview"]').length,
						    	             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
						    	             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
						    	             //图片裁剪
						    	             compress:{
						    	            	 width: 300,
						    	            	 height: 300,
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
						     			warp :"editProductImageListWarp",
						     			fileType:"PRODUCTIMAGELIST",
						     			method:"update",
						     			filePicker_replace :"filePicker_replace_Photos",
//						     			filePicker2 :"filePicker2_productImageList",
						     			uploadBeforeSend:function(object, data, headers){
						     				 data.imageType ='PRODUCTIMAGELIST';
						     			},
						     			uploadSuccess:function(file, response){
						     				photoUrlList.push(response.content);
						     			},
						     			uploadFinished:function(){}
						     	};
						     	
						     	multipleUpload(multipleOptions);
			    	        }   
			    	    }  
			    	});	
			    },
			    onClose:function(){
			    	$('#editProduct').empty();
			    }
			});  
		},
		
		marketable:function(isMarketable){
			var _rows = $('#product-table-list').datagrid('getSelections');
			if (_rows.length == 0) {
				$.messager.alert(message("csh.common.prompt"),
						message("csh.common.select.deleteRow"), 'warning');
			} else {
				var _ids = [];
				for (var i = 0; i < _rows.length; i++) {
					_ids.push(_rows[i].id);
				}
				$.ajax({
					url:"../product/marketable.jhtml",
					type:"post",
					traditional : true,
					data : {
						"isMarketable":isMarketable,
						"ids":_ids
					},
					beforeSend:function(){
						$.messager.progress({
							text:message("csh.common.saving")
						});
					},
					success:function(result,response,status){
						$.messager.progress('close');
						showSuccessMsg(result.content);
						$("#product-table-list").datagrid('reload');
					}
				});
			}		
		},
		saveProduct:function(){

				//将返回的图片路径放到隐藏的input中，用于表单保存
			$("#addProductImageList_form_file_input").val(photoUrlList.join(","));
			var requestParameters = $('#addProductProductParameter').propertygrid('getData').rows;
			var request ="";
			for(var i=0;i<requestParameters.length;i++){
				request = request +'&propertyGridResponses['+i+'].name='+requestParameters[i].name+
						'&propertyGridResponses['+i+'].value='+requestParameters[i].value+
						'&propertyGridResponses['+i+'].id='+requestParameters[i].id;
			}
			$.ajax({
				url:"../product/add.jhtml",
				type:"post",
				data:$("#addProduct_form").serialize()+request,
				beforeSend:function(){
					$.messager.progress({
						text:message("csh.common.saving")
					});
				},
				success:function(result,response,status){
					$.messager.progress('close');
					if(response == "success"){
						showSuccessMsg(result.content);
						$('#addProduct').dialog("close")
						$("#addProduct_form").form("reset");
						$("#product-table-list").datagrid('reload');
					}else{
						alertErrorMsg();
					}
				}
			});
			
		},
		updateProduct:function(){
			var requestParameters = $('#editProductProductParameter').propertygrid('getData').rows;
			var request ="";
			for(var i=0;i<requestParameters.length;i++){
				request = request +'&propertyGridResponses['+i+'].name='+requestParameters[i].name+
						'&propertyGridResponses['+i+'].value='+requestParameters[i].value+
						'&propertyGridResponses['+i+'].id='+requestParameters[i].id;
			}
			$("#editProductImageList_form_file_input").val(photoUrlList.join(","));
			$.ajax({
				url:"../product/update.jhtml",
				type:"post",
				data:$("#editProduct_form").serialize()+request,
				beforeSend:function(){
					$.messager.progress({
						text:message("csh.common.saving")
					});
				},
				success:function(result,response,status){
					$.messager.progress('close');
						showSuccessMsg(result.content);
						$('#editProduct').dialog("close");
						$("#product-table-list").datagrid('reload');
				}
			});
		},
		remove:function(){
			listRemove('product-table-list','../product/delete.jhtml');
		},
		deleteProductImageList:function(id){
			$(".productImage_edit"+id).remove();
			deleteImageIdList.push(id);
		}
};

$(function(){
	
	$("#product-table-list").datagrid({
		title:message("csh.product.list"),
		fitColumns:true,
		toolbar:"#product_manager_tool",
		url:'../product/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#productDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../product/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#productDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#productDetail').empty();
			    },
			    onLoad:function(){

			    	$("#productDetail_parent").combotree({    
			    	    url: '../product/findAll.jhtml',    
			    	    method:"get",
			    	    animate:true,
			    	    lines:true,
			    	    prompt:message("csh.common.please.select"),
			    	    formatter:function(node){
			    	    	node.text = node.name;
			    			return node.name;
			    		},
			    	});
			    	$("#productDetail_parent").combotree('setValue',$("#productDetail_parent").attr('data-value'));
			    
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.product.sn"),field:"sn",sortable:true},
		      {title:message("csh.product.name"),field:"name",sortable:true},
		      {title:message("csh.product.fullName"),field:"fullName",sortable:true},
		      {title:message("csh.product.productStatus"),field:"productStatus",sortable:true,formatter: function(value,row,index){
		    	  if(value == "created"){
		    		  return message("csh.product.productStatus.created");
		    	  }else if(value == "supply"){
		    		  return message("csh.product.productStatus.supply");
		    	  }else if(value == "approvedPass"){
		    		  return message("csh.product.productStatus.approvedPass");
		    	  }else if(value == "approvedFailed"){
		    		  return message("csh.product.productStatus.approvedFailed");
		    	  }else if(value == "marketed"){
		    		  return message("csh.product.productStatus.marketed");
		    	  }else if(value == "unmarketed"){
		    		  return message("csh.product.productStatus.unmarketed");
		    	  }
		      }},
//		      {title:message("csh.product.parent"),field:"parent",sortable:true,formatter: function(value,row,index){
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

	
	$("#product-search-btn").click(function(){
	  var _queryParams = $("#product-search-form").serializeJSON();
	  $('#product-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#product-table-list").datagrid('reload');
	});
	
})
