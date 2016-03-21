package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.WalletRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.WalletRecordDao;
@Repository("walletRecordDaoImpl")
public class WalletRecordDaoImpl extends  BaseDaoImpl<WalletRecord,Long> implements WalletRecordDao {

}