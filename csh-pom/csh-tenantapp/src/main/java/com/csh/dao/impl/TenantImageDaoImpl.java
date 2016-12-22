package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.TenantImage;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TenantImageDao;
@Repository("tenantImageDaoImpl")
public class TenantImageDaoImpl extends  BaseDaoImpl<TenantImage,Long> implements TenantImageDao {

}