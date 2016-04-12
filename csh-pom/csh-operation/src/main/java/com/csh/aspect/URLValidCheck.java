package com.csh.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented  

public @interface URLValidCheck {
  
  public enum UrlMethodType {
    /**
     * list请求
     */
    LIST,
    /**
     * update请求
     */
    UPDATE,

  };
  
  UrlMethodType urlMethod();
}
