package com.csh.controller.estore;

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
import com.csh.entity.estore.Cart;
import com.csh.entity.estore.CartItem;
import com.csh.entity.estore.Product;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.CartRequest;
import com.csh.service.CartItemService;
import com.csh.service.CartService;
import com.csh.service.EndUserService;
import com.csh.service.ProductService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;


/**
 * Controller - 电商购物车
 * 
 * @author sujinxuan
 *
 */
@Controller("cartController")
@RequestMapping("/estore/cart")
public class CartController extends MobileBaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "productServiceImpl")
  private ProductService productService;

  @Resource(name = "cartServiceImpl")
  private CartService cartService;

  @Resource(name = "cartItemServiceImpl")
  private CartItemService cartItemService;



  /**
   * 添加商品至购物车
   * 
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse add(@RequestBody CartRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long productId = request.getProductId();
    Integer quantity = request.getQuantity();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    EndUser endUser = endUserService.find(userId);
    Cart cart = endUser.getCart();
    if (cart == null) {
      cart = new Cart();
      cart.setEndUser(endUser);
    }

    Boolean isSameProduct = false;

    for (CartItem cartItem : cart.getCartItems()) {
      if (cartItem.getProduct().getId().equals(productId)) {
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        isSameProduct = true;
        break;
      }
    }

    if (!isSameProduct) {
      CartItem cartItem = new CartItem();
      Product product = productService.find(productId);
      cartItem.setProduct(product);
      cartItem.setQuantity(quantity);
      cartItem.setTenantID(product.getTenantID());
      cart.getCartItems().add(cartItem);
    }

    cartService.update(cart);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }

  /**
   * 购物车列表
   * 
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getCategory(
      @RequestBody CartRequest request) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();

    Long userId = request.getUserId();
    String token = request.getToken();
    Integer pageSize = request.getPageSize();
    Integer pageNumber = request.getPageNumber();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }


    EndUser endUser = endUserService.find(userId);
    Cart cart = endUser.getCart();
    List<Filter> filters = new ArrayList<Filter>();
    Filter cartFilter = new Filter("cart", Operator.eq, cart.getId());
    filters.add(cartFilter);

    Pageable pageable = new Pageable();
    pageable.setPageNumber(pageNumber);
    pageable.setPageSize(pageSize);
    pageable.setOrderProperty("createDate");
    pageable.setOrderDirection(Direction.desc);
    Page<CartItem> page = cartItemService.findPage(pageable);
    String[] propertys =
        {"id", "quantity", "product.id", "product.name", "product.price", "product.image",
            "product.stock"};
    List<Map<String, Object>> result =
        FieldFilterUtils.filterCollectionMap(propertys, page.getContent());

    response.setMsg(result);
    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 修改购物车（删除商品）
   * 
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse details(@RequestBody CartRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long[] itemIds = request.getItemIds();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    cartItemService.delete(itemIds);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


  /**
   * 修改购物车（增减商品数量）
   * 
   * @return
   */
  @RequestMapping(value = "/oprNum", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody BaseResponse oprNum(@RequestBody CartRequest request) {

    BaseResponse response = new BaseResponse();

    Long userId = request.getUserId();
    String token = request.getToken();
    Long itemId = request.getItemId();
    Integer opr = request.getOpr();

    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    CartItem cartItem = cartItemService.find(itemId);
    cartItem.setQuantity(cartItem.getQuantity() + opr);
    cartItemService.update(cartItem);

    String newtoken = TokenGenerator.generateToken(request.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);
    return response;
  }


}
