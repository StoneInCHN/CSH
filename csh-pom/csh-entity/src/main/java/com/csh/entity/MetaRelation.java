package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

/**
 * 配置元关系
 * 
 * @author shijun
 *
 */
@Entity
@Table (name = "csh_meta_relation")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_meta_relation_sequence")
public class MetaRelation extends BaseEntity
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 主配置元ID
   */
  private ConfigMeta mainID;

  /**
   * 关系配置元ID
   */
  private ConfigMeta relationID;

  /**
   * 关系类型
   */
  private com.csh.entity.commonenum.CommonEnum.MetaRelation metaRelation;

 
  @ManyToOne
  public ConfigMeta getMainID ()
  {
    return mainID;
  }

  public void setMainID (ConfigMeta mainID)
  {
    this.mainID = mainID;
  }

  @ManyToOne
  public ConfigMeta getRelationID ()
  {
    return relationID;
  }

  public void setRelationID (ConfigMeta relationID)
  {
    this.relationID = relationID;
  }

  public com.csh.entity.commonenum.CommonEnum.MetaRelation getMetaRelation ()
  {
    return metaRelation;
  }

  public void setMetaRelation (
      com.csh.entity.commonenum.CommonEnum.MetaRelation metaRelation)
  {
    this.metaRelation = metaRelation;
  }

}
