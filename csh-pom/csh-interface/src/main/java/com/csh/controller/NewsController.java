package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.News;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.NewsRequest;
import com.csh.service.EndUserService;
import com.csh.service.NewsService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


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
   * 获取新闻列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getNewsList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getMsgList(@RequestBody BaseRequest req) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Integer pageSize = req.getPageSize();
    Integer pageNumber = req.getPageNumber();
    String token = req.getToken();
    Long userId = req.getUserId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    Page<News> newsList = newsService.findPage(pageable);

    PageResponse pageInfo = new PageResponse();
    pageInfo.setPageNumber(pageNumber);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal((int) newsList.getTotal());


    String[] propertys = {"id", "title", "content"};

    List<Map<String, Object>> result =
        FieldFilterUtils.filterCollectionMap(propertys, newsList.getContent());

    response.setMsg(result);
    response.setPage(pageInfo);

    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

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
