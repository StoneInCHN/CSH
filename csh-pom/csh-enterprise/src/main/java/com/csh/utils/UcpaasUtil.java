package com.csh.utils;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import sun.misc.BASE64Encoder;

import com.csh.beans.Setting;



public class UcpaasUtil {

  public static Setting setting = SettingUtils.get();


  public static String SendCodeBySms(String mobile, String content) {

    try {
      String param = content;
      String time = DateTimeUtils.convertDateToString (new Date (),"yyyyMMddHHmmss");
      String sig = getSigParam(time);
      Map<String, String> head = getAuthorization(time);
      String template = setting.getUcpaasTemplate();
      String appId = setting.getUcpaasAppId();
      String reqUrl =
          setting.getUcpaasUrl() + "/" + setting.getUcpaasVersion() + "/Accounts/"
              + setting.getUcpaasSid() + "/Messages/templateSMS?sig=" + sig;
      // 设置post json数据
      String body =
          (new StringBuilder("{\"templateSMS\":{").append("\"appId\":").append("\"" + appId + "\"")
              .append(",").append("\"to\":").append("\"" + mobile + "\"").append(",")
              .append("\"templateId\":").append("\"" + template + "\"").append(",")
              .append("\"param\":").append("\"" + param + "\"").append("}}")).toString();
      //System.out.println(body);
      // {"resp":{"respCode":"000000","failure":1,"templateSMS":{"createDate":20140623185016,"smsId":"f96f79240e372587e9284cd580d8f953"}}}
      String response = ApiUtils.post(reqUrl, body, head);
      return response;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  
  public static String SendCodeByVoice(String mobile, String code) {

	    try {
	      String displayNum = setting.getUcpaasCallDisplay();
	      String time = DateTimeUtils.convertDateToString (new Date (),"yyyyMMddHHmmss");
	      String sig = getSigParam(time);
	      Map<String, String> head = getAuthorization(time);
	      String appId = setting.getUcpaasAppId();
	      String reqUrl =setting.getUcpaasUrl()+"/"+setting.getUcpaasVersion()+"/Accounts/"+setting.getUcpaasSid()+"/Calls/voiceCode?sig=" + sig;
	          
	      // 设置post json数据
	      String body = (new StringBuilder("{\"voiceCode\":{")
			.append("\"appId\":").append("\""+appId+"\"").append(",")
			.append("\"to\":").append("\""+mobile+"\"").append(",")
			.append("\"verifyCode\":").append("\""+code+"\"").append(",")
			.append("\"displayNum\":").append("\""+displayNum+"\"")
			.append("}}")).toString();
	      System.out.println(body);
	      // {"resp":{"respCode":"000000","failure":1,"templateSMS":{"createDate":20140623185016,"smsId":"f96f79240e372587e9284cd580d8f953"}}}
	      String response = ApiUtils.post(reqUrl, body, head);
	      return response;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
  
  /**
   * MD5数字签名
   * 
   * @param src
   * @return
   * @throws Exception
   */
  public static String md5Digest(String src) throws Exception {
    // 定义数字签名方法, 可用：MD5, SHA-1
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] b = md.digest(src.getBytes("UTF-8"));
    return byte2HexStr(b);
  }

  /**
   * 字节数组转化为大写16进制字符串
   * 
   * @param b
   * @return
   */
  public static String byte2HexStr(byte[] b) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < b.length; i++) {
      String s = Integer.toHexString(b[i] & 0xFF);
      if (s.length() == 1) {
        sb.append("0");
      }
      sb.append(s.toUpperCase());
    }
    return sb.toString();
  }


  /**
   * 获取REST API 验证参数
   * 
   * @return
   */
  public static String getSigParam(String time) throws Exception {
    String sid = setting.getUcpaasSid();
    String token = setting.getUcpaasToken();
    // 生成验证参数
    String sig = md5Digest(sid + token + time).toUpperCase();
    return sig;

  }

  /**
   * 获取包头验证信息
   * 
   * @param time
   * @return
   * @throws Exception
   */
  private static Map<String, String> getAuthorization(String time) throws Exception {
    String authorization = base64Encoder(setting.getUcpaasSid() + ":" + time);
    // 设置post包头参数
    Map<String, String> head = new HashMap<String, String>();
    head.put("Host", "api.ucpaas.com");
    head.put("Accept", "application/json");
    head.put("Content-Type", "application/json;charset=utf-8");
    head.put("Authorization", authorization);
    return head;
  }

  /**
   * BASE64编码
   * 
   * @param src
   * @return
   * @throws Exception
   */
  @SuppressWarnings("restriction")
  public static String base64Encoder(String src) throws Exception {
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(src.getBytes("UTF-8"));
  }
  
  /**
   * 产生随机密码
   * @return
   */
  public static String randomPwd() {
    Random r = new Random();
    char[] str1 =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};
    char[] str2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    StringBuffer sBuffer = new StringBuffer();
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    int Intindex = Math.abs(r.nextInt(5));
    sBuffer.replace(Intindex, Intindex, str2[Math.abs(r.nextInt(str2.length))] + "");
   
    return sBuffer.toString();
  }
  
  /**
   * 通过短信发送账号
   * 
   * @param mobile
   * @param code
   * @return
   */
  public static String SendAccountBySms(String mobile,String password) {
    try {
      String param = mobile + "," + password;
      String time = DateTimeUtils.convertDateToString (new Date (),"yyyyMMddHHmmss");
      String sig = getSigParam(time);
      Map<String, String> head = getAuthorization(time);
      String template = setting.getUcpaasAccountTemplate();
      String appId = setting.getUcpaasAppId();
      String reqUrl =
          setting.getUcpaasUrl() + "/" + setting.getUcpaasVersion() + "/Accounts/"
              + setting.getUcpaasSid() + "/Messages/templateSMS?sig=" + sig;
      // 设置post json数据
      String body =
          (new StringBuilder("{\"templateSMS\":{").append("\"appId\":").append("\"" + appId + "\"")
              .append(",").append("\"to\":").append("\"" + mobile + "\"").append(",")
              .append("\"templateId\":").append("\"" + template + "\"").append(",")
              .append("\"param\":").append("\"" + param + "\"").append("}}")).toString();
      // {"resp":{"respCode":"000000","failure":1,"templateSMS":{"createDate":20140623185016,"smsId":"f96f79240e372587e9284cd580d8f953"}}}
      String response = ApiUtils.post(reqUrl, body, head);
      return response;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
