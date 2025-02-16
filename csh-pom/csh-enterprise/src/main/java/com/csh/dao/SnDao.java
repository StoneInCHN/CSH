
package com.csh.dao;

import com.csh.entity.Sn.Type;

/**
 * Dao - 序列号
 * 
 * @author 
 * @version 
 */
public interface SnDao {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Type type);

}