package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Wallet;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.WalletDao;
@Repository("walletDaoImpl")
public class WalletDaoImpl extends  BaseDaoImpl<Wallet,Long> implements WalletDao {

}