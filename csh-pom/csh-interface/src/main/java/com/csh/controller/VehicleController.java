package com.csh.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.csh.entity.AdvanceDeposits;
import com.csh.entity.App;
import com.csh.entity.DeviceInfo;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.entity.commonenum.CommonEnum.AdvanceUsageType;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.VehicleRequest;
import com.csh.service.AdvanceDepositsService;
import com.csh.service.AppService;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.SystemConfigService;
import com.csh.service.TenantInfoService;
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

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;


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

    EndUser endUser = endUserService.find(userId);
    String[] properties =
        {"id", "isDefault", "plate", "vehicleFullBrand", "brandIcon", "deviceNo", "vehicleNo",
            "trafficInsuranceExpiration", "nextAnnualInspection", "driveMileage",
            "lastMaintainMileage"};
    List<Vehicle> vehicles = new ArrayList<Vehicle>();

    /**
     * 外部接口调用时会有车牌号为000000的虚拟车辆，显示时要过滤掉
     */
    for (Vehicle vehicle : endUser.getVehicles()) {
      if (!"0000000".equals(vehicle.getPlate())) {
        vehicles.add(vehicle);
      }
    }
    List<Map<String, Object>> map = FieldFilterUtils.filterCollectionMap(properties, vehicles);
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
  public @ResponseBody ResponseOne<Map<String, Object>> add(@RequestBody VehicleRequest vehicleReq) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Vehicle plateVehicle = vehicleService.getVehicleByPlate(vehicleReq.getPlateNo());
    if (plateVehicle != null) {
      response.setCode(CommonAttributes.FAIL_VEHICLE_PLATE_EXIST);
      response.setDesc(Message.error("csh.vehicle.plate.exist").getContent());
      return response;
    }

    Vehicle vehicleNoVehicle = vehicleService.getVehicleByVehicleNo(vehicleReq.getVehicleNo());
    if (vehicleNoVehicle != null) {
      response.setCode(CommonAttributes.FAIL_VEHICLE_PLATE_EXIST);
      response.setDesc(Message.error("csh.vehicle.num.exist").getContent());
      return response;
    }
    Vehicle vehicle = new Vehicle();
    VehicleBrandDetail brandDetail = vehicleBrandDetailService.find(vehicleReq.getBrandDetailId());
    vehicle.setVehicleBrandDetail(brandDetail);
    vehicle.setBrandIcon(brandDetail.getVehicleLine().getIcon());
    vehicle.setPlate(vehicleReq.getPlateNo());
    vehicle.setVehicleNo(vehicleReq.getVehicleNo());
    vehicle.setTrafficInsuranceExpiration(vehicleReq.getTrafficInsuranceExpiration());
    // vehicle.setCommercialInsuranceExpiration(vehicleReq.getCommercialInsuranceExpiration());
    vehicle.setNextAnnualInspection(vehicleReq.getNextAnnualInspection());
    vehicle.setDriveMileage(vehicleReq.getDriveMileage());
    vehicle.setLastMaintainMileage(vehicleReq.getLastMaintainMileage());

    EndUser endUser = endUserService.find(userId);
    // if (endUser.getVehicles() == null || endUser.getVehicles().size() <= 0) {
    // vehicle.setIsDefault(true);
    // } else {
    // if (vehicleReq.getIsDefault()) {
    // for (Vehicle vehi : endUser.getVehicles()) {
    // vehi.setIsDefault(false);
    //
    // }
    // } else {
    // vehicle.setIsDefault(false);
    // }
    // }
    // vehicle.setEndUser(endUser);
    // if (LogUtil.isDebugEnabled(VehicleController.class)) {
    // LogUtil.debug(VehicleController.class, "AddVehicle",
    // "Add vehicle for User with UserName: %s,VehiclePlate", endUser.getUserName(),
    // vehicleReq.getPlateNo());
    // }
    // vehicleService.save(vehicle);
    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "AddVehicle",
          "Add vehicle for User with UserName: %s,VehiclePlate: %s,isDefault: %s",
          endUser.getUserName(), vehicleReq.getPlateNo(), vehicleReq.getIsDefault());
    }
    Vehicle addVehicle = vehicleService.addVehicle(vehicle, endUser, vehicleReq.getIsDefault());
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("vehicleId", addVehicle.getId());
    response.setMsg(map);

    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
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
  public @ResponseBody ResponseOne<Map<String, Object>> edit(@RequestBody VehicleRequest vehicleReq) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
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
    if (!vehicle.getPlate().equals(vehicleReq.getPlateNo())) {
      Vehicle plateVehicle = vehicleService.getVehicleByPlate(vehicleReq.getPlateNo());
      if (plateVehicle != null) {
        response.setCode(CommonAttributes.FAIL_VEHICLE_PLATE_EXIST);
        response.setDesc(Message.error("csh.vehicle.plate.exist").getContent());
        return response;
      }
    }

    if (!vehicle.getVehicleNo().equals(vehicleReq.getVehicleNo())) {
      Vehicle vehicleNoVehicle = vehicleService.getVehicleByVehicleNo(vehicleReq.getVehicleNo());
      if (vehicleNoVehicle != null) {
        response.setCode(CommonAttributes.FAIL_VEHICLE_PLATE_EXIST);
        response.setDesc(Message.error("csh.vehicle.num.exist").getContent());
        return response;
      }
    }

    if (vehicleReq.getBrandDetailId() != null) {
      VehicleBrandDetail brandDetail =
          vehicleBrandDetailService.find(vehicleReq.getBrandDetailId());
      vehicle.setVehicleBrandDetail(brandDetail);
      vehicle.setBrandIcon(brandDetail.getVehicleLine().getIcon());
    }
    vehicle.setPlate(vehicleReq.getPlateNo());
    vehicle.setVehicleNo(vehicleReq.getVehicleNo());
    vehicle.setTrafficInsuranceExpiration(vehicleReq.getTrafficInsuranceExpiration());
    // vehicle.setCommercialInsuranceExpiration(vehicleReq.getCommercialInsuranceExpiration());
    vehicle.setNextAnnualInspection(vehicleReq.getNextAnnualInspection());
    vehicle.setDriveMileage(vehicleReq.getDriveMileage());
    vehicle.setLastMaintainMileage(vehicleReq.getLastMaintainMileage());

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "editVehicle",
          "Update vehicle Info. UserId: %s,VehicleId: %s,isDefault: %s", userId,
          vehicleReq.getVehicleId(), vehicleReq.getIsDefault());
    }
    EndUser endUser = endUserService.find(userId);
    Vehicle updateVehicle =
        vehicleService.updateVehicle(vehicle, endUser, vehicleReq.getIsDefault());
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("vehicleId", updateVehicle.getId());
    map.put("appTitle", "default");
    if (updateVehicle.getIsDefault()) {
      App app = appService.getTenantAppById(updateVehicle.getTenantID());
      if (app != null) {
        map.put("appTitle", app.getAppTitleName());
      }
    }

    response.setMsg(map);

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
  public @ResponseBody ResponseOne<Map<String, Object>> bindTenant(
      @RequestBody VehicleRequest vehicleReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();
    // String deviceNo = vehicleReq.getDeviceNo();
    Long vehicleId = vehicleReq.getVehicleId();
    Long tenantId = vehicleReq.getTenantId();
    String orgCode = vehicleReq.getOrgCode();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Vehicle vehicle = vehicleService.find(vehicleId);
    if (vehicle.getDevice() != null
        || (vehicle.getTenantID() != null && advanceDepositsService.isAlreadyPurDevice(tenantId,
            userId))) {
      response.setCode(CommonAttributes.FAIL_VEHICLE_BIND_TENANT);
      response.setDesc(Message.error("csh.vehicle.bind.tenant").getContent());
      return response;
    }
    /**
     * 外部接口调用时不传tenantId,传orgCode ======Start
     */
    if (orgCode != null && tenantId == null) {
      TenantInfo tenantInfo = tenantInfoService.findTenantWithOrgCode(orgCode);
      tenantId = tenantInfo.getId();
    }
    /**
     * =====end
     */
    vehicle.setTenantID(tenantId);
    Vehicle v = vehicleService.bindTenant(vehicle);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "bindTenant",
          "bind vehicle and tenant.TenantID: %s, VehicleId: %s,", tenantId, vehicleId);
    }
    App app = appService.getTenantAppById(tenantId);
    String[] properties = {"appTitleName"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, app);
    map.put("isGetCoupon", v.getIsGetCoupon());
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }

  /**
   * 用户获取当前可以绑定的设备列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getAvailableDevice", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getAvailableDevice(
      @RequestBody VehicleRequest vehicleReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter endUserFilter = new Filter("endUser", Operator.eq, endUser);
    Filter typeFilter = new Filter("usageType", Operator.eq, AdvanceUsageType.DEVICE);
    Filter bindFilter = new Filter("isBind", Operator.eq, false);
    filters.add(bindFilter);
    filters.add(typeFilter);
    filters.add(endUserFilter);
    List<AdvanceDeposits> advanceDeposits =
        advanceDepositsService.findList(null, null, filters, null);

    String[] properties = {"deviceNo", "tenantName"};
    List<Map<String, Object>> maps =
        FieldFilterUtils.filterCollectionMap(properties, advanceDeposits);
    response.setMsg(maps);
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
  public @ResponseBody ResponseOne<Map<String, Object>> bindDevice(
      @RequestBody VehicleRequest vehicleReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
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

    Vehicle vehicle = vehicleService.find(vehicleId);
    if (vehicle.getTenantID() != null && vehicle.getDevice() != null) {
      response.setCode(CommonAttributes.FAIL_VEHICLE_BIND_TENANT);
      response.setDesc(Message.error("csh.vehicle.binded").getContent());
      return response;
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter deviceNoFilter = new Filter("deviceNo", Operator.eq, deviceNo);
    Filter bindStatusFilter = new Filter("bindStatus", Operator.eq, BindStatus.UNBINDED);
    Filter deviceStatusFilter = new Filter("deviceStatus", Operator.eq, DeviceStatus.SALEOUT);
    filters.add(deviceNoFilter);
    filters.add(bindStatusFilter);
    filters.add(deviceStatusFilter);
    List<DeviceInfo> deviceInfos = deviceInfoService.findList(null, filters, null);
    if (deviceInfos.size() != 1) {
      response.setCode(CommonAttributes.FAIL_BIND_DEVICE);
      response.setDesc(Message.error("csh.bind.device.invalid").getContent());
      return response;
    }

    // SystemConfig devicePrice =
    // systemConfigService.getConfigByKey(SystemConfigKey.DEVICE_PRICE, null);
    // if (devicePrice == null || devicePrice.getConfigValue() == null) {
    // response.setCode(CommonAttributes.FAIL_COMMON);
    // response.setDesc(Message.error("csh.wallet.config.error").getContent());
    // return response;
    // }
    // BigDecimal bindPrice = new BigDecimal(devicePrice.getConfigValue());

    DeviceInfo deviceInfo = deviceInfos.get(0);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "bindDevice",
          "bind vehicle and device.DeviceNo: %s, VehicleId: %s,", deviceNo, vehicleId);
    }
    vehicleService.bindDevice(vehicle, deviceInfo);

    App app = appService.getTenantAppById(deviceInfo.getTenantID());
    String[] properties = {"appTitleName"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, app);
    map.put("isGetCoupon", vehicle.getIsGetCoupon());
    response.setMsg(map);
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
    Long tenantId = null;
    for (Vehicle vehicle : endUser.getVehicles()) {
      if (vehicle.getId().equals(vehicleId)) {
        vehicle.setIsDefault(true);
        tenantId = vehicle.getTenantID();
      } else {
        vehicle.setIsDefault(false);
      }
    }
    endUserService.update(endUser);

    if (LogUtil.isDebugEnabled(VehicleController.class)) {
      LogUtil.debug(VehicleController.class, "Update",
          "set default vehicle.UserId: %s, VehicleId: %s,", userId, vehicleId);
    }
    String[] properties = {"id", "isDefault", "plate", "vehicleFullBrand", "brandIcon", "deviceNo"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, endUser.getVehicles());
    response.setMsg(map);

    response.setMsg(map);

    App app = appService.getTenantAppById(tenantId);
    if (app != null) {
      response.setDesc(app.getAppTitleName());
    }
    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }


  /**
   * 查询车辆品牌
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

    List<VehicleBrand> vehicleLines = vehicleBrandService.findList(null, null, null);
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
   * 根据品牌查询车辆车系
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getVehicleLineByBrand", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getVehicleLineByBrand(
      @RequestBody VehicleRequest vehicleReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = vehicleReq.getUserId();
    String token = vehicleReq.getToken();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    VehicleBrand vehicleBrand = vehicleBrandService.find(vehicleReq.getBrandId());
    List<Filter> filters = new ArrayList<Filter>();
    Filter brandFilter = new Filter("vehicleBrand", Operator.eq, vehicleBrand);
    Filter parentFilter = new Filter("parent", Operator.isNull, null);
    filters.add(brandFilter);
    filters.add(parentFilter);
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("code", Direction.asc);
    orderings.add(ordering);
    List<VehicleLine> vehicleLines = vehicleLineService.findList(null, filters, orderings);
    String[] properties = {"id", "name"};
    String[] childProperties = {"id", "name", "icon"};

    List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
    for (VehicleLine v : vehicleLines) {
      Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, v);
      List<Map<String, Object>> childMap =
          FieldFilterUtils.filterCollectionMap(childProperties, v.getChildren());
      map.put("childLine", childMap);
      maps.add(map);
    }
    response.setMsg(maps);

    String newtoken = TokenGenerator.generateToken(vehicleReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;
  }

  /**
   * 查询车辆车型
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getVehicleBrandDetailByLine", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getVehicleBrandDetailById(
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
