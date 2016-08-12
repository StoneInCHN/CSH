var deleteImageIdList = new Array();
var tenantInfo_manager_tool = {
	edit:function(){
		var validate = $('#tenantInfoConfig_form').form('validate');
		var $photoLi = $("#tenantImageUploader-edit ul.filelist li");
		$("#deleteImageIdList").val(deleteImageIdList.join(","));
		if(validate){
			if($photoLi.length >0){
				$("#tenantImageUploader-edit .uploadBtn").trigger("upload");
			}else{
				$.ajax({
					url:"../tenantInfo/edit.jhtml",
					type:"post",
					data:$("#tenantInfoConfig_form").serialize(),
					beforeSend:function(){
						$.messager.progress({
							text:message("csh.common.saving")
						});
					},
					success:function(result,response,status){
						$.messager.progress('close');
						if(response == "success"){
							showSuccessMsg(result.content);
							$("#editTenandImage_form").form("reset");
							$("#tenantImageUploader-edit .uploadBtn").trigger("clearFiles");//清空上次所选图片文件
							var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
							tab.panel('refresh');
						}else{
							alertErrorMsg();
						}
					}
				});
			}
		}
	},
	saveTenantInfo:function(){
		$("#tenantImageList_input").val(photoUrlList.join(","));
		$.ajax({
			url:"../tenantInfo/edit.jhtml",
			type:"post",
			data:$("#tenantInfoConfig_form").serialize(),
			beforeSend:function(){
				$.messager.progress({
					text:message("csh.common.saving")
				});
			},
			success:function(result,response,status){
				$.messager.progress('close');
				showSuccessMsg(result.content);
				$("#tenantInfoConfig_form").form("reset");
				$("#tenantImageUploader-edit .uploadBtn").trigger("clearFiles");//清空上次所选图片文件
				var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
				tab.panel('refresh');
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
				alertErrorMsg();
			}
		});
	},
	deleteTenantImage:function(id){
		$(".tenantImage_edit"+id).remove();
		deleteImageIdList.push(id);
//		$.ajax({
//			url:"../tenantInfo/deleteImage.jhtml?id="+id,
//			type:"post",
//			beforeSend:function(){
//				$.messager.progress({
//					text:message("csh.common.saving")
//				});
//			},
//			success:function(result,response,status){
//				$.messager.progress('close');
//				showSuccessMsg(result.content);
//				$("#tenantInfoConfig_form").form("reset");
//				var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
//				tab.panel('refresh');
//
//			},
//			error:function (XMLHttpRequest, textStatus, errorThrown) {
//				$.messager.progress('close');
//				alertErrorMsg();
//			}
//		});
	},
};
$(function(){
	//图片上传
 	var options ={
 			createOption:{
 				pick: {
	                 id: '#tenantImageFilePicker-edit',
	                 innerHTML: '选择文件',
	                 multiple :true
	             },
	             dnd: '#tenantImageUploader-edit .queueList',
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
	             fileNumLimit: 4-$('img[class="preview"]').length,
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
 			fileType:"TENANTIMAGE",
 			//包裹上传组件的div id
 			warp :"tenantInfoConfig_form",
 			filePicker_replace :"filePicker_replace_Photos",
 			filePicker2 :"filePicker2_Photos",
 			uploadBeforeSend:function(object, data, headers){
 				 data.imageType ='TENANTIMAGE';
 			},
 			uploadSuccess:function(file, response){
 				photoUrlList.push(response.content);
 			},
 	};
 	if($('img[class="preview"]').length>0 && $('img[class="preview"]').length<4){
 		options.createOption.pick.innerHTML="继续添加";
 		multipleUpload(options);
 	}else if($('img[class="preview"]').length==0 && $('img[class="preview"]').length<4){
 		options.createOption.pick.innerHTML="选择文件";
 		multipleUpload(options);
 	}
 	
// 	singleUpload(options);
});
