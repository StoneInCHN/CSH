package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Identifier;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.IdentifierDao;
@Repository("identifierDaoImpl")
public class IdentifierDaoImpl extends  BaseDaoImpl<Identifier,Long> implements IdentifierDao {

}