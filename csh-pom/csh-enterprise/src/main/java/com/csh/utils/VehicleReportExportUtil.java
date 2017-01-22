package com.csh.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.xalan.templates.ElemApplyImport;
import org.json.JSONArray;
import org.json.JSONObject;

import com.csh.entity.Vehicle;

/**
 * 导出车辆报表数据到excel
 */
public class VehicleReportExportUtil extends Thread {
    
    private int days;
    private String yearMonth;
    private List<Vehicle> vehicleList;
    private String[] items;
    private Map<String, Map<String, String[]>> sheetMap;
    private OutputStream out;
    private Object locker;
    public VehicleReportExportUtil(int days, String yearMonth, List<Vehicle> vehicleList, 
        String[] items, Map<String, Map<String, String[]>> sheetMap, OutputStream out, Object locker){
        this.days = days;
        this.yearMonth = yearMonth;
        this.vehicleList = vehicleList;
        this.items = items;
        this.sheetMap = sheetMap;
        this.out = out;
        this.locker = locker;
    }
   @Override
   public void run() {
     exportExcel(days, yearMonth, vehicleList, items, sheetMap, out);//导出excel
     synchronized (this.locker) {
       locker.notify();//导出excel完毕，notify主线程的对象锁
    }
   }
   
   public void exportExcel(int days, String yearMonth, List<Vehicle> vehicleList, 
       String[] items, Map<String, Map<String, String[]>> sheetMap, OutputStream out) {
     
     //创建一个excel工作簿
     HSSFWorkbook workbook = new HSSFWorkbook();
     //第一行标题样式（白字蓝底）
     HSSFCellStyle titleStyle = getTitleStyle(workbook) ;
     //内容行样式   （白底黑字）
     HSSFCellStyle contentStyle = getContentStyle(workbook); 
     //第一行内容
     String[] titleStr = new String[days + 1];
     titleStr[0] = "车牌号";
     for (int i = 1; i < titleStr.length; i++) {
       titleStr[i] = yearMonth+i+"日";
     }
     Map<String, String> devicePlate = new HashMap<String, String>();
     for (int i = 0; i < vehicleList.size(); i++) {
       devicePlate.put(vehicleList.get(i).getDeviceNo(), vehicleList.get(i).getPlate());
     }
     Map<String, String> sheetNameMap = getSheetNameMap();
     for (int i = 0; i < items.length; i++) {
       //String sheetName = SpringUtils.getMessage("csh.vehicleReportExport."+items[i]);//为啥获取不到？？？？
       HSSFSheet dailyMileageSheet = workbook.createSheet(sheetNameMap.get(items[i]));
       populateSheet(devicePlate,titleStr,titleStyle, contentStyle, dailyMileageSheet, sheetMap.get(items[i]));
     }
     try {
         workbook.write(out);//将excel工作簿写入到输出流中
         
     } catch (IOException e) {
        e.printStackTrace();
     }
 
   }
   private Map<String, String> getSheetNameMap() {
     Map<String, String> sheetNameMap = new HashMap<String, String>();
     sheetNameMap.put("dailyMileage", "行驶里程(KM)");
     sheetNameMap.put("averageFuelConsumption", "平均耗油(百公里耗油量)");
     sheetNameMap.put("fuelConsumption", "油耗量(L)");
     sheetNameMap.put("cost", "油费金额(元)");
     sheetNameMap.put("averageSpeed", "平均速度");
     sheetNameMap.put("emergencybrakecount", "急刹车次数");
     sheetNameMap.put("suddenturncount", "急转弯次数");
     sheetNameMap.put("rapidlyspeedupcount", "急加速次数");
    return sheetNameMap;
  }
  //第一行标题样式（白字蓝底）
   private HSSFCellStyle getTitleStyle(HSSFWorkbook workbook){
     HSSFCellStyle titleStyle = workbook.createCellStyle();
     HSSFPalette palette = workbook.getCustomPalette();  
     palette.setColorAtIndex((short) 63, (byte) (50), (byte) (126), (byte) (179));
     titleStyle.setFillForegroundColor((short) 63);
     titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
     titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
     titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font = workbook.createFont();
     font.setColor(HSSFColor.WHITE.index);
     font.setFontHeightInPoints((short) 12);
     font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     titleStyle.setFont(font);
     titleStyle.setWrapText(false);  
     return titleStyle;
   }
   //内容行样式   （白底黑字）
   private HSSFCellStyle getContentStyle(HSSFWorkbook workbook){
     HSSFCellStyle contentStyle = workbook.createCellStyle();      
     contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     contentStyle.setFillForegroundColor(HSSFColor.WHITE.index);
     contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
     contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
     contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);     
     HSSFFont font2 = workbook.createFont();
     font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
     contentStyle.setFont(font2);
     contentStyle.setWrapText(true);
     return contentStyle;
   } 
   private void populateSheet(Map<String, String> devicePlate, String[] titleStr, 
       HSSFCellStyle titleStyle, HSSFCellStyle contentStyle, HSSFSheet sheet, Map<String, String[]> map){
     //excel列默认宽度
     sheet.setDefaultColumnWidth(20);
     HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
     HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
     comment.setString(new HSSFRichTextString("车辆报表数据"));
     comment.setAuthor("csh");
     
     //填充标题,就是第一行啦
     HSSFRow row = sheet.createRow(0);
     row.setHeight((short)500);
     for (int i = 0; i < titleStr.length ; i++) {
       HSSFCell cell = row.createCell(i);
       cell.setCellStyle(titleStyle); 
       HSSFRichTextString text = null;
       text = new HSSFRichTextString(titleStr[i]);
       cell.setCellValue(text);
     }
     
     //填充内容行,就是除第一行外的所有行,从第二行开始
     int rowNum = 1;
     for (Map.Entry<String, String[]> entry : map.entrySet()) {
       row = sheet.createRow(rowNum);
       row.setHeight((short)350);
       
       HSSFCell cellFirst = row.createCell(0);
       cellFirst.setCellStyle(contentStyle);
       cellFirst.setCellValue(new HSSFRichTextString(devicePlate.get(entry.getKey())));
       
       String[] rowCells = entry.getValue();
       for (int j = 0; j < rowCells.length; j++) {
         HSSFCell cell = row.createCell(j + 1);
         cell.setCellStyle(contentStyle);
         if (rowCells[j] != null) {
           Pattern p = Pattern.compile("^//d+(//.//d+)?$"); //匹配是否是数值类型
           Matcher matcher = p.matcher(rowCells[j]);
           if(matcher.matches()){
              cell.setCellValue(new DecimalFormat("#.00").format(rowCells[j]));
           }else {
             cell.setCellValue(rowCells[j]);
           }
         }else {
           cell.setCellValue(new HSSFRichTextString("0"));
         }

         
       }
       rowNum++;
      }
   }
}