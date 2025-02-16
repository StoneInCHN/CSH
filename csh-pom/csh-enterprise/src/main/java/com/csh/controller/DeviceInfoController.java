package com.csh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeviceInfoService;
import com.csh.service.VehicleService;
import com.csh.utils.DateTimeUtils;

/**
 * 设备管理
 * @author huyong
 *
 */
@Controller ("deviceController")
@RequestMapping ("console/deviceInfo")
public class DeviceInfoController extends BaseController
{

  @Resource (name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  
  @Resource (name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  
  @RequestMapping (value = "/deviceInfo", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "deviceInfo/deviceInfo";
  }
  @RequestMapping (value = "/commonDeviceInfoSearch", method = RequestMethod.GET)
  public String commonDeviceInfoSearch (ModelMap model)
  {
    return "common/commonDeviceInfoSearch";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DeviceInfo> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String deviceNoSearch,String deviceTpyeSearch,BindStatus bindStatusSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "deviceNo",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery statusQuery = null;
    TermQuery typeqQuery =null;
   
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (deviceNoSearch != null)
    {
      String text = QueryParser.escape (deviceNoSearch);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (DeviceInfoController.class))
          {
            LogUtil.debug (DeviceInfoController.class, "search", "Search device NO: "
                + deviceNoSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (bindStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("bindStatus",bindStatusSearch.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (deviceTpyeSearch != null)
    {
      typeqQuery = new TermQuery (new Term ("type.name",deviceTpyeSearch.toLowerCase ()));
      query.add (typeqQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (DeviceInfoController.class))
      {
        LogUtil.debug (DeviceInfoController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || typeqQuery != null || statusQuery != null)
    {
      return deviceInfoService.search (query, pageable, analyzer,filter,true);
    }
      return deviceInfoService.findPage (pageable, true);
    
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    DeviceInfo deviceInfo = deviceInfoService.find (id);
    model.put ("deviceInfo", deviceInfo);
    return "deviceInfo/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (DeviceInfo deviceInfo)
  {
    deviceInfoService.save (deviceInfo,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (DeviceInfo deviceInfo)
  { 
    deviceInfoService.update (deviceInfo);
    return SUCCESS_MESSAGE;
  }
 

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 检查是否能被删除
      // if()
      deviceInfoService.delete (ids);
    }
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 删除
   */
  @RequestMapping (value = "/deviceStatusUpdate", method = RequestMethod.POST)
  public @ResponseBody Message deviceStatusUpdate (Long[] ids,DeviceStatus deviceStatus)
  {
    if (ids != null)
    {
     List<DeviceInfo> deviceInfos =  deviceInfoService.findList (ids);
     for (DeviceInfo deviceInfo : deviceInfos)
     {
        if (deviceStatus == DeviceStatus.SALEOUT 
            && deviceInfo.getDeviceStatus ()!= DeviceStatus.STORAGEOUT )
        {
          return ERROR_MESSAGE;
        }
        if (deviceStatus == DeviceStatus.REFUNDED )
        {
          if (deviceInfo.getDeviceStatus () != DeviceStatus.STORAGEOUT 
              && (deviceInfo.getDeviceStatus () != DeviceStatus.SALEOUT || deviceInfo.getBindStatus () == BindStatus.BINDED))
          {
            return ERROR_MESSAGE;
          }else {
            deviceStatus= DeviceStatus.SENDOUT;
            deviceInfo.setTenantID (null);
          }
         
        }
        deviceInfo.setDeviceStatus (deviceStatus);
     }
     deviceInfoService.update (deviceInfos);
    }
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    DeviceInfo deviceInfo = deviceInfoService.find(id);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("deviceId", Long.parseLong (deviceInfo.getDeviceNo ()));
    
    model.addAttribute("realTimeCarCondition",  vehicleService.getRealTimeCarCondition (params));;
    model.addAttribute("deviceInfo", deviceInfo);
    return "deviceInfo/details";
  }
  /**
   * 解绑设备
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/unBind", method = RequestMethod.GET)
  public @ResponseBody Message unBind(ModelMap model, Long id) {
    DeviceInfo deviceInfo = deviceInfoService.find(id);
    deviceInfoService.unBind (deviceInfo);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 解绑设备
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/commonVehicleSearch", method = RequestMethod.GET)
  public String  commonVehicleSearch(ModelMap model, Long deviceId,Long vehicleId) {
    return "common/commonVehicleSearch";
  }
  /**
   * 解绑设备
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/bind", method = RequestMethod.POST)
  public @ResponseBody Message bind(ModelMap model, Long deviceId,Long vehicleId) {
    DeviceInfo deviceInfo = deviceInfoService.find(deviceId);
    Vehicle vehicle = vehicleService.find (vehicleId);
    deviceInfo.setBindStatus (BindStatus.BINDED);
    deviceInfo.setVehicle (vehicle);
    vehicle.setTenantID (deviceInfo.getTenantID ());
    deviceInfo.setBindTime (new Date ());
    
    deviceInfoService.update (deviceInfo);
    return SUCCESS_MESSAGE;
  }
  
  
}
