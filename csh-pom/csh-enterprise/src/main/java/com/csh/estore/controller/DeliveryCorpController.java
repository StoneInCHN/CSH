package com.csh.estore.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.DeliveryCorp;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.DeliveryCorpService;

/**
 * 物流公司
 *
 */
@Controller
@RequestMapping ("console/deliveryCorp")
public class DeliveryCorpController extends BaseController {

  
  @Resource (name = "deliveryCorpServiceImpl")
  private DeliveryCorpService deliveryCorpService;
  
  
  /**
   * 物流公司页面
   */
  @RequestMapping (value = "/deliveryCorp", method = RequestMethod.GET)
  public String config(ModelMap model) {
    return "estore/deliveryCorp/deliveryCorp";
  }
  
  
  /**
   * 物流公司列表
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DeliveryCorp> deliveryCorpList (Pageable pageable,String nameSearch, ModelMap model) {
      if (nameSearch == null) {
        return deliveryCorpService.findPage(pageable,true);
      }else {
        return deliveryCorpService.findPageByFilter(nameSearch, pageable,true);
      }
      
  }
  /**
   * 添加物流公司
   */
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message addDeliveryCorp(DeliveryCorp deliveryCorp) {
    deliveryCorp.setShippingMethods(null);
    deliveryCorpService.save(deliveryCorp,true);
    return SUCCESS_MESSAGE;
  } 
  
  /**
   * 编辑/查看 物流公司
   */
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String deliveryCorpDetails(ModelMap model, Long id, String path) {
    DeliveryCorp deliveryCorp = deliveryCorpService.find (id);
    model.put ("deliveryCorp", deliveryCorp);
    return "estore/deliveryCorp/" + path;
  }
  /**
   * 更新物流公司
   */
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message updateDeliveryCorp(DeliveryCorp deliveryCorp) {   
    deliveryCorpService.update(deliveryCorp,"tenantID","shippingMethods");
    return SUCCESS_MESSAGE;
  } 
  /**
   * 删除物流公司
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message deleteDeliveryCorp (Long[] ids) {
    if (ids != null) {
      deliveryCorpService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
