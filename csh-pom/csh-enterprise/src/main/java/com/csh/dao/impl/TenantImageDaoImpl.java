package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.TenantImageDao;
import com.csh.entity.TenantImage;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * Dao - 租户图片
 * 
 */
@Repository("tenantImageDaoImpl")
public class TenantImageDaoImpl extends BaseDaoImpl<TenantImage, Long> implements
TenantImageDao {
 

}
