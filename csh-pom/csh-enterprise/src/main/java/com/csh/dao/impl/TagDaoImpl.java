package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Tag;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.TagDao;
@Repository("tagDaoImpl")
public class TagDaoImpl extends  BaseDaoImpl<Tag,Long> implements TagDao {

}