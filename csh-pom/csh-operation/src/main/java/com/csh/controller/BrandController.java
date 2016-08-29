package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.entity.estore.Brand;
import com.csh.framework.paging.Pageable;
import com.csh.service.BrandService;
import com.csh.service.FileService;

@Controller("brandController")
@RequestMapping("console/brand")
public class BrandController extends BaseController {

  @Resource(name = "brandServiceImpl")
  private BrandService brandService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  
  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/brand/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Brand brand) {
    if (!isValid(brand)) {
      return ERROR_VIEW;
    }
    if (brand.getImgFile() != null && fileService.isValid(FileType.image,brand.getImgFile())) {
      String imgUrl = fileService.saveImage(brand.getImgFile(), ImageType.BrandLogo,false);
      brand.setLogo(imgUrl);;
    }
    brandService.save(brand);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("brand", brandService.find(id));
    return "/brand/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Brand brand) {
    if (!isValid(brand)) {
      return ERROR_VIEW;
    }
    Brand temp = brandService.find(brand.getId());
    temp.setName(brand.getName());
    if (brand.getIntroduction() !=null) {
      temp.setIntroduction(brand.getIntroduction());
    }
    temp.setUrl(brand.getUrl());
    temp.setOrder(brand.getOrder());
    if (brand.getImgFile() != null && fileService.isValid(FileType.image,brand.getImgFile())) {
      String imgUrl = fileService.saveImage(brand.getImgFile(), ImageType.BrandLogo,false);
      temp.setLogo(imgUrl);
    }
    brandService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", brandService.findPage(pageable));
    return "/brand/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        Brand brand = brandService.find(id);
        if (brand.getProducts()!=null && brand.getProducts().size() <1 ) {
          brandService.delete(id);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }


}
