package com.csh.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;

/**
 * 租户展示图片
 * 
 * @author huyong
 *
 */
@Entity
@Table(name = "csh_tenant_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_info_sequence")
public class TenantImage extends BaseEntity {

  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String image;
  
  private TenantInfo tenantInfo;

  public String getImage ()
  {
    return image;
  }

  public void setImage (String image)
  {
    this.image = image;
  }

  public TenantInfo getTenantInfo ()
  {
    return tenantInfo;
  }

  public void setTenantInfo (TenantInfo tenantInfo)
  {
    this.tenantInfo = tenantInfo;
  }
  
  
}
