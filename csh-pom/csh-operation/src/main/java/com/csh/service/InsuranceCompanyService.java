package com.csh.service;

import java.util.List;

import com.csh.entity.InsuranceCompany;
import com.csh.framework.service.BaseService;

public interface InsuranceCompanyService extends BaseService<InsuranceCompany, Long> {
  /**
   * 查找顶级保险公司
   * 
   * @return 顶级保险公司
   */
  List<InsuranceCompany> findRoots();

  /**
   * 查找保险公司
   * 
   * @param count 数量
   * @return 顶级保险公司
   */
  List<InsuranceCompany> findRoots(Integer count);

  /**
   * 查找顶级保险公司(缓存)
   * 
   * @param count 数量
   * @param cacheRegion 缓存区域
   * @return 顶级保险公司(缓存)
   */
  List<InsuranceCompany> findRoots(Integer count, String cacheRegion);

  /**
   * 查找上级保险公司
   * 
   * @param InsuranceCompany 保险公司
   * @return 上级保险公司
   */
  List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany);

  /**
   * 查找上级保险公司
   * 
   * @param InsuranceCompany 保险公司
   * @param count 数量
   * @return 上级保险公司
   */
  List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany, Integer count);

  /**
   * 查找上级保险公司(缓存)
   * 
   * @param InsuranceCompany 保险公司
   * @param count 数量
   * @param cacheRegion 缓存区域
   * @return 上级保险公司(缓存)
   */
  List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany, Integer count,
      String cacheRegion);

  /**
   * 查找保险公司树
   * 
   * @return 保险公司树
   */
  List<InsuranceCompany> findTree();

  /**
   * 查找下级保险公司
   * 
   * @param InsuranceCompany 保险公司
   * @return 下级保险公司
   */
  List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany);

  /**
   * 查找下级保险公司
   * 
   * @param InsuranceCompany 保险公司
   * @param count 数量
   * @return 下级保险公司
   */
  List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany, Integer count);

  /**
   * 查找下级保险公司(缓存)
   * 
   * @param InsuranceCompany 保险公司
   * @param count 数量
   * @param cacheRegion 缓存区域
   * @return 下级保险公司(缓存)
   */
  List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany, Integer count,
      String cacheRegion);
}
