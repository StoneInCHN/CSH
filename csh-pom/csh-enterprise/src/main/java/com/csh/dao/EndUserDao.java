package com.csh.dao; 
import com.csh.entity.EndUser;
import com.csh.framework.dao.BaseDao;

public interface EndUserDao extends  BaseDao<EndUser,Long>{
   Long countUserByTenantID (Long tenantID);
}