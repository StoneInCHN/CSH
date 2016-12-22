package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.MsgEndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.MsgEndUserDao;
@Repository("msgEndUserDaoImpl")
public class MsgEndUserDaoImpl extends  BaseDaoImpl<MsgEndUser,Long> implements MsgEndUserDao {

}