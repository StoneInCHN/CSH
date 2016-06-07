package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csh.controller.base.BaseController;
import com.csh.service.CarWashingCouponService;

@RequestMapping("console/carWashingCoupon")
@Controller("carWashingCouponController")
public class CarWashingCouponController extends BaseController{

  @Resource(name="carWashingCouponServiceImpl")
  private CarWashingCouponService carWashingCouponService;
  
}
