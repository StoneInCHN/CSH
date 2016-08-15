package com.csh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.csh.entity.App;
import com.csh.entity.CarService;
import com.csh.entity.EndUser;
import com.csh.entity.TenantInfo;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.ServiceStatus;
import com.csh.entity.commonenum.CommonEnum.SortType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.TenantInfoRequest;
import com.csh.service.AppService;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.service.ItemPartService;
import com.csh.service.TenantInfoJdbcService;
import com.csh.service.TenantInfoService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;



@Controller("tenantInfoController")
@RequestMapping("/tenantInfo")
public class TenantInfoController extends MobileBaseController {

  @Resource(name = "tenantInfoJdbcServiceImpl")
  private TenantInfoJdbcService tenantInfoJdbcService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  @Resource(name = "appServiceImpl")
  private AppService appService;

  @Resource(name = "carServiceServiceImpl")
  private CarServiceService carServiceService;

  @Resource(name = "itemPartServiceImpl")
  private ItemPartService itemPartService;


  /**
   * 首页改版前租户列表接口（由于新接口返回值数据结构发生变化，为保证老版本用户不报错保留此接口）
   * 
   * @param req
   * @return
   */
  private ResponseMultiple<Map<String, Object>> oldVersion_list(TenantInfoRequest tenantInfoReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = tenantInfoReq.getUserId();
    String token = tenantInfoReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }
    Integer radius = setting.getSearchRadius();
    String latitude = tenantInfoReq.getLatitude();// 纬度
    String longitude = tenantInfoReq.getLongitude();// 经度
    Long serviceCategoryId = tenantInfoReq.getServiceCategoryId();

    Long tenantId = null;
    EndUser endUser = endUserService.find(userId);
    if (endUser.getDefaultVehicle() != null && tenantInfoReq.getPageNumber() == 1) {
      tenantId = endUser.getDefaultVehicle().getTenantID();
    }

