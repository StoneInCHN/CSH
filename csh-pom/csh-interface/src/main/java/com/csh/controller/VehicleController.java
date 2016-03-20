package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.VehicleRequest;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;
import com.csh.service.VehicleService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;



@Controller("vehicleController")
@RequestMapping("/vehicle")
public class VehicleController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;
  
  @Resource(name = "vehicleLineServiceImpl")
  private VehicleLineService vehicleLineService;
  
  @Resource(name = "vehicleBrandServiceImpl")
  private VehicleBrandService vehicleBrandService;



  /**
   * 车辆列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody ResponseMultiple<Map<String, Object>> list(@RequestBody BaseRequest req) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = req.getUserId();
    String token = req.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser user = endUserService.find(userId);
    String[] properties = {"id", "isDefault", "plate", "vehicleFullBrand"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, user.getVehicles());
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(req.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 添加车辆
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody BaseResponse add(@RequestBody VehicleRequest vehicleReq) {

    BaseResponse response = new BaseResponse();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Vehicle vehicle = new Vehicle();
    VehicleBrandDetail brandDetail = vehicleBrandDetailService.find(vehicleReq.getBrandDetailId());
    vehicle.setVehicleBrandDetail(brandDetail);
    vehicle.setPlate(vehicleReq.getPlateNo());
    vehicle.setVehicleNo(vehicleReq.getVehicleNo());
    vehicle.setTrafficInsuranceExpiration(vehicleReq.getTrafficInsuranceExpiration());
    vehicle.setCommercialInsuranceExpiration(vehicleReq.getCommercialInsuranceExpiration());
    vehicle.setNextAnnualInspection(vehicleReq.getNextAnnualInspection());
    vehicle.setDriveMileage(vehicleReq.getDriveMileage());
    vehicle.setLastMaintainMileage(vehicleReq.getLastMaintainMileage());

    EndUser endUser = endUserService.find(userId);
    if (endUser.getVehicles() == null || endUser.getVehicles().size() <= 0) {
      vehicle.setIsDefault(true);
    }

    vehicleService.save(vehicle);
    if (LogUtil.isDebugEnabled(VehicleController.class)) {
        LogUtil.debug(VehicleController.class, "save",
            "Add vehicle for User with UserName: %s", endUser.getUserName());
      }
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(vehicle.getId().toString());
    return response;
  }

  /**
   * 车辆设备绑定
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/bindDevice", method = RequestMethod.POST)
  public @ResponseBody BaseResponse bindDevice(@RequestBody VehicleRequest vehicleReq) {

    BaseResponse response = new BaseResponse();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    String deviceNo = vehicleReq.getDeviceNo();
    Long vehicleId = vehicleReq.getVehicleId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter deviceNoFilter = new Filter("deviceNo", Operator.eq, deviceNo);
    Filter deviceStatusFilter = new Filter("bindStatus", Operator.eq, BindStatus.UNBINDED);
    filters.add(deviceNoFilter);
    filters.add(deviceStatusFilter);
    List<DeviceInfo> deviceInfos = deviceInfoService.findList(null, filters, null);
    if (deviceInfos.size() != 1) {
      response.setCode(CommonAttributes.FAIL_BIND_DEVICE);
      response.setDesc(Message.error("csh.bind.device.invalid").getContent());
      return response;
    }
    DeviceInfo deviceInfo = deviceInfos.get(0);
    Vehicle vehicle = vehicleService.find(vehicleId);
    vehicle.setDeviceNo(deviceNo);
    deviceInfo.setVehicle(vehicle);
    deviceInfo.setBindTime(new Date());
    deviceInfo.setBindStatus(BindStatus.BINDED);
    vehicle.setDevice(deviceInfo);
    vehicleService.update(vehicle);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
        LogUtil.debug(VehicleController.class, "Update",
            "bind vehicle and device.DeviceNo: %s, VehicleId: %s,", deviceNo,vehicleId);
    }
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }
  
  
  /**
   * 查询车辆品牌，车系，车型
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getVehicleBrand", method = RequestMethod.POST)
  public @ResponseBody ResponseMultiple<Map<String, Object>> getVehicleBrand(@RequestBody VehicleRequest vehicleReq) {

	ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    Long vehicleLineId = vehicleReq.getVehicleLineId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
    
    if (vehicleLineId == null) {
    	List<Filter> filters = new ArrayList<Filter>();
        Filter parentFilter = new Filter("parent", Operator.isNull,null);
        filters.add(parentFilter);
		List<VehicleLine> vehicleLines = vehicleLineService.findList(null, filters, null);
		String[] properties ={"id", "code", "name"};
		map = FieldFilterUtils.filterCollectionMap(properties, vehicleLines);
	}else {
		VehicleLine vehicleLine = vehicleLineService.find(vehicleLineId);
		if (vehicleLine.getParent()==null) {//子车系（只有2级树形结构）
			String[] properties ={"id", "code", "name"};
			map = FieldFilterUtils.filterCollectionMap(properties, vehicleLine.getChildren());
		}else {//车型
			List<Filter> filters = new ArrayList<Filter>();
	        Filter filter = new Filter("vehicleLine", Operator.eq,vehicleLine);
	        filters.add(filter);
	        List<VehicleBrandDetail> vehicleBrandDetails = vehicleBrandDetailService.findList(null, filters, null);
	        String[] properties ={"id", "code", "name"};
			map = FieldFilterUtils.filterCollectionMap(properties, vehicleBrandDetails);
		}
	}
    
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }
}
