package com.csh.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.csh.entity.FaultCode;

public class FaultcodeUtils {
  public static List<FaultCode> readCode(){
    List<FaultCode> lists = new ArrayList<FaultCode>(); 
    int count = 0;
    try {
      File f = new File("C:\\Users\\tanbiao\\Desktop\\csh\\OBD-II-P1.txt");
      InputStream input = new FileInputStream(f);
      BufferedReader b = new BufferedReader(new InputStreamReader(input,"utf-8"));
      String value = b.readLine();
     // if(value != null)
      FaultCode faultCode = new FaultCode();
      while(value !=null){
       count++;
         switch (count%7){
           case 1:
             faultCode.setRangeScope(value.substring(7, value.length()).trim());
             break;
           case 2:
             faultCode.setCode(value.substring(5, value.length()).trim());
             break;
           case 3:
             if (value.length() >6) {
               faultCode.setDefineCh(value.substring(6, value.length()).trim());
             }
             break;
           case 4:
             if (value.length() >6) {
               faultCode.setDefineEn(value.substring(6, value.length()).trim());
             }
             break;
           case 5:
             if (value.length() >4) {
               faultCode.setScope(value.substring(4, value.length()).trim());
             }
             break;
           case 6:
             if (value.length() >6) {
               faultCode.setInfo(value.substring(6, value.length()).trim());
             }
             break;
           default:
             lists.add(faultCode);
             faultCode = new FaultCode();
         }
         value = b.readLine();
      }
      b.close();
      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("该txt文件的总行数为 :" +count);
    return lists;
  }
  /**
   * 准备导出的数据
   * @param response
   * @param lists
   * @return
   */
  public static List<Map<String, String>> prepareExportData(List<FaultCode> lists){
      List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
      for (FaultCode faultCode : lists) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", faultCode.getCode());
        map.put("rangeScope", faultCode.getRangeScope());
        map.put("defineCh", faultCode.getDefineCh());
        map.put("defineEn", faultCode.getDefineEn());
        map.put("scope", faultCode.getScope());
        map.put("info", faultCode.getInfo());
        mapList.add(map);
      }
      return mapList;
  }
  
}
