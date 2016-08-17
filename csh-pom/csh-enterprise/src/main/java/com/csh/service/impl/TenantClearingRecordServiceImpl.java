package com.csh.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Setting;
import com.csh.dao.CarServiceRecordDao;
import com.csh.dao.TenantClearingRecordDao;
import com.csh.entity.CarServiceRecord;
import com.csh.entity.CommissionRate;
import com.csh.entity.Sn.Type;
import com.csh.entity.TenantClearingRecord;
import com.csh.entity.TenantInfo;
import com.csh.entity.commonenum.CommonEnum.ClearingStatus;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CommissionRateService;
import com.csh.service.SnService;
import com.csh.service.TenantClearingRecordService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.SettingUtils;

/**
 * 结算记录
 * 
 * @author shijun
 *
 */
@Service("tenantClearingRecordServiceImpl")
public class TenantClearingRecordServiceImpl extends BaseServiceImpl<TenantClearingRecord, Long> implements
TenantClearingRecordService {


  @Resource(name = "tenantClearingRecordDaoImpl")
  private TenantClearingRecordDao tenantClearingRecordDao;

  @Resource (name = "carServiceRecordDaoImpl")
  private CarServiceRecordDao carServiceRecordDao;
  @Resource (name = "snServiceImpl")
  private SnService snService;
  private Setting setting = SettingUtils.get();
  
  @Resource(name = "commissionRateServiceImpl")
  private CommissionRateService commissionRateService;
  @Resource()
  public void setBaseDao(TenantClearingRecordDao tenantClearingRecordDao) {
    super.setBaseDao(tenantClearingRecordDao);
  }

  @Override
  public Map<String, Date> findPeriodBeginEndDate (TenantInfo tenantInfo)
  {
    
    Map<String, Date> peroidDate = new HashMap<String, Date>();
    Date peroidBeginDate =  tenantClearingRecordDao.findLastPeriodEndDate ();
    Date now = new Date ();
    //第一次记录以租户创建时间开始
    if (peroidBeginDate == null)
    {
      peroidBeginDate = tenantInfo.getCreateDate ();
    }
    Integer cycle = setting.getClearingRecordCycle ();
    int days = (int) ((now.getTime() - peroidBeginDate.getTime())
        / (24 * 60 * 60 * 1000)/cycle*cycle);
    if (days - cycle < 0)
    {
      return peroidDate;
    }
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(peroidBeginDate);
    rightNow.add(Calendar.DATE,days);
    
    Timestamp peroidEndDate = new Timestamp (rightNow.getTimeInMillis ());
    
    peroidDate.put ("peroidBeginDate", peroidBeginDate);
    peroidDate.put ("peroidEndDate", peroidEndDate);
    
    return peroidDate;
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRES_NEW)
  public Boolean saveTenantClearingRecord (
      TenantClearingRecord tenantClearingRecord, Long[] ids)
  {
    
    //validate before save
    List<CommissionRate> commissionRateList = commissionRateService.findAll ();
    if (commissionRateList == null || commissionRateList.size () == 0)
    {
      return false;
    }
    BigDecimal totalMoney = new BigDecimal (0);
    BigDecimal totalDeductMoney = new BigDecimal (0);
    BigDecimal totalRealIncome = new BigDecimal (0);
    
    List<CarServiceRecord> carServiceRecordList = new ArrayList<CarServiceRecord>();
    for (Long id : ids)
    {
      
      CarServiceRecord record = carServiceRecordDao.find (id);
      switch (record.getPaymentType ())
      {
        case ALIPAY:
        case WECHAT:
        case WALLET:
          //全额支付，全额结算
          totalMoney = totalMoney.add (record.getPrice ());
          totalDeductMoney = totalDeductMoney.add (record.getPrice ());
          break;
        case DIRECTREDPACKAGE:
          //直接支付加上红包支付，结算金额为优惠后金额
          totalMoney = totalMoney.add (record.getPrice ());
          totalDeductMoney = totalDeductMoney.add (record.getPrice ());
        case COUPON:
          //根据优惠券来源结算，平台优惠券，结算全额，租户优惠券结算discountPrice
          if(record .getCouponSource () == SystemType.ENTERPRISE){
            totalMoney = totalMoney.add (record.getDiscountPrice ());
            totalDeductMoney = totalDeductMoney.add (record.getPrice ());
          }else if(record .getCouponSource () == SystemType.OPERATION){
            totalMoney = totalMoney.add (record.getPrice ());
            //优惠券优惠金额
            totalDeductMoney = totalDeductMoney.add (record.getDiscountPrice ());
          }
        break;
        case COUPONREDPACKAGE:
           //优惠券加红包，平台优惠券结算原价减红包，
          //租户优惠券
          if(record .getCouponSource () == SystemType.ENTERPRISE){
            totalMoney = totalMoney.add (record.getDiscountPrice ());
            totalDeductMoney = totalDeductMoney.add (record.getPrice ());
          }else if(record .getCouponSource () == SystemType.OPERATION){
            totalMoney = totalMoney.add (record.getPrice ().subtract (record.getRedPackageUsage ()));
            //优惠券优惠金额
            BigDecimal couponMoney=record.getPrice ().subtract (record.getDiscountPrice ()).subtract (record.getRedPackageUsage ());
            totalDeductMoney = totalDeductMoney.add (record.getPrice ().subtract (couponMoney));
          }
          break;
        case WASHCOUPON:break;//洗车券支付，结算金额为0
        case OFFLINEBALLANCE:
          //线下余额支付,减去clearBalance
          totalMoney = totalMoney.add (record.getPrice ().subtract (record.getOfflineBalance ()));
          totalDeductMoney = totalDeductMoney.add (record.getPrice ().subtract (record.getOfflineBalance ()));
        break;
        case OFFLINEBALLANCEREDPACKAGE:
          //线下余额,红包支付,减去clearBalance
          totalMoney = totalMoney.add (record.getDiscountPrice ().subtract (record.getOfflineBalance ()));
          totalDeductMoney = totalDeductMoney.add (record.getDiscountPrice ().subtract (record.getOfflineBalance ()));
        case MIXCOUPONOFFLINE:
          //结算clearBalance,根据优惠券来源，判断是否结算discountPrice
          if(record .getCouponSource () == SystemType.ENTERPRISE){
            totalMoney = totalMoney.add (record.getDiscountPrice ().subtract (record.getOfflineBalance ()));
            totalDeductMoney = totalDeductMoney.add (record.getPrice ().subtract (record.getOfflineBalance ()));
          }else if(record .getCouponSource () == SystemType.OPERATION){
            totalMoney = totalMoney.add (record.getPrice ().subtract (record.getOfflineBalance ()));
            //优惠券优惠金额
            BigDecimal couponMoney=record.getPrice ().subtract (record.getDiscountPrice ()).subtract (record.getRedPackageUsage ());
            totalDeductMoney = totalDeductMoney.add (record.getPrice ().subtract (couponMoney).subtract (record.getOfflineBalance ()));
          }
         break;
        case MIXCOUPONOFFLINEREDPACKAGE:
          //在COUPONREDPACKAGE 基础上减去线下余额部分
          if(record .getCouponSource () == SystemType.ENTERPRISE){
          totalMoney = totalMoney.add (record.getDiscountPrice ().subtract (record.getOfflineBalance ()));
          totalDeductMoney = totalDeductMoney.add (record.getPrice ().subtract (record.getOfflineBalance ()));
        }else if(record .getCouponSource () == SystemType.OPERATION){
          totalMoney = totalMoney.add (record.getPrice ().subtract (record.getRedPackageUsage ()).subtract (record.getOfflineBalance ()));
        //优惠券优惠金额
          BigDecimal couponMoney=record.getPrice ().subtract (record.getDiscountPrice ()).subtract (record.getRedPackageUsage ());
          totalDeductMoney = totalDeductMoney.add (record.getPrice ().subtract (couponMoney).subtract (record.getOfflineBalance ()));
        }
        default:
          break;
      }
      carServiceRecordList.add (record);
    }
    //格式化数据
    DecimalFormat format=new DecimalFormat("0.00");
    String str = format.format(totalMoney.doubleValue ()-(commissionRateList.get (0).getPlatformRate ()*totalDeductMoney.doubleValue ()));    
    totalRealIncome = new BigDecimal(str);
    totalMoney = new BigDecimal (format.format (totalMoney));
    if (!totalMoney.equals (new BigDecimal (format.format (tenantClearingRecord.getAmountOfCurrentPeriod ()))) 
        || !totalRealIncome.equals (new BigDecimal (format.format (tenantClearingRecord.getAmountRealIncome ()))))
    {
      return false;
    }
    tenantClearingRecord.setClearingStatus (ClearingStatus.UNPAID);
    tenantClearingRecord.setClearingSn (snService.generate (Type.clearing));
    FieldFilterUtils.addFieldValue("tenantID", tenantAccountService.getCurrentTenantID(), tenantClearingRecord);
    tenantClearingRecordDao.persist (tenantClearingRecord);
    for (CarServiceRecord carServiceRecord : carServiceRecordList)
    {
      carServiceRecord.setTenantClearingRecord (tenantClearingRecord);
      carServiceRecord.setClearingDate (tenantClearingRecord.getCreateDate ());
      carServiceRecordDao.merge (carServiceRecord);
    }
    
    return true;
  }
}
