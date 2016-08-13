package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csh.dao.ReceiverAddressDao;
import com.csh.entity.EndUser;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ReceiverAddressService;

@Service("receiverAddressServiceImpl")
public class ReceiverAddressServiceImpl extends BaseServiceImpl<ReceiverAddress, Long> implements
    ReceiverAddressService {

  @Resource(name = "receiverAddressDaoImpl")
  private ReceiverAddressDao receiverAddressDao;

  @Resource(name = "receiverAddressDaoImpl")
  public void setBaseDao(ReceiverAddressDao receiverAddressDao) {
    super.setBaseDao(receiverAddressDao);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void addOrEditAddress(ReceiverAddress receiverAddress, EndUser endUser) {
    if (CollectionUtils.isEmpty(endUser.getReceivers())) {
      receiverAddress.setIsDefault(true);
    } else {
      if (receiverAddress.getIsDefault()) {
        for (ReceiverAddress receiver : endUser.getReceivers()) {
          if (receiver.getIsDefault()) {
            receiver.setIsDefault(false);
            receiverAddressDao.merge(receiver);
          }
        }
      }
    }
    receiverAddressDao.merge(receiverAddress);

  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void deleteAddress(ReceiverAddress receiverAddress, EndUser endUser) {
    Boolean isDefault = receiverAddress.getIsDefault();
    receiverAddressDao.remove(receiverAddress);
    if (isDefault) {
      for (ReceiverAddress address : endUser.getReceivers()) {
        if (address.getIsDefault() == false) {
          address.setIsDefault(true);
          receiverAddressDao.merge(address);
          break;
        }
      }
    }

  }
}
