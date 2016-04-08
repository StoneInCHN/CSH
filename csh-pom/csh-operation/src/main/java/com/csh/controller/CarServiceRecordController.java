package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.framework.paging.Pageable;
import com.csh.service.CarServiceRecordService;

@RequestMapping("console/carServiceRecord")
@Controller("carServiceRecordController")
public class CarServiceRecordController extends BaseController{

    @Resource(name="carServiceRecordServiceImpl")
    private CarServiceRecordService carServiceRecordService;
    
    /**
     * 详情
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(Long id, ModelMap model) {
      model.addAttribute("vehicle", carServiceRecordService.find(id));
      return "/carServiceRecord/details";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
      model.addAttribute("page", carServiceRecordService.findPage(pageable));
      return "/carServiceRecord/list";
    }
    
    
}
