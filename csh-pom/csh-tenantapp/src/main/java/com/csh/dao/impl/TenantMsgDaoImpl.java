package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantMsg;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantMsgDao;
@Repository("tenantMsgDaoImpl")
public class TenantMsgDaoImpl extends  BaseDaoImpl<TenantMsg,Long> implements TenantMsgDao {

}