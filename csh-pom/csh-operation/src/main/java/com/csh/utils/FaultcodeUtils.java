package com.csh.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.csh.entity.FaultCode;

public class FaultcodeUtils {
  public static List<FaultCode> readCode(){
    System.out.println("read ...");
    List<FaultCode> lists = new ArrayList<FaultCode>(); 
    int count = 0;
    try {
      File f = new File("C:\\Users\\tanbiao\\Desktop\\csh\\OBD-II.txt");
      InputStream input = new FileInputStream(f);
      BufferedReader b = new BufferedReader(new InputStreamReader(input,"gbk"));
      String value = b.readLine();
     // if(value != null)
      FaultCode faultCode = new FaultCode();
      while(value !=null){
       count++;
         /*if(count%6 ==1){
           System.out.println(count +": " +value.substring(5, value.length()));
         }*/
         /*if(count%6 ==2){
           System.out.println(count +": " +value.substring(6, value.length()));
         }*/
         /*if(count%6 ==3){
           System.out.println(count +": " +value);
         }*/
         /*if(count%6 ==4){
           System.out.println(count +": " +value.substring(4, value.length()));
         }*/
       /*if(count%6 ==5){
         if (value.length() >6){
         System.out.println(count +": " +value.substring(6, value.length()));
         }else{
           System.out.println(count +": " +value.substring(5, value.length()));
         }
       }*/
       
         switch (count%6){
           case 1:
             faultCode.setCode(value.substring(5, value.length()));
             break;
           case 2:
             if (value.length() >6) {
               faultCode.setDefineCh(value.substring(6, value.length()));
             }
             break;
           case 3:
             if (value.length() >6) {
               faultCode.setDefineEn(value.substring(6, value.length()));
             }
             break;
           case 4:
             if (value.length() >4) {
               faultCode.setScope(value.substring(4, value.length()));
             }
             break;
           case 5:
             if (value.length() >6) {
               faultCode.setInfo(value.substring(6, value.length()));
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
}
