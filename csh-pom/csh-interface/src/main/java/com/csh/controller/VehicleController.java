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

import com.csh.aspect.UserValidCheck;
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
import com.csh.utils.VehicleUtil;



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
  @UserValidCheck
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
    String[] properties =
        {"id", "isDefault", "plate", "vehicleFullBrand", "brandIcon", "deviceNo", "vehicleNo",
            "trafficInsuranceExpiration", "commercialInsuranceExpiration", "nextAnnualInspection",
            "driveMileage", "lastMaintainMileage"};
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
  @UserValidCheck
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
    vehicle.setBrandIcon(brandDetail.getVehicleLine().getParent().getIcon());
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
    } else {
      vehicle.setIsDefault(false);
    }
    vehicle.setEndUser(endUser);
    vehicleService.save(vehicle);
    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "save", "Add vehicle for User with UserName: %s",
          endUser.getUserName());
    }
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(vehicle.getId().toString());
    return response;
  }


  /**
   * 车辆编辑
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse edit(@RequestBody VehicleRequest vehicleReq) {

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

    Vehicle vehicle = vehicleService.find(vehicleReq.getVehicleId());
    if (vehicleReq.getBrandDetailId() != null) {
      VehicleBrandDetail brandDetail =
          vehicleBrandDetailService.find(vehicleReq.getBrandDetailId());
      vehicle.setVehicleBrandDetail(brandDetail);
      vehicle.setBrandIcon(brandDetail.getVehicleLine().getParent().getIcon());
    }
    vehicle.setPlate(vehicleReq.getPlateNo());
    vehicle.setVehicleNo(vehicleReq.getVehicleNo());
    vehicle.setTrafficInsuranceExpiration(vehicleReq.getTrafficInsuranceExpiration());
    vehicle.setCommercialInsuranceExpiration(vehicleReq.getCommercialInsuranceExpiration());
    vehicle.setNextAnnualInspection(vehicleReq.getNextAnnualInspection());
    vehicle.setDriveMileage(vehicleReq.getDriveMileage());
    vehicle.setLastMaintainMileage(vehicleReq.getLastMaintainMileage());

    vehicleService.update(vehicle);
    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "Update", "Update vehicle Info. UserId: %s", userId);
    }

    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(vehicle.getId().toString());
    return response;
  }


  /**
   * 手机扫描商家二维码时用户车辆与商家绑定
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/bindTenant", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse bindTenant(@RequestBody VehicleRequest vehicleReq) {

    BaseResponse response = new BaseResponse();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    // String deviceNo = vehicleReq.getDeviceNo();
    Long vehicleId = vehicleReq.getVehicleId();
    Long tenantId = vehicleReq.getTenantId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Vehicle vehicle = vehicleService.find(vehicleId);
    vehicle.setTenantID(tenantId);
    vehicleService.update(vehicle);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "Update",
          "bind vehicle and tenant.TenantID: %s, VehicleId: %s,", tenantId, vehicleId);
    }
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }


  /**
   * 车辆与设备绑定
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/bindDevice", method = RequestMethod.POST)
  @UserValidCheck
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
    vehicle.setTenantID(deviceInfo.getTenantID());
    vehicleService.update(vehicle);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "Update",
          "bind vehicle and device.DeviceNo: %s, VehicleId: %s,", deviceNo, vehicleId);
    }
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }


  /**
   * 设置默认车辆
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/setDefault", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> setDefault(
      @RequestBody VehicleRequest vehicleReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    Long vehicleId = vehicleReq.getVehicleId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    for (Vehicle vehicle : endUser.getVehicles()) {
      if (vehicle.getId().equals(vehicleId)) {
        vehicle.setIsDefault(true);
      } else {
        vehicle.setIsDefault(false);
      }
    }
    endUserService.update(endUser);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "Update",
          "set default vehicle.UserId: %s, VehicleId: %s,", userId, vehicleId);
    }
    String[] properties = {"id", "isDefault", "plate", "vehicleFullBrand", "brandIcon"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, endUser.getVehicles());
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }

  /**
   * 查询车辆一级车系
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getVehicleBrandByCode", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<List<Map<String, Object>>> getVehicleBrandByCode(
      @RequestBody VehicleRequest vehicleReq) {

    ResponseMultiple<List<Map<String, Object>>> response =
        new ResponseMultiple<List<Map<String, Object>>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<List<Map<String, Object>>> maps = new ArrayList<List<Map<String, Object>>>();
    List<Filter> filters = new ArrayList<Filter>();
    Filter parentFilter = new Filter("parent", Operator.isNull, null);
    filters.add(parentFilter);
    List<VehicleLine> vehicleLines = vehicleLineService.findList(null, filters, null);
    String[] properties = {"id", "code", "name", "icon"};
    List<Map<String, Object>> map = FieldFilterUtils.filterCollectionMap(properties, vehicleLines);
    maps = VehicleUtil.getVehicleLineByCode(map);
    response.setMsg(maps);

    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }

  /**
   * 查询车辆二级车系，车型
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getVehicleBrandById", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getVehicleBrand(
      @RequestBody VehicleRequest vehicleReq) {

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



    List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
    VehicleLine vehicleLine = vehicleLineService.find(vehicleLineId);
    if (vehicleLine.getParent() == null) {// 子车系（只有2级树形结构）
      String[] properties = {"id", "code", "name", "icon"};
      map = FieldFilterUtils.filterCollectionMap(properties, vehicleLine.getChildren());
    } else {// 车型
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("vehicleLine", Operator.eq, vehicleLine);
      filters.add(filter);
      List<VehicleBrandDetail> vehicleBrandDetails =
          vehicleBrandDetailService.findList(null, filters, null);
      String[] properties = {"id", "name"};
      map = FieldFilterUtils.filterCollectionMap(properties, vehicleBrandDetails);
    }
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }
}
