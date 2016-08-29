//保养记录按天统计
var reportMaintainStatistics = {
	chart: {type: 'spline', renderTo : "dailyMaintainReportID"},
	title: {text: '保养记录统计',useHTML:true},
	subtitle: {text: '周期：按天统计'},
    plotOptions: {
        spline: {
            marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
        }
    },
	credits : {
		enabled : false
	// 禁用版权信息
	},
	xAxis : {
		gridLineWidth : 1,
		lineColor : '#000',
		categories : []
	},
	yAxis : {
		minorTickInterval : 'auto',
		lineColor : '#000',
		lineWidth : 1,
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
	series : []
};

$("#reportMaintainStatistics-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportMaintainStatistics/dailyReport.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"收入/元",field:"totalIncome",width:'50%',align:"center",sortable:true},
		     {title:"统计时间",field:"statisticsDate",width:'50%',align:"center",sortable:true,
		    	 formatter: function(value,row,index){
		    		 if(value != null){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
						}else{
							return "";
						}
		    	  }
		     }
		 ]
	],
	onLoadSuccess:function(data){
		reportMaintainStatistics.series= [];
		refreshLine(reportMaintainStatistics,data.rows,
				'statisticsDate',[ 'totalIncome'],[ '收入'],"yyyy年MM月dd日");
	}

});
$("#report_maintain_statistics_search_btn").click(function(){
	  var _queryParams = $("#report_maintain_statistics_search_form").serializeJSON();
	  $('#reportMaintainStatistics-table-list').datagrid('options').queryParams = _queryParams;
	  $("#reportMaintainStatistics-table-list").datagrid('reload');
});



//保养记录按月统计
var monthlyReportMaintain = {
	chart: {type: 'spline', renderTo : "monthlyReportMaintainID"},
	title: {text: '保养记录统计',useHTML:true},
	subtitle: {text: '周期：按月统计'},
    plotOptions: {
        spline: {marker: {radius: 4,lineColor: '#666666',lineWidth: 1}}
    },
	credits : {enabled : false},
	xAxis : {gridLineWidth : 1,lineColor : '#000',categories : []},
	yAxis : {
		minorTickInterval : 'auto',
		lineColor : '#000',
		lineWidth : 1,
		tickWidth : 1,
		tickColor : '#000',
		title : {text : '元'},
		plotLines : [ {value : 0,width : 1,color : '#808080'} ]
	},
	tooltip : {valueSuffix : '元'},
	legend : {layout : 'vertical',align : 'right',verticalAlign : 'middle',borderWidth : 0},
	series : []
};

$("#monthlyRreportMaintain-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportMaintainStatistics/monthlyReport.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[[
		     {title:"收入/元",field:"totalIncome",width:'50%',align:"center",sortable:true},
		     {title:"统计时间",field:"statisticsDate",width:'50%',align:"center",sortable:true,
		    	 formatter: function(value,row,index){
		    		 if(value != null){
							return new Date(value).Format("yyyy-MM");
						}else{
							return "";
						}
		    	  }
		     }
	] ],
	onLoadSuccess:function(data){
		monthlyReportMaintain.series= [];
		refreshLine(monthlyReportMaintain,data.rows,
				'statisticsDate',[ 'totalIncome'],[ '收入'],"yyyy年MM月");
	}

});
$("#monthly_report_maintain_search_btn").click(function(){
	  var _queryParams = $("#monthly_report_maintain_form").serializeJSON();
	  $('#monthlyRreportMaintain-table-list').datagrid('options').queryParams = _queryParams;
	  $("#monthlyRreportMaintain-table-list").datagrid('reload');
});	
	