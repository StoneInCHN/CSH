var advertisement_manager_tool = {
			add:function(){
				$('#addAdvertisement').dialog({
				    title: message("csh.advertisement.add"),    
				    width: 700,    
				    height: 600,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("csh.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addAdvertisement_form').form('validate');
							var $photoLi = $("#advertisementUploader-add ul.filelist li");
							if(validate){
								if($photoLi.length >0){
									$("#advertisementUploader-add .uploadBtn").trigger("upload");
								}else{
									$.ajax({
										url:"../advertisement/add.jhtml",
										type:"post",
										data:$("#addAdvertisement_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
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
								}
							};
						}
					},{
						text:message("csh.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addAdvertisement').dialog("close");
							 $('#addAdvertisement_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addAdvertisement_form').show();
				    	//头像上传
				     	var options ={
				     			createOption:{
				     				pick: {
						                 id: '#advertisementFilePicker-add',
						                 label: '',
						                 multiple :false
						             },
						             dnd: '#advertisementUploader-add .queueList',
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
				     			warp :"addAdvertisement_form",
				     			uploadBeforeSend:function(object, data, headers){
				     				 data.imageType ='ADVIMAGE';
				     			},
				     			uploadSuccess:function(file, response){
				     				//将返回的图片路径放到隐藏的input中，用于表单保存
				     				$("#addAdvertisement_form_file_input").val(response.content);
				     				$.ajax({
										url:"../advertisement/add.jhtml",
										type:"post",
										data:$("#addAdvertisement_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addAdvertisement').dialog("close")
											$("#addAdvertisement_form").form("reset");
											$("#advertisement-table-list").datagrid('reload');
											
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
				     			}
				     	};
				     	
				     	singleUpload(options);
				    },
				    onClose:function(){
				    	$("#advertisementUploader-add .uploadBtn").trigger("clearFileQuene");
				    	$('#addAdvertisement').empty();
				    }
				});  
				
			},
			edit:function(){
				var _edit_row = $('#advertisement-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("csh.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editAdvertisement').dialog({    
					title: message("csh.common.edit"),   
				    width: 700,    
				    height: 600,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../advertisement/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("csh.common.save"),
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
											text:message("csh.common.saving")
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
						text:message("csh.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editAdvertisement').dialog("close");
							 $('#editAdvertisement_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editAdvertisement_form').show();
				    	var editOptions ={
				     			createOption:{
				     				pick: {
						                 id: '#advertisementFilePicker-edit',
						                 innerHTML :'',
						                 multiple :true
						             },
						             dnd: '#advertisementUploader-edit .queueList',
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
						             server: '../advertisement/uploadPhoto.jhtml',
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
				     			warp :"editAdvertisement_form",
				     			uploadImageType:"edit",
				     			addButton:{
				     				id: '#advertisementFilePicker-edit2',
				     				innerHTML: '替换头像'
				     			},
				     			uploadBeforeSend:function(object, data, headers){
				     				 //
//				     				 data.staffID =$("#editCarService_form").find("input[name='staffID']").val();
				     				 data.advertisementId=$("#editAdvertisement_form").find("input[name='id']").val();;
				     			}
				     	};
				     	singleUpload(editOptions);
				     	$("#editAdvertisement_form").find(".savePhoto").on("click",function(){
				     		$.messager.confirm('确认','头像保存后将直接修改当前用户的头像，确认要上传吗？',function(res){    
				     		    if (res){
				     		    	$("#advertisementUploader-edit .uploadBtn").trigger("upload");
				     		    }    
				     		}); 
				     		//alert("保存头像");
				     	});
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
		title:message("csh.advertisement.list"),
		fitColumns:true,
		toolbar:"#advertisement_manager_tool",
		url:'../advertisement/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#advertisementDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../advertisement/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.cancel"),
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
		      {title:message("csh.advertisement.advName"),field:"advName",width:50,sortable:true},
		      {title:message("csh.advertisement.status"),field:"status",width:50,sortable:true,
		    	  formatter: function(value,row,index){
		    		  if(value == "ENABLE"){
		    			  return message("csh.common.enable");
		    		  }else if(value == "DISABLE"){
		    			  return message("csh.common.disable");
		    		  }
		    	  }},
		      {title:message("csh.common.createDate"),field:"createDate",width:50,sortable:true,formatter: function(value,row,index){
					if(value != null)
						return new Date(value).Format("yyyy-MM-dd");
					else 
						return "";
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
