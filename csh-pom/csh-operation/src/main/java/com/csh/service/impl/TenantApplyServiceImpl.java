package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.TenantApplyDao;
import com.csh.dao.TenantInfoDao;
import com.csh.dao.VersionConfigDao;
import com.csh.entity.TenantApply;
import com.csh.entity.TenantInfo;
import com.csh.entity.VersionConfig;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.IdentifierType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.IdentifierService;
import com.csh.service.TenantApplyService;
import com.csh.service.VersionConfigService;

@Service("tenantApplyServiceImpl")
public class TenantApplyServiceImpl extends BaseServiceImpl<TenantApply, Long> implements
    TenantApplyService {

  @Resource(name = "tenantApplyDaoImpl")
  public void setBaseDao(TenantApplyDao tenantApplyDao) {
    super.setBaseDao(tenantApplyDao);
  }

  @Resource(name = "tenantApplyDaoImpl")
  private TenantApplyDao tenantApplyDao;

  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "identifierServiceImpl")
  private IdentifierService identifierService;
  
  @Resource(name = "versionConfigDaoImpl")
  private VersionConfigDao versionConfigDao;
  
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void auditPassed(TenantApply tenantApply) {
    tenantApplyDao.merge(tenantApply);
    TenantInfo tenantInfo = new TenantInfo();
    tenantInfo.setAddress(tenantApply.getAddress());
    tenantInfo.setArea(tenantApply.getArea());
    tenantInfo.setStoreLogo(tenantApply.getPhoto());
    tenantInfo.setOrgCode(identifierService.getLatestOrgCode());
    tenantInfo.setAccountStatus(AccountStatus.ACTIVED);
    tenantInfo.setIsHaveAccount(false);
    tenantInfo.setLicense(tenantApply.getLicense());
    tenantInfo.setLatitude(tenantApply.getLatitude());
    tenantInfo.setLongitude(tenantApply.getLongitude());
    tenantInfo.setStoreLogo(tenantApply.getPhoto());
    tenantInfo.setContactPerson(tenantApply.getContactPerson());
    tenantInfo.setContactPhone(tenantApply.getContactPhone());
    tenantInfo.setTenantName(tenantApply.getTenantName());
    VersionConfig versionConfig = versionConfigDao.find(tenantApply.getVersionConfig());
    if (versionConfig!=null) {
      tenantInfo.setVersionConfig(versionConfig);
    }
    tenantInfoDao.persist(tenantInfo);
  }

}
