package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.InsuranceCompany;
import com.csh.dao.InsuranceCompanyDao;
import com.csh.service.InsuranceCompanyService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("insuranceCompanyServiceImpl")
public class InsuranceCompanyServiceImpl extends BaseServiceImpl<InsuranceCompany,Long> implements InsuranceCompanyService {

      @Resource(name="insuranceCompanyDaoImpl")
      public void setBaseDao(InsuranceCompanyDao insuranceCompanyDao) {
         super.setBaseDao(insuranceCompanyDao);
  }
}