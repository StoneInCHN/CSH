package com.csh.service;

import java.util.List;

import com.csh.entity.Area;
import com.csh.framework.service.BaseService;

/**
 * Service - 地区
 * 
 */
public interface AreaService extends BaseService<Area, Long> {

	/**
	 * 查找顶级地区
	 * 
	 * @return 顶级地区
	 */
	List<Area> findRoots();

	/**
	 * 查找顶级地区
	 * 
	 * @param count
	 *            数量
	 * @return 顶级地区
	 */
	List<Area> findRoots(Integer count);

}