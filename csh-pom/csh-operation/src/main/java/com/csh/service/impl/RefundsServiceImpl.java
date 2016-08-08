package com.csh.service.impl; 

import java.math.BigDecimal;

import javax.annotation.Resource; 
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.entity.commonenum.CommonEnum.PaymentStatus;
import com.csh.entity.commonenum.CommonEnum.Type;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderLog;
import com.csh.entity.estore.Refunds;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderItemDao;
import com.csh.dao.OrderLogDao;
import com.csh.dao.RefundsDao;
import com.csh.service.RefundsService;
import com.csh.framework.service.impl.BaseServiceImpl;

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

      @Override
      @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
      public void saveRefunds(Order order, Refunds refunds) {
        orderDao.lock(order, LockModeType.PESSIMISTIC_WRITE);
        refunds.setOrder(order);
        refundsDao.persist(refunds);
        if (refunds.getMethod() == Refunds.Method.deposit) {
//            Member member = order.getMember();
//            memberDao.lock(member, LockModeType.PESSIMISTIC_WRITE);
//            member.setBalance(member.getBalance().add(refunds.getAmount()));
//            memberDao.merge(member);
//
//            Deposit deposit = new Deposit();
//            deposit.setType(Deposit.Type.adminRefunds);
//            deposit.setCredit(refunds.getAmount());
//            deposit.setDebit(new BigDecimal(0));
//            deposit.setBalance(member.getBalance());
//            deposit.setOperator(operator != null ? operator.getUsername() : null);
//            deposit.setMember(member);
//            deposit.setOrder(order);
//            depositDao.persist(deposit);
        }

        order.setAmountPaid(order.getAmountPaid().subtract(refunds.getAmount()));
        order.setExpire(null);
        if (order.getAmountPaid().compareTo(new BigDecimal(0)) == 0) {
            order.setPaymentStatus(PaymentStatus.refunded);
        } else if (order.getAmountPaid().compareTo(new BigDecimal(0)) > 0) {
            order.setPaymentStatus(PaymentStatus.partialRefunds);
        }
        orderDao.merge(order);

        OrderLog orderLog = new OrderLog();
        orderLog.setType(Type.refunds);
        orderLog.setOperator(refunds.getOperator() != null ? refunds.getOperator() : null);
        orderLog.setOrder(order);
        orderLogDao.persist(orderLog);
        
      }
}