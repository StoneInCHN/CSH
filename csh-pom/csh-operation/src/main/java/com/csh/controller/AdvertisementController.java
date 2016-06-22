package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.Advertisement;
import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdvertisementService;
import com.csh.service.FileService;
import com.csh.utils.SettingUtils;
import com.csh.utils.SpringUtils;

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
  public@ResponseBody Message save(Advertisement advertisement) {
    if (!isValid(advertisement)) {
      return ERROR_MESSAGE;
    }
    String advImageUrl = "";
    if (advertisement.getAdvImage() != null && fileService.isValid(FileType.image, advertisement.getAdvImage())) {
    	Setting setting = SettingUtils.get();
    	if (advertisement.getAdvImage().getSize() > setting.getImageMaxSize()) {
    		  return Message.error(SpringUtils.getMessage("csh.advertisement.imageMaxSize"));
		}
      advImageUrl = fileService.saveImage(advertisement.getAdvImage(), ImageType.ADVERTISEMENT);
    }else {
      return Message.error(SpringUtils.getMessage("csh.advertisement.image.file.type.error"));
    }

    advertisement.setAdvImageUrl(advImageUrl);
    advertisement.setSystemType(SystemType.OPERATION);
    advertisementService.save(advertisement);
    return Message.success("csh.advertisement.image.file.upload.success");
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("advertisement", advertisementService.find(id));
    return  "/advertisement/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Advertisement advertisement) {
    if (!isValid(advertisement)) {
      return ERROR_MESSAGE;
    }
    if(advertisement.getAdvImage()!=null && advertisement.getAdvImage().getSize() >0){
      if (fileService.isValid(FileType.image, advertisement.getAdvImage())) {
          Setting setting = SettingUtils.get();
          if (advertisement.getAdvImage().getSize() > setting.getImageMaxSize()) {
                return Message.error(SpringUtils.getMessage("csh.advertisement.imageMaxSize"));
          }
       String advImageUrl = fileService.saveImage(advertisement.getAdvImage(), ImageType.ADVERTISEMENT);
       advertisement.setAdvImageUrl(advImageUrl);
      }else {
        return Message.error(SpringUtils.getMessage("csh.advertisement.image.file.type.error"));
      }
    }
   
    advertisementService.update(advertisement,"tenantId","createDate","systemType");
    return Message.success("csh.advertisement.image.file.upload.success");
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable,Status status, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("status", status));
    pageable.setFilters(filters);
    model.addAttribute("page", advertisementService.findPage(pageable));
    model.addAttribute("status", status);
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
