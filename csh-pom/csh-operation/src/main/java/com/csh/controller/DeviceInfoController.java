package com.csh.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.Admin;
import com.csh.entity.DeviceInfo;
import com.csh.entity.DeviceType;
import com.csh.entity.Distributor;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.entity.commonenum.CommonEnum.DeviceStatus;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.request.SendCommandParameter;
import com.csh.json.response.SendCommandResponse;
import com.csh.service.AdminService;
import com.csh.service.DeviceInfoService;
import com.csh.service.DeviceTypeService;
import com.csh.service.DistributorService;
import com.csh.service.TenantInfoService;
import com.csh.utils.ApiUtils;
import com.csh.utils.ExcelUtils;
import com.csh.utils.SettingUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("console/deviceInfo")
@Controller("deviceInfoController")
public class DeviceInfoController extends BaseController {

  @Resource(name = "deviceTypeServiceImpl")
  private DeviceTypeService deviceTypeService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

  @Resource(name = "distributorServiceImpl")
  private DistributorService distributorService;

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
  
  private Setting setting = SettingUtils.get();

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap modelMap) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("status");
    filter.setValue(Status.ENABLE);
    filter.setOperator(Operator.eq);
    filters.add(filter);
    modelMap.addAttribute("types", deviceTypeService.findList(null, filters, null));
    return "/deviceInfo/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(DeviceInfo deviceInfo, Long typeId) {
    if (!isValid(deviceInfo)) {
      return ERROR_VIEW;
    }
    DeviceType type = deviceTypeService.find(typeId);
    deviceInfo.setType(type);
    deviceInfo.setDeviceStatus(DeviceStatus.INITED);
    deviceInfo.setBindStatus(BindStatus.UNBINDED);
    deviceInfoService.save(deviceInfo);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("deviceInfo", deviceInfoService.find(id));
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("status");
    filter.setValue(Status.ENABLE);
    filter.setOperator(Operator.eq);
    filters.add(filter);
    model.addAttribute("types", deviceTypeService.findList(null, filters, null));
    return "/deviceInfo/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(DeviceInfo deviceInfo, Long typeId) {
    DeviceInfo info = deviceInfoService.find(deviceInfo.getId());
    DeviceType type = deviceTypeService.find(typeId);
    info.setType(type);
    info.setSimNo(deviceInfo.getSimNo());
    deviceInfoService.update(info);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model, DeviceStatus deviceStatus,
      Date beginDate ,Date endDate) {
    model.addAttribute("deviceStatus", deviceStatus);
    model.addAttribute("beginDate", beginDate);
    model.addAttribute("endDate", endDate);
    List<Filter> filters = new ArrayList<Filter>();
    if (deviceStatus != null) {
      Filter filter = new Filter();
      filter.setProperty("deviceStatus");
      filter.setValue(deviceStatus);
      filter.setOperator(Operator.eq);
      filters.add(filter);
    }
    if(beginDate !=null){
      Filter filter = new Filter();
      filter.setProperty("createDate");
      filter.setValue(beginDate);
      filter.setOperator(Operator.ge);
      filters.add(filter);
    }
    if(endDate !=null){
      Filter filter = new Filter();
      filter.setProperty("createDate");
      filter.setValue(endDate);
      filter.setOperator(Operator.le);
      filters.add(filter);
    }
    pageable.setFilters(filters);
    Page<DeviceInfo>  page = deviceInfoService.findPage(pageable);
    List<DeviceInfo> lists = page.getContent();
    for (DeviceInfo deviceInfo : lists) {
      if (deviceInfo.getTenantID() !=null) {
        TenantInfo tenantInfo = tenantInfoService.find(deviceInfo.getTenantID());
        if(tenantInfo !=null && tenantInfo.getTenantName() !=null){
          deviceInfo.setTenantName(tenantInfo.getTenantName());
        }
      }
    }
    model.addAttribute("page", new Page<DeviceInfo>(lists, page.getTotal(), pageable));
    return "/deviceInfo/list";
  }


  /**
   * 列表
   */
  @RequestMapping(value = "/list4distributor", method = RequestMethod.GET)
  public String list4distributor(Pageable pageable, DeviceStatus deviceStatus, ModelMap model) {
    model.addAttribute("deviceStatus", deviceStatus);
    Admin admin = adminService.getCurrent();
    List<Filter> filters = new ArrayList<Filter>();
    if (deviceStatus == null) {
      Filter filter = new Filter();
      filter.setProperty("deviceStatus");
      filter.setValue(DeviceStatus.INITED);
      filter.setOperator(Operator.ne);
      filters.add(filter);
    } else {
      Filter filter = new Filter();
      filter.setProperty("deviceStatus");
      filter.setValue(deviceStatus);
      filter.setOperator(Operator.eq);
      filters.add(filter);
    }
    if (admin.getIsDistributor() != null && admin.getIsDistributor()
        && admin.getDistributor() != null) {
      Filter distributorFilter = new Filter();
      distributorFilter.setProperty("distributor");
      distributorFilter.setValue(admin.getDistributor().getId());
      distributorFilter.setOperator(Operator.eq);
      filters.add(distributorFilter);
    }
    pageable.setFilters(filters);
    Page<DeviceInfo>  page = deviceInfoService.findPage(pageable);
    List<DeviceInfo> lists = page.getContent();
    for (DeviceInfo deviceInfo : lists) {
      if (deviceInfo.getTenantID() !=null) {
        TenantInfo tenantInfo = tenantInfoService.find(deviceInfo.getTenantID());
        if(tenantInfo !=null && tenantInfo.getTenantName() !=null){
          deviceInfo.setTenantName(tenantInfo.getTenantName());
        }
      }
    }
    model.addAttribute("page", new Page<DeviceInfo>(lists, page.getTotal(), pageable));
    return "/deviceInfo/list4distributor";
  }


  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      deviceInfoService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/tenantInfoList4distributor", method = RequestMethod.GET)
  public String tenantInfo4distributor(Pageable pageable, ModelMap model) {
    Admin admin = adminService.getCurrent();
    List<Filter> filters = new ArrayList<Filter>();
    Filter distributorFilter = new Filter();
    distributorFilter.setProperty("distributor");
    if (admin.getIsDistributor() != null && admin.getIsDistributor()
        && admin.getDistributor() != null) {
      distributorFilter.setValue(admin.getDistributor().getId());
    } else {
      distributorFilter.setValue(0);
    }
    distributorFilter.setOperator(Operator.eq);
    filters.add(distributorFilter);
    pageable.setFilters(filters);
    pageable.setPageSize(5);;
    model.addAttribute("page", tenantInfoService.findPage(pageable));
    return "/deviceInfo/tenantInfo4distributor";
  }


  @RequestMapping(value = "/deviceProvide", method = RequestMethod.GET)
  public String deviceProvide(ModelMap modelMap, Pageable pageable) {
    pageable.setPageSize(5);;
    modelMap.addAttribute("page", distributorService.findPage(pageable));
    return "/deviceInfo/deviceProvide";
  }
  
  @RequestMapping(value = "/deviceProvide4edit", method = RequestMethod.GET)
  public String deviceProvide4edit(ModelMap modelMap, Pageable pageable) {
    pageable.setPageSize(5);;
    modelMap.addAttribute("page", distributorService.findPage(pageable));
    return "/deviceInfo/deviceProvide4edit";
  }

  @RequestMapping(value = "/provide4distributor", method = RequestMethod.POST)
  public @ResponseBody Message provide4distributor(String ids, Long[] tenantInfoIds) {
    if (tenantInfoIds.length != 1) {
      return ERROR_MESSAGE;
    }
    String[] deviceIds = ids.split("&ids=");
    Long tenantInfoId = tenantInfoIds[0];
    for (String string : deviceIds) {
      if (string.length() > 0) {
        Long id = Long.valueOf(string);
        DeviceInfo deviceInfo = deviceInfoService.find(id);
        if (DeviceStatus.SENDOUT.equals(deviceInfo.getDeviceStatus())) {
          deviceInfo.setTenantID(tenantInfoId);
          deviceInfo.setDeviceStatus(DeviceStatus.STORAGEOUT);
          deviceInfoService.update(deviceInfo);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/provide", method = RequestMethod.POST)
  public @ResponseBody Message provide(String ids, Long[] distributorIds) {
    if (distributorIds.length != 1) {
      return ERROR_MESSAGE;
    }
    String[] deviceIds = ids.split("&ids=");
    Long distributorId = distributorIds[0];
    for (String string : deviceIds) {
      if (string.length() > 0) {
        Long id = Long.valueOf(string);
        DeviceInfo deviceInfo = deviceInfoService.find(id);
        if (DeviceStatus.INITED.equals(deviceInfo.getDeviceStatus())) {
          Distributor distributor = distributorService.find(distributorId);
          deviceInfo.setDistributor(distributor);
          deviceInfo.setDeviceStatus(DeviceStatus.SENDOUT);
          deviceInfoService.update(deviceInfo);
        }

      }
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/provide4edit", method = RequestMethod.POST)
  public @ResponseBody Message provide4edit(Long id, Long distributorId) {
    if(id ==null || distributorId ==null){
      return ERROR_MESSAGE;
    }
    DeviceInfo deviceInfo = deviceInfoService.find(id);
    if (deviceInfo !=null && DeviceStatus.SENDOUT.equals(deviceInfo.getDeviceStatus())) {
      Distributor distributor = distributorService.find(distributorId);
      if(distributor !=null){
        deviceInfo.setDistributor(distributor);
        deviceInfoService.update(deviceInfo);
        return SUCCESS_MESSAGE;
      }
    }
      return ERROR_MESSAGE;
  }

  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    DeviceInfo deviceInfo = deviceInfoService.find(id);
    if (deviceInfo.getTenantID() !=null) {
      TenantInfo tenantInfo = tenantInfoService.find(deviceInfo.getTenantID());
      if(tenantInfo !=null && tenantInfo.getTenantName() !=null){
        deviceInfo.setTenantName(tenantInfo.getTenantName());
      }
    }
    model.addAttribute("deviceInfo",deviceInfo );
    String response = ApiUtils.post(setting.getObdServiceUrl ()+"receiverData/getDeviceStatus.jhtml?deviceId="+deviceInfo.getDeviceNo());
    if (response != null){
      if (response != null && !response.equals (""))
      {
        JSONObject resultJson = JSONObject.fromObject(response);
        model.put("deviceStatus", resultJson);
        model.put("createtime", getCreateDate(resultJson.get("createtime")));
        model.put("obddate", getCreateDate(resultJson.get("obddate")));
        model.put("obddtcdate", getCreateDate(resultJson.get("obddtcdate")));
        model.put("agpstime", getCreateDate(resultJson.get("agpstime")));
        model.put("istatustime", getCreateDate(resultJson.get("istatustime")));
        model.put("t3dspeedtime", getCreateDate(resultJson.get("t3dspeedtime")));
        model.put("tstatustime", getCreateDate(resultJson.get("tstatustime")));
        model.put("xyz3dtime", getCreateDate(resultJson.get("xyz3dtime")));
      }
    }
    return "/deviceInfo/details";
  }
  private String getCreateDate(Object object) {
    if (object != null && !object.toString().equals("null")) {
      SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
      Long time = new Long(object.toString());  
      String formatStr = format.format(time);  
      return formatStr;
    }
    return null;
  }

  /**
   * 跳转到批量上传页面
   * 
   */
  @RequestMapping(value = "/batchAdd", method = RequestMethod.GET)
  public String batchAdd(DeviceInfo device, ModelMap model) {
    return "/deviceInfo/batch_add";
  }

  /**
   * 批量上传
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/batchAddSave", method = RequestMethod.POST)
  public String batchAddSave(DeviceInfo device, ModelMap model, HttpSession session) {
    Message message = new Message();
    int successCount = 0;// 成功条数
    int faileCount = 0;// 失败条数
    List<String> dupfailDeviceIds = new ArrayList<String>();
    List<String> formatFailDeviceIds = new ArrayList<String>();
    try {
      String fileSuffix = device.getFile().getOriginalFilename().split("\\.")[1];
      List<DeviceInfo> devices = null;

      if (fileSuffix.toUpperCase().equals("XLS")) {
        devices = ExcelUtils.processingExcel_XLS(device.getFile().getInputStream());
      } else if (fileSuffix.toUpperCase().equals("XLSX")) {
        devices = ExcelUtils.processingExcel_XLSX(device.getFile().getInputStream());
      }

      model.addAttribute("device", device);

      if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
        LogUtil.debug(DeviceInfoController.class, "batchAddSave",
            "Load data from excel successful and count is : %s", devices.size());
      }

      if (devices.size() > 3000) {
        if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
          LogUtil.debug(DeviceInfoController.class, "batchAddSave", "batchAddSave",
              "Rows exceed 3000 : %s", devices.size());
        }
        message.setType(Message.Type.error);
        message.setContent("gutid.batch.upload.err");
        model.addAttribute("resMessage", message);
        return "/deviceInfo/batch_add";
      }


      for (DeviceInfo deviceTemp : devices) {
        try {
          if (deviceTemp.getDeviceNo() != null && deviceTemp.getSimNo() != null) {
            if (deviceTemp.getDeviceNo().length() != 10) {
              formatFailDeviceIds.add(deviceTemp.getDeviceNo());
              faileCount++;
            } else {
              deviceTemp.setDeviceStatus(DeviceStatus.INITED);
              deviceTemp.setBindStatus(BindStatus.UNBINDED);
              deviceInfoService.save(deviceTemp);
              if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
                LogUtil.debug(DeviceInfoController.class, "batchAddSave",
                    "DeviceInfo saved , Sn : %s , simNo : %s", deviceTemp.getDeviceNo(),
                    deviceTemp.getSimNo());
              }
              successCount++;
            }
          } else {
            formatFailDeviceIds.add(deviceTemp.getDeviceNo() + "\n");
            faileCount++;
          }
        } catch (Exception e) {
          faileCount++;
          dupfailDeviceIds.add(deviceTemp.getDeviceNo());
        }
      }
      if (successCount > 0) {
        message.setType(Message.Type.success);
        message.setContent(message("gutid.batch.upload.success"));
        model.addAttribute("resMessage", message);
      } else {
        message.setType(Message.Type.error);
        message.setContent("gutid.batch.upload.fail");
        model.addAttribute("resMessage", message);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("successCount", successCount);
    model.addAttribute("faileCount", faileCount);
    model.addAttribute("dupfailDeviceIds", dupfailDeviceIds);
    model.addAttribute("formatFailDeviceIds", formatFailDeviceIds);
    return "/deviceInfo/batch_add";
  }

  /**
   * 检查用户名是否存在
   */
  @RequestMapping(value = "/check_deviceNo", method = RequestMethod.GET)
  public @ResponseBody boolean checkUsername(String deviceNo, Long id) {
    if (StringUtils.isEmpty(deviceNo)) {
      return false;
    }
    DeviceInfo deviceInfo = deviceInfoService.findByDeviceNo(deviceNo);
    if (deviceInfo != null) {
      if (id != null && deviceInfo.getId().longValue() == id.longValue()) {
        return true;
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/unbind", method = RequestMethod.POST)
  public @ResponseBody Message unbind(Long id) {
    if (id != null) {
      DeviceInfo deviceInfo = deviceInfoService.find(id);
      if (deviceInfo != null && deviceInfo.getBindStatus() == BindStatus.BINDED) {
        deviceInfo = deviceInfoService.unBind(deviceInfo);
        java.util.Date unBindTime = deviceInfo.getUnBindTime();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Message(com.csh.beans.Message.Type.success, sdFormat.format(unBindTime));
      }
    }
    return ERROR_MESSAGE;
  }
  /**
   * 平台下发指令
   */
  @RequestMapping(value = "/sendCommand", method = RequestMethod.GET)
  public String sendCommand(Long id, ModelMap model) {
    model.addAttribute("deviceInfo", deviceInfoService.find(id));
    return "/deviceInfo/sendCommand";
  }
  /**
   * 平台下发指令返回页面
   */
  @RequestMapping(value = "/sendCommandResult", method = RequestMethod.POST)
  public String sendCommandResult(String deviceNo, CommandType commandType, 
      SendCommandParameter parameter, ModelMap model) {
    ObjectMapper objectMapper = new ObjectMapper ();
    List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>> ();
    Map<String, Object> map =new HashMap<String, Object> ();
    map.put ("deviceId", deviceNo);
    map.put ("commandType", commandType);
    if (commandType == CommandType.sm) {
      map.put ("parameter", parameter.getParameterSM());
    }else if (commandType == CommandType.nobd) {
      map.put ("parameter", parameter.getParameterNOBD());
    }
    paramList.add(map);
    try{
      String params = objectMapper.writeValueAsString(paramList);
      String response = ApiUtils.post(setting.getObdServiceUrl ()+"receiverData/sendTCPCommand.jhtml", "UTF-8", "UTF-8", params);
      if (response != null){
        if (response != null && !response.equals (""))
        {
          JsonNode rootNode = objectMapper.readTree(response);
          JsonNode msgNode = rootNode.path ("msg");
          String msg = objectMapper.writeValueAsString(msgNode);
          List<SendCommandResponse> responseList = objectMapper.readValue (msg, new TypeReference<List<SendCommandResponse>>() {});
          if (responseList != null && responseList.size() > 0) {
            model.put("response", responseList.get(0));
          }
          model.put("commandType", commandType);
        }
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return "/deviceInfo/sendCommandResult";
  }
  /**
   * 命令类型枚举
   */
  public enum CommandType {
    /** 标定初始里程，参数 parameter为设定的初始里程，如果不传参数，代码逻辑默认设定parameter=0*/
    sm,
    /** 车辆设防 ，无参数*/
    arm,
    /** 车辆撤防 ，无参数 */
    disarm,
    /** 断油电 */
    ion,
    /** 恢复油电 */
    ioff,
    /** nobd模式 ,参数 parameter=1 为nobd模式，parameter=0 为obd模式*/
    nobd,
    /** 终端(硬件)重启 ，无参数*/
    rhw
  }
}
