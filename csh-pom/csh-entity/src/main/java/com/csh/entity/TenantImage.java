package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 租户展示图片
 * 
 * @author huyong
 *
 */
@Entity
@Table(name = "csh_tenant_image")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_tenant_image_sequence")
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

  @ManyToOne
  public TenantInfo getTenantInfo ()
  {
    return tenantInfo;
  }

  public void setTenantInfo (TenantInfo tenantInfo)
  {
    this.tenantInfo = tenantInfo;
  }
  
  
}
