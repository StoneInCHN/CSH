package com.csh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csh.controller.base.BaseController;
import com.csh.entity.AccountBalance;
import com.csh.entity.CouponEndUser;
import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.AccountBalanceService;
import com.csh.service.EndUserService;


@RequestMapping("console/endUser")
@Controller("endUserController")
public class EndUserController extends BaseController{

  @Resource(name="endUserServiceImpl")
  private EndUserService endUserService;
  
  @Resource(name="accountBalanceServiceImpl")
  private AccountBalanceService accountBalanceService;
  
  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("endUser", endUserService.find(id));
    return "/endUser/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    
    Page<EndUser> page = endUserService.findPage(pageable);
    List<EndUser> endUsers = page.getContent();
    for (EndUser endUser : endUsers) {
      List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
      filters.add(com.csh.framework.filter.Filter.eq("endUser",endUser.getId()));
      List<AccountBalance> lists = accountBalanceService.findList(null, filters, null);
      //线下转线上余额
      BigDecimal balance = new BigDecimal(0);
      if(lists!=null && lists.size() >0){
        for (AccountBalance accountBalance : lists) {
          balance = balance.add(accountBalance.getBalance());
        }
      }
      endUser.setAccountBalance(balance);
      //充值到平台的余额
      Wallet wallet =  endUser.getWallet();
      if(wallet !=null && wallet.getBalanceAmount()!=null){
        endUser.setSelfBalance(wallet.getBalanceAmount());
      }else{
        endUser.setSelfBalance(new BigDecimal(0));
      }
   }
    model.addAttribute("page", endUserService.findPage(pageable));
    return "/endUser/list";
  }
  
}
