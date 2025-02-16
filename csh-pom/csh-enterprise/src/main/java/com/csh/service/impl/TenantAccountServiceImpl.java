package com.csh.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csh.beans.Principal;
import com.csh.dao.TenantAccountDao;
import com.csh.entity.ConfigMeta;
import com.csh.entity.Role;
import com.csh.entity.TenantAccount;
import com.csh.entity.TenantInfo;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantAccountService;



/**
 * Service - 租户用户
 * 
 */
@Service("tenantAccountServiceImpl")
public class TenantAccountServiceImpl extends BaseServiceImpl<TenantAccount, Long> implements
    TenantAccountService {

  @Resource(name = "tenantAccountDaoImpl")
  private TenantAccountDao tenantAccountDao;

  @Resource(name = "tenantAccountDaoImpl")
  public void setBaseDao(TenantAccountDao tenantAccountDao) {
    super.setBaseDao(tenantAccountDao);
  }

  @Transactional(readOnly = true)
  public boolean usernameExists(String username) {
    return tenantAccountDao.usernameExists(username);
  }

  @Transactional(readOnly = true)
  public boolean emailExists(String email) {
    return tenantAccountDao.emailExists(email);
  }

  @Transactional(readOnly = true)
  public TenantAccount findByUsername(String username) {
    return tenantAccountDao.findByUsername(username);
  }

  public TenantAccount findByNameAndOrgCode(String username, String orgCode) {
    return tenantAccountDao.findByNameAndOrgCode(username,orgCode);
  }

  @Transactional(readOnly = true)
  public List<TenantAccount> findByName(String name) {
    return tenantAccountDao.findByName(name);
  }

  @Override
  public TenantAccount findByNameAccurate(String name) {
    return tenantAccountDao.findByNameAccurate(name);
  }


  @Transactional(readOnly = true)
  public List<ConfigMeta> findAuthorities(Long id) {
    List<ConfigMeta> authorityResources = new ArrayList<ConfigMeta>();
    TenantAccount tenantUser = tenantAccountDao.find(id);
    if (tenantUser != null) {
      for (Role role : tenantUser.getRoles()) {
        authorityResources.addAll(role.getConfigMetas ());
      }
    }
    return authorityResources;
  }

  @Transactional(readOnly = true)
  public boolean isAuthenticated() {
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      return subject.isAuthenticated();
    }
    return false;
  }

  @Transactional(readOnly = true)
  public TenantAccount getCurrent() {
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      Principal principal = (Principal) subject.getPrincipal();
      if (principal != null) {
        return tenantAccountDao.find(principal.getId());
      }
    }
    return null;
  }

  @Transactional(readOnly = true)
  public String getCurrentUsername() {
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      Principal principal = (Principal) subject.getPrincipal();
      if (principal != null) {
        return principal.getUsername();
      }
    }
    return null;
  }
  
  @Transactional(readOnly = true)
  public Long getCurrentTenantID() {
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      Principal principal = (Principal) subject.getPrincipal();
      if (principal != null) {
        return principal.getTenantInfo().getId();
      }
    }
    return null;
  }
  @Transactional(readOnly = true)
  public TenantInfo getCurrentTenantInfo() {
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      Principal principal = (Principal) subject.getPrincipal();
      if (principal != null) {
        return principal.getTenantInfo();
      }
    }
    return null;
  }
  
  @Transactional(readOnly = true)
  public String getCurrentTenantOrgCode() {

    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
      Principal principal = (Principal) subject.getPrincipal();
      if (principal != null) {
        return principal.getTenantInfo().getOrgCode();
      }
    }
    return null;
  
  }

  @Override
  @Transactional
  @CacheEvict(value = "authorization", allEntries = true)
  public void save(TenantAccount admin) {
    super.save(admin);
  }

  @Override
  @Transactional
  @CacheEvict(value = "authorization", allEntries = true)
  public TenantAccount update(TenantAccount admin) {
    return super.update(admin);
  }

  @Override
  @Transactional
  @CacheEvict(value = "authorization", allEntries = true)
  public TenantAccount update(TenantAccount admin, String... ignoreProperties) {
    return super.update(admin, ignoreProperties);
  }

  @Override
  @Transactional
  @CacheEvict(value = "authorization", allEntries = true)
  public void delete(Long id) {
    super.delete(id);
  }

  @Override
  @Transactional
  @CacheEvict(value = "authorization", allEntries = true)
  public void delete(Long... ids) {
    super.delete(ids);
  }

  @Override
  @Transactional
  @CacheEvict(value = "authorization", allEntries = true)
  public void delete(TenantAccount admin) {
    super.delete(admin);
  }

  @Transactional(readOnly = true)
  public boolean isSystemAdmin() {
    TenantAccount admin = getCurrent();
    Set<Role> roles = admin.getRoles();
    Iterator<Role> it = roles.iterator();
    while (it.hasNext()) {
      Role role = it.next();
      if (role.getIsSystem()) {
        return true;
      }
    }
    return false;
  }

}
