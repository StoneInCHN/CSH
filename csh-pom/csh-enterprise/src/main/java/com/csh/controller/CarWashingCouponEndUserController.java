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
import com.csh.entity.CarWashingCoupon;
import com.csh.entity.CarWashingCouponEndUser;
import com.csh.entity.EndUser;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarWashingCouponEndUserService;
import com.csh.service.CarWashingCouponService;
import com.csh.service.EndUserService;
import com.csh.utils.SpringUtils;

@RequestMapping("console/carWashingCouponEndUser")
@Controller("carWashingCouponEndUserController")
public class CarWashingCouponEndUserController extends BaseController {

  @Resource(name = "carWashingCouponServiceImpl")
  private CarWashingCouponService carWashingCouponService;

  @Resource(name = "carWashingCouponEndUserServiceImpl")
  private CarWashingCouponEndUserService carWashingCouponEndUserService;

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @RequestMapping(value = "/carWashingCouponEndUser", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/carWashingCouponEndUser/carWashingCouponEndUser";
  }


  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long endUserID, Integer counts, Long id) {
    if (endUserID == null || counts == null || id == null) {
      return ERROR_MESSAGE;
    }
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("carWashingCoupon", id));
    filters.add(Filter.eq("endUser", endUserID));
    List<CarWashingCouponEndUser> lists =
        carWashingCouponEndUserService.findList(null, filters, null);
    if (lists != null && lists.size() > 0) {
      return Message.warn(SpringUtils.getMessage("csh.carWashingCoupon.endUser.publish"));
    }
    EndUser endUser = endUserService.find(endUserID);
    CarWashingCoupon carWashingCoupon = carWashingCouponService.find(id);
    if (counts > 0) {
      for (int i = 0; i < counts; i++) {
        CarWashingCouponEndUser carWashingCouponEndUser = new CarWashingCouponEndUser();
        carWashingCouponEndUser.setCarWashingCoupon(carWashingCoupon);
        carWashingCouponEndUser.setEndUser(endUser);
        carWashingCouponEndUser.setIsUsed(false);
        carWashingCouponEndUser.setRemark(carWashingCoupon.getRemark());
        carWashingCouponEndUserService.save(carWashingCouponEndUser);
      }
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<CarWashingCouponEndUser> list(Long id, Pageable pageable) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("carWashingCoupon", id));
    return carWashingCouponEndUserService.findPage(pageable);
  }

}
