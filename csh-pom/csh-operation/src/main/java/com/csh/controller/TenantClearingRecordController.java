package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.entity.Admin;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.TenantClearingRecordService;

@RequestMapping("console/clearingRecord")
@Controller("tenantClearingRecordController")
public class TenantClearingRecordController extends BaseController{

  @Resource(name="tenantClearingRecordServiceImpl")
  private TenantClearingRecordService tenantClearingRecordService;
  
  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  
  
  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("clearingRecord", tenantClearingRecordService.find(id));
    return "/clearingRecord/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable,Date beginDate,Date endDate, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    if (beginDate !=null) {
      Filter beginDateFilter = new Filter();
      beginDateFilter.setProperty("createDate");
      beginDateFilter.setValue(beginDate);
      beginDateFilter.setOperator(Operator.ge);
      filters.add(beginDateFilter);
    }
    if (endDate !=null) {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty("createDate");
      endDateFilter.setValue(endDate);
      endDateFilter.setOperator(Operator.le);
      filters.add(endDateFilter);
    }
    pageable.setFilters(filters);
    model.addAttribute("beginDate", beginDate);
    model.addAttribute("endDate", endDate);
    model.addAttribute("page", tenantClearingRecordService.findPage(pageable));
    return "/clearingRecord/list";
  }
  
  /**
   * 列表
   */
  @RequestMapping(value = "/list4distributor", method = RequestMethod.GET)
  public String list4distributor(Pageable pageable,Date beginDate,Date endDate, ModelMap model) {
    Admin admin = adminService.getCurrent();
    List<Filter> filters = new ArrayList<Filter>();
    if (admin.getIsDistributor() != null && admin.getIsDistributor()
        && admin.getDistributor() != null) {
      Filter distributorFilter = new Filter();
      distributorFilter.setProperty("distributorId");
      distributorFilter.setValue(admin.getDistributor().getId());
      distributorFilter.setOperator(Operator.eq);
      filters.add(distributorFilter);
    }
    if (beginDate !=null) {
      Filter beginDateFilter = new Filter();
      beginDateFilter.setProperty("createDate");
      beginDateFilter.setValue(beginDate);
      beginDateFilter.setOperator(Operator.ge);
      filters.add(beginDateFilter);
    }
    if (endDate !=null) {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty("createDate");
      endDateFilter.setValue(endDate);
      endDateFilter.setOperator(Operator.le);
      filters.add(endDateFilter);
    }
    pageable.setFilters(filters);
    model.addAttribute("page", tenantClearingRecordService.findPage(pageable));
    return "/tenantInfo/list4distributor";
  }
  
  
}
