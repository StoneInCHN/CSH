package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Refunds;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.RefundsDao;
@Repository("refundsDaoImpl")
public class RefundsDaoImpl extends  BaseDaoImpl<Refunds,Long> implements RefundsDao {

}