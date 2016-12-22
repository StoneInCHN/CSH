package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.InsuranceCompany;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.InsuranceCompanyDao;
@Repository("insuranceCompanyDaoImpl")
public class InsuranceCompanyDaoImpl extends  BaseDaoImpl<InsuranceCompany,Long> implements InsuranceCompanyDao {

}