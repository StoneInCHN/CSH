package com.csh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.AccountBalance;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.request.EndUserRequest;
import com.csh.service.AccountBalanceService;
import com.csh.service.EndUserService;
import com.csh.service.FileService;
import com.csh.service.RSAService;
import com.csh.service.TenantAccountService;
import com.csh.utils.ExcelImporter;
import com.csh.utils.POIUtil;
import com.csh.utils.SpringUtils;

/**
 * 终端用户
 * @author huyong
 *
 */
@Controller ("endUserController")
@RequestMapping ("console/endUser")
public class EndUserController extends BaseController
{

  @Resource (name = "endUserServiceImpl")
  private EndUserService endUserService;
  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;
  
  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name="accountBalanceServiceImpl")
  private AccountBalanceService accountBalanceService;
  
  @Resource(name="fileServiceImpl")
  private FileService fileService;
  
  @RequestMapping (value = "/endUser", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "endUser/endUser";
  }
 
  @RequestMapping (value = "/commonEndUserSearch", method = RequestMethod.GET)
  public String commonEndUserSearch (ModelMap model)
  {
    return "common/commonEndUserSearch";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<EndUser> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,AccountStatus accountStatusSearch)
  {
    Long tenantId = tenantAccountService.getCurrentTenantID();
    // Page<EndUser> page = endUserService.findPage(pageable);
     Page<EndUser> page = endUserService.findEndUser (pageable, model, beginDate, endDate, userNameSearch, accountStatusSearch);
   List<EndUser> endUsers = page.getRows();
     for (EndUser endUser : endUsers) {
       List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
       filters.add(com.csh.framework.filter.Filter.eq("endUser",endUser.getId()));
       filters.add(com.csh.framework.filter.Filter.eq("tenantID", tenantId));
       List<AccountBalance> lists = accountBalanceService.findList(null, filters, null);
       if(lists!=null && lists.size() >0){
         endUser.setAccountBalance(lists.get(0).getBalance());
       }
    }
     return new Page<EndUser>(endUsers, page.getTotal(), pageable);
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    EndUser endUser = endUserService.find (id);
    model.put ("endUser", endUser);
    return "endUser/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (EndUser endUser)
  {
    endUserService.save (endUser,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (String enPassword,EndUser endUser)
  {
    if (!enPassword.equals (endUser.getPassword ()))
    {
      endUser.setPassword (DigestUtils.md5Hex(endUser.getPassword ()));
    }
    return SUCCESS_MESSAGE;
  }
 

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 检查是否能被删除
      // if()
      try
      {
        endUserService.delete (ids);
      }
      catch (Exception e)
      {
        e.printStackTrace ();
        return Message.error ("csh.delete.fail");
      }
      
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    EndUser endUser = endUserService.find(id);
    model.addAttribute("endUser", endUser);
    return "endUser/details";
  }
  
  
  /**
   * 设置余额
   */
  @RequestMapping (value = "/setBalance", method = RequestMethod.POST)
  public @ResponseBody Message setBalance (AccountBalance accountBalance,Long endUserId)
  {
    if(accountBalance.getBalance() ==null || endUserId == null){
      return Message.error(SpringUtils.getMessage("csh.accountBalance.data.error"));
    }
    Long tenantId = tenantAccountService.getCurrentTenantID();
    List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
    filters.add(com.csh.framework.filter.Filter.eq("endUser",endUserId));
    filters.add(com.csh.framework.filter.Filter.eq("tenantID", tenantId));
    List<AccountBalance> lists = accountBalanceService.findList(null, filters, null);
    if(lists!=null && lists.size() >0){
      return Message.error(SpringUtils.getMessage("csh.accountBalance.resubmit"));
    }
    EndUser endUser = endUserService.find(endUserId);
    accountBalance.setTenantID(tenantId);
    accountBalance.setEndUser(endUser);
    accountBalanceService.save(accountBalance);
    return SUCCESS_MESSAGE;
  }
  /**
   * 设置余额
   */
  @RequestMapping (value = "/saveImport", method = RequestMethod.POST)
  public @ResponseBody Message saveImport (HttpServletResponse response,MultipartFile filePath)
  {
    ExcelImporter<EndUserRequest> impoter = new ExcelImporter<EndUserRequest>(EndUserRequest.class);
    String[] params = {"name","mobile","plate"};
    String sufix = filePath.getOriginalFilename ().split("\\.")[1];
    try {
      List<EndUserRequest> list = impoter.getListEntity(filePath.getInputStream (), sufix,params,0);
      
      endUserService.bulkSave(list);
      String[] respParams = {"名称","联系电话","车牌号","结论","备注"};
      POIUtil.createExcel (response, respParams, list);
    } catch (Exception e) {
      
      e.printStackTrace();
    }
    
//    return Message.success ("执行结果保存在result.xls文件中");
    return SUCCESS_MESSAGE;
  }
  
  
}
