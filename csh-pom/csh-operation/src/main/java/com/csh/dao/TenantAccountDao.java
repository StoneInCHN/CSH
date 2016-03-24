package com.csh.dao; 
import com.csh.entity.TenantAccount;
import com.csh.framework.dao.BaseDao;

public interface TenantAccountDao extends  BaseDao<TenantAccount,Long>{
  /**
   * 判断用户名是否存在
   * 
   * @param username 用户名(忽略大小写)
   * @return 用户名是否存在
   */
  boolean usernameExists(String username);
}