package com.csh.dao.impl; 

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository; 

import com.csh.entity.SmsToken;
import com.csh.entity.commonenum.CommonEnum.SmsTokenType;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.SmsTokenDao;
@Repository("smsTokenDaoImpl")
public class SmsTokenDaoImpl extends  BaseDaoImpl<SmsToken,Long> implements SmsTokenDao {

    
         public SmsToken findByUserMobile(String mobile) {
                if (mobile == null) {
                  return null;
                }
                try {
                  String jpql = "select st from SmsToken st where lower(st.mobile) = lower(:mobile)";
                  return entityManager.createQuery(jpql, SmsToken.class).setFlushMode(FlushModeType.COMMIT)
                      .setParameter("mobile", mobile).getSingleResult();
                } catch (NoResultException e) {
                  return null;
                }
          }

          @Override
          public SmsToken findByUserMobile(String mobile, SmsTokenType smsTokenType) {
            if (mobile == null || smsTokenType==null) {
              return null;
            }
            try {
              String jpql = "select st from SmsToken st where lower(st.mobile) = lower(:mobile) and st.smsTokenType = :smsTokenType";
              return entityManager.createQuery(jpql, SmsToken.class).setFlushMode(FlushModeType.COMMIT)
                  .setParameter("mobile", mobile).setParameter("smsTokenType", smsTokenType).getSingleResult();
            } catch (NoResultException e) {
              return null;
            }
          }
}