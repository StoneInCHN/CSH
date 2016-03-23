/**
 *绑定右侧点击事件 
 */
function clickNotificationNews(event) {
	var _this = $(event.target);
	var _url = _this.attr("data-url");
	if($('#manager-tabs').tabs("exists","通知消息")){
		$('#manager-tabs').tabs("select","通知消息");

		// 调用 'refresh' 方法更新选项卡面板的内容
		var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
		tab.panel('refresh', _url);
	}else{
		$('#manager-tabs').tabs('add',{    
		    title:"通知消息",    
		    href:_url,    
		    closable:true      
		}); 
	}
};

/**
 *绑定流程点击事件 
 */
function shortcutNavigation(title,data_url){
	if(title){
		if($('#manager-tabs').tabs("exists",title)){
			$('#manager-tabs').tabs("select",title)
		}else{
			$('#manager-tabs').tabs('add',{    
			    title:title,    
			    href:data_url,    
			    closable:true      
			}); 
		}
	}
};

$(function(){
	/**
	 *初始化右侧的选项卡
	 */
	$("#manager-tabs").tabs({
		fit:true,
		border:false
	});
	
	
	$("#nav-switcher").mouseover(function(){
		$(".nav-wrap").addClass("nav-silde");
		$("#nav-switcherset").show();
	})
	$("#nav-wrap ul").mouseleave(function(){
		$("#nav-wrap").removeClass("nav-silde");
		$("#nav-switcherset").hide();
	})
	
	$("#dropdownMenu1").dropdown();
	//初始化显示首页，隐藏菜单栏
	$('.easyui-layout').layout('collapse','west');
	
	var westLayour = $('.easyui-layout').layout('panel','west');
	$("#nav-wrap > ul >li >a").click(function(){
		
		var $this = $(this);
		$(".left-content > ul").hide();
		if($this.text()=="首页"){
			$('.easyui-layout').layout('collapse','west');
			$('#manager-tabs').tabs("select",'起始页');
			
			westLayour.hide();
		}else{
			
			$('.easyui-layout').layout('expand','west');
			westLayour.show();
		}
		
		$($this.attr("href")).show();
	})
	
	/**
	 *绑定左侧导航条的点击事件 
	 */
	$(".left-content a").click(function(){
		var _this = $(this);
		var _url = _this.attr("data-url");
		if(_this.text()){
			if($('#manager-tabs').tabs("exists",_this.text())){
				$('#manager-tabs').tabs("select",_this.text())
			}else{
				$('#manager-tabs').tabs('add',{    
				    title:_this.text(),    
				    href:_url,    
				    closable:true      
				}); 
			}
		}
	});
		/**
		 * 修改密码事件
		 */
		$("#changePasswordHref").click(function(){
			$('#changePassword').dialog({
			    title: "修改密码",//message("yly.drugsInfo.add"),    
			    width: 500,    
			    height: 480,
			    iconCls:'icon-mini-add',
			    href:'../common/changePassword.jhtml',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						debugger;
						var validate = $('#changePassword_form').form('validate');
						if(validate){
							$.ajax({
								url:"../common/savePassword.jhtml",
								type:"post",
								data:$("#changePassword_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#changePassword').dialog("close");
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
						 $('#changePassword').dialog("close");
					}
			    }],
			    onOpen:function(){
			    	$('#changePassword').show();
			    },
			
			});
		});
//维修记录统计
var reportMaintainStatistics = {
//			colors : [ '#008000', '#FF0000', '#FFFF00', '#DDDF00', '#24CBE5',
//					'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
			chart : {
				type:'spline',
				renderTo : 'serviceStatisticsReportId',
				backgroundColor : {
					linearGradient : {
						x1 : 0,
						y1 : 0,
						x2 : 1,
						y2 : 1
					},
					stops : [ [ 0, 'rgb(255, 255, 255)' ], [ 1, 'rgb(240, 240, 255)' ] ]
				},
			},
			title : {
				text : '业务统计',
			// center
			},
			credits : {
				enabled : false
			// 禁用版权信息
			},
			xAxis : {
				gridLineWidth : 0,
				lineColor : '#000',
				categories:[]
			},
			yAxis : {
				minorTickInterval : 'auto',
				lineColor : '#000',
				lineWidth : 0,
				tickWidth : 1,
				tickColor : '#000',
				title : {
					text : '元'
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},
			tooltip : {
				valueSuffix : '元'
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'middle',
				borderWidth : 0
			},
			series: []
		};
		
		$.ajax({
			url : "../../console/reportServiceStatistics/report.jhtml",
			type : "post",
			cache : false,
			success : function(data) {
				 if (data.length > 0) {
					 
					 var viewName = [];
					 var categoryValue = [];
					for (var k = 0; k < data.length; k++) {
						var name = data[k]["serviceCategory"].categoryName;
						var category = new Date(data[k]["statisticsDate"])
								.Format("yyyy年MM月");
						if (categoryValue.indexOf(category) == -1) {
							categoryValue.push(category);
						}
						if (viewName.indexOf(name) == -1) {
							viewName.push(name);
							var value = new Object();
							value.name = name;
							value.data = [];
							reportMaintainStatistics.series.push(value);
						}
					}
					reportMaintainStatistics.xAxis.categories = categoryValue;
					for (var k = 0; k < categoryValue.length; k++) {
						for (var j = 0; j < viewName.length; j++) {
							// 如果该分类没数据，用0填充
							var viewValue = 0;
							for (var i = 0; i < data.length; i++) {
								var date = new Date(
										data[i]["statisticsDate"])
										.Format("yyyy年MM月");
								if (viewName[j] == data[i]["serviceCategory"].categoryName
										&& date == categoryValue[k]) {
									viewValue = data[i]["totalCount"]
								}
							}
							reportMaintainStatistics.series[j].data.push(viewValue);
						}
					}
				}
				var chart = new Highcharts.Chart(reportMaintainStatistics);
			}
		});
});

