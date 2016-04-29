package com.csh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.InsuranceCompanyDao;
import com.csh.entity.InsuranceCompany;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.InsuranceCompanyService;

@Service("insuranceCompanyServiceImpl")
public class InsuranceCompanyServiceImpl extends BaseServiceImpl<InsuranceCompany, Long> implements
    InsuranceCompanyService {

  @Resource(name = "insuranceCompanyDaoImpl")
  private InsuranceCompanyDao insuranceCompanyDao;
  
  @Resource(name = "insuranceCompanyDaoImpl")
  public void setBaseDao(InsuranceCompanyDao insuranceCompanyDao) {
    super.setBaseDao(insuranceCompanyDao);
  }


  public List<InsuranceCompany> findRoots() {
    return insuranceCompanyDao.findRoots(null);
  }

  public List<InsuranceCompany> findRoots(Integer count) {
    return insuranceCompanyDao.findRoots(count);
  }

  public List<InsuranceCompany> findRoots(Integer count, String cacheRegion) {
    return insuranceCompanyDao.findRoots(count);
  }

  public List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany) {
    return insuranceCompanyDao.findParents(InsuranceCompany, null);
  }

  public List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany,
      Integer count) {
    return insuranceCompanyDao.findParents(InsuranceCompany, count);
  }

  public List<InsuranceCompany> findParents(InsuranceCompany InsuranceCompany,
      Integer count, String cacheRegion) {
    return insuranceCompanyDao.findParents(InsuranceCompany, count);
  }

  public List<InsuranceCompany> findTree() {
    return insuranceCompanyDao.findChildren(null, null);
  }

  public List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany) {
    return insuranceCompanyDao.findChildren(InsuranceCompany, null);
  }

  public List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany,
      Integer count) {
    return insuranceCompanyDao.findChildren(InsuranceCompany, count);
  }

  public List<InsuranceCompany> findChildren(InsuranceCompany InsuranceCompany,
      Integer count, String cacheRegion) {
    return insuranceCompanyDao.findChildren(InsuranceCompany, count);
  }



}
