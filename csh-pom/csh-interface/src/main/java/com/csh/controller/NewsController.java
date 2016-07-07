package com.csh.controller;

import java.util.ArrayList;
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
import com.csh.entity.EndUser;
import com.csh.entity.News;
import com.csh.entity.NewsCategory;
import com.csh.entity.NewsComment;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseRequest;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.base.ResponseOne;
import com.csh.json.request.NewsRequest;
import com.csh.service.EndUserService;
import com.csh.service.NewsCategoryService;
import com.csh.service.NewsCommentService;
import com.csh.service.NewsService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


/**
 * Controller - 新闻资讯
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

  @Resource(name = "newsCategoryServiceImpl")
  private NewsCategoryService newsCategoryService;

  @Resource(name = "newsCommentServiceImpl")
  private NewsCommentService newsCommentService;


  /**
   * 获取新闻类别信息
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getNewsType", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getNewsType(
      @RequestBody BaseRequest req) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    String token = req.getToken();
    Long userId = req.getUserId();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("isEnabled", Operator.eq, true);
    filters.add(filter);
    List<NewsCategory> newsCategories = newsCategoryService.findList(null, filters, null);

    String[] properties = {"id", "name"};
    List<Map<String, Object>> result =
        FieldFilterUtils.filterCollectionMap(properties, newsCategories);

    response.setMsg(result);

    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 获取新闻列表
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/getNewsList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getMsgList(@RequestBody NewsRequest req) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Integer pageSize = req.getPageSize();
    Integer pageNumber = req.getPageNumber();
    String token = req.getToken();
    Long userId = req.getUserId();
    Long categoryId = req.getCategoryId();

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
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("isEnabled", Operator.eq, true);
    filters.add(filter);
    if (categoryId != null) {
      NewsCategory category = newsCategoryService.find(categoryId);
      Filter categoryFilter = new Filter("newsCategory", Operator.eq, category);
      filters.add(categoryFilter);
    }
    pageable.setFilters(filters);
    pageable.setOrderProperty("modifyDate");
    pageable.setOrderDirection(Direction.desc);
    Page<News> newsList = newsService.findPage(pageable);

    PageResponse pageInfo = new PageResponse();
    pageInfo.setPageNumber(pageNumber);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal((int) newsList.getTotal());


    String[] propertys =
        {"id", "title", "subTitle", "modifyDate", "imgUrl", "readCounts", "likeCounts",
            "commentCounts"};
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
   * 查看新闻详情
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/readNewsDetail", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> readNewsDetail(@RequestBody NewsRequest req) {
    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();
    String token = req.getToken();
    Long userId = req.getUserId();
    Long newsId = req.getNewsId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    News news = newsService.find(newsId);
    news.setReadCounts(news.getReadCounts() + 1);
    newsService.update(news);

    String[] properties =
        {"id", "title", "content", "modifyDate", "readCounts", "likeCounts", "commentCounts"};
    Map<String, Object> result = FieldFilterUtils.filterEntityMap(properties, news);
    result.put("category", news.getNewsCategory().getName());

    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("news", Operator.eq, news);
    filters.add(filter);
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("createDate", Direction.desc);
    orderings.add(ordering);
    List<NewsComment> commentList = newsCommentService.findList(null, filters, orderings);
    String[] commentProperties = {"userName", "content", "createDate", "photoUrl"};
    List<Map<String, Object>> comments =
        FieldFilterUtils.filterCollectionMap(commentProperties, commentList);
    result.put("comment", comments);
    result.put("commentCounts", comments.size());
    response.setMsg(result);

    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 新闻资讯评论
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/doComment", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> doComment(@RequestBody NewsRequest req) {
    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    String token = req.getToken();
    Long userId = req.getUserId();
    Long newsId = req.getNewsId();
    String commentStr = req.getComment();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    News news = newsService.find(newsId);
    NewsComment comment = new NewsComment();
    comment.setContent(commentStr);
    comment.setNews(news);
    comment.setPhotoUrl(endUser.getPhoto());
    comment.setUserName(endUser.getNickName() != null ? endUser.getNickName() : endUser
        .getUserName());
    news.setCommentCounts(news.getCommentCounts() + 1);
    news.getNewsComments().add(comment);
    newsService.update(news);


    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("news", Operator.eq, news);
    filters.add(filter);
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("createDate", Direction.desc);
    orderings.add(ordering);
    List<NewsComment> commentList = newsCommentService.findList(null, filters, orderings);
    String[] commentProperties = {"userName", "content", "createDate", "photoUrl"};
    List<Map<String, Object>> comments =
        FieldFilterUtils.filterCollectionMap(commentProperties, commentList);
    response.setMsg(comments);
    String newtoken = TokenGenerator.generateToken(token);
    endUserService.createEndUserToken(newtoken, userId);
    response.setDesc(news.getCommentCounts().toString());
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 新闻资讯点赞
   * 
   * @param req
   * @return
   */
  @RequestMapping(value = "/doLikeClick", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse doLikeClick(@RequestBody NewsRequest req) {
    BaseResponse response = new BaseResponse();
    String token = req.getToken();
    Long userId = req.getUserId();
    Long newsId = req.getNewsId();
    Integer doLikeOpr = req.getDoLikeOpr();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    News news = newsService.find(newsId);
    news.setLikeCounts(news.getLikeCounts() + doLikeOpr);
    newsService.update(news);

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
