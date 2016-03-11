package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.IdentityCard;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.IdentityCardDao;
@Repository("identityCardDaoImpl")
public class IdentityCardDaoImpl extends  BaseDaoImpl<IdentityCard,Long> implements IdentityCardDao {

}