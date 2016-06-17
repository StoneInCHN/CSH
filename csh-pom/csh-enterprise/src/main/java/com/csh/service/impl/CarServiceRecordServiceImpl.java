package com.csh.service.impl; 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Setting;
import com.csh.dao.CarServiceDistributorDeductRecordDao;
import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.CarServiceTenantDeductRecordDao;
import com.csh.entity.CarServiceDistributorDeductRecord;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.CarServiceTenantDeductRecord;
import com.csh.entity.CommissionRate;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Distributor;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.ChargeStatus;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.SendType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceRecordService;
import com.csh.service.CommissionRateService;
import com.csh.service.DistributorService;
import com.csh.service.MessageInfoService;
import com.csh.service.TenantAccountService;
import com.csh.utils.ApiUtils;
import com.csh.utils.SettingUtils;

@Service("carServiceRecordServiceImpl")
public class CarServiceRecordServiceImpl extends BaseServiceImpl<CarServiceRecord,Long> implements CarServiceRecordService {

      @Resource(name="carServiceRecordDaoImpl")
      private CarServiceRecordDao carServiceRecordDao;
      @Resource(name="tenantAccountServiceImpl")
      private TenantAccountService tenantAccountService;
      @Resource(name="distributorServiceImpl")
      private DistributorService distributorService;
      
      @Resource(name= "carServiceTenantDeductRecordDaoImpl")
      private CarServiceTenantDeductRecordDao carServiceTenantDeductRecordDao;
      @Resource(name= "carServiceDistributorDeductRecordDaoImpl")
      private CarServiceDistributorDeductRecordDao carServiceDistributorDeductRecordDao;
      @Resource(name = "commissionRateServiceImpl")
      private CommissionRateService commissionRateService;
      @Resource(name = "messageInfoServiceImpl")
      private MessageInfoService messageInfoService;
      @Resource
      public void setBaseDao(CarServiceRecordDao carServiceRecordDao) {
         super.setBaseDao(carServiceRecordDao);
  }

      @Override
      public List<CarServiceRecord> findCurrentClearingRecords ()
      {
//        return carServiceRecordDao.callProcedureWithResult ("findCurrentClearingServiceRecord", 1,7);
        
        List<Filter> filters = new ArrayList<Filter>();
        Filter tenantFilter = new Filter("tenantID",Operator.eq,tenantAccountService.getCurrentTenantID ());
        filters.add (tenantFilter);
        Filter chargeStatusFilter = new Filter("chargeStatus",Operator.eq,ChargeStatus.FINISH);
        filters.add ( chargeStatusFilter);
        Filter clearingDateFilter = new Filter("clearingDate",Operator.isNull);
        filters.add ( clearingDateFilter);
        
        return carServiceRecordDao.findList (null, null, filters, null);
        
      }

