package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.controller.base.BaseController;
import com.csh.entity.Vehicle;
import com.csh.framework.paging.Pageable;
import com.csh.json.response.VehicleTrack;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.DateTimeUtils;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.LatLonUtil;
import com.csh.utils.SettingUtils;

/**
 * Controller - 车辆轨迹
 * 
 * @author yohu
 *
 */
@Controller("vehicleTrackController")
@RequestMapping("console/vehicleTrack")
public class VehicleTrackController extends BaseController {


  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/track", method = RequestMethod.GET)
  public String page(ModelMap model) {
    return "/vehicle/track";
  }


  @RequestMapping(value = "/drawVehicleTrack", method = RequestMethod.POST)
  @SuppressWarnings("unchecked")
  public @ResponseBody List<Map<String, Object>> singleVehicleTrack(Model model, Long vehicleID,
      Date searchDate) {
    if (vehicleID == null || searchDate == null) {
      return null;
    }
    Vehicle vehicle = vehicleService.find(vehicleID);
    String deviceNo = vehicle.getDeviceNo();
    String date = DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, searchDate);
    Setting set = SettingUtils.get();
    String url =
        set.getObdServiceUrl() + "/appVehicleData/vehicleTrack.jhtml?date=" + date + "&deviceId="
            + deviceNo;
    // String url =
    // "http://139.129.5.114:20001/obd-data/appVehicleData/vehicleTrack.jhtml?deviceId=8856017290&date=2016-07-11";
    String res = ApiUtils.post(url);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(res, Map.class);
      List<Map<String, Object>> maps = (List<Map<String, Object>>) map.get("msg");
      return LatLonUtil.convertCoordinates(maps);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  @RequestMapping(value = "/drawVehicleTrackMultiple", method = RequestMethod.POST)
  @SuppressWarnings("unchecked")
  public @ResponseBody List<Map<String, Object>> multipleVehicleTrack(Model model, Long vehicleID,
      Date searchDate) {
    if (vehicleID == null || searchDate == null) {
      return null;
    }
    List<Map<String, Object>> responseTrackList = new ArrayList<Map<String, Object>>();
    Vehicle vehicle = vehicleService.find(vehicleID);
    String deviceNo = vehicle.getDeviceNo();
    String date = DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, searchDate);
    Setting set = SettingUtils.get();
    String url =
        set.getObdServiceUrl() + "/appVehicleData/vehicleTrackFragment.jhtml?date=" + date
            + "&deviceId=" + deviceNo;
    // String url =
    // "http://139.129.5.114:20001/obd-data/appVehicleData/vehicleTrack.jhtml?deviceId=8856017290&date=2016-07-11";
    String res = ApiUtils.post(url);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(res, Map.class);
      List<Map<String, Object>> trackMapList = (List<Map<String, Object>>) map.get("msg");
      String value = mapper.writeValueAsString(trackMapList);


