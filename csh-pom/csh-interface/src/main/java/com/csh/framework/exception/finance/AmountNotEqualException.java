package com.csh.framework.exception.finance;

import com.csh.framework.exception.GeneralException;

/**
 * 金额不相等异常
 * 
 * @author shijun
 *
 */  
public class AmountNotEqualException extends GeneralException {

  /**
   * 
   */
  private static final long serialVersionUID = 1077986555881474678L;

  public AmountNotEqualException(String message) {
    super(message);
  }

}
