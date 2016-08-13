package com.csh.service;

import com.csh.entity.EndUser;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.framework.service.BaseService;

public interface ReceiverAddressService extends BaseService<ReceiverAddress, Long> {

  public void addOrEditAddress(ReceiverAddress receiverAddress, EndUser endUser);

  public void deleteAddress(ReceiverAddress receiverAddress, EndUser endUser);
}
