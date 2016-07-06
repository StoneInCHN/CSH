package com.csh.estore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.entity.estore.DeliveryCorp;
import com.csh.entity.estore.ShippingMethod;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeliveryCorpService;
import com.csh.service.FileService;
import com.csh.service.ShippingMethodService;
import com.csh.utils.SettingUtils;
import com.csh.utils.SpringUtils;

/**
 * 配送方式
 *
 */
@Controller
@RequestMapping ("console/shippingMethod")
public class ShippingMethodController extends BaseController {

  
  @Resource (name = "shippingMethodServiceImpl")
  private ShippingMethodService shippingMethodService;
  
  @Resource (name = "deliveryCorpServiceImpl")
  private DeliveryCorpService deliveryCorpService;
  
  @Resource (name = "fileServiceImpl")
  private FileService fileService;
  
  
  /**
   * 配送方式页面
   */
  @RequestMapping (value = "/shippingMethod", method = RequestMethod.GET)
  public String config(ModelMap model) {
    List<DeliveryCorp> deliveryCorps = deliveryCorpService.findAll();
    model.put("deliveryCorps", deliveryCorps);
    return "estore/shippingMethod/shippingMethod";
  }
  
  
  /**
   * 配送方式列表
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ShippingMethod> shippingMethodList (Pageable pageable,String nameSearch, ModelMap model) {
      if (nameSearch == null) {
        return shippingMethodService.findPage(pageable,true);
      }else {
        return shippingMethodService.findPageByFilter(nameSearch, pageable,true);
      }
  }
  /**
   * 添加配送方式
   */
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message addShippingMethod(@RequestParam("iconImage") MultipartFile iconImage,ShippingMethod shippingMethod,
       Long deliveryCorpId) {
    String iconUrl = null;
    if (iconImage != null && fileService.isValid(FileType.image, iconImage)) {
        Setting setting = SettingUtils.get();
        if (iconImage.getSize() > setting.getImageMaxSize()) {
           return Message.error(SpringUtils.getMessage("csh.limit.imageMaxSize"));
        }
        iconUrl = fileService.saveImage(iconImage, ImageType.SHIPPINGMETHODICON);
    }
    shippingMethod.setIcon(iconUrl);
    if (deliveryCorpId != null) {
      DeliveryCorp deliveryCorp = deliveryCorpService.find(deliveryCorpId);
      if (deliveryCorp !=  null) {
        shippingMethod.setDefaultDeliveryCorp(deliveryCorp);
      }
    }
    shippingMethod.setOrders(null);
    shippingMethodService.save(shippingMethod,true);
    return SUCCESS_MESSAGE;
  } 
  
  /**
   * 编辑/查看 配送方式
   */
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String shippingMethodDetails(ModelMap model, Long id, String path) {
    ShippingMethod shippingMethod = shippingMethodService.find (id);
    model.put("shippingMethod", shippingMethod);
    List<DeliveryCorp> deliveryCorps = deliveryCorpService.findAll();
    model.put("deliveryCorps", deliveryCorps);
    return "estore/shippingMethod/" + path;
  }
  /**
   * 更新配送方式
   */
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message updateShippingMethod(@RequestParam("iconImage") MultipartFile iconImage,
      ShippingMethod shippingMethod, Long deliveryCorpId) {   
    if (iconImage != null && fileService.isValid(FileType.image, iconImage)) {
      String iconUrl = null;
        Setting setting = SettingUtils.get();
        if (iconImage.getSize() > setting.getImageMaxSize()) {
           return Message.error(SpringUtils.getMessage("csh.limit.imageMaxSize"));
        }
        iconUrl = fileService.saveImage(iconImage, ImageType.SHIPPINGMETHODICON);
        shippingMethod.setIcon(iconUrl);
    }
    if (deliveryCorpId != null) {
      DeliveryCorp deliveryCorp = deliveryCorpService.find(deliveryCorpId);
      if (deliveryCorp !=  null) {
        shippingMethod.setDefaultDeliveryCorp(deliveryCorp);
      }
    }
    shippingMethodService.update(shippingMethod,"tenantID","orders");
    return SUCCESS_MESSAGE;
  } 
  /**
   * 删除配送方式
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message deleteShippingMethod (Long[] ids) {
    if (ids != null) {
      shippingMethodService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
