package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.Area;
import com.csh.entity.InsuranceCompany;
import com.csh.entity.commonenum.CommonEnum.AreaRegions;
import com.csh.framework.paging.Pageable;
import com.csh.service.AreaService;
import com.csh.service.InsuranceCompanyService;
import com.csh.utils.CommonUtils;

@RequestMapping("console/insurance")
@Controller("insuranceCompanyController")
public class InsuranceCompanyController extends BaseController {

  @Resource(name = "insuranceCompanyServiceImpl")
  private InsuranceCompanyService insuranceCompanyService;

  @Resource(name = "areaServiceImpl")
  private AreaService areaService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    List<InsuranceCompany> categories = insuranceCompanyService.findTree();
    model.addAttribute("categories", categories);
    return "/insurance/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(InsuranceCompany insuranceCompany, Long areaId, Long parentId) {
    if (!isValid(insuranceCompany)) {
      return ERROR_VIEW;
    }
    Area area = areaService.find(areaId);
    Map<AreaRegions, String> map = CommonUtils.spritArea(area);
    insuranceCompany.setProvince(map.get(AreaRegions.province));
    insuranceCompany.setMunicipality(map.get(AreaRegions.municipality));
    insuranceCompany.setPrefecture(map.get(AreaRegions.prefecture));
    insuranceCompany.setParent(insuranceCompanyService.find(parentId));
    insuranceCompany.setArea(area);
    insuranceCompanyService.save(insuranceCompany);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    List<InsuranceCompany> categories = insuranceCompanyService.findTree();
    model.addAttribute("categories", categories);
    model.addAttribute("insuranceCompany", insuranceCompanyService.find(id));
    return "/insurance/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(InsuranceCompany insuranceCompany, Long areaId, Long parentId) {
    if (!isValid(insuranceCompany)) {
      return ERROR_VIEW;
    }
    insuranceCompany.setParent(insuranceCompanyService.find(parentId));
    insuranceCompany.setArea(areaService.find(areaId));

    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    List<InsuranceCompany> categories = insuranceCompanyService.findTree();
    model.addAttribute("categories", categories);
    model.addAttribute("page", insuranceCompanyService.findPage(pageable));
    return "/insurance/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      insuranceCompanyService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }


}
