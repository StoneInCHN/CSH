package com.csh.dao.impl;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.csh.dao.SnDao;
import com.csh.entity.Sn;
import com.csh.entity.Sn.Type;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.utils.FreemarkerUtils;

import freemarker.template.TemplateException;

@Repository("snDaoImpl")
public class SnDaoImpl extends BaseDaoImpl<Sn, Long> implements SnDao {

  private HiloOptimizer productHiloOptimizer;
  private HiloOptimizer clearingHiloOptimizer;
  private HiloOptimizer deductClearingHiloOptimizer;
  private HiloOptimizer refundsHiloOptimizer;
  private HiloOptimizer shippingHiloOptimizer;
  private HiloOptimizer returnsHiloOptimizer;
  private HiloOptimizer orderHiloOptimizer;

  @PersistenceContext
  private EntityManager entityManager;
  @Value("${sn.product.prefix}")
  private String productPrefix;
  @Value("${sn.product.maxLo}")
  private int productMaxLo;
  @Value("${sn.clearing.prefix}")
  private String clearingPrefix;
  @Value("${sn.clearing.maxLo}")
  private int clearingMaxLo;
  @Value("${sn.refunds.prefix}")
  private String refundsPrefix;
  @Value("${sn.refunds.maxLo}")
  private int refundsMaxLo;
  @Value("${sn.shipping.prefix}")
  private String shippingPrefix;
  @Value("${sn.shipping.maxLo}")
  private int shippingMaxLo;
  @Value("${sn.returns.prefix}")
  private String returnsPrefix;
  @Value("${sn.returns.maxLo}")
  private int returnsMaxLo;
  @Value("${sn.order.prefix}")
  private String orderPrefix;
  @Value("${sn.order.maxLo}")
  private int orderMaxLo;


  public void afterPropertiesSet() throws Exception {
    productHiloOptimizer = new HiloOptimizer(Type.product, productPrefix, productMaxLo);
    clearingHiloOptimizer = new HiloOptimizer(Type.clearing, clearingPrefix, clearingMaxLo);
    deductClearingHiloOptimizer = new HiloOptimizer(Type.clearing, clearingPrefix, clearingMaxLo);
    refundsHiloOptimizer = new HiloOptimizer(Type.refunds, refundsPrefix, refundsMaxLo);
    shippingHiloOptimizer = new HiloOptimizer(Type.shipping, shippingPrefix, shippingMaxLo);
    returnsHiloOptimizer = new HiloOptimizer(Type.returns, returnsPrefix, returnsMaxLo);
    orderHiloOptimizer = new HiloOptimizer(Type.order, orderPrefix, orderMaxLo);
  }

  public String generate(Type type) {
    Assert.notNull(type);
    if (type == Type.product) {
      return productHiloOptimizer.generate();
    } else if (type == Type.clearing) {
      return clearingHiloOptimizer.generate();
    } else if (type == Type.deductClearing) {
      return deductClearingHiloOptimizer.generate();
    } else if (type == Type.refunds) {
      return refundsHiloOptimizer.generate();
    } else if (type == Type.shipping) {
      return shippingHiloOptimizer.generate();
    } else if (type == Type.returns) {
      return returnsHiloOptimizer.generate();
    } else if (type == Type.order) {
      return orderHiloOptimizer.generate();
    }
    return null;
  }

  private long getLastValue(Type type) {
    String jpql = "select sn from Sn sn where sn.type = :type";
    Sn sn =
        entityManager.createQuery(jpql, Sn.class).setFlushMode(FlushModeType.COMMIT)
            .setLockMode(LockModeType.PESSIMISTIC_WRITE).setParameter("type", type)
            .getSingleResult();
    long lastValue = sn.getLastValue();
    sn.setLastValue(lastValue + 1);
    entityManager.merge(sn);
    return lastValue;
  }

  /**
   * 高低位算法
   */
  private class HiloOptimizer {

    private Type type;
    private String prefix;
    private int maxLo;
    private int lo;
    private long hi;
    private long lastValue;

    public HiloOptimizer(Type type, String prefix, int maxLo) {
      this.type = type;
      this.prefix = prefix != null ? prefix.replace("{", "${") : "";
      this.maxLo = maxLo;
      this.lo = maxLo + 1;
    }

    public synchronized String generate() {
      if (lo > maxLo) {
        lastValue = getLastValue(type);
        lo = lastValue == 0 ? 1 : 0;
        hi = lastValue * (maxLo + 1);
      }
      try {
        return FreemarkerUtils.process(prefix, null) + (hi + lo++);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (TemplateException e) {
        e.printStackTrace();
      }
      return String.valueOf(hi + lo++);
    }
  }

}
