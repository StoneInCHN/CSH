package com.csh.controller.intf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.VehicleRequest;
import com.csh.service.AdvanceDepositsService;
import com.csh.service.AppService;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.SystemConfigService;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.LatLonUtil;
import com.csh.utils.TimeUtils;



@Controller("VehicleIntfController")
@RequestMapping("/vehicleIntf")
public class VehicleIntfController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;

  @Resource(name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;

  @Resource(name = "vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;

  @Resource(name = "appServiceImpl")
  private AppService appService;

  @Resource(name = "advanceDepositsServiceImpl")
  private AdvanceDepositsService advanceDepositsService;


  /**
   * 车辆轨迹分段接口
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/vehicleTrack", method = RequestMethod.POST)
  public @ResponseBody ResponseMultiple<Map<String, Object>> vehicleTrack(
      @RequestBody VehicleRequest req, HttpServletRequest request) {
    Date startTime = new Date();
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    String deviceNo = req.getDeviceNo();
    Date searchDate = req.getSearchDate();
    String apiKey = req.getApiKey();
    if (LogUtil.isDebugEnabled(VehicleIntfController.class)) {
      LogUtil.debug(VehicleIntfController.class, "vehicleTrack",
          "Start API vehicleTrack. deviceNo: %s,searchDate: %s,apiKey: %s,Request IP: %s",
          deviceNo, searchDate, apiKey, request.getRemoteAddr());
    }
    if (deviceNo == null || searchDate == null) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.intf.vehicle.param.invalid").getContent());
      return response;
    }
    if (!setting.getIntfApiKey().equals(apiKey)) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.intf.apikey.invalid").getContent());
      return response;
    }
    searchDate = TimeUtils.formatDate2Day(searchDate);
    Date today = TimeUtils.formatDate2Day(new Date());
    if (today.getTime() - searchDate.getTime() < 0
        || (today.getTime() - searchDate.getTime()) / (1000 * 60 * 60 * 24) > 6) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.intf.vehicle.trackDate.range").getContent());
      return response;
    }
    String date = TimeUtils.format("yyyy-MM-dd", searchDate.getTime());

    // String url =
    // setting.getObdServerUrl() + "/appVehicleData/vehicleTrack.jhtml?date=" + date
    // + "&deviceId=" + deviceNo;
    String url =
        setting.getObdServerUrl() + "/appVehicleData/vehicleTrackFragment.jhtml?date=" + date
            + "&deviceId=" + deviceNo;
    String res = ApiUtils.post(url);
    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(res, Map.class);
      List<Map<String, Object>> resultList = (List<Map<String, Object>>) map.get("msg");
      for (Map<String, Object> result : resultList) {
        List<Map<String, Object>> trackMap = (List<Map<String, Object>>) result.get("tracks");
        if (trackMap != null && trackMap.size() > 0) {
          Map<String, Object> startTrack = trackMap.get(0);
          Map<String, Object> endTrack = trackMap.get(trackMap.size() - 1);
          String startAddr =
              LatLonUtil.convertCoorForAddr(startTrack.get("lat").toString(), startTrack.get("lon")
                  .toString());
          String endAddr =
              LatLonUtil.convertCoorForAddr(endTrack.get("lat").toString(), endTrack.get("lon")
                  .toString());
          result.put("startAddr", startAddr);
          result.put("endAddr", endAddr);
        }
      }
      response.setMsg(resultList);
    } catch (Exception e) {
      e.printStackTrace();
    }

    response.setCode(CommonAttributes.SUCCESS);
    if (LogUtil.isDebugEnabled(VehicleIntfController.class)) {
      LogUtil
          .debug(
              VehicleIntfController.class,
              "vehicleTrack",
              "End API vehicleTrack. deviceNo: %s,searchDate: %s,apiKey: %s,Request IP: %s,Used Time：%s ms",
              deviceNo, searchDate, apiKey, request.getRemoteAddr(), new Date().getTime()
                  - startTime.getTime());
    }

    return response;
  }

}
