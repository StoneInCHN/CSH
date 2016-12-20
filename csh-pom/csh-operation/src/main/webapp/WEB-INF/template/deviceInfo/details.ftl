<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.deviceInfo.details")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.deviceInfo")}</a> 
				<span class="divider">/</span> 
				<a href="#" onclick="history.go(-1)"><i class="fa fa-list"></i>${message("csh.deviceInfo.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.deviceInfo.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.deviceInfo.details")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
                     		<tr>
								<th>
									${message("csh.deviceInfo.bindTime")}:
								</th>
								<td>
									[#if deviceInfo.bindTime??]
										<span title="${deviceInfo.bindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.bindTime}</span>
									[#else]
										-
									[/#if]
								</td>
								<th>
									${message("csh.deviceInfo.unBindTime")}:
								</th>
								<td>
									[#if deviceInfo.unBindTime??]
											<span title="${deviceInfo.unBindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.unBindTime}</span>
									[#else]
											-
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.deviceInfo.deviceNo")}:
								</th>
								<td>
									${deviceInfo.deviceNo}
								</td>
								<th>
									${message("csh.deviceInfo.simNo")}:
								</th>
								<td>
									${deviceInfo.simNo}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.deviceInfo.deviceStatus")}:
								</th>
								<td>
									[#if deviceInfo.deviceStatus??]
										${message("csh.deviceInfo.deviceStatus."+deviceInfo.deviceStatus)}
									[/#if]
								</td>
								<th>
									${message("csh.deviceInfo.bindStatus")}:
								</th>
								<td>
									[#if deviceInfo.bindStatus??]
									 ${message("csh.deviceInfo.bindStatus."+deviceInfo.bindStatus)}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.deviceInfo.type")}:
								</th>
								<td>
									${deviceInfo.type.name}
								</td>
								<th>
									${message("csh.deviceInfo.vehicle")}:
								</th>
								<td>
									[#if deviceInfo.vehicle ??]
										<a href="">${deviceInfo.vehicle.plate}</a>
									[/#if]
									
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.deviceInfo.tenantName")}:
								</th>
								<td>
										[#if deviceInfo.tenantName ??]
											${deviceInfo.tenantName}
										[#else]
											-
										[/#if]
								</td>
								<th>
									${message("csh.deviceInfo.distributorId")}:
								</th>
								<td>	
									[#if deviceInfo.distributor ??]
										${deviceInfo.distributor.distributorName}
									[/#if]
								</td>
							</tr>
							<tr>
							<td colspan="4"><hr></td>
							</tr>
                     		<tr>
								<th>
									设备编号:
								</th>
								<td>
								[#if deviceStatus.deviceid != "null"]
									${deviceStatus.deviceid}
								[#else]
									-
								[/#if]
								</td>
								<th>
									最新上报时间:
								</th>
								<td>
									${createtime!'-'}
								</td>
							</tr>
							<tr>
								<th>
									经度:
								</th>
								<td>
								[#if deviceStatus.lon != "null"]
									${deviceStatus.lon}
								[#else]
									-
								[/#if]
								</td>
								<th>
									是否定位:
								</th>
								<td>
								[#if deviceStatus.gpsstatus != "null"]
									${deviceStatus.gpsstatus}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									纬度:
								</th>
								<td>
								[#if deviceStatus.lat != "null"]
									${deviceStatus.lat}
								[#else]
									-
								[/#if]
								</td>
								<th>
									速度:
								</th>
								<td>
								[#if deviceStatus.speed != "null"]
									${deviceStatus.speed}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									方位角:
								</th>
								<td>
								[#if deviceStatus.azimuth != "null"]
									${deviceStatus.azimuth}
								[#else]
									-
								[/#if]
								</td>
								<th>
									设备状态位:
								</th>
								<td>
								[#if deviceStatus.dstatus != "null"]
									${deviceStatus.dstatus}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									acc状态:
								</th>
								<td>
								[#if deviceStatus.acc != "null"]
									${deviceStatus.acc}
								[#else]
									-
								[/#if]
								</td>
								<th>
									水温:
								</th>
								<td>
								[#if deviceStatus.obdwatertemp != "null"]
									${deviceStatus.obdwatertemp}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									发动机负荷:
								</th>
								<td>
								[#if deviceStatus.obdengload != "null"]
									${deviceStatus.obdengload}
								[#else]
									-
								[/#if]
								</td>
								<th>
									转速:
								</th>
								<td>
								[#if deviceStatus.obdrpm != "null"]
									${deviceStatus.obdrpm}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									车速:
								</th>
								<td>
								[#if deviceStatus.obdspeed != "null"]
									${deviceStatus.obdspeed}
								[#else]
									-
								[/#if]
								</td>
								<th>
									发动机运行时间:
								</th>
								<td>
								[#if deviceStatus.obdengruntime != "null"]
									${deviceStatus.obdengruntime}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									蓄电池电压:
								</th>
								<td>
								[#if deviceStatus.obdbv != "null"]
									${deviceStatus.obdbv}
								[#else]
									-
								[/#if]
								</td>
								<th>
									空燃比系数:
								</th>
								<td>
								[#if deviceStatus.obdafr != "null"]
									${deviceStatus.obdafr}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									环境温度:
								</th>
								<td>
								[#if deviceStatus.obdtemp != "null"]
									${deviceStatus.obdtemp}
								[#else]
									-
								[/#if]
								</td>
								<th>
									气节门开度:
								</th>
								<td>
								[#if deviceStatus.obdcta != "null"]
									${deviceStatus.obdcta}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									每小时油耗:
								</th>
								<td>
								[#if deviceStatus.obdfcph != "null"]
									${deviceStatus.obdfcph}
								[#else]
									-
								[/#if]
								</td>
								<th>
									百公里油耗:
								</th>
								<td>
								[#if deviceStatus.obdifc != "null"]
									${deviceStatus.obdifc}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									里程:
								</th>
								<td>
								[#if deviceStatus.obdmileage != "null"]
									${deviceStatus.obdmileage}
								[#else]
									-
								[/#if]
								</td>
								<th>
									剩余油量:
								</th>
								<td>
								[#if deviceStatus.obdremaininggas != "null"]
									${deviceStatus.obdremaininggas}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									生成obd记录时间:
								</th>
								<td>
									${obddate!'-'}
								</td>
								<th>
									生成故障码记录时间:
								</th>
								<td>
									${obddtcdate!'-'}
								</td>
							</tr>
							<tr>
								<th>
									故障码:
								</th>
								<td>
								[#if deviceStatus.obddtc != "null"]
									${deviceStatus.obddtc}
								[#else]
									-
								[/#if]
								</td>
								<th>
									在线:
								</th>
								<td>
								[#if deviceStatus.online != "null" && deviceStatus.online != ""]
									${deviceStatus.online}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									最后定位时间:
								</th>
								<td>
									${agpstime!'-'}
								</td>
								<th>
									设备内电池电压:
								</th>
								<td>
								[#if deviceStatus.bv != "null"]
									${deviceStatus.bv}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									设备告警:
								</th>
								<td>
								[#if deviceStatus.istatus != "null"]
									${deviceStatus.istatus}
								[#else]
									-
								[/#if]
								</td>
								<th>
									故障码数量:
								</th>
								<td>
								[#if deviceStatus.dtccount != "null"]
									${deviceStatus.dtccount}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									GPS算出的里程:
								</th>
								<td>
								[#if deviceStatus.mileage != "null"]
									${deviceStatus.mileage}
								[#else]
									-
								[/#if]
								</td>
								<th>
									客户设置的报警标志位:
								</th>
								<td>
								[#if deviceStatus.clientalarmset != "null"]
									${deviceStatus.clientalarmset}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									基站纬度:
								</th>
								<td>
								[#if deviceStatus.lbslat != "null"]
									${deviceStatus.lbslat}
								[#else]
									-
								[/#if]
								</td>
								<th>
									基站经度:
								</th>
								<td>
								[#if deviceStatus.lbslon != "null"]
									${deviceStatus.lbslon}
								[#else]
									-
								[/#if]
								</td>
							</tr>
							<tr>
								<th>
									备注:
								</th>
								<td>
								[#if deviceStatus.reserve != "null"]
									${deviceStatus.reserve}
								[#else]
									-
								[/#if]
								</td>
								<th>
									设备告警时间:
								</th>
								<td>
									${istatustime!'-'}
								</td>
							</tr>
							<tr>
								<th>
									急加速急减速时3d传感器xyz值和前后10秒速度:
								</th>
								<td>
								[#if deviceStatus.t3dspeed != "null"]
									${deviceStatus.t3dspeed}
								[#else]
									-
								[/#if]
								</td>
								<th>
									急加速急减速发生时间:
								</th>
								<td>
									${t3dspeedtime!'-'}
								</td>
							</tr>
							<tr>
								<th>
									开门等触发时车身状态:
								</th>
								<td>
								[#if deviceStatus.tstatus != "null"]
									${deviceStatus.tstatus}
								[#else]
									-
								[/#if]
								</td>
								<th>
									开门等触发时间:
								</th>
								<td>
									${tstatustime!'-'}
								</td>
							</tr>
							<tr>
								<th>
									定时上传时3d传感器的xyz值:
								</th>
								<td>
								[#if deviceStatus.xyz3d != "null"]
									${deviceStatus.xyz3d}
								[#else]
									-
								[/#if]
								</td>
								<th>
									上传时3d传感器的xyz值时间:
								</th>
								<td>
									${xyz3dtime!'-'}
								</td>
							</tr>
							<tr>
								<th>
									3d传感器状态:
								</th>
								<td>
								[#if deviceStatus.t3dstatus != "null"]
									${deviceStatus.t3dstatus}
								[#else]
									-
								[/#if]
								</td>
							</tr>																																																																						
							<tr>
								<th colspan="3">
									&nbsp;
								</th>
								<td>
									<input type="button" class="button" value="${message("csh.common.back")}" onclick="history.go(-1)" />
								</td>
							</tr>
						</table>
                  </div>
                </div>
              </div>  
            </div>
          </div>
        </div>
	   </div>
	</div>
	<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
</body>
</html>