      List<VehicleTrack> trackList =
          mapper.readValue(value,
              mapper.getTypeFactory().constructParametricType(List.class, VehicleTrack.class));
      for (VehicleTrack vehicleTrack : trackList) {
        if (vehicleTrack.getTracks() != null && vehicleTrack.getTracks().size() > 0) {
        	Map<String, Object> startPoint = LatLonUtil.convertCoordinate(vehicleTrack.getTracks()
					.get(0));
			Map<String, Object> endPoint = LatLonUtil.convertCoordinate(vehicleTrack.getTracks()
					.get(vehicleTrack.getTracks().size() - 1));
          String startAddr =
              LatLonUtil.convertCoorForAddr(startPoint.get("lat").toString(), startPoint.get("lon")
                  .toString());
          String endAddr =
              LatLonUtil.convertCoorForAddr(endPoint.get("lat").toString(), endPoint.get("lon")
                  .toString());
          vehicleTrack.setStartAddr(startAddr);
          vehicleTrack.setEndAddr(endAddr);

          /*
           * gps坐标转为百度坐标
           */
          List<Map<String, Object>> convertMaps =
              LatLonUtil.convertCoordinates(vehicleTrack.getTracks());
          vehicleTrack.setTracks(convertMaps);

          vehicleTrack.setPlate(vehicle.getPlate());
        }

      }
      String[] properties =
          {"from", "to", "plate", "mileage", "runTime", "tracks", "endAddr", "startAddr"};
      responseTrackList = FieldFilterUtils.filterCollectionMap(properties, trackList);
      return responseTrackList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }
	/**
	 * 导出列表数据，即用户已经查询出来的数据
	 * 
	 * @param withDays
	 */
  		@RequestMapping(value = "/exportData", method = { RequestMethod.GET,
			RequestMethod.POST })
		public void exportData(HttpServletResponse response, Pageable pageable,
			ModelMap model, Date beginDate, Date endDate, Long vehicleID) {

		if (vehicleID == null || (beginDate == null && endDate == null)) {
//			return Message.error("csh.common.missingparam");
			return;
		}

		Vehicle vehicle = vehicleService.find(vehicleID);
		String deviceNo = vehicle.getDeviceNo();
		String dateStart = DateTimeUtils.getSimpleFormatString(
				DateTimeUtils.filePostfixFormat, beginDate);
		String dateEnd = DateTimeUtils.getSimpleFormatString(
				DateTimeUtils.filePostfixFormat, endDate);
		Setting set = SettingUtils.get();
//		String url = set.getObdServiceUrl()
//				+ "/appVehicleData/vehicleTrackFragment.jhtml?date="
//				+ dateStart + "&deviceId=" + deviceNo;
		String url = "http://139.129.5.114:20001/obd-data/appVehicleData/vehicleTrackFragmentByTime.jhtml?deviceId="+deviceNo
				+"&startDate="+dateStart+"&endDate="+dateEnd+"";
		// String url =
		// "http://139.129.5.114:20001/obd-data/appVehicleData/vehicleTrack.jhtml?deviceId=8856017290&date=2016-07-11";
		String res = ApiUtils.post(url);
		try {

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(res, Map.class);
			List<Map<String, Object>> trackMapList = (List<Map<String, Object>>) map
					.get("msg");
			String value = mapper.writeValueAsString(trackMapList);

			List<VehicleTrack> trackList = mapper.readValue(
					value,
					mapper.getTypeFactory().constructParametricType(List.class,
							VehicleTrack.class));
			for (VehicleTrack vehicleTrack : trackList) {
				if (vehicleTrack.getTracks() != null
						&& vehicleTrack.getTracks().size() > 0) {
					Map<String, Object> startPoint = LatLonUtil.convertCoordinate(vehicleTrack.getTracks()
							.get(0));
					Map<String, Object> endPoint = LatLonUtil.convertCoordinate(vehicleTrack.getTracks()
							.get(vehicleTrack.getTracks().size() - 1));
					
					String startAddr = LatLonUtil.convertCoorForAddr(startPoint
							.get("lat").toString(), startPoint.get("lon")
							.toString());
					String endAddr = LatLonUtil.convertCoorForAddr(endPoint
							.get("lat").toString(), endPoint.get("lon")
							.toString());
					vehicleTrack.setStartAddr(startAddr);
					vehicleTrack.setEndAddr(endAddr);

					/*
					 * gps坐标转为百度坐标
					 */
//					List<Map<String, Object>> convertMaps = LatLonUtil
//							.convertCoordinates(vehicleTrack.getTracks());
//					vehicleTrack.setTracks(convertMaps);

					vehicleTrack.setPlate(vehicle.getPlate());
				}

			}
			String title = "车辆轨迹"; // 工作簿标题，同时也是excel文件名前缀
			String[] headers = { "from", "to", "plate", "mileage", "runTime",
					"startAddr", "endAddr" }; // 需要导出的字段
			String[] headersName = { "启动时间", "熄火时间", "车牌号", "里程", "运行时间",
					"起点", "终点" }; // 字段对应列的列名
			List<Map<String, String>> resultMapList = prepareMap(trackList);
			if (resultMapList.size() > 0) {
				exportListToExcel(response, resultMapList, title, headers,
						headersName);
//				return Message.success("csh.common.exportFinish");
			}else {
//				return Message.error("csh.common.missingparam");
			}
		} catch (Exception e) {
		}
//		 return ERROR_MESSAGE;
	}
  
  /**
   * 准备excel需要的map数据
   * 
   * @param vehicleList
   * @return
   */
  private List<Map<String, String>> prepareMap(List<VehicleTrack> trackList) {
    List<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();
    for (VehicleTrack vehicleTrack : trackList) {
      Map<String, String> trackMap = new HashMap<String, String>();
      trackMap.put("from", DateTimeUtils.convertDateToString(vehicleTrack.getFrom(), "yyyy-MM-dd HH:mm:ss"));
      trackMap.put("to", DateTimeUtils.convertDateToString(vehicleTrack.getTo(), "yyyy-MM-dd HH:mm:ss"));
      trackMap.put("plate", vehicleTrack.getPlate());
      trackMap.put("mileage", vehicleTrack.getMileage().toString());
      trackMap.put("runTime", DateTimeUtils.formatLongDateTimeString(vehicleTrack.getRunTime()));
      trackMap.put("startAddr", vehicleTrack.getStartAddr());
      trackMap.put("endAddr", vehicleTrack.getEndAddr());
      resultMapList.add(trackMap);
    }
    return resultMapList;
  }
}
