//洗车记录统计
var reportWashStatistics = {
	chart: {type: 'spline', renderTo : "washStatisticsReportId"},
	title: {text: '洗车记录统计',useHTML:true},
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

$("#reportWashStatistics-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportWashStatistics/dailyReport.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"收入",field:"totalIncome",width:'50%',align:"center",sortable:true},
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
		reportWashStatistics.series= [ ];
		refreshLine(reportWashStatistics,data.rows,
				'statisticsDate',
				[ 'totalIncome'],
				[ '收入'],"yyyy年MM月dd日");
	}

});
$("#report_wash_statistics_search_btn").click(function(){
	  var _queryParams = $("#report_wash_statistics_search_form").serializeJSON();
	  $('#reportWashStatistics-table-list').datagrid('options').queryParams = _queryParams;
	  $("#reportWashStatistics-table-list").datagrid('reload');
});

//洗车记录按月统计
var monthlyReportWash = {
	chart: {type: 'spline', renderTo : "monthlyReportWashID"},
	title: {text: '洗车记录统计',useHTML:true},
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

$("#monthlyRreportWash-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportWashStatistics/monthlyReport.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
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
		 
		 ]
	],
	onLoadSuccess:function(data){
		monthlyReportWash.series= [ ];
		refreshLine(monthlyReportWash,data.rows,
				'statisticsDate',
				[ 'totalIncome'],
				[ '收入'],"yyyy年MM月");
	}

});
$("#monthly_report_wash_search_btn").click(function(){
	  var _queryParams = $("#monthly_report_wash_form").serializeJSON();
	  $('#monthlyRreportWash-table-list').datagrid('options').queryParams = _queryParams;
	  $("#monthlyRreportWash-table-list").datagrid('reload');
});	