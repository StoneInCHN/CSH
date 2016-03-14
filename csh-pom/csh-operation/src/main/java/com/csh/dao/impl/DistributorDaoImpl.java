package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.DistributorDao;
import com.csh.entity.Distributor;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("distributorDaoImpl")
public class DistributorDaoImpl extends BaseDaoImpl<Distributor, Long> implements DistributorDao {

}
