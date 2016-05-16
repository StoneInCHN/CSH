$(function(){
	$("#appSave").click(function(){
		var validate = $('#app_form').form('validate');
		if(validate){
			$.ajax({
				url:"../app/add.jhtml",
				type:"post",
				data:$("#app_form").serialize(),
				beforeSend:function(){
					$.messager.progress({
						text:message("csh.common.saving")
					});
				},
				success:function(result,response,status){
					$.messager.progress('close');
					showSuccessMsg(result.content);
					var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
					tab.panel('refresh');
				}
			});
		}
	})
})
