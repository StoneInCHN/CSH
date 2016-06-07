package com.csh.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.CarWashingCoupon;
import com.csh.entity.Coupon;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.CouponOverDueType;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.entity.commonenum.CommonEnum.CouponType;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarWashingCouponService;
import com.csh.service.TenantAccountService;
import com.csh.utils.DateTimeUtils;

@RequestMapping("console/carWashingCoupon")
@Controller("carWashingCouponController")
public class CarWashingCouponController extends BaseController {

  @Resource(name = "carWashingCouponServiceImpl")
  private CarWashingCouponService carWashingCouponService;

  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/carWashingCoupon", method = RequestMethod.GET)
  public String list(ModelMap model) {
    List<CarWashingCoupon> lists = carWashingCouponService.findAll(true);
    if (lists !=null && lists.size() >0) {
      model.addAttribute("isCreate", true);
    }else{
      model.addAttribute("isCreate", false);
    }
    return "/carWashingCoupon/carWashingCoupon";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    return "carWashingCoupon/edit";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(CarWashingCoupon carWashingCoupon) {
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo ();
    carWashingCoupon.setSystemType(SystemType.ENTERPRISE);
    carWashingCoupon.setTenantID(tenantInfo.getId());
    carWashingCoupon.setTenantName(tenantInfo.getTenantName());
    carWashingCouponService.save(carWashingCoupon);
    return SUCCESS_MESSAGE;
  }

  
  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<CarWashingCoupon> list (Model model, Pageable pageable){
    return carWashingCouponService.findPage (pageable,true);
  }
  

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      try {
        carWashingCouponService.delete(ids);
      } catch (Exception e) {
        e.printStackTrace();
        return Message.error("csh.delete.fail");
      }
    }
    return SUCCESS_MESSAGE;
  }

}
