package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.FaultCode;
import com.csh.framework.paging.Pageable;
import com.csh.service.FaultCodeService;

@Controller("faultCodeController")
@RequestMapping("console/faultCode")
public class FaultCodeController extends BaseController {


  @Resource(name = "faultCodeServiceImpl")
  private FaultCodeService faultCodeService;


  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    return "/faultCode/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(FaultCode faultCode) {
    faultCodeService.save(faultCode);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("faultCode", faultCodeService.find(id));
    return "/faultCode/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(FaultCode faultCode) {
    faultCodeService.update(faultCode);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", faultCodeService.findPage(pageable));
    return "/faultCode/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      faultCodeService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
}
