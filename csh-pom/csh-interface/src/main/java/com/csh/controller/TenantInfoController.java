package com.csh.controller;

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
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.TenantInfo;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.TenantInfoRequest;
import com.csh.service.EndUserService;
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


  /**
   * 租户列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody ResponseMultiple<Map<String, Object>> list(
      @RequestBody TenantInfoRequest tenantInfoReq) {

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

    Pageable pageable = new Pageable(tenantInfoReq.getPageNumber(), tenantInfoReq.getPageSize());
    Page<Map<String, Object>> tenantPage =
        tenantInfoJdbcService.getTenantInfos(longitude, latitude, pageable, radius,
            serviceCategoryId);

    PageResponse page = new PageResponse();
    page.setPageNumber(tenantInfoReq.getPageNumber());
    page.setPageSize(tenantInfoReq.getPageSize());
    page.setTotal((int) tenantPage.getTotal());
    response.setPage(page);
    response.setMsg(tenantPage.getContent());
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
  @RequestMapping(value = "/getTenantById", method = RequestMethod.POST)
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

    String[] service_properties =
        {"id", "serviceName", "price", "promotionPrice", "serviceStatus",
            "serviceCategory.categoryName"};
    List<Map<String, Object>> service_map =
        FieldFilterUtils.filterCollectionMap(service_properties, tenantInfo.getCarServices());
    map.put("carServices", service_map);
    response.setMsg(map);
    String newtoken = TokenGenerator.generateToken(tenantInfoReq.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


}
