package com.csh.utils;

import java.util.Random;

/**
 * 
 * @author tanbiao
 *  公共方法
 */
public class CommonUtils {
  
  public static String randomPwd(){
    Random random = new Random();
    return random.longs(6).toString();
  }
  
}