    if (LogUtil.isDebugEnabled(TenantInfoController.class)) {
      LogUtil
          .debug(
              TenantInfoController.class,
              "getTenantList",
              "search tenant for User with UserName: %s,UserId: %s,Longitude: %s,Latitude: %s,radius: %s, serviceCategoryId: %s",
              endUser.getUserName(), endUser.getId(), longitude, latitude, radius,
              serviceCategoryId);
    }
    Pageable pageable = new Pageable(tenantInfoReq.getPageNumber(), tenantInfoReq.getPageSize());
    Page<Map<String, Object>> tenantPage =
        tenantInfoJdbcService.getTenantInfos(longitude, latitude, pageable, radius,
            serviceCategoryId, tenantId, null);
    if (tenantId != null) {
      TenantInfo bindTenant = tenantInfoService.find(tenantId);

      if (bindTenant != null && AccountStatus.ACTIVED.equals(bindTenant.getAccountStatus())) {
        Boolean flag = false;
        for (CarService service : bindTenant.getCarServices()) {
          if (service.getServiceCategory().getId().equals(serviceCategoryId)
              && ServiceStatus.ENABLED.equals(service.getServiceStatus())) {
            flag = true;
            break;
          }
        }
        if (flag) {
          String[] properties =
              {"id", "contactPhone", "latitude", "longitude", "address", "tenantName", "photo",
                  "praiseRate", "distance"};
          Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, bindTenant);
          tenantPage.getContent().add(0, map);
        }

      }
    }
    for (Map<String, Object> map : tenantPage.getContent()) {
      TenantInfo tenantInfo = tenantInfoService.find(Long.parseLong(map.get("id").toString()));
      List<CarService> services =
          carServiceService.getServicesByTenantAndCategory(tenantInfo, serviceCategoryId);
      List<Map<String, Object>> serviceList = new ArrayList<Map<String, Object>>();
      for (CarService carService : services) {
        Map<String, Object> serviceMap = new HashMap<>();
        serviceMap.put("service_id", carService.getId());
        serviceMap.put("serviceName", carService.getServiceName());
        serviceMap.put("price", carService.getPrice());
        serviceMap.put("promotion_price", carService.getPromotionPrice());
        serviceMap.put("categoryId", carService.getServiceCategory().getId());
        serviceList.add(serviceMap);
      }

      map.put("carService", serviceList);
    }
    PageResponse page = new PageResponse();
    page.setPageNumber(tenantInfoReq.getPageNumber());
    page.setPageSize(tenantInfoReq.getPageSize());
    page.setTotal((int) tenantPage.getTotal());
    response.setPage(page);
    response.setMsg(tenantPage.getContent());

    EndUser user = endUserService.find(userId);
    if (user.getDefaultVehicle() != null) {
      App app = appService.getTenantAppById(user.getDefaultVehicle().getTenantID());
      if (app != null) {
        response.setDesc(app.getAppTitleName());
      }
    }

    String newtoken = TokenGenerator.generateToken(tenantInfoReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 租户列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> list(
      @RequestBody TenantInfoRequest tenantInfoReq) {
    /**
     * 老版本用户
     */
    if (tenantInfoReq.getSortType() == null) {
      return oldVersion_list(tenantInfoReq);
    }
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = tenantInfoReq.getUserId();
    String token = tenantInfoReq.getToken();
    Integer radius = setting.getSearchRadius();
    String latitude = tenantInfoReq.getLatitude();// 纬度
    String longitude = tenantInfoReq.getLongitude();// 经度
    Long serviceCategoryId = tenantInfoReq.getServiceCategoryId();
    SortType sortType = tenantInfoReq.getSortType();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Long tenantId = null;
    EndUser endUser = endUserService.find(userId);
    if (endUser.getDefaultVehicle() != null && tenantInfoReq.getPageNumber() == 1) {
      tenantId = endUser.getDefaultVehicle().getTenantID();
    }

    if (LogUtil.isDebugEnabled(TenantInfoController.class)) {
      LogUtil
          .debug(
              TenantInfoController.class,
              "getTenantList",
              "search tenant for User with UserName: %s,UserId: %s,Longitude: %s,Latitude: %s, SortType: %s, radius: %s, serviceCategoryId: %s",
              endUser.getUserName(), endUser.getId(), longitude, latitude, sortType, radius,
              serviceCategoryId);
    }
    Pageable pageable = new Pageable(tenantInfoReq.getPageNumber(), tenantInfoReq.getPageSize());
    Page<Map<String, Object>> tenantPage =
        tenantInfoJdbcService.getTenantInfos(longitude, latitude, pageable, radius,
            serviceCategoryId, tenantId, sortType);
    if (tenantId != null) {
      TenantInfo bindTenant = tenantInfoService.find(tenantId);

      if (bindTenant != null && AccountStatus.ACTIVED.equals(bindTenant.getAccountStatus())) {
        Boolean flag = false;
        for (CarService service : bindTenant.getCarServices()) {
          if (service.getServiceCategory().getId().equals(serviceCategoryId)
              && ServiceStatus.ENABLED.equals(service.getServiceStatus())) {
            flag = true;
            break;
          }
        }
        if (flag) {
          String[] properties =
              {"id", "contactPhone", "latitude", "longitude", "address", "tenantName", "photo",
                  "praiseRate", "distance", "rateCounts"};
          Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, bindTenant);
          tenantPage.getContent().add(0, map);
        }

      }
    }

    /**
     * 洗车服务显示服务及价格
     */
    if (serviceCategoryId.equals(setting.getServiceCateWash())) {
      for (Map<String, Object> map : tenantPage.getContent()) {
        TenantInfo tenantInfo = tenantInfoService.find(Long.parseLong(map.get("id").toString()));
        List<CarService> services =
            carServiceService.getServicesByTenantAndCategory(tenantInfo, serviceCategoryId);
        CarService lowPriceService = null;
        for (CarService carService : services) {
          if (lowPriceService == null) {
            lowPriceService = carService;
            continue;
          }
          if (carService.getPromotionPrice().compareTo(lowPriceService.getPromotionPrice()) < 0) {
            lowPriceService = carService;
          }
        }

        Map<String, Object> serviceMap = new HashMap<>();
        serviceMap.put("service_id", lowPriceService.getId());
        serviceMap.put("serviceName", lowPriceService.getServiceName());
        serviceMap.put("price", lowPriceService.getPrice());
        serviceMap.put("promotion_price", lowPriceService.getPromotionPrice());
        // serviceMap.put("categoryId", lowPriceService.getServiceCategory().getId());
        map.put("carService", serviceMap);
      }

      if (SortType.PRICEASC.equals(sortType)) {
        List<Map<String, Object>> serviceList = new ArrayList<Map<String, Object>>();
        Map<String, Object> lowPriceMap = new HashMap<String, Object>();
        for (Map<String, Object> map : tenantPage.getContent()) {
          if (serviceList.size() == 0) {
            lowPriceMap = map;
            serviceList.add(0, lowPriceMap);
            continue;
          }
          BigDecimal price =
              new BigDecimal(((Map<String, Object>) map.get("carService")).get("promotion_price")
                  .toString());
          BigDecimal lowPrice =
              new BigDecimal(((Map<String, Object>) lowPriceMap.get("carService")).get(
                  "promotion_price").toString());
          if (price.compareTo(lowPrice) < 0) {
            lowPriceMap = map;
            serviceList.add(0, lowPriceMap);
          } else {
            serviceList.add(map);
          }
        }
        response.setMsg(serviceList);
      }
    }

    PageResponse page = new PageResponse();
    page.setPageNumber(tenantInfoReq.getPageNumber());
    page.setPageSize(tenantInfoReq.getPageSize());
    page.setTotal((int) tenantPage.getTotal());
    response.setPage(page);
    if (!SortType.PRICEASC.equals(sortType)) {
      response.setMsg(tenantPage.getContent());
    }


    EndUser user = endUserService.find(userId);
    if (user.getDefaultVehicle() != null) {
      App app = appService.getTenantAppById(user.getDefaultVehicle().getTenantID());
      if (app != null) {
        response.setDesc(app.getAppTitleName());
      }
    }

    String newtoken = TokenGenerator.generateToken(tenantInfoReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 租户详情
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getTenantById", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> getTenantById(
      @RequestBody TenantInfoRequest tenantInfoReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = tenantInfoReq.getUserId();
    String token = tenantInfoReq.getToken();
    Long tenantId = tenantInfoReq.getTenantId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    TenantInfo tenantInfo = tenantInfoService.find(tenantId);

    String[] properties =
        {"id", "tenantName", "contactPhone", "address", "businessTime", "photo", "latitude",
            "longitude"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, tenantInfo);

    String[] imagesProperties = {"id", "image"};
    List<Map<String, Object>> imageMaps =
        FieldFilterUtils.filterCollectionMap(imagesProperties, tenantInfo.getTenantImages());
    map.put("tenantImages", imageMaps);
    List<Map<String, Object>> service_map = new ArrayList<Map<String, Object>>();
    Set<String> categoryNames = new HashSet<String>();
    String[] service_properties =
        {"id", "serviceName", "price", "promotionPrice", "serviceDesc", "imgPath"};
    for (CarService carService : tenantInfo.getCarServices()) {
      if (ServiceStatus.ENABLED.equals(carService.getServiceStatus())) {
        List<Map<String, Object>> sub_service_maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> sub_service_map = new HashMap<String, Object>();
        Map<String, Object> category_map = new HashMap<String, Object>();
        if (!categoryNames.contains(carService.getServiceCategory().getCategoryName())) {
          categoryNames.add(carService.getServiceCategory().getCategoryName());
          category_map.put("categoryId", carService.getServiceCategory().getId());
          category_map.put("categoryName", carService.getServiceCategory().getCategoryName());
          sub_service_map = FieldFilterUtils.filterEntityMap(service_properties, carService);

          service_map.add(category_map);
        } else {
          for (Map<String, Object> serviceMap : service_map) {
            if (serviceMap.get("categoryName").equals(
                carService.getServiceCategory().getCategoryName())) {
              sub_service_maps = (List<Map<String, Object>>) serviceMap.get("subServices");
              sub_service_map = FieldFilterUtils.filterEntityMap(service_properties, carService);
            }
          }
        }
        sub_service_maps.add(sub_service_map);
        category_map.put("subServices", sub_service_maps);
      }

    }

    map.put("carServices", service_map);
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(tenantInfoReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 用户所属租户保养美容服务
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getTenantByUser", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> getTenantByUser(
      @RequestBody TenantInfoRequest tenantInfoReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = tenantInfoReq.getUserId();
    String token = tenantInfoReq.getToken();
    Long categoryId = tenantInfoReq.getServiceCategoryId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Map<String, Object> map =
        tenantInfoService.getTenantByUserAndServiceCategory(userId, categoryId);

    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(tenantInfoReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 服务详情
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getServiceById", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getServiceById(
      @RequestBody TenantInfoRequest tenantInfoReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = tenantInfoReq.getUserId();
    String token = tenantInfoReq.getToken();
    Long serviceId = tenantInfoReq.getServiceId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    if (endUser.getDefaultVehicle() == null) {
      response.setCode(CommonAttributes.FAIL_DEFAULT_VEHICLE);
      response.setDesc(Message.error("csh.default.vehicle.not.exist").getContent());
      return response;
    }

    VehicleBrandDetail brandDetail = endUser.getDefaultVehicle().getVehicleBrandDetail();
    Long brandDetailId = brandDetail.getId();
    if (LogUtil.isDebugEnabled(TenantInfoController.class)) {
      LogUtil
          .debug(
              TenantInfoController.class,
              "getServiceById",
              "search tenant service detail for User Vehicle with UserName: %s,brandDetailId: %s,serviceId: %s",
              endUser.getUserName(), brandDetailId, serviceId);
    }
    CarService carService = carServiceService.find(serviceId);
    List<Map<String, Object>> serviceMap =
        itemPartService.getItemPartMaps(carService, brandDetailId);
    response.setMsg(serviceMap);
    String newtoken = TokenGenerator.generateToken(tenantInfoReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setDesc(brandDetail.getName());
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
