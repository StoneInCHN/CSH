package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.controller.base.MobileBaseController;
import com.csh.json.base.BaseResponse;
import com.csh.json.request.NewsRequest;
import com.csh.service.EndUserService;
import com.csh.service.NewsService;


/**
 * Controller - 新闻
 * 
 * @author sujinxuan
 *
 */
@Controller("NewsController")
@RequestMapping("/news")
public class NewsController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "newsServiceImpl")
  private NewsService newsService;

  /**
   * 推送新闻
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/pushNews", method = RequestMethod.POST)
  public @ResponseBody BaseResponse pushMsg(@RequestBody NewsRequest newsReq) {
    BaseResponse response = new BaseResponse();

    String title = newsReq.getTitle();
    String contentUrl = newsReq.getContentUrl();

    // 极光推送消息
    newsService.jpushNews(title, contentUrl);

    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }
}
