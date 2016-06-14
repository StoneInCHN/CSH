package com.csh.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.News;
import com.csh.entity.NewsCategory;
import com.csh.framework.paging.Pageable;
import com.csh.service.NewsCategoryService;

@RequestMapping("console/newsCategory")
@Controller("newsCategoryController")
public class NewsCategoryController extends BaseController {

  @Resource(name = "newsCategoryServiceImpl")
  private NewsCategoryService newsCategoryService;


  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    return "/newsCategory/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(NewsCategory newsCategory) {
    newsCategoryService.save(newsCategory);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("newsCategory", newsCategoryService.find(id));
    return "/newsCategory/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(NewsCategory newsCategory) {
    newsCategoryService.update(newsCategory);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", newsCategoryService.findPage(pageable));
    return "/newsCategory/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        NewsCategory newsCategory = newsCategoryService.find(id);
        Set<News> newsInfos = newsCategory.getNewsInfo();
        if (newsInfos != null && newsInfos.size() > 0) {
          return ERROR_MESSAGE;
        } else {
          newsCategoryService.delete(id);
        }
      }

    }
    return SUCCESS_MESSAGE;
  }

}
