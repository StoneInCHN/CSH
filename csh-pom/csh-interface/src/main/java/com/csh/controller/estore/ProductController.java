package com.csh.controller.estore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.ProductStatus;
import com.csh.entity.commonenum.CommonEnum.SortType;
import com.csh.entity.estore.Cart;
import com.csh.entity.estore.CartItem;
import com.csh.entity.estore.Parameter;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ProductCategory;
import com.csh.entity.estore.Review;
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
import com.csh.json.request.ProductRequest;
import com.csh.service.EndUserService;
import com.csh.service.ProductCategoryService;
import com.csh.service.ProductService;
import com.csh.service.ReviewService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


/**
 * Controller - 电商商品
 * 
 * @author sujinxuan
 *
 */
@Controller("productController")
@RequestMapping("/estore/product")
public class ProductController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "productCategoryServiceImpl")
  private ProductCategoryService productCategoryService;

  @Resource(name = "productServiceImpl")
  private ProductService productService;

  @Resource(name = "reviewServiceImpl")
  private ReviewService reviewService;


  /**
   * 商品类别
   * 
   * @return
   */
  @RequestMapping(value = "/getCategory", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getCategory(
      @RequestBody ProductRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long categoryId = request.getCategoryId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter categoryFilter = new Filter("parent", Operator.isNotNull, null);
    filters.add(categoryFilter);
    // if (categoryId != null) {
    // Filter categoryFilter = new Filter("parent", Operator.eq, categoryId);
    // filters.add(categoryFilter);
    // } else {
    // Filter categoryFilter = new Filter("parent", Operator.isNull, null);
    // filters.add(categoryFilter);
    // }
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering("order", Direction.asc);
    orderings.add(ordering);
    List<ProductCategory> list = productCategoryService.findList(null, filters, orderings);
    String[] propertys = {"id", "name"};
    List<Map<String, Object>> result = FieldFilterUtils.filterCollectionMap(propertys, list);

    response.setMsg(result);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 商品列表
   * 
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> list(
      @RequestBody ProductRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long categoryId = request.getCategoryId();
    String keyWord = request.getSearchKeyWord();
    SortType sortType = request.getSortType();
    Integer pageSize = request.getPageSize();
    Integer pageNumber = request.getPageNumber();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(ProductController.class)) {
      LogUtil
          .debug(
              ProductController.class,
              "list",
              "Search Product with Params: categoryId: %s, keyWord: %s, sortType: %s,pageNumer: %s, pageSize: %s",
              categoryId, keyWord, sortType, pageNumber, pageSize);
    }

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    SortField sortField = null;

    TermQuery statusTermQuery =
        new TermQuery(new Term("productStatus", ProductStatus.marketed.toString()));
    query.add(statusTermQuery, Occur.MUST);

    if (keyWord != null) {
      try {
        String text = QueryParser.escape(keyWord);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "fullName", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (categoryId != null) {
      ProductCategory productCategory = productCategoryService.find(categoryId);
      if (productCategory.getChildren() != null && productCategory.getChildren().size() > 0) {
        BooleanQuery categoryQuery = new BooleanQuery();
        TermQuery parentTermQuery =
            new TermQuery(new Term("productCategory.id", categoryId.toString()));
        categoryQuery.add(parentTermQuery, Occur.SHOULD);
        for (ProductCategory childCategory : productCategory.getChildren()) {
          TermQuery childTermQuery =
              new TermQuery(new Term("productCategory.id", childCategory.getId().toString()));
          categoryQuery.add(childTermQuery, Occur.SHOULD);
        }
        query.add(categoryQuery, Occur.MUST);
      } else {
        TermQuery categoryTermQuery =
            new TermQuery(new Term("productCategory.id", categoryId.toString()));
        query.add(categoryTermQuery, Occur.MUST);
      }
    }

    if (SortType.RECOMMEND.equals(sortType)) {
      Long tenantId = null;
      EndUser endUser = endUserService.find(userId);
      if (endUser.getDefaultVehicle() != null) {
        tenantId = endUser.getDefaultVehicle().getTenantID();
        if (tenantId != null) {
          TermQuery tenantTermQuery = new TermQuery(new Term("tenantID", tenantId.toString()));
          query.add(tenantTermQuery, Occur.MUST);
        }
      }
    } else if (SortType.PRICEASC.equals(sortType)) {
      sortField = new SortField("price", SortField.STRING, false);
    } else if (sortType == null || SortType.SALESDESC.equals(sortType)) {
      sortField = new SortField("sales", SortField.LONG, true);
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    Page<Product> products = productService.search(query, pageable, analyzer, null, sortField);

    String[] propertys = {"id", "fullName", "price", "image", "stock", "sales"};
    List<Map<String, Object>> result =
        FieldFilterUtils.filterCollectionMap(propertys, products.getContent());
    response.setMsg(result);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) products.getTotal());
    response.setPage(page);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }



  /**
   * 商品详情
   * 
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseOne<Map<String, Object>> details(@RequestBody ProductRequest request) {

    ResponseOne<Map<String, Object>> response = new ResponseOne<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long productId = request.getProductId();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(ProductController.class)) {
      LogUtil.debug(ProductController.class, "details", "Search Product Details. productId: %s",
          productId);
    }

    Product product = productService.find(productId);
    product.setHits(product.getHits() + 1);
    productService.update(product);

    String[] propertys = {"id", "fullName", "price", "image", "sales", "stock", "introduction"};
    Map<String, Object> result = FieldFilterUtils.filterEntityMap(propertys, product);

    String[] imagePropertys = {"id", "mobileicon"};
    List<Map<String, Object>> productImages =
        FieldFilterUtils.filterCollectionMap(imagePropertys, product.getProductImages());
    result.put("productImages", productImages);
    Map<Parameter, String> productParam = product.getParameterValue();
    List<Map<String, String>> paramMaps = new ArrayList<Map<String, String>>();
    for (Map.Entry<Parameter, String> entry : productParam.entrySet()) {
      Map<String, String> paramMap = new HashMap<String, String>();
      paramMap.put("name", entry.getKey().getName());
      paramMap.put("value", entry.getValue());
      paramMaps.add(paramMap);
    }
    result.put("productParam", paramMaps);

    Pageable pageable = new Pageable();
    pageable.setPageNumber(1);
    pageable.setPageSize(1);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("product", Operator.eq, productId);
    filters.add(filter);
    pageable.setFilters(filters);
    Page<Review> reviews = reviewService.findPage(pageable);
    String[] reviewPropertys =
        {"id", "createDate", "member.userName", "member.photo", "bizReply", "score", "content"};
    List<Map<String, Object>> reviewList =
        FieldFilterUtils.filterCollectionMap(reviewPropertys, reviews.getContent());
    result.put("reviewCount", product.getReviews().size());
    result.put("reviews", reviewList);

    // 计算购物车内商品数量
    EndUser endUser = endUserService.find(userId);
    Cart cart = endUser.getCart();
    Integer cartProductCount = 0;
    if (cart != null && cart.getCartItems() != null) {
      for (CartItem cartItem : cart.getCartItems()) {
        cartProductCount += cartItem.getQuantity();
      }
    }
    result.put("cartProductCount", cartProductCount);

    response.setMsg(result);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 评论列表
   * 
   * @return
   */
  @RequestMapping(value = "/reviewList", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> reviewList(
      @RequestBody ProductRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long productId = request.getProductId();
    Integer pageSize = request.getPageSize();
    Integer pageNumber = request.getPageNumber();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    if (LogUtil.isDebugEnabled(ProductController.class)) {
      LogUtil.debug(ProductController.class, "reviewList",
          "Search Product review List with Params: userId: %s, productId: %s", userId, productId);
    }

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("product", Operator.eq, productId);
    Filter showfilter = new Filter("isShow", Operator.eq, true);
    filters.add(filter);
    filters.add(showfilter);
    pageable.setFilters(filters);
    Page<Review> reviews = reviewService.findPage(pageable);
    String[] reviewPropertys =
        {"id", "createDate", "member.userName", "member.photo", "bizReply", "score", "content"};
    List<Map<String, Object>> reviewList =
        FieldFilterUtils.filterCollectionMap(reviewPropertys, reviews.getContent());
    response.setMsg(reviewList);

    PageResponse page = new PageResponse();
    page.setPageNumber(request.getPageNumber());
    page.setPageSize(request.getPageSize());
    page.setTotal((int) reviews.getTotal());
    response.setPage(page);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 刷新索引
   * 
   * @return
   */
  @RequestMapping(value = "/refreshIndex", method = RequestMethod.GET)
  public @ResponseBody BaseResponse refreshIndex(BaseRequest request) {

    BaseResponse response = new BaseResponse();
    endUserService.refreshIndex();
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }



}
