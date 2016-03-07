package com.csh.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 * 
 * @author sujinxuan
 *
 */
public class TimeUtils {

  private static final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * 获取输入日期前或后N个月时间
   * 
   * @param date
   * @param month
   * @return
   */
  public static Date getMonthTime(Date date, Integer month) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, month);
    return calendar.getTime();

  }

  /**
   * 格式化时间 YYYY-MM-DD
   * 
   * @param date
   * @return
   */
  public static String getSimpleDateString(Date date) {
    return simpleFormat.format(date);
  }

  /**
   * 比较当前时间与传入时间相差的天数
   * 
   * @param date
   * @param days 相差的天数
   * @return
   */
  public static boolean isOverDate(Date createDate, Integer days) {
    if ((new Date().getTime() - createDate.getTime()) / 1000 / 60 / 60 / 24 >= days) {
      return true;
    }
    return false;
  }

  /**
   * 在现有的时间基础上往后基于传入的天数添加时间
   * 
   * @param days
   * @return
   */
  public static Date addDays(Date originalDate, Integer days) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(originalDate);
    calendar.add(Calendar.DATE, days);
    Date date = calendar.getTime();
    return date;
  }
  
  /**
   * 在现有的时间基础上往后基于传入的月数添加时间
   * 
   * @param month
   * @return
   */
  public static Date addMonth(Date originalDate, Integer month) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(originalDate);
    calendar.add(Calendar.MONTH, month);
    Date date = calendar.getTime();
    return date;
  }
  
  /**
   * 取距离当前日期i天的指定日期的零点零分零秒
   * @param date
   * @param i
   * @return
   */
  public static Date getDateStart(Date date,int i) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE,i);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }
  
}
