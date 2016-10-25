package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.csh.dao.EndUserDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.request.EndUserRequest;
import com.csh.json.request.EndUserRequest.ResultType;
import com.csh.service.EndUserService;
import com.csh.service.TenantAccountService;
import com.csh.utils.UcpaasUtil;

@Service ("endUserServiceImpl")
public class EndUserServiceImpl extends BaseServiceImpl<EndUser, Long>
    implements EndUserService
{

  @Resource (name = "endUserDaoImpl")
  private EndUserDao endUserDao;
  @Resource (name = "vehicleDaoImpl")
  private VehicleDao vehicleDao;
  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource
  public void setBaseDao (EndUserDao endUserDao)
  {
    super.setBaseDao (endUserDao);
  }

  @Override
  public Long countUserByTenantID (Long tenantID)
  {

    return endUserDao.countUserByTenantID (tenantID);
  }

  @Override
  public Page<EndUser> findEndUser (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,
      AccountStatus accountStatusSearch)
  {
    return endUserDao.findEndUser (pageable, model, beginDate, endDate,
        userNameSearch, accountStatusSearch,
        tenantAccountService.getCurrentTenantID ());

  }

  @Override
  public void bulkSave (List<EndUserRequest> endUserRequestList)
  {
    for (EndUserRequest endUserRequest : endUserRequestList)
    {
      if (endUserRequest.getName ().trim ().equals ("") 
          ||  endUserRequest.getMobile ().trim ().equals ("")
          || endUserRequest.getPlate ().trim ().equals (""))
      {
        endUserRequest.setResult (ResultType.MissingParameters.getResultTypeName ());
        continue;
      }
      EndUser endUser = new EndUser ();
      endUser.setMobileNum (endUserRequest.getMobile ());
      endUser.setUserName (endUserRequest.getName ());
      String password = UcpaasUtil.randomPwd ();
      endUser.setPassword (DigestUtils.md5Hex(password));
      Vehicle vehicle = new Vehicle ();
      vehicle.setPlate (endUserRequest.getPlate ());
      vehicle.setEndUser (endUser);
      
      EndUser checkedEnduser = checkMobileExist (endUser.getMobileNum ());
      //checkedEnduser 为空表示数据库不存在user，直接保存
       try
      {
         if (checkedEnduser == null)
         {
           this.save (endUser);
           UcpaasUtil.SendAccountBySms(endUser.getMobileNum (),password);
           endUserRequest.setResult (ResultType.Success.getResultTypeName ());
         }else {
           Vehicle checkedVehicle = checkVehiclePlate (vehicle.getPlate ());
           if (checkedVehicle == null)
           {
             vehicle.setEndUser (checkedEnduser);
             vehicleDao.persist (vehicle);
             endUserRequest.setResult (ResultType.Success.getResultTypeName ());
           }else {
             endUserRequest.setResult (ResultType.Already.getResultTypeName ());
          }
         }
         
      }
      catch (Exception e)
      {
        endUserRequest.setResult (ResultType.Faild.getResultTypeName ());
      }
    }
  }
  
  /**
   * 判断手机号是否存在
   * @param mobileNum
   * @return
   */
  private EndUser checkMobileExist(String mobileNum){
    List<Filter> filters = new ArrayList<Filter> ();
    Filter mobileFilter = new Filter ("mobileNum", Operator.eq, mobileNum);
    
    filters.add (mobileFilter);
    List<EndUser> endUserList=this.findList (null, filters, null);
    if (endUserList!= null && endUserList.size ()==1)
    {
      return endUserList.get (0);
    }
    return null;
    
  }
  
  /**
   * 判断车牌号是否存在
   * @param plate
   * @return
   */
  private Vehicle checkVehiclePlate(String plate){
    List<Filter> filters = new ArrayList<Filter> ();
    Filter plateFilter = new Filter ("plate", Operator.eq, plate);
    Filter defaultPlateFilter = new Filter("plate", Operator.ne, "000000");
    filters.add (plateFilter);
    filters.add (defaultPlateFilter);
    
    List<Vehicle> vehicleList=vehicleDao.findList (null, null, filters, null);
    if (vehicleList!= null && vehicleList.size ()==1)
    {
      return vehicleList.get (0);
    }
    return null;
    
  }
}