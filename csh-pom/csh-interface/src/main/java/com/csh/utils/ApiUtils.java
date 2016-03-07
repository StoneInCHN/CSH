package com.csh.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.csh.beans.Setting;
import com.csh.common.log.LogUtil;




/**
 * API工具类
 * 
 * @author sujinxuan
 * 
 */
public final class ApiUtils {

  public static Setting setting = SettingUtils.get();

  /**
   * 向基础API发送请求
   * 
   * @param controller
   * @param parameters
   * @return
   * @throws Exception
   */
  private static String sendPost(String parameters) {

    StringBuffer response = new StringBuffer();
    try {
          String domain = setting.getSmsUrl();
      if (LogUtil.isDebugEnabled(ApiUtils.class)) {
        LogUtil.debug(null,"Request API URL is : %s", domain + parameters);
      }

      URL url = new URL(domain);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();

      // add reuqest header
      con.setRequestMethod("GET");
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.write(parameters.getBytes());
      // wr.writeBytes(parameters);
      wr.flush();
      wr.close();

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    if (LogUtil.isDebugEnabled(ApiUtils.class)) {
      LogUtil.debug(ApiUtils.class, "Response", "Response from API is : %s", response.toString());
    }
    return response.toString();
  }

 

  public static String sendSmsMsg(String mobile,String msg) {
    try {
          String user = setting.getSmsUser();
          String pwd = setting.getSmsPwd();
          String apikey = setting.getApiKey();
          String message = URLEncoder.encode(msg, "UTF-8");
          String param = "username=" + user + "&password=" + pwd + "&apikey="+apikey+"&mobile=" + mobile + "&content=" + message;
          String rs = sendPost(param);
          return rs;
    } catch (Exception e) {
      e.printStackTrace();
    }
      return null;
  }

  

}
