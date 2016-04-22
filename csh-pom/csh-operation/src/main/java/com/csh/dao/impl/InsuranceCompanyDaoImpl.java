package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.InsuranceCompanyDao;
import com.csh.entity.InsuranceCompany;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("insuranceCompanyDaoImpl")
public class InsuranceCompanyDaoImpl extends BaseDaoImpl<InsuranceCompany, Long> implements
    InsuranceCompanyDao {

}
