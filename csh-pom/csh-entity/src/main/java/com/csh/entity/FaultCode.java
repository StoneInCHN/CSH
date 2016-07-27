package com.csh.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;

@Entity
@Table(name = "csh_fault_code")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_fault_code_sequence")
public class FaultCode extends BaseEntity{

  /**
   * 
   */
  private static final long serialVersionUID = 2857218353963389324L;
  
  /**
   * 故障代码关键字
   */
  private String codeKey;
  
  /**
   * 故障代码值
   */
  private String codeValue;

  public String getCodeKey() {
    return codeKey;
  }

  public void setCodeKey(String codeKey) {
    this.codeKey = codeKey;
  }

  public String getCodeValue() {
    return codeValue;
  }

  public void setCodeValue(String codeValue) {
    this.codeValue = codeValue;
  }
  
}
