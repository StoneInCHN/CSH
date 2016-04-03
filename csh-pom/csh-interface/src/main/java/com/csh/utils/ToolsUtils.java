package com.csh.utils;

import java.lang.reflect.Field;
import java.util.Date;

public class ToolsUtils {


  private static Integer baseNo = 1;


  /**
   * 生成账单号 格式 yyMMddHHmmss-组织机构代码-随机4位数-(1-1000累加)
   * 
   * @return
   */
  public synchronized static String generateRecordNo(String orgCode) {
    StringBuffer strBuffer = new StringBuffer();
    String strDate = TimeUtils.getLongDateStr(new Date());
    strBuffer.append(strDate);
    strBuffer.append(orgCode);
    strBuffer.append((int) ((Math.random() * 9 + 1) * 1000));
    // 订单尾号增自999时重置
    if (baseNo == 999) {
      baseNo = 1;
    }
    if (baseNo.toString().length() == 1) {
      strBuffer.append("00");
    }
    if (baseNo.toString().length() == 2) {
      strBuffer.append("0");
    }
    strBuffer.append(baseNo++);
    return strBuffer.toString();
  }



  /**
   * 检查对象obj的成员变量是否都为null
   * 
   * @param obj
   * @return
   */
  public static boolean checkObjAllFieldNull(Object obj) {
    for (Field f : obj.getClass().getDeclaredFields()) {
      f.setAccessible(true);
      try {
        if (f.get(obj) != null) {
          return false;
        }
      } catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  /**
   * 对象字段toString显示方法
   * 
   * @param obj
   * @return
   */
  public static String entityToString(Object obj) {

    if (obj == null)
      return "null";
    StringBuffer sb = new StringBuffer();
    Class<?> clazz = obj.getClass();
    Field[] fields = clazz.getDeclaredFields();

    sb.append(clazz.getName() + "{");
    try {
      for (Field field : fields) {
        field.setAccessible(true);
        sb.append("\n  " + field.getName() + ":" + field.get(obj));
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
    sb.append("\n}");
    return sb.toString();
  }
}
