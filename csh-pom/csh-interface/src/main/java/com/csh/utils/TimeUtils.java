package com.csh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author sujinxuan
 *
 */
public class TimeUtils {

  /**
   * 格式化时间
   * 
   * @param sdf 格式化时间格式:yyyyMMdd
   * @param time 时间戳
   * @return
   */
  public static String format(String format, long time) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Date d = new Date(time);
    return sdf.format(d);
  }

}
