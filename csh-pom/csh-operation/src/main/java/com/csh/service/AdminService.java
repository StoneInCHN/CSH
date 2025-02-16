package com.csh.service;

import java.util.List;

import com.csh.entity.Admin;
import com.csh.framework.service.BaseService;

/**
 * Service - 管理员
 * 
 */
public interface AdminService extends BaseService<Admin, Long> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
	
	/**
     * 判断E-mail是否存在
     * 
     * @param email
     *            E-mail(忽略大小写)
     * @return E-mail是否存在
     */
    boolean emailExists(String email);

	/**
	 * 根据用户名查找管理员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 管理员，若不存在则返回null
	 */
	Admin findByUsername(String username);
	
	
  /**
   * 根据名字查询
   * @param name
   * @return
   */
	List<Admin> findByName(String name);
	
	/**
	 * 通过名字精确查找
	 * @param name
	 * @return
	 */
	Admin findByNameAccurate (String name);
  
  
	/**
	 * 根据ID查找权限
	 * 
	 * @param id
	 *            ID
	 * @return 权限,若不存在则返回null
	 */
	List<String> findAuthorities(Long id);

	/**
	 * 判断管理员是否登录
	 * 
	 * @return 管理员是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录管理员
	 * 
	 * @return 当前登录管理员,若不存在则返回null
	 */
	Admin getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名,若不存在则返回null
	 */
	String getCurrentUsername();
	
	
	/**
	 * 判断管理员是否为内置
	 * 
	 * @return 管理员是否为内置
	 */
	boolean isSystemAdmin();
	
}