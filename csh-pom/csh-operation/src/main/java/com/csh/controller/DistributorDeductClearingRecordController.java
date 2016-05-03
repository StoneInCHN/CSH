package com.csh.controller;

import java.math.BigDecimal;
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
import com.csh.entity.Admin;
import com.csh.entity.CarServiceDistributorDeductRecord;
import com.csh.entity.Distributor;
import com.csh.entity.DistributorDeductClearingRecord;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.CarServiceDistributorDeductRecordService;
import com.csh.service.DistributorDeductClearingRecordService;
import com.csh.service.DistributorService;

@RequestMapping("console/distributorDeductClearingRecord")
@Controller("distributorDeductClearingRecordController")
public class DistributorDeductClearingRecordController extends BaseController {

  @Resource(name = "distributorDeductClearingRecordServiceImpl")
  private DistributorDeductClearingRecordService distributorDeductClearingRecordService;

  @Resource(name = "carServiceDistributorDeductRecordServiceImpl")
  private CarServiceDistributorDeductRecordService carServiceDistributorDeductRecordService;

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;

  @Resource(name = "distributorServiceImpl")
  private DistributorService distributorService;

  /**
   * 详情
   */
  @RequestMapping(value = "/details4distributor", method = RequestMethod.GET)
  public String details4distributor(Long id, ModelMap model) {
    model.addAttribute("distributorDeductClearingRecord",
        distributorDeductClearingRecordService.find(id));
    return "/distributorDeductClearingRecord/details4distributor";
  }

  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("distributorDeductClearingRecord",
        distributorDeductClearingRecordService.find(id));
    return "/distributorDeductClearingRecord/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, Date beginDate, Date endDate, ModelMap model) {
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
    model.addAttribute("page", distributorDeductClearingRecordService.findPage(pageable));
    return "/distributorDeductClearingRecord/list";
  }


  /**
   * 修改状态
   */
  @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
  public @ResponseBody Message changeStatus(Long id) {
    try {
      DistributorDeductClearingRecord clearingRecord =
          distributorDeductClearingRecordService.find(id);
      clearingRecord.setClearingStatus(ClearingStatus.PAID);
      distributorDeductClearingRecordService.update(clearingRecord);
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return ERROR_MESSAGE;
    }
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list4distributor", method = RequestMethod.GET)
  public String list4distributor(Pageable pageable, Date beginDate, Date endDate, ModelMap model) {
    Admin admin = adminService.getCurrent();
    List<Filter> filters = new ArrayList<Filter>();
    if (admin.getIsDistributor() != null && admin.getIsDistributor()
        && admin.getDistributor() != null) {
      filters.add(Filter.eq("distributor", admin.getDistributor().getId()));
    }
    if (beginDate != null) {
      filters.add(Filter.ge("createDate", beginDate));
    }
    if (endDate != null) {
      filters.add(Filter.le("createDate", endDate));
    }
    pageable.setFilters(filters);
    model.addAttribute("beginDate", beginDate);
    model.addAttribute("endDate", endDate);
    model.addAttribute("page", distributorDeductClearingRecordService.findPage(pageable));
    return "/distributorDeductClearingRecord/list4distributor";
  }


  /**
   * 修改状态
   */
  @RequestMapping(value = "/applyClearingRecord", method = RequestMethod.POST)
  public @ResponseBody Message applyClearingRecord() {
    Admin admin = adminService.getCurrent();
    if (admin.getIsDistributor() != null && admin.getIsDistributor()
        && admin.getDistributor() != null) {
      return  distributorDeductClearingRecordService.createClearingRecord(admin.getDistributor());
     // return SUCCESS_MESSAGE;
    } else {
      return ERROR_MESSAGE;
    }
  }

  /**
   * 结算单预览
   */
  @RequestMapping(value = "/viewClearingRecord", method = RequestMethod.GET)
  public String viewClearingRecord(ModelMap model) {
    DistributorDeductClearingRecord clearRecord = new DistributorDeductClearingRecord();
    Admin admin = adminService.getCurrent();
    if (admin.getIsDistributor() != null && admin.getIsDistributor()
        && admin.getDistributor() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      filters.add(Filter.eq("distributorId", admin.getDistributor().getId()));
      filters.add(Filter.isNull("clearingDate"));

      List<CarServiceDistributorDeductRecord> lists =
          carServiceDistributorDeductRecordService.findList(null, null, filters, null);
      if (lists.size() > 0) {
        Distributor distributor = new Distributor();

        BigDecimal amount = new BigDecimal(0);
        Date currentDate = new Date();
        for (CarServiceDistributorDeductRecord carServiceDistributorDeductRecord : lists) {
          amount = amount.add(carServiceDistributorDeductRecord.getDeductMoney());
          carServiceDistributorDeductRecord.setClearingDate(currentDate);
        }
        clearRecord.setAmountOfCurrentPeriod(amount);
        clearRecord.setClearingStatus(ClearingStatus.UNPAID);
        clearRecord.setDistributor(distributor);
        clearRecord.setCarServiceDistributorDeductRecords(lists);
      }else{
        clearRecord.setCarServiceDistributorDeductRecords(null);
      }
    }
    model.addAttribute("distributorDeductClearingRecord", clearRecord);
    return "/distributorDeductClearingRecord/clearingRecordView";
  }
}
