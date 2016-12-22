package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.TenantAccount;
import com.csh.entity.TenantInfo;
import com.csh.dao.TenantAccountDao;
import com.csh.dao.TenantInfoDao;
import com.csh.service.TenantAccountService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tenantAccountServiceImpl")
public class TenantAccountServiceImpl extends BaseServiceImpl<TenantAccount,Long> implements TenantAccountService {
	
	  @Resource(name="tenantAccountDaoImpl")
	  private TenantAccountDao tenantAccountDao;
	  
	  @Resource(name="tenantInfoDaoImpl")
	  private TenantInfoDao tenantInfoDao;
	  
      @Resource(name="tenantAccountDaoImpl")
      public void setBaseDao(TenantAccountDao tenantAccountDao) {
         super.setBaseDao(tenantAccountDao);
      }

      @Override
      public String getTenantUserToken(Long id) {
    	  return tenantAccountDao.getTenantUserToken(id);
      }

	@Override
	public String createTenantUserToken(String token, Long id) {
		return tenantAccountDao.createTenantUserToken(token, id);
	}

	@Override
	public void deleteTenantUserToken(Long id) {
		tenantAccountDao.deleteTenantUserToken(id);
	}

	@Override
	public TenantInfo findTenantWithOrgCode(String orgCode) {
		return tenantInfoDao.findTenantWithOrgCode(orgCode);
	}

	@Override
	public TenantAccount findByNameAndOrgCode(String name, String orgCode) {
		return tenantAccountDao.findByNameAndOrgCode(name,orgCode);
	}					
}