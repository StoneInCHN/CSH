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
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.WalletRecordService;

@RequestMapping("console/walletRecord")
@Controller("walletRecordController")
public class WalletRecordController extends BaseController{

  @Resource(name="walletRecordServiceImpl")
  private WalletRecordService walletRecordService;
  
  
  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model,Date beginDate, Date endDate) {
    
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
      pageable.setFilters(filters);
    }
    model.addAttribute("beginDate", beginDate);
    model.addAttribute("endDate", endDate);
    model.addAttribute("page", walletRecordService.findPage(pageable));
    return "/walletRecord/list";
  }
  
    
}
