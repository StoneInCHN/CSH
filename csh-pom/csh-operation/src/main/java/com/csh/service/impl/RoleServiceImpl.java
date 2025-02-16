package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.RoleDao;
import com.csh.entity.Role;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.RoleService;

/**
 * Service - 角色
 * 
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Resource(name = "roleDaoImpl")
    private RoleDao roleDao;
  
	@Resource(name = "roleDaoImpl")
	public void setBaseDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void save(Role role) {
		super.save(role);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Role update(Role role) {
		return super.update(role);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Role update(Role role, String... ignoreProperties) {
		return super.update(role, ignoreProperties);
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
	public void delete(Role role) {
		super.delete(role);
	}

  @Override
  public boolean hasContainAdmin(Role role) {
    return roleDao.hasContainAdmin(role);
  }

}