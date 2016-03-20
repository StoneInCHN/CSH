package com.csh.dao.impl; 


import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.csh.dao.MsgEndUserDao;
import com.csh.entity.EndUser;
import com.csh.entity.MessageInfo;
import com.csh.entity.MsgEndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
@Repository("msgEndUserDaoImpl")
public class MsgEndUserDaoImpl extends  BaseDaoImpl<MsgEndUser,Long> implements MsgEndUserDao {

	public MsgEndUser findMsgEndUserByUserAndMsg(EndUser endUser, MessageInfo msg) {
		if (endUser == null || msg == null) {
		      return null;
		    }
		    try {
		      String jpql =
		          "select meu from MsgEndUser AS meu where meu.endUser=:endUser and meu.message=:message";
		      return entityManager.createQuery(jpql, MsgEndUser.class).setFlushMode(FlushModeType.COMMIT)
		          .setParameter("endUser", endUser).setParameter("message", msg).getSingleResult();
		    } catch (Exception e) {
		      e.printStackTrace();
		      return null;
		    }
	}

}