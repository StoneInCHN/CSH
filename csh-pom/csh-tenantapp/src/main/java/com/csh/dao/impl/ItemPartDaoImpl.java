package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ItemPart;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ItemPartDao;
@Repository("itemPartDaoImpl")
public class ItemPartDaoImpl extends  BaseDaoImpl<ItemPart,Long> implements ItemPartDao {

}