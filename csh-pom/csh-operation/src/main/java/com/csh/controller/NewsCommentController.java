package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.controller.base.BaseController;
import com.csh.entity.NewsComment;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.NewsCommentService;
import com.csh.service.NewsService;

@RequestMapping("console/newsComment")
@Controller("newsCommentController")
public class NewsCommentController extends BaseController {

  @Resource(name = "newsServiceImpl")
  private NewsService newsService;

  @Resource(name = "newsCommentServiceImpl")
  private NewsCommentService newsCommentService;

  @RequestMapping
  public @ResponseBody List<NewsComment> list(Long newsId, Pageable pageable) {
    List<NewsComment> newsComments =new ArrayList<NewsComment>();
    if(newsId ==null){
      return newsComments;
    }
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("news", newsId));
    Page<NewsComment> pages = newsCommentService.findPage(pageable);
    newsComments = pages.getContent();
    return newsComments;
  }

}
