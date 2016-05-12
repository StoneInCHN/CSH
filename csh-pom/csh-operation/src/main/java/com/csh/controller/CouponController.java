package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.Coupon;
import com.csh.entity.DeviceType;
import com.csh.entity.commonenum.CommonEnum.CouponType;
import com.csh.framework.paging.Pageable;
import com.csh.service.CouponService;

@RequestMapping("console/coupon")
@Controller("couponController")
public class CouponController extends BaseController{

  @Resource(name="couponServiceImpl")
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
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("coupon", couponService.find(id));
    return "/coupon/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(DeviceType deviceType) {
    if (!isValid(deviceType)) {
      return ERROR_VIEW;
    }

    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
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
