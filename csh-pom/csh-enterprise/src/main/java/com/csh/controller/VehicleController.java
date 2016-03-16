package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
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
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeviceInfoService;
import com.csh.service.EndUserService;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleService;
import com.csh.utils.DateTimeUtils;

/**
 * 车辆管理
 * @author huyong
 *
 */
@Controller ("vehicleController")
@RequestMapping ("console/vehicle")
public class VehicleController extends BaseController
{

  @Resource (name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  @Resource (name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  @Resource (name = "endUserServiceImpl")
  private EndUserService endUserService;
  @Resource (name = "vehicleBrandDetailServiceImpl")
  private VehicleBrandDetailService vehicleBrandDetailService;
  @RequestMapping (value = "/vehicle", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicle/vehicle";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String plateSearch,String userNameSearch,String mobileNumSearch)
  {
   return searchVehicleList (pageable, model, beginDate, endDate, plateSearch, userNameSearch, mobileNumSearch);
  }

  /**
   * 私有查询车辆信息方法
   * @param pageable
   * @param model
   * @param beginDate
   * @param endDate
   * @param plateSearch
   * @param userNameSearch
   * @param mobileNumSearch
   * @return
   */
  private Page<Vehicle> searchVehicleList(Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String plateSearch,String userNameSearch,String mobileNumSearch){

    
    Page<Vehicle> vehiclePage;
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser plateParser = new QueryParser (Version.LUCENE_35, "plate",
        analyzer);
    QueryParser userNameParser = new QueryParser (Version.LUCENE_35, "endUser.userName",
        analyzer);
    QueryParser mobileNumParser = new QueryParser (Version.LUCENE_35, "endUser.mobileNum",
        analyzer);
    Query plateQuery = null;
    Query userNameQuery = null;
    Query mobileNumQuery = null;
    TermRangeQuery rangeQuery = null;
    
    
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (plateSearch != null)
    {
      String text = QueryParser.escape (plateSearch);
        try
        {
          //通配符查询，开启*开头，但影响效率
          plateParser.setAllowLeadingWildcard (true);

          plateQuery = plateParser.parse ("*"+text+"*");
          
          query.add (plateQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search plate: "
                + plateSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (userNameSearch != null)
    {
      String text = QueryParser.escape (userNameSearch);
        try
        {
          userNameQuery = userNameParser.parse (text);
          query.add (userNameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search user name: "
                + userNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (mobileNumSearch != null)
    {

      String text = QueryParser.escape (mobileNumSearch);
        try
        {
          mobileNumParser.setAllowLeadingWildcard (true);
          mobileNumQuery = mobileNumParser.parse ("*"+text+"*");
          query.add (mobileNumQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (VehicleController.class))
          {
            LogUtil.debug (VehicleController.class, "search", "Search user name: "
                + userNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (VehicleController.class))
      {
        LogUtil.debug (VehicleController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (plateQuery != null || userNameQuery != null || mobileNumQuery != null || rangeQuery != null )
    {
      vehiclePage= vehicleService.search (query, pageable, analyzer,filter,true);
    }else {
      vehiclePage= vehicleService.findPage (pageable, true);
    }
    for (Vehicle vehicle : vehiclePage.getRows ())
    {
      DeviceInfo deviceInfo = vehicle.getDevice ();
      if ( deviceInfo != null)
      {
        vehicle.setDeviceNo (deviceInfo.getDeviceNo ());
      }
    }
    return vehiclePage;
  
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
    Vehicle vehicle = vehicleService.find (id);
    model.put ("vehicle", vehicle);
    return "vehicle/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Vehicle vehicle,Long endUserID,Long deviceInfoID, Long vehicleBrandDetailId)
  {
    EndUser endUser = endUserService.find (endUserID);
    DeviceInfo deviceInfo = deviceInfoService.find (deviceInfoID);
    deviceInfo.setDeviceStatus (DeviceStatus.BINDED);
    deviceInfo.setBindTime (new Date ());
    VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailService.find (vehicleBrandDetailId);
    vehicle.setDevice (deviceInfo);
    vehicle.setEndUser (endUser);
    vehicle.setVehicleBrandDetail (vehicleBrandDetail);
    vehicleService.save (vehicle,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Vehicle vehicle,Long endUserID,Long deviceInfoID, Long vehicleBrandDetailId)
  { 
    EndUser endUser = endUserService.find (endUserID);
    DeviceInfo deviceInfo = deviceInfoService.find (deviceInfoID);
    deviceInfo.setDeviceStatus (DeviceStatus.BINDED);
    deviceInfo.setBindTime (new Date ());
    VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailService.find (vehicleBrandDetailId);
    vehicle.setDevice (deviceInfo);
    vehicle.setEndUser (endUser);
    vehicle.setVehicleBrandDetail (vehicleBrandDetail);
    vehicleService.update (vehicle);
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
      vehicleService.delete (ids);
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
    Vehicle vehicle = vehicleService.find(id);
    model.addAttribute("vehicle", vehicle);
    return "vehicle/details";
  }
  
  /**
   * 导出列表数据，即用户已经查询出来的数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response,  Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String plateSearch,String userNameSearch,String mobileNumSearch) {
    
    List<Vehicle> vehicleList=null;
    if (plateSearch != null || beginDate != null 
        || endDate != null || userNameSearch != null || mobileNumSearch != null)
    {
      vehicleList = searchVehicleList (pageable, model, beginDate, endDate, plateSearch, userNameSearch, mobileNumSearch).getRows ();
    }else {
      vehicleList = vehicleService.findAll ();
      for (Vehicle vehicle : vehicleList)
      {
        DeviceInfo deviceInfo = vehicle.getDevice ();
        if ( deviceInfo != null)
        {
          vehicle.setDeviceNo (deviceInfo.getDeviceNo ());
        }
      }
    }
    
    if (vehicleList != null && vehicleList.size() > 0) {
      String title = "车辆信息"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"user", "mobileNum", "plate", "vehicleBrandDetail","color", "bindingDevice"}; // 需要导出的字段
      String[] headersName = {"所有人", "电话号码", "车牌号", "车型","颜色", "绑定的设备"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> eventRecordMapList = prepareMap(vehicleList);
      if (eventRecordMapList.size() > 0) {
        exportListToExcel(response, eventRecordMapList, title, headers, headersName);
      }
    }
  }
  
  /**
   * 准备excel需要的map数据
   * @param vehicleList
   * @return
   */
  private List<Map<String, String>> prepareMap(List<Vehicle> vehicleList){
    List<Map<String, String>> resultMapList = new ArrayList<Map<String,String>>();
    for (Vehicle vehicle : vehicleList)
    {
      Map<String, String> vehicleMap = new HashMap<String, String> ();
      
      vehicleMap.put ("user", vehicle.getEndUser ().getUserName ());
      vehicleMap.put ("mobileNum", vehicle.getEndUser ().getMobileNum ());
      vehicleMap.put ("plate", vehicle.getPlate ());
      if (vehicle.getVehicleBrandDetail () != null)
      {
        vehicleMap.put ("vehicleBrandDetail", vehicle.getVehicleBrandDetail ().getName ());
      }
      
      vehicleMap.put ("color", vehicle.getColor ());
      vehicleMap.put ("bindingDevice", vehicle.getDeviceNo ());
      resultMapList.add (vehicleMap);
    }
    return resultMapList;
  }
}
