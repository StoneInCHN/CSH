package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ReceiverAddress;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReceiverAddressDao;
@Repository("receiverAddressDaoImpl")
public class ReceiverAddressDaoImpl extends  BaseDaoImpl<ReceiverAddress,Long> implements ReceiverAddressDao {

}