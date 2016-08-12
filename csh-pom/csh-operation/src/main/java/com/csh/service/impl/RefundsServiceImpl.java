package com.csh.service.impl; 

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource; 
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.entity.EndUser;
import com.csh.entity.Wallet;
import com.csh.entity.WalletRecord;
import com.csh.entity.commonenum.CommonEnum.BalanceType;
import com.csh.entity.commonenum.CommonEnum.OrderLogType;
import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.PaymentType;
import com.csh.entity.commonenum.CommonEnum.RefundsStatus;
import com.csh.entity.commonenum.CommonEnum.WalletType;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Refunds;
import com.csh.beans.CommonAttributes;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderItemDao;
import com.csh.dao.OrderLogDao;
import com.csh.dao.RefundsDao;
import com.csh.dao.WalletDao;
import com.csh.service.RefundsService;
import com.csh.utils.RefundUtil;
import com.csh.utils.SpringUtils;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.ResponseOne;

@Service("refundsServiceImpl")
public class RefundsServiceImpl extends BaseServiceImpl<Refunds,Long> implements RefundsService {

      @Resource(name="refundsDaoImpl")
      public void setBaseDao(RefundsDao refundsDao) {
         super.setBaseDao(refundsDao);
      }
      @Resource(name="refundsDaoImpl")
      RefundsDao refundsDao;
      @Resource(name="orderDaoImpl")
      OrderDao orderDao;
      @Resource(name="orderItemDaoImpl")
      OrderItemDao orderItemDao;
      @Resource(name="orderLogDaoImpl")
      OrderLogDao orderLogDao;  
      @Resource(name="walletDaoImpl")
      WalletDao walletDao;      
      

      @Override
      @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
      public void saveRefunds(Order order, Refunds refunds) {
        orderDao.lock(order, LockModeType.PESSIMISTIC_WRITE);
        refunds.setOrder(order);
        
        /**退款**/
        PaymentType paymentType = order.getPaymentType();
        refunds.setPaymentMethod(paymentType.toString());
        //支付宝支付，退款到支付宝
        if (paymentType == PaymentType.ALIPAY) {
          ResponseOne<Map<String, Object>> response = 
              RefundUtil.alipayRefund(order.getSn(), refunds.getSn(), order.getAmountPaid().doubleValue());
          if (response.getCode().equals(CommonAttributes.SUCCESS)) {
            refunds.setRefundsStatus(RefundsStatus.refund_success);
            refunds.setMemo(SpringUtils.getMessage("csh.refunds.success",
                SpringUtils.getMessage("csh.order.paymentType.ALIPAY")));
          }else if (response.getCode().equals(CommonAttributes.FAIL_COMMON)){
            refunds.setRefundsStatus(RefundsStatus.refund_failed);
            refunds.setMemo(response.getDesc());
          }
        }
        //微信支付，退款到微信
        else if (paymentType == PaymentType.WECHAT) {
          ResponseOne<Map<String, Object>> response = 
              RefundUtil.wechatRefund(order.getSn(), refunds.getSn(), order.getAmount(), order.getAmountPaid());
          if (response.getCode().equals(CommonAttributes.SUCCESS)) {
            refunds.setRefundsStatus(RefundsStatus.refund_success);
            refunds.setMemo(SpringUtils.getMessage("csh.refunds.success",
                SpringUtils.getMessage("csh.order.paymentType.WECHAT")));
          }else if (response.getCode().equals(CommonAttributes.FAIL_COMMON)){
            refunds.setRefundsStatus(RefundsStatus.refund_failed);
            refunds.setMemo(response.getDesc());
          }
        }
        //其它或者余额支付，暂时统一退款到余额(用户钱包)
        else {
          refunds.setAmount(order.getAmountPaid());
          EndUser endUser = order.getEndUser();
          Wallet wallet = endUser.getWallet();
          wallet.setBalanceAmount(wallet.getBalanceAmount().add(order.getAmountPaid()));
          WalletRecord walletRecord = new WalletRecord();
          walletRecord.setBalanceType(BalanceType.INCOME);
          walletRecord.setWalletType(WalletType.MONEY);
          walletRecord.setMoney(order.getAmountPaid());
          walletRecord.setRecordNo(null);
          walletRecord.setRemark(SpringUtils.getMessage("csh.wallet.chargeIn.orderRefunds", order.getSn()));
          walletRecord.setWallet(wallet);
          wallet.getWalletRecords().add(walletRecord);
          walletDao.merge(wallet);
          refunds.setRefundsStatus(RefundsStatus.refund_success);
          refunds.setMemo(SpringUtils.getMessage("csh.refunds.success",
              SpringUtils.getMessage("csh.order.paymentType.WALLET")));
        }
        
        refundsDao.persist(refunds);
        //如果退款成功,修改订单 “已付金额” 以及 “支付状态”
        if (refunds.getRefundsStatus() == RefundsStatus.refund_success) {
          order.setAmountPaid(order.getAmountPaid().subtract(refunds.getAmount()));
          if (order.getAmountPaid().compareTo(new BigDecimal(0)) == 0) {
              order.setPaymentStatus(PaymentStatus.refunded);
          } else if (order.getAmountPaid().compareTo(new BigDecimal(0)) > 0) {
              order.setPaymentStatus(PaymentStatus.partialRefunds);
          }
        }
        order.setExpire(null);
        orderDao.merge(order);

        OrderLog orderLog = new OrderLog();
        orderLog.setType(OrderLogType.refunds);
        orderLog.setOperator(refunds.getOperator() != null ? refunds.getOperator() : null);
        orderLog.setOrder(order);
        orderLog.setContent(refunds.getMemo());
        orderLogDao.persist(orderLog);
        
      }
}