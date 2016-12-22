package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Store;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.StoreDao;
@Repository("storeDaoImpl")
public class StoreDaoImpl extends  BaseDaoImpl<Store,Long> implements StoreDao {

}