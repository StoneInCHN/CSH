package com.csh.dao; 
import com.csh.entity.TenantAccount;
import com.csh.framework.dao.BaseDao;

public interface TenantAccountDao extends  BaseDao<TenantAccount,Long>{
	/**
	   * 获取租户登录token
	   * 
	   * @return
	   */
	  String getTenantUserToken(Long id);

	  /**
	   * 创建终端用户租户登录token
	   */
	  String createTenantUserToken(String token, Long id);
	  
	  /**
	   * 删除租户用户登录token
	   */
	  void deleteTenantUserToken(Long id);
	  /**
		 * 根据名字和机构代码查询用户
		 * @param name
		 * @param orgCode
		 * @return
		 */
	  public TenantAccount findByNameAndOrgCode(String name,String orgCode);
}