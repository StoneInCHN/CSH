package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.Coupon;
import com.csh.entity.commonenum.CommonEnum.CouponSendType;
import com.csh.entity.commonenum.CommonEnum.CouponType;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Pageable;
import com.csh.service.CouponService;

@RequestMapping("console/coupon")
@Controller("couponController")
public class CouponController extends BaseController {

  @Resource(name = "couponServiceImpl")
  private CouponService couponService;


  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/coupon/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Coupon coupon) {
    coupon.setType(CouponType.COMMON);
    coupon.setRemainNum(coupon.getCounts());
    coupon.setSendType(CouponSendType.NORMAL);
    coupon.setSystemType(SystemType.OPERATION);
   /* coupon.setIsEnabled(true);*/
    couponService.save(coupon);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    Coupon coupon = couponService.find(id);
    model.addAttribute("coupon", coupon);
    if (CouponSendType.REG.equals(coupon.getSendType())
        || CouponSendType.DEVICEBIND.equals(coupon.getSendType())) {
      return "/coupon/edit4init";
    } else {
      return "/coupon/edit";
    }

  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Coupon coupon) {
    Coupon temp = couponService.find(coupon.getId());
    if (CouponSendType.REG ==temp.getSendType() || CouponSendType.DEVICEBIND ==temp.getSendType()) {
    	temp.setAmount(coupon.getAmount());
	}
    temp.setIsEnabled(coupon.getIsEnabled());
    temp.setDeadlineTime(coupon.getDeadlineTime());
    couponService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("systemType", SystemType.OPERATION));
    pageable.setFilters(filters);
    model.addAttribute("page", couponService.findPage(pageable));
    return "/coupon/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      couponService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }



}