      @Override
      @Transactional(propagation=Propagation.REQUIRED)
      public void updateCarServiceRecord (CarServiceRecord oldCarServiceRecord,
          CarServiceRecord newCarServiceRecord)
      {
        List<CommissionRate> commissionRateList = commissionRateService.findAll ();
        if (commissionRateList == null || commissionRateList.size () != 1)
        {
          return;
        }
        CommissionRate rate = commissionRateList.get (0);
        //订单状态修改，推送消息
       
        if (oldCarServiceRecord.getChargeStatus () == ChargeStatus.PAID
            && newCarServiceRecord.getChargeStatus () == ChargeStatus.FINISH)
        {
        //生成分销商提成订单
          Distributor distributor = null;
          DeviceInfo deviceInfo = oldCarServiceRecord.getVehicle ().getDevice ();
          if (deviceInfo != null)
          {
            distributor = deviceInfo.getDistributor ();
          }
          if (distributor != null)
          {
            CarServiceDistributorDeductRecord distributorDeductRecord = new CarServiceDistributorDeductRecord ();
            
            distributorDeductRecord.setCarService (oldCarServiceRecord.getCarService ());
            distributorDeductRecord.setTenantName (oldCarServiceRecord.getTenantName ());
            distributorDeductRecord.setChargeStatus (ChargeStatus.FINISH);
            distributorDeductRecord.setEndUser (oldCarServiceRecord.getEndUser ());
            distributorDeductRecord.setFinishDate (oldCarServiceRecord.getFinishDate ());
            distributorDeductRecord.setPaymentDate (oldCarServiceRecord.getPaymentDate ());
            distributorDeductRecord.setPaymentType (oldCarServiceRecord.getPaymentType ());
            //如果用洗车券，提成金额为0
            if (oldCarServiceRecord.getPaymentType ()== PaymentType.WASHCOUPON)
            {
              distributorDeductRecord.setPrice (new BigDecimal (0));
            }
            //如果线下余额抵用部分
            else if (oldCarServiceRecord.getPaymentType ()== PaymentType.OFFLINEBALLANCE)
            {
              distributorDeductRecord.setPrice (oldCarServiceRecord.getPrice ().subtract (oldCarServiceRecord.getOfflineBalance ()));
            }else if (oldCarServiceRecord.getPaymentType ()== PaymentType.MIXCOUPONOFFLINE) {
              distributorDeductRecord.setPrice (oldCarServiceRecord.getDiscountPrice ().subtract (oldCarServiceRecord.getOfflineBalance ()));
            }
            else {
              distributorDeductRecord.setPrice (oldCarServiceRecord.getPrice ());
            }
            distributorDeductRecord.setRecordNo (oldCarServiceRecord.getRecordNo ());
            distributorDeductRecord.setDistributorId (distributor.getId ());
            
            distributorDeductRecord.setTenantName (oldCarServiceRecord.getTenantName ());
            distributorDeductRecord.setVehicle (oldCarServiceRecord.getVehicle ());
            BigDecimal deductMoney = new BigDecimal(rate.getDistributorRate ()*distributorDeductRecord.getPrice ().doubleValue ());
            distributorDeductRecord.setDeductMoney (deductMoney);
            carServiceDistributorDeductRecordDao.persist (distributorDeductRecord);
          
          }
          
          Vehicle vehicle = oldCarServiceRecord.getVehicle ();
          if (vehicle.getTenantID () != oldCarServiceRecord.getTenantID ())
          {
          //生成租户提成订单
            CarServiceTenantDeductRecord tenantDeductRecord = new CarServiceTenantDeductRecord ();
            tenantDeductRecord.setTenantName (oldCarServiceRecord.getTenantName ());
            tenantDeductRecord.setCarService (oldCarServiceRecord.getCarService ());
            tenantDeductRecord.setChargeStatus (ChargeStatus.FINISH);
            tenantDeductRecord.setEndUser (oldCarServiceRecord.getEndUser ());
            tenantDeductRecord.setFinishDate (oldCarServiceRecord.getFinishDate ());
            tenantDeductRecord.setPaymentDate (oldCarServiceRecord.getPaymentDate ());
            tenantDeductRecord.setPaymentType (oldCarServiceRecord.getPaymentType ());
            tenantDeductRecord.setPrice (oldCarServiceRecord.getPrice ());
            tenantDeductRecord.setRecordNo (oldCarServiceRecord.getRecordNo ());
            tenantDeductRecord.setTenantID (oldCarServiceRecord.getVehicle ().getTenantID ());
            tenantDeductRecord.setTenantName (oldCarServiceRecord.getTenantName ());
            tenantDeductRecord.setVehicle (oldCarServiceRecord.getVehicle ());
            BigDecimal deductMoney = new BigDecimal(rate.getTenantRate ()*oldCarServiceRecord.getPrice ().doubleValue ());
            tenantDeductRecord.setDeductMoney (deductMoney);
            carServiceTenantDeductRecordDao.persist (tenantDeductRecord);
          }
          
          
      }
        if (oldCarServiceRecord.getChargeStatus () != newCarServiceRecord.getChargeStatus ())
        {
          sendRecordStatusUpdateMessag (oldCarServiceRecord,newCarServiceRecord.getChargeStatus ());
        }
        oldCarServiceRecord.setPrice (newCarServiceRecord.getPrice ());
        oldCarServiceRecord.setChargeStatus (newCarServiceRecord.getChargeStatus ());
        oldCarServiceRecord.setFinishDate (new Date ());
        carServiceRecordDao.merge (oldCarServiceRecord);
        
        
      }
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void sendRecordStatusUpdateMessag(CarServiceRecord record,ChargeStatus newChargeStatus){
        MessageInfo msgInfo = new MessageInfo ();
        msgInfo.setMessageContent ("订单状态已改为："+newChargeStatus.getChargeStatusName ());
        msgInfo.setMessageTitle ("订单修改");
        msgInfo.setTenantID (tenantAccountService.getCurrentTenantID ());
        msgInfo.setMessageType (MessageType.PERSONALMSG);
        msgInfo.setSendType (SendType.PUSH);
        Set<MsgEndUser> msgEndUserList =new HashSet<MsgEndUser> ();
        MsgEndUser msgEndUser = new MsgEndUser ();
        msgEndUser.setEndUser (record.getEndUser ());
        msgEndUser.setIsPush (false);
        msgEndUser.setIsRead (false);
        msgEndUser.setMessage (msgInfo);
        msgEndUserList.add (msgEndUser);
        msgInfo.setMsgUser (msgEndUserList);
        messageInfoService.save (msgInfo);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put ("msgId", msgInfo.getId ());
        Setting setting = SettingUtils.get();
        ApiUtils.post (setting.getMsgPushUrl ());
        this.update (record);
      }
}