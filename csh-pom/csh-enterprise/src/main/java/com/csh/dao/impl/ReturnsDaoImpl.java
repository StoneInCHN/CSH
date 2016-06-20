package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Returns;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReturnsDao;
@Repository("returnsDaoImpl")
public class ReturnsDaoImpl extends  BaseDaoImpl<Returns,Long> implements ReturnsDao {

}