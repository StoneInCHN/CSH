package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.DeviceType;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeviceInfoService;
import com.csh.service.DeviceTypeService;

@RequestMapping("console/deviceInfo")
@Controller("deviceInfoController")
public class DeviceInfoController extends BaseController {

  @Resource(name = "deviceTypeServiceImpl")
  private DeviceTypeService deviceTypeService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

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
  public String update(DeviceInfo deviceInfo,Long typeId) {
    DeviceInfo info = deviceInfoService.find(deviceInfo.getId());
    DeviceType type = deviceTypeService.find(typeId);
    info.setType(type);
    info.setDeviceNo(deviceInfo.getDeviceNo());
    info.setSimNo(deviceInfo.getSimNo());
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", deviceInfoService.findPage(pageable));
    return "/deviceInfo/list";
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
}
