package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.EndUserDao;
import com.csh.dao.OrderDao;
import com.csh.dao.OrderItemDao;
import com.csh.dao.ReturnsDao;
import com.csh.dao.SnDao;
import com.csh.entity.EndUser;
import com.csh.entity.Sn.Type;
import com.csh.entity.commonenum.CommonEnum.AfterSalesStatus;
import com.csh.entity.commonenum.CommonEnum.ReturnsStatus;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.OrderItem;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.entity.estore.Returns;
import com.csh.entity.estore.ReturnsItem;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReturnsService;

@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns, Long> implements ReturnsService {

  @Resource(name = "orderDaoImpl")
  private OrderDao orderDao;

  @Resource(name = "endUserDaoImpl")
  private EndUserDao endUserDao;

  @Resource(name = "snDaoImpl")
  private SnDao snDao;

  @Resource(name = "returnsDaoImpl")
  private ReturnsDao returnsDao;

  @Resource(name = "orderItemDaoImpl")
  private OrderItemDao orderItemDao;

  @Resource(name = "returnsDaoImpl")
  public void setBaseDao(ReturnsDao returnsDao) {
    super.setBaseDao(returnsDao);
  }

  @Override
  public Returns createOrEdit(Long orderId, Long[] orderItemIds, Long returnsId, Long userId,
      String trackingNo, String deliveryCorp) {
    Returns returns = null;
    if (returnsId == null) {
      returns = new Returns();
      Order order = orderDao.find(orderId);
      EndUser endUser = endUserDao.find(userId);
      ReceiverAddress receiverAddress = endUser.getDefaultAddress();
      returns.setSn(snDao.generate(Type.returns));
      if (receiverAddress != null) {
        returns.setShipper(receiverAddress.getConsignee());
        returns.setAddress(receiverAddress.getAddress());
        returns.setArea(receiverAddress.getAreaName());
        returns.setPhone(receiverAddress.getPhone());
        returns.setZipCode(receiverAddress.getZipCode());
      }
      returns.setTenantID(order.getTenantID());
      returns.setOperator(endUser.getUserName());
      returns.setOrder(order);
      returns.setReturnsStatus(ReturnsStatus.applyReturn);

      List<ReturnsItem> returnsItems = new ArrayList<ReturnsItem>();
      for (Long orderItemId : orderItemIds) {
        ReturnsItem returnsItem = new ReturnsItem();
        OrderItem orderItem = orderItemDao.find(orderItemId);
        returnsItem.setSn(orderItem.getSn());
        returnsItem.setName(orderItem.getName());
        returnsItem.setQuantity(orderItem.getQuantity());
        returnsItem.setPrice(orderItem.getPrice());
        returnsItem.setThumbnail(orderItem.getThumbnail());
        returnsItem.setTenantID(orderItem.getTenantID());
        returnsItem.setReturns(returns);
        returnsItems.add(returnsItem);
      }
      returnsDao.persist(returns);

      if (returnsItems.size() == order.getOrderItems().size()) {
        order.setAfterSalesStatus(AfterSalesStatus.applyRefund);
      } else {
        order.setAfterSalesStatus(AfterSalesStatus.applyPartialRefund);
      }
      orderDao.merge(order);
    } else {
      returns = returnsDao.find(returnsId);
      returns.setTrackingNo(trackingNo);
      returns.setDeliveryCorp(deliveryCorp);
      returnsDao.merge(returns);
    }
    return returns;
  }
}
