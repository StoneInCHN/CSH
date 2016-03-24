package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.Advertisement;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdvertisementService;
import com.csh.service.FileService;

@RequestMapping("console/advertisement")
@Controller("advertisementController")
public class AdvertisementController extends BaseController{

  @Resource(name="advertisementServiceImpl")
  private AdvertisementService advertisementService;
  
  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  
  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/advertisement/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Advertisement advertisement) {
    if (!isValid(advertisement)) {
      return ERROR_VIEW;
    }
    String advImageUrl = "";
    if (advertisement.getAdvImage() != null) {
      advImageUrl = fileService.saveImage(advertisement.getAdvImage(), ImageType.ADVERTISEMENT);
    }
    advertisement.setAdvImageUrl(advImageUrl);
    advertisement.setSystemType(SystemType.ENTERPRISE);
    advertisementService.save(advertisement);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("advertisement", advertisementService.find(id));
    return "/advertisement/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Advertisement advertisement) {
    if (!isValid(advertisement)) {
      return ERROR_VIEW;
    }
    if(advertisement.getAdvImage()!=null && advertisement.getAdvImage().getSize() >0){
     String advImageUrl = fileService.saveImage(advertisement.getAdvImage(), ImageType.ADVERTISEMENT);
     advertisement.setAdvImageUrl(advImageUrl);
    }
    advertisementService.update(advertisement,"tenantId","createDate","systemType");
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", advertisementService.findPage(pageable));
    return "/advertisement/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      advertisementService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  
}
