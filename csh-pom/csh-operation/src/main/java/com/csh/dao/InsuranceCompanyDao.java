package com.csh.dao;

import java.util.List;

import com.csh.entity.InsuranceCompany;
import com.csh.framework.dao.BaseDao;

public interface InsuranceCompanyDao extends BaseDao<InsuranceCompany, Long> {
  /**
   * 查找顶级保险公司
   * 
   * @param count 数量
   * @return 顶级保险公司
   */
  List<InsuranceCompany> findRoots(Integer count);

  /**
   * 查找上级保险公司
   * 
   * @param InsuranceCompany 保险公司
   * @param count 数量
   * @return 上级保险公司
   */
  List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany, Integer count);

  /**
   * 查找下级保险公司
   * 
   * @param InsuranceCompany 保险公司
   * @param count 数量
   * @return 下级保险公司
   */
  List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany,
      Integer count);
}
