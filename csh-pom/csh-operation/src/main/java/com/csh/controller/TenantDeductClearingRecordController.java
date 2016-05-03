package com.csh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantDeductClearingRecord;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantDeductClearingRecordService;

@RequestMapping("console/tenantDeductClearingRecord")
@Controller("tenantDeductClearingRecordController")
public class TenantDeductClearingRecordController extends BaseController {

  @Resource(name = "tenantDeductClearingRecordServiceImpl")
  private TenantDeductClearingRecordService tenantDeductClearingRecordService;

  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("tenantDeductClearingRecord", tenantDeductClearingRecordService.find(id));
    return "/tenantDeductClearingRecord/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable,Date beginDate, Date endDate, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    if (beginDate != null) {
      filters.add(Filter.ge("createDate", beginDate));
    }
    if (endDate != null) {
      filters.add(Filter.le("createDate", endDate));
    }
    pageable.setFilters(filters);
    model.addAttribute("beginDate", beginDate);
    model.addAttribute("endDate", endDate);
    model.addAttribute("page", tenantDeductClearingRecordService.findPage(pageable));
    return "/tenantDeductClearingRecord/list";
  }


  /**
   * 修改状态
   */
  @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
  public @ResponseBody Message changeStatus(Long id) {
    try {
      TenantDeductClearingRecord clearingRecord = tenantDeductClearingRecordService.find(id);
      clearingRecord.setClearingStatus(ClearingStatus.PAID);
      tenantDeductClearingRecordService.update(clearingRecord);
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return ERROR_MESSAGE;
    }
  }


}
