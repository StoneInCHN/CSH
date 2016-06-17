package com.csh.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjuna.ats.internal.arjuna.objectstore.jdbc.accessors.accessor;
import com.csh.beans.Message;
import com.csh.beans.Setting;
import com.csh.controller.base.BaseController;
import com.csh.entity.News;
import com.csh.entity.NewsCategory;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.NewsCategoryService;
import com.csh.service.NewsService;
import com.csh.utils.ApiUtils;
import com.csh.utils.JsonUtil;
import com.csh.utils.SettingUtils;

@RequestMapping("console/news")
@Controller("newsController")
public class NewsController extends BaseController {

  @Resource(name = "newsServiceImpl")
  private NewsService newsService;

  @Resource(name = "newsCategoryServiceImpl")
  private NewsCategoryService newsCategoryService;

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  
  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    model.addAttribute("newsCategorys", newsCategoryService.findAll());
    return "/news/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(News news,Long newsCategoryId) {
    NewsCategory newsCategory =newsCategoryService.find(newsCategoryId);
    news.setNewsCategory(newsCategory);
    news.setPublishReminder(true);
    news.setUserName(adminService.getCurrentUsername());
    newsService.save(news);
    if (news.getId() !=null) {
      try {
        Setting setting = SettingUtils.get();
        Map<String, Object> params = new HashMap<String, Object>();
        params.putIfAbsent("title", news.getTitle());
        params.putIfAbsent("contentUrl",setting.getNewsDetailsUrl()+"?id="+news.getId());
        String url = setting.getNewsPushUrl();
        String data =JsonUtil.getJsonString4JavaPOJO(params);
        ApiUtils.post(url,data);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("newsCategorys", newsCategoryService.findAll());
    model.addAttribute("news", newsService.find(id));
    return "/news/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(News news,Long newsCategoryId) {
    News temp = newsService.find(news.getId());
    temp.setNewsCategory(newsCategoryService.find(newsCategoryId));
    temp.setTitle(news.getTitle());
    temp.setContent(news.getContent());
    newsService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", newsService.findPage(pageable));
    return "/news/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      newsService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    if (id == null) {
      return ERROR_VIEW;
    }
    model.addAttribute("news", newsService.find(id));
    return "/news/details";
  }

}
