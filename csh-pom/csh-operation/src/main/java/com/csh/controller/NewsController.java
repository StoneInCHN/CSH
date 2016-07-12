package com.csh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.News;
import com.csh.entity.NewsCategory;
import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdminService;
import com.csh.service.FileService;
import com.csh.service.NewsCategoryService;
import com.csh.service.NewsService;
import com.csh.utils.ApiUtils;
import com.csh.utils.JsonUtil;
import com.csh.utils.SettingUtils;
import com.csh.utils.SpringUtils;

@RequestMapping("console/news")
@Controller("newsController")
public class NewsController extends BaseController {

  @Resource(name = "newsServiceImpl")
  private NewsService newsService;

  @Resource(name = "newsCategoryServiceImpl")
  private NewsCategoryService newsCategoryService;

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  
  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  
  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("isEnabled", true));
    model.addAttribute("newsCategorys", newsCategoryService.findList(null, filters, null));
    return "/news/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(News news,Long newsCategoryId) {
    String imgUrl = "";
    if (news.getImgFile() != null && fileService.isValid(FileType.image,news.getImgFile())) {
      imgUrl = fileService.saveImage(news.getImgFile(), ImageType.NEWS);
    }
    news.setImgUrl(imgUrl);
    NewsCategory newsCategory =newsCategoryService.find(newsCategoryId);
    news.setNewsCategory(newsCategory);
    news.setPublishReminder(true);
    news.setUserName(adminService.getCurrentUsername());
    news.setHasReminder(false);
    newsService.save(news);
    if (news.getId() !=null && news.getIsEnabled() && news.getPublishReminder()) {
      try {
        Setting setting = SettingUtils.get();
        Map<String, Object> params = new HashMap<String, Object>();
        params.putIfAbsent("title", news.getTitle());
        params.putIfAbsent("newsId", news.getId());
        params.putIfAbsent("contentUrl",setting.getNewsDetailsUrl()+"?id="+news.getId());
        String url = setting.getNewsPushUrl();
        String data =JsonUtil.getJsonString4JavaPOJO(params);
        ApiUtils.post(url,data);
        news.setHasReminder(true);
        newsService.update(news);
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
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("isEnabled", true));
    model.addAttribute("newsCategorys", newsCategoryService.findList(null, filters, null));
    model.addAttribute("news", newsService.find(id));
    return "/news/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(News news,Long newsCategoryId) {
    News temp = newsService.find(news.getId());
    if (news.getImgFile() != null && fileService.isValid(FileType.image,news.getImgFile())) {
      String imgUrl = fileService.saveImage(news.getImgFile(), ImageType.NEWS);
      temp.setImgUrl(imgUrl);
    }
    temp.setNewsCategory(newsCategoryService.find(newsCategoryId));
    temp.setTitle(news.getTitle());
    temp.setSubTitle(news.getSubTitle());
    temp.setIsEnabled(news.getIsEnabled());
    temp.setPublishReminder(news.getPublishReminder());
    temp.setContent(news.getContent());
    newsService.update(temp);
    if (!temp.getHasReminder() && temp.getIsEnabled() && temp.getPublishReminder()) {
      try {
        Setting setting = SettingUtils.get();
        Map<String, Object> params = new HashMap<String, Object>();
        params.putIfAbsent("title", news.getTitle());
        params.putIfAbsent("newsId", news.getId());
        params.putIfAbsent("contentUrl",setting.getNewsDetailsUrl()+"?id="+news.getId());
        String url = setting.getNewsPushUrl();
        String data =JsonUtil.getJsonString4JavaPOJO(params);
        ApiUtils.post(url,data);
        temp.setHasReminder(true);
        newsService.update(news);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
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
