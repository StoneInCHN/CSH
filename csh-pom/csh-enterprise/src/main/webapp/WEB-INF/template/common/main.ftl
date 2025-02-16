[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="CSH管理系统">
    <meta name="author" content="CSH">
    <link rel="icon" href="${base}/resources/images/favicon.ico">
    <title>管理中心</title>
    <link href="${base}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/resources/css/font-awesome.min.css" rel="stylesheet">
 	<link rel="stylesheet" type="text/css" href="${base}/resources/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${base}/resources/easyui/themes/icon.css">
 	<link rel="stylesheet" type="text/css" href="${base}/resources/css/common.css">
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/main.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/webuploader.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/upload-style.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/uploadPhotos_style.css" >
    <link rel="stylesheet" type="text/css" href="${base}/resources/css/evaluting.css">
	<!--[if lt IE 9]>
    <script type="text/javascript" src="${base}/resources/js/respond.1.4.2.min.js"></script>
    <![endif]-->
    <!-- 百度地图api -->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${message("csh.baiduMap.ak")}"></script>
    <!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	
    <!-- 坐标转换-->
    <script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
    
  </head>

  <body class="easyui-layout" >   
	<div class="header" data-options="region:'north',split:true,noheader:true,collapse:'west'">
		<div class="logo">
			[#if (tenantInfo.systemName)??]${tenantInfo.systemName}
			[#else]
				商铺管理中心
			[/#if]
		</div>
		<div id="nav-wrap" class="nav-wrap"  style="width:730px">
			<ul class="nav nav-pills">
				<li><a href="#main"><i class="fa fa-home fa-1x"></i>首页</a></li>
				[@shiro.hasPermission name="vehicleManage"]
				<li><a href="#vehicle"><i class="fa fa-car fa-1x"></i>${message("csh.vehicle.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="deviceManage"]
				<li><a href="#device"><i class="fa fa-cogs fa-1x"></i>${message("csh.device.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="endUserManage"]
				<li><a href="#endUser"><i class="fa fa-users fa-1x"></i>${message("csh.endUser.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="clearingManage"]
				<li><a href="#clearingManage"><i class="fa fa-users fa-1x"></i>${message("csh.clearingManage.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="carServiceManage"]
				<li><a href="#carService"><i class="fa fa-cogs fa-1x"></i>${message("csh.carServiceManage.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="estoreManage"]
				<li><a href="#estoreManage"><i class="fa fa-users fa-1x"></i>${message("csh.estoreManage.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="orderManager"]
				<li><a href="#orderManager"><i class="fa fa-users fa-1x"></i>${message("csh.orderManager.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="shippingManager"]
				<li><a href="#shippingManager"><i class="fa fa-users fa-1x"></i>${message("csh.shippingManager.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="reservationManage"]
				<li><a href="#reservationManage"><i class="fa fa-mobile fa-1x"></i>${message("csh.reservationMange.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="informationManage"]
				<li><a href="#informationManage"><i class="fa fa-envelope fa-1x"></i>${message("csh.informationManage.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="statisticsReport"]
				<li><a href="#statisticsReport"><i class="fa fa-bar-chart fa-1x"></i>${message("csh.statisticsReport.report")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="insuranceManage"]
				<li><a href="#insuranceManage"><i class="fa fa-briefcase fa-1x"></i>${message("csh.insuranceManage.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="appActivity"]
				<li><a href="#appActivity"><i class="fa fa-briefcase fa-1x"></i>${message("csh.appActivity.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="personnelManage"]
				<li><a href="#personnel"><i class="fa fa-user fa-1x"></i>${message("csh.personnel.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="systemManage"]
				<li><a href="#system"><i class="fa fa-windows fa-1x"></i>系统管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="systemManage"]
				<li><a href="#estoreOrder"><i class="fa fa-windows fa-1x"></i>商品订单管理</a></li>
				[/@shiro.hasPermission]
				<a href="#" id="nav-switcher" class="nav-switcher">更多<i class="fa fa-angle-down fa-1x"></i></a>
				<!--<a id="nav-switcherset" href="#" class="router nav-switcherset off"><span class="middlehelper">设置</span><span><i class="fa fa-cog"></i></span></a>-->
			</ul>
		</div>
		<div class="welcome pull-right">
		<marquee scrollamount='2'>欢迎 ${tenantAccount.userName}登录！</marquee></div>
		<ul class="user-profile">
		    <li  class="dropdown" >
				  <a class="btn  dropdiown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    <i class="fa fa-cog"></i>
				   
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				     <li><a href="#" id="changePasswordHref">修改密码</a></li>
				     <li><a href="#" id="tenantInfoConfigHref">店铺管理</a></li>
				    <li role="separator" class="divider"></li>
				    <li class="pull-right"><a href="${base}/console/common/logout.jhtml">退出</a></li>
				  </ul>
		    </li>
		</ul>
	</div>   
    <div class="footer" data-options="region:'south',split:false,noheader:true" ></div>   
    <div class="left-content" data-options="region:'west',title:'导航菜单',split:true,width:115" >
    	<ul title="${message("csh.system.config")}" id="system">
    		[@shiro.hasPermission name="tenantAccount"]
    		<li><a href="#" data-url="${base}/console/tenantAccount/tenantAccount.jhtml">${message("csh.system.tenantAccount")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="tenantInfo"]
    		<li><a href="#" data-url="${base}/console/tenantInfo/tenantInfo.jhtml">${message("csh.system.tenantInfo")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="role"]
    		<li><a href="#" data-url="${base}/console/role/role.jhtml">${message("csh.system.tenantAccount.role")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="operationLog"]
    		<li><a href="#" data-url="${base}/console/operationLog/operationLog.jhtml">${message("csh.system.operationLog")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="appConfig"]
    		<li><a href="#" data-url="${base}/console/app/app.jhtml">${message("csh.system.appConfig")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="advertisementConfig"]
    		<li><a href="#" data-url="${base}/console/advertisement/advertisement.jhtml">${message("csh.system.advertisementConfig")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.personnel.config")}" id="personnel">
    		[@shiro.hasPermission name="department"]
    		<li><a href="#" data-url="${base}/console/department/department.jhtml">${message("csh.personnel.department")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="position"]
    		<li><a href="#" data-url="${base}/console/position/position.jhtml">${message("csh.personnel.position")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="tenantUser"]
    		<li><a href="#" data-url="${base}/console/tenantUser/tenantUser.jhtml">${message("csh.personnel.tenantuser")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.vehicle.config")}" id="vehicle">
    		[@shiro.hasPermission name="vehicle"]
    		<li><a href="#" data-url="${base}/console/vehicle/vehicle.jhtml">${message("csh.vehicle.vehicle")}</a></li>
    		[/@shiro.hasPermission]
    		<!--
    		[@shiro.hasPermission name="realTimeCarCondition"]
    		<li><a href="#" data-url="${base}/console/vehicle/realTimeCarCondition.jhtml">${message("csh.vehicle.realTimeCarCondition")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleDailyReport"]
    		<li><a href="#" data-url="${base}/console/vehicle/vehicleDailyReport.jhtml">${message("csh.vehicle.vehicleDailyReport")}</a></li>
    		[/@shiro.hasPermission]
    		-->
    		[@shiro.hasPermission name="vehicleMileageReport"]
    		<li><a href="#" data-url="${base}/console/vehicleMileageReport/vehicleMileageReport.jhtml">车辆数据统计</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleMaintain"]
    		<li><a href="#" data-url="${base}/console/vehicleMaintain/vehicleMaintain.jhtml">${message("csh.vehicle.vehicleMaintain")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleTrack"]
    		<li><a href="#" data-url="${base}/console/vehicleTrack/track.jhtml">${message("csh.vehicle.vehicleTrack")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.device.config")}" id="device">
    		[@shiro.hasPermission name="device"]
    		<li><a href="#" data-url="${base}/console/deviceInfo/deviceInfo.jhtml">${message("csh.device.device")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.carServiceManage.serviceConfig")}" id="carService">
    		[@shiro.hasPermission name="serviceConfig"]
    		<li><a href="#" data-url="${base}/console/carService/carService.jhtml">${message("csh.carServiceManage.serviceConfig")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="carServiceItem"]
    		<li><a href="#" data-url="${base}/console/carServiceItem/carServiceItem.jhtml">${message("csh.carServiceManage.carServiceItem")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="itemPart"]
    		<li><a href="#" data-url="${base}/console/itemPart/itemPart.jhtml">${message("csh.carServiceManage.itemPart")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.endUser.config")}" id="endUser">
    		[@shiro.hasPermission name="endUser"]
    		<li><a href="#" data-url="${base}/console/endUser/endUser.jhtml">${message("csh.endUser.endUser")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.reservationManage.config")}" id="reservationManage">
    		<!--[@shiro.hasPermission name="repareReservation"]
    		<li><a href="#" data-url="${base}/console/repareReservation/repareReservation.jhtml">${message("csh.repareReservation.repareReservation")}</a></li>
    		[/@shiro.hasPermission]
    		-->
    		[@shiro.hasPermission name="maintainReservation"]
    		<li><a href="#" data-url="${base}/console/maintainReservation/maintainReservation.jhtml">${message("csh.maintainReservation.maintainReservation")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="beautifyReservation"]
    		<li><a href="#" data-url="${base}/console/beautifyReservation/beautifyReservation.jhtml">${message("csh.beautifyReservation.beautifyReservation")}</a></li>
    		[/@shiro.hasPermission]
    		
    	</ul>
    	<ul title="${message("csh.informationManage.config")}" id="informationManage">
    		[@shiro.hasPermission name="notificationPush"]
    		<li><a href="#" data-url="${base}/console/messageInfo/notificationPush.jhtml">${message("csh.informationManage.notificationPush")}</a></li>
    		[/@shiro.hasPermission]
    		<!--[@shiro.hasPermission name="shortMessagePush"]
    		<li><a href="#" data-url="${base}/console/messageInfo/shortMessagePush.jhtml">${message("csh.informationManage.shortMessagePush")}</a></li>
    		[/@shiro.hasPermission]
    		-->
    	</ul>
    	<ul title="${message("csh.statisticsReport.report")}" id="statisticsReport">
    		[@shiro.hasPermission name="repareReport"]
    		<li><a href="#" data-url="${base}/console/reportRepareStatistics/reportRepareStatistics.jhtml">${message("csh.statisticsReport.repareStatisticsReport")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="maintainReport"]
    		<li><a href="#" data-url="${base}/console/reportMaintainStatistics/reportMaintainStatistics.jhtml">${message("csh.statisticsReport.maintainStatisticsReport")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="washReport"]
    		<li><a href="#" data-url="${base}/console/reportWashStatistics/reportWashStatistics.jhtml">${message("csh.statisticsReport.washStatisticsReport")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	
    	<ul title="${message("csh.insuranceManage.config")}" id="insuranceManage">
    		[@shiro.hasPermission name="vehicleInsurance"]
    		<li><a href="#" data-url="${base}/console/vehicleInsurance/vehicleInsurance.jhtml">${message("csh.insuranceManage.vehicleInsurance")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.estoreManage.config")}" id="estoreManage">
    		[@shiro.hasPermission name="productBrand"]
    		<li><a href="#" data-url="${base}/console/brand/brand.jhtml">${message("csh.estoreManage.productBrand")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="product"]
    		<li><a href="#" data-url="${base}/console/product/product.jhtml">${message("csh.estoreManage.product")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="productCategory"]
    		<li><a href="#" data-url="${base}/console/productCategory/productCategory.jhtml">${message("csh.estoreManage.productCategory")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="productTag"]
    		<li><a href="#" data-url="${base}/console/tag/tag.jhtml">${message("csh.estoreManage.productTag")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="productAttribute"]
    		<li><a href="#" data-url="${base}/console/attribute/attribute.jhtml">${message("csh.estoreManage.productAttribute")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="productParameter"]
    		<li><a href="#" data-url="${base}/console/parameterGroup/parameterGroup.jhtml">${message("csh.estoreManage.productParameter")}</a></li>
    		[/@shiro.hasPermission]
    		
    		[@shiro.hasPermission name="marketingCenter"]
    		<li><a href="#" data-url="${base}/console/marketingCenter/marketingCenter.jhtml">${message("csh.estoreManage.marketingCenter")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	
    	<ul title="${message("csh.orderManager.config")}" id="orderManager">
    		[@shiro.hasPermission name="order"]
    		<li><a href="#" data-url="${base}/console/estore/order/order.jhtml">${message("csh.orderManager.order")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="orderItem"]
    		<li><a href="#" data-url="${base}/console/estore/orderItem/orderItem.jhtml">${message("csh.orderManager.orderItem")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="orderLog"]
    		<li><a href="#" data-url="${base}/console/estore/orderLog/orderLog.jhtml">${message("csh.orderManager.orderLog")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.shippingManager.config")}" id="shippingManager">
    		[@shiro.hasPermission name="shipping"]
    		<li><a href="#" data-url="${base}/console/shipping/shipping.jhtml">${message("csh.shippingManager.shipping")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="returns"]
    		<li><a href="#" data-url="${base}/console/returnsRefunds/returnsRefunds.jhtml">${message("csh.shippingManager.returns")}${message("csh.shippingManager.refunds")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="shippingMethod"]
    		<li><a href="#" data-url="${base}/console/shippingMethod/shippingMethod.jhtml">${message("csh.shippingManager.shippingMethod")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="deliveryCorp"]
    		<li><a href="#" data-url="${base}/console/deliveryCorp/deliveryCorp.jhtml">${message("csh.shippingManager.deliveryCorp")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>  
    	 
    	<ul title="${message("csh.clearingManage.config")}" id="clearingManage">
    		[@shiro.hasPermission name="clearingRecord"]
    		<li><a href="#" data-url="${base}/console/tenantClearingRecord/tenantClearingRecord.jhtml">${message("csh.clearingManage.clearingRecord")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="carServiceRecord"]
    		<li><a href="#" data-url="${base}/console/carServiceRecord/carServiceRecord.jhtml">${message("csh.clearingManage.carServiceRecord")}</a></li>
    		[/@shiro.hasPermission]

			[@shiro.hasPermission name="carServiceTenantDeductRecord"]
    		<li><a href="#" data-url="${base}/console/carServiceTenantDeductRecord/carServiceTenantDeductRecord.jhtml">${message("csh.clearingManage.carServiceTenantDeductRecord")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="tenantDeductClearingRecord"]
    		<li><a href="#" data-url="${base}/console/tenantDeductClearingRecord/tenantDeductClearingRecord.jhtml">${message("csh.clearingManage.tenantDeductClearingRecord")}</a></li>
    		[/@shiro.hasPermission]    	</ul>
    	<ul title="${message("csh.appActivity.config")}" id="appActivity">
    		[@shiro.hasPermission name="coupon"]
    		<li><a href="#" data-url="${base}/console/coupon/coupon.jhtml">${message("csh.appActivity.coupon")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="joyDraw"]
    		<li><a href="#" data-url="${base}/console/joyDraw/joyDraw.jhtml">${message("csh.appActivity.joyDraw")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="carWashingCoupon"]
    		<li><a href="#" data-url="${base}/console/carWashingCoupon/carWashingCoupon.jhtml">${message("csh.appActivity.carWashingCoupon")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>  
    	<ul title="${message("csh.estoreOrder.config")}" id="estoreOrder">
    		<li><a href="#" data-url="${base}/console/estore/order/order.jhtml">${message("csh.estore.order")}</a></li>
    	</ul>  
    	                 
    </div>
  
    <div class="main-content" data-options="region:'center'">
    	<div id="manager-tabs">   
		    <div title="起始页">
		    	<div class="main-content-left">
		    			<div class="row shortcutNavigation">
		    				<div class="row">
						        <div class="col-md-12 ">
						        	<a href ="#"><img class="imgNav" title="车辆信息" onclick="shortcutNavigation('车辆信息','${base}/console/vehicle/vehicle.jhtml')" src="${base}/resources/images/car.png"/></a>
						        	<a href ="#"><img class="imgNav" title="设备信息" onclick="shortcutNavigation('设备信息','${base}/console/deviceInfo/deviceInfo.jhtml')" src="${base}/resources/images/device.png"/></a>
						        	<a href ="#"><img class="imgNav" title="保险管理" onclick="shortcutNavigation('车辆保险','${base}/console/vehicleInsurance/vehicleInsurance.jhtml')" src="${base}/resources/images/insurance.png"/></a>
						        	<a href ="#"><img class="imgNav" title="保养预约" onclick="shortcutNavigation('保养预约','${base}/console/maintainReservation/maintainReservation.jhtml')" src="${base}/resources/images/maintain.png"/></a>
						        	<!--<a href ="#"><img class="imgNav" title="维修预约" onclick="shortcutNavigation('维修预约','${base}/console/repareReservation/repareReservation.jhtml')" src="${base}/resources/images/service.png"/></a>-->
						        	<a href ="#"><img class="imgNav" title="美容预约" onclick="shortcutNavigation('美容预约','${base}/console/beautifyReservation/beautifyReservation.jhtml')" src="${base}/resources/images/service.png"/></a>
						        </div>
						    </div>
						    <div class="row">
								<div class="col-md-12 index-report-item">
									<div class = "row">
						         		<div class="col-md-4 index-report-item"><div id = "deviceInfoStatisticsId" style="height:240px"></div></div>
						         		<!--<div class="col-md-4 index-report-item"><div id = "repareReservationStatisticsId" style="height:240px"></div></div>-->
						         		<div class="col-md-4 index-report-item"><div id = "beautifyReservationStatisticsId" style="height:240px"></div></div>
						         		<div class="col-md-4 index-report-item"><div id = "maintainReservationStatisticsId" style="height:240px"></div></div>
						         		
						         	</div>
					         	</div>
					         </div>
					         <div class="row">
								<div class="col-md-12">
						    		<div id="serviceStatisticsReportId" style="height:260px"></div>
						    	</div><!--end col-->
						     </div>
						</div><!-- end row shortcutNavigation-->
					</div>
					<div class="main-content-right">
						<div class="row">
							<div class="">
							[#if generateQrCode == true]
								
								<button type="button" id="generateQrcodeButton" onclick="generateQrcode()" class="btn btn-primary">生成二维码</button>
							[#else]
								<a href='${base}/console/common/showQrCode.jhtml'>
									<img id="qrCodeImage" style="width:120px;height:120px" src="${base}/console/common/showQrCode.jhtml" title="扫码加入"/>
								</a>
							[/#if]	
							</div>
						
							<div class = "">
								<div id="calendar-panel " class="easyui-panel" title="日历" 
									style="width:220px;height:180px;padding:5px;background:#fafafa;"data-options="collapsible:true">
									<div id="cc" class="easyui-calendar" style="width:100%;height:100%;"></div>  
								</div>
							</div>
						</div>
						<div class="row">
							<div class="mini-widget pull-right" style="height:60px;width: 260px;margin-top:30px;">
								<div class="mini-widget-heading clearfix" >设备：</div>
								<div class="mini-widget-body clearfix">
								  <div class="pull-left" style="color:green">已绑定：${response.bindedDeviceCount}</div>
								  <input type="hidden" id='bindedDeviceCount' value='${response.bindedDeviceCount}'/>
								  <div class="pull-right" style="color:red">
									未绑定：${response.unbindedDeviceCount}
								  </div>
								  <input type="hidden" id='unbindedDeviceCount' value='${response.unbindedDeviceCount}'/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="mini-widget pull-right" style="height:60px;width: 260px;">
								<div class="mini-widget-heading"><span >用户</span></div>
								<div class="mini-widget-body">
									<div style="padding:5px">用户数:<pan class="endUserInfoCount">${response.endUserCount}</pan></div>
									<div style="padding:5px">车辆数:<pan class="endUserInfoCount">${response.vehicleCount}</pan><div>
								</div>
							</div>
						</div>
					</div>
		   	 	</div>
		   	 	
			</div>
		<div id = "commonMainDialog"></div> 
		<div id = "player"></div> 
		<div id = "info"></div> 
    </div>    
    
    
    
    
    <!-- JavaScript-->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.serializejson.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${base}/resources/js/easyui-extends.js"></script>
	<script type="text/javascript" src="${base}/resources/js/validator.js"></script>
	<script type="text/javascript" src="${base}/resources/js/highcharts.js"></script>
	<script type="text/javascript" src="${base}/resources/js/modules/exporting.src.js"></script>
	<script type="text/javascript" src="${base}/resources/js/modules/export-csv.js"></script>
	<script type="text/javascript" src="${base}/resources/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/js/message.js"></script>
	<script type="text/javascript" src="${base}/resources/js/main.js"></script>
	<script type="text/javascript" src="${base}/resources/js/dropdown.js"></script>
	<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/resources/js/webuploader.min.js"></script>
	<script type="text/javascript" src="${base}/resources/js/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${base}/resources/js/multiselect.js"></script>
	<script type="text/javascript" src="${base}/resources/js/prettify.min.js"></script>
	<script type="text/javascript" src="${base}/resources/js/jquery.imagePreview.1.0.js"></script>
	<script src="${base}/resources/js/jquery.bootstrap.newsbox.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/fileUploadCommon.js"></script>
	<script src="${base}/resources/js/multiplefileUpload.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.easing.1.3.js"></script>
	<script>
		var BASE_URL = '${base}/resources' ;
		
		function jumpPage(e){
			var aClick = $(e);
			var _url = aClick.attr("data-url");
			var _title = aClick.attr("data-name");
			if($('#manager-tabs').tabs("exists",_title)){
				$('#manager-tabs').tabs("select",_title)
			}else{
				$('#manager-tabs').tabs('add',{    
				    title:_title,    
				    href:_url,    
				    closable:true      
				}); 
			}
			
	}
	</script>
  </body>
</html>
