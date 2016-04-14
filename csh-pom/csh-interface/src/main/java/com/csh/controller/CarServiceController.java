package com.csh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
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
import com.csh.entity.CarService;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.CarServiceRequest;
import com.csh.json.request.InsuranceRequest;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CarServiceService;
import com.csh.service.EndUserService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.PayUtil;
import com.csh.utils.TokenGenerator;


@Controller("CarServiceController")
@RequestMapping("/carService")
public class CarServiceController extends MobileBaseController {


  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "carServiceRecordServiceImpl")
  private CarServiceRecordService carServiceRecordService;

  @Resource(name = "carServiceServiceImpl")
  private CarServiceService carServiceService;


  /**
   * 用户预约汽车服务
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/subscribeService", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse subscribeService(@RequestBody CarServiceRequest serviceReq) {

    BaseResponse response = new BaseResponse();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // ChargeStatus chargeStatus = serviceReq.getChargeStatus();
    BigDecimal price = serviceReq.getPrice();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    CarService carService = carServiceService.find(serviceReq.getServiceId());

    Boolean existRecord =
        carServiceRecordService.existRecordByUser(endUser, carService, ChargeStatus.RESERVATION);
    if (existRecord) {
      response.setCode(CommonAttributes.FAIL_COMMON);
      response.setDesc(Message.error("csh.subscribe.duplicate").getContent());
      return response;
    }
    CarServiceRecord carServiceRecord =
        carServiceRecordService.createServiceRecord(endUser, carService, ChargeStatus.RESERVATION,
            price, null, serviceReq.getSubscribeDate());

    if (LogUtil.isDebugEnabled(CarServiceController.class)) {
      LogUtil
          .debug(
              CarServiceController.class,
              "Save",
              "User buy Car Service. UserName: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
              endUser.getUserName(), carServiceRecord.getTenantName(), carService.getServiceName(),
              carServiceRecord.getPrice(), carServiceRecord.getPaymentType(),
              carServiceRecord.getChargeStatus());
    }

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    response.setDesc(carServiceRecord.getId().toString());
    return response;
  }

  /**
   * 更新支付状态
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/updatePayStatus", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse updatePayStatus(@RequestBody CarServiceRequest serviceReq) {

    BaseResponse response = new BaseResponse();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    CarServiceRecord carServiceRecord = carServiceRecordService.find(serviceReq.getRecordId());
    carServiceRecord.setPaymentDate(new Date());
    carServiceRecord.setChargeStatus(serviceReq.getChargeStatus());
    carServiceRecordService.updatePayStatus(carServiceRecord);
    if (LogUtil.isDebugEnabled(CarServiceController.class)) {
      LogUtil
          .debug(
              CarServiceController.class,
              "Update",
              "Update Car Service pay status. UserName: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
              endUser.getUserName(), carServiceRecord.getTenantName(), carServiceRecord
                  .getCarService().getServiceName(), carServiceRecord.getPrice(), carServiceRecord
                  .getPaymentType(), carServiceRecord.getChargeStatus());
    }

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 支付(余额支付直接支付)
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/payService", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> payService(
      @RequestBody CarServiceRequest serviceReq, HttpServletRequest httpReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    PaymentType paymentType = serviceReq.getPaymentType();
    // ChargeStatus chargeStatus = serviceReq.getChargeStatus();
    BigDecimal price = serviceReq.getPrice();
    Long serviceId = serviceReq.getServiceId();
    Long recordId = serviceReq.getRecordId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    Wallet wallet = endUser.getWallet();
    if (PaymentType.WALLET.equals(paymentType)) {// 余额支付
      if (serviceReq.getPrice().compareTo(wallet.getBalanceAmount()) > 0) {
        response.setCode(CommonAttributes.FAIL_COMMON);
        response.setDesc(Message.error("csh.wallet.money.insufficient").getContent());
        return response;
      }
    }

    CarServiceRecord carServiceRecord = new CarServiceRecord();
    CarService carService = carServiceService.find(serviceId);
    if (recordId == null) {
      carServiceRecord =
          carServiceRecordService.createServiceRecord(endUser, carService, ChargeStatus.UNPAID,
              price, paymentType, null);
      if (LogUtil.isDebugEnabled(CarServiceController.class)) {
        LogUtil
            .debug(
                CarServiceController.class,
                "Save",
                "User buy Car Service. UserName: %s, Tenant: %s, Service: %s, price: %s, paymentType: %s, chargeStatus: %s",
                endUser.getUserName(), carServiceRecord.getTenantName(),
                carService.getServiceName(), carServiceRecord.getPrice(),
                carServiceRecord.getPaymentType(), carServiceRecord.getChargeStatus());
      }
    } else {
      carServiceRecord = carServiceRecordService.find(recordId);
    }

    if (PaymentType.WECHAT.equals(paymentType)) {
      try {
        response =
            PayUtil.wechat(carServiceRecord.getRecordNo(), carService.getServiceName(),
                httpReq.getRemoteAddr(), serviceId.toString(), price.toString());
      } catch (DocumentException e) {
        e.printStackTrace();
      }
    } else {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("out_trade_no", carServiceRecord.getRecordNo());
      response.setMsg(map);
      response.setDesc(carServiceRecord.getId().toString());
      response.setCode(CommonAttributes.SUCCESS);
    }

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    return response;
  }

  /**
   * 记录详情
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/recordDetail", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> recordDetail(
      @RequestBody CarServiceRequest serviceReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    CarServiceRecord carServiceRecord = carServiceRecordService.find(serviceReq.getRecordId());
    String[] properties =
        {"id", "createDate", "price", "chargeStatus", "recordNo", "paymentDate", "subscribeDate",
            "finishDate", "serviceFlag"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, carServiceRecord);
    map.put("serviceName", carServiceRecord.getCarService().getServiceName());
    String[] tenant_properties =
        {"id", "praiseRate", "tenantName", "contactPhone", "address", "businessTime", "photo",
            "latitude", "longitude"};
    Map<String, Object> tenant_map =
        FieldFilterUtils.filterEntityMap(tenant_properties, carServiceRecord.getCarService()
            .getTenantInfo());
    map.put("tenantInfo", tenant_map);

    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户购买汽车服务列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/purchaseList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> purchaseList(
      @RequestBody CarServiceRequest serviceReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    EndUser endUser = endUserService.find(userId);

    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("endUser", Operator.eq, endUser);
    filters.add(filter);

    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("createDate", Direction.desc);
    orderings.add(ordering);
    Pageable pageable = new Pageable(serviceReq.getPageNumber(), serviceReq.getPageSize());
    pageable.setFilters(filters);
    pageable.setOrders(orderings);
    Page<CarServiceRecord> records = carServiceRecordService.findPage(pageable);

    PageResponse page = new PageResponse();
    page.setPageNumber(serviceReq.getPageNumber());
    page.setPageSize(serviceReq.getPageSize());
    page.setTotal((int) records.getTotal());
    response.setPage(page);

    String[] properties =
        {"id", "createDate", "paymentDate", "chargeStatus", "price", "carService.serviceCategory",
            "carService.serviceName", "carService.id", "tenantName", "tenantID", "tenantPhoto",
            "tenantEvaluate"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, records.getContent());
    response.setMsg(map);

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 用户购买保险列表(如果用户已绑定设备，购买保险由设备对应租户处理;如果用户未绑定，保单由平台处理)
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/insuranceList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> insuranceList(
      @RequestBody CarServiceRequest serviceReq) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Pageable pageable = new Pageable(serviceReq.getPageNumber(), serviceReq.getPageSize());
    Long categoryId = setting.getInsuranceId();// 保险类别ID
    Page<CarServiceRecord> records =
        carServiceRecordService.getBuyRecordListByUserAndCategory(userId, categoryId, pageable);

    PageResponse page = new PageResponse();
    page.setPageNumber(serviceReq.getPageNumber());
    page.setPageSize(serviceReq.getPageSize());
    page.setTotal((int) records.getTotal());
    response.setPage(page);

    String[] properties =
        {"id", "createDate", "chargeStatus", "price", "carService.serviceName", "tenantName"};
    List<Map<String, Object>> map =
        FieldFilterUtils.filterCollectionMap(properties, records.getContent());
    response.setMsg(map);

    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 保险记录详情
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/insuranceDetail", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> insuranceDetail(
      @RequestBody CarServiceRequest serviceReq) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    Long userId = serviceReq.getUserId();
    String token = serviceReq.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    CarServiceRecord carServiceRecord = carServiceRecordService.find(serviceReq.getRecordId());
    String[] properties = {"id", "createDate", "price", "recordNo"};
    Map<String, Object> map = FieldFilterUtils.filterEntityMap(properties, carServiceRecord);
    map.put("serviceName", carServiceRecord.getCarService().getServiceName());
    String[] insurance_properties =
        {"id", "insuredCompany", "insuranceStartDate", "insuranceEndDate", "isOwned", "isLoaned",
            "driverLicensePhoto", "drivingLicensePhoto", "IDphoto"};
    Map<String, Object> insurance_map =
        FieldFilterUtils.filterEntityMap(insurance_properties,
            carServiceRecord.getVehicleInsurance());
    map.put("insurance", insurance_map);

    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(serviceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 购买保险
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/buyInsurance", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse buyInsurance(@RequestBody InsuranceRequest insuranceReq) {

    BaseResponse response = new BaseResponse();
    Long userId = insuranceReq.getUserId();
    String token = insuranceReq.getToken();
    String company = insuranceReq.getInsuredCompany();
    String IDphoto = insuranceReq.getIDphoto();
    String drivingLicensePhoto = insuranceReq.getDrivingLicensePhoto();
    String driverLicensePhoto = insuranceReq.getDriverLicensePhoto();
    Boolean isOwned = insuranceReq.getIsOwned();
    Boolean isLoaned = insuranceReq.getIsLoaned();
    BigDecimal price = insuranceReq.getPrice();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    carServiceRecordService.createInsuranceRecord(endUser, null, price, company, IDphoto,
        drivingLicensePhoto, driverLicensePhoto, isOwned, isLoaned);

    String newtoken = TokenGenerator.generateToken(insuranceReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
