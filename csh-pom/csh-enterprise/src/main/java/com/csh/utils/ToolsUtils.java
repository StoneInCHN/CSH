package com.csh.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class ToolsUtils implements Serializable {

  private static final long serialVersionUID = 7708468758384338657L;

  private static final String billDateFormat = "yyMMddHHmmss";

  private static Integer baseNo = 1;


  /**
   * 生成账单号 格式 yyMMddHHmmss-组织机构代码-(1-1000累加)
   * 
   * @return
   */
  public synchronized static String generateBillNo(String orgCode) {
    StringBuffer strBuffer = new StringBuffer();
    SimpleDateFormat sdf = new SimpleDateFormat(billDateFormat);
    String strDate = sdf.format(new Date());
    strBuffer.append(strDate);
    strBuffer.append(orgCode);
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
   * 在date的基础上加时间
   * 
   * @param date
   * @param field 比如Calendar.HOUR Calendar.SECOND
   * @param amount
   * @return
   */
  public static Date addTime(Date date, int field, int amount) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(field, amount);
    return calendar.getTime();
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
  
  public static Map<String, Object> convertStrToJson(String str) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JavaType javaType =
          mapper.getTypeFactory()
              .constructParametricType(HashMap.class, String.class, Object.class);
      Map<String, Object> map = mapper.readValue(str, javaType);
      return map;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * 生成账单号 格式 yyMMddHHmmss-组织机构代码-随机4位数-(1-1000累加)
   * 
   * @return
   */
  public synchronized static String generateRecordNo(String orgCode) {
    StringBuffer strBuffer = new StringBuffer();
    String strDate = DateTimeUtils.convertDateToString (new Date (), "yyyyMMddHHmmss");
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
}
