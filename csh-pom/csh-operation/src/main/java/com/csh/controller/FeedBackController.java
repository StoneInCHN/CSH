package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.framework.paging.Pageable;
import com.csh.service.FeedBackService;

@Controller("feedBackController")
@RequestMapping("console/feedBack")
public class FeedBackController extends BaseController {

  @Resource(name = "feedBackServiceImpl")
  private FeedBackService feedBackService;


  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", feedBackService.findPage(pageable));
    return "/feedBack/list";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id ,ModelMap model) {
    model.addAttribute("feedBack", feedBackService.find(id));
    return "/feedBack/details";
  }
  
  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        feedBackService.delete(ids);
      }

    }
    return SUCCESS_MESSAGE;
  }

}
