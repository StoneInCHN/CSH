package com.csh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.csh.common.log.LogUtil;

public class POIUtil {

	/**
	 * 获取excel表格中的数据
	 * @param file
	 * @param sufix
	 * @param tableName
	 * @return
	 */
	public static List<List<String>> getExcelListData(InputStream is,String sufix,Integer sheetNumber) {

		long startTime = System.currentTimeMillis();
		List<List<String>> excelData = new ArrayList<List<String>>();
		try {
			LogUtil.debug (POIUtil.class,"getExcelListData", "*******************开始读取数据*********************");
			if ("xls".equals(sufix)) {
				excelData = readExcel4Xls(is,sheetNumber);
			} else if ("xlsx".equals(sufix)) {
				excelData = readExcel4Xlsx(is,sheetNumber);
			} else {
				throw new RuntimeException("文件格式不支持");
			}
			long endTime = System.currentTimeMillis();
			LogUtil.debug (POIUtil.class,"getExcelListData", "耗时：" + (endTime - startTime) / 1000 + "秒");
			LogUtil.debug (POIUtil.class,"getExcelListData", "*******************读取数据完成*********************");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelData;
	}
	
	public static HSSFSheet getSheet(FileInputStream is,Integer sheetNumber) {
		
		try {
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(sheetNumber);
			return sheet;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<List<String>> readExcel4Xls(InputStream is,Integer sheetNumber) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(sheetNumber);

		List<List<String>> excelList = new ArrayList<List<String>>();
		for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			List<String> rowList = new ArrayList<String>();
			HSSFRow row = sheet.getRow(rowIndex);
			int len = sheet.getRow(0).getLastCellNum();
			for (int colIndex = 0; colIndex < len; colIndex++) {
				HSSFCell cell = row.getCell(colIndex);
				if (null != cell) {
					String value = getCellValue(cell);
					rowList.add(value);
				} else {
					rowList.add("  ");
				}
			}
			excelList.add(rowList);
		}
		is.close();
		return excelList;
	}

	public static List<List<String>> readExcel4Xlsx(InputStream is,Integer sheetNumber) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(sheetNumber);

		List<List<String>> excelList = new ArrayList<List<String>>();
		for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			List<String> rowList = new ArrayList<String>();
			XSSFRow row = sheet.getRow(rowIndex);
			// 获取列的长度
			int len = sheet.getRow(0).getLastCellNum();
			for (int colIndex = 0; colIndex < len; colIndex++) {
				XSSFCell cell = row.getCell(colIndex);
				if (null != cell) {
					String value = getCellValue(cell);
					rowList.add(value);
				} else {
					rowList.add(" ");
				}
			}
			excelList.add(rowList);
		}

		is.close();
		return excelList;
	}

	/**
	 * 读取excel模版中的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(HSSFCell cell) {
		String cellValue = " ";
		DecimalFormat df = new DecimalFormat("#");
		Integer cellType = cell.getCellType();

		switch (cellType) {
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().getString().trim();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
        Date d = cell.getDateCellValue();
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        cellValue = formater.format (d);
      }else {
        cellValue = df.format(cell.getNumericCellValue()).toString();
      };
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			cellValue = " ";
			break;
		default:
			cellValue = " ";
			break;
		}
		return cellValue;
	}

	/**
	 * 读取excel模版中的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(XSSFCell cell) {
		String cellValue = "";
		DecimalFormat df = new DecimalFormat("#");
		int cellType = cell.getCellType();

		switch (cellType) {
		case XSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().getString().trim();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
		  if (DateUtil.isCellDateFormatted(cell)) {
		    Date d = cell.getDateCellValue();
		    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		    cellValue = formater.format (d);
		  }else {
		    cellValue = df.format(cell.getNumericCellValue()).toString();
      };
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;
		case XSSFCell.CELL_TYPE_ERROR:
			cellValue = " ";
			break;
		default:
			cellValue = " ";
			break;
		}
		return cellValue;
	}

	public static void createExcel(OutputStream out ,String[] params,List list){

    // 创建Excel的工作书册 Workbook,对应到一个excel文档  
    XSSFWorkbook wb = new XSSFWorkbook();
    // 创建Excel的工作sheet,对应到一个excel文档的tab  
    XSSFSheet sheet = wb.createSheet("导入结果"); 
    
    //创建标题
    XSSFRow titleRow = sheet.createRow(0);  
    for (int i =0;i<params.length;i++)
    {
      // 创建一个Excel的单元格  
      XSSFCell cell = titleRow.createCell(i);  
      // 给Excel的单元格设置样式和赋值  
      cell.setCellValue(params[i]);  
    }
    
    Class clazz = list.get (0).getClass ();
    Field[] fields = clazz.getDeclaredFields ();
    for (int j=0;j<list.size ();j++)
    {
      XSSFRow row = sheet.createRow(j+1);  
      for (int i=0;i<fields.length;i++)
      {
        String name = fields[i].getName();
//        String key = params[i];
//        if (key.equals(name)) {
          String methodName = getGetterMetodName (name);
//          Class<?> parameterType = this.getFieldType(fields[i]);
          try
          {
            Method method = clazz.getMethod(methodName);
            XSSFCell cell = row.createCell(i);
            Object value = method.invoke(list.get (j));
            if (value != null)
            {
              cell.setCellValue( value.toString ());
            }else {
              cell.setCellValue ("");
            }
            
          }
          catch (Exception e)
          {
            e.printStackTrace ();
          }
//        }
      }
     
    }
    try
    {
      wb.write(out);
      out.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }//将excel工作簿写入到输出流中
    //关闭输出流
	}
	/**
	 * 创建excel，结果通过流返回界面
	 * @param response
	 * @param params
	 * @param list
	 */
	public static void createExcel (HttpServletResponse response,String[] params,List list)
  {
    // 创建Excel的工作书册 Workbook,对应到一个excel文档  
    XSSFWorkbook wb = new XSSFWorkbook();
    // 创建Excel的工作sheet,对应到一个excel文档的tab  
    XSSFSheet sheet = wb.createSheet("导入结果"); 
    
    //创建标题
    XSSFRow titleRow = sheet.createRow(0);  
    for (int i =0;i<params.length;i++)
    {
      // 创建一个Excel的单元格  
      XSSFCell cell = titleRow.createCell(i);  
      // 给Excel的单元格设置样式和赋值  
      cell.setCellValue(params[i]);  
    }
    
    Class clazz = list.get (0).getClass ();
    Field[] fields = clazz.getDeclaredFields ();
    for (int j=0;j<list.size ();j++)
    {
      XSSFRow row = sheet.createRow(j+1);  
      for (int i=0;i<fields.length;i++)
      {
        String name = fields[i].getName();
//        String key = params[i];
//        if (key.equals(name)) {
          String methodName = getGetterMetodName (name);
//          Class<?> parameterType = this.getFieldType(fields[i]);
          try
          {
            Method method = clazz.getMethod(methodName);
            XSSFCell cell = row.createCell(i);
            Object value = method.invoke(list.get (j));
            if (value != null)
            {
              cell.setCellValue( value.toString ());
            }else {
              cell.setCellValue ("");
            }
            
          }
          catch (Exception e)
          {
            e.printStackTrace ();
          }
//        }
      }
      
      
      
    }
  
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition","attachment;filename=text.xlsx");
    try
    {
      //创建输出流
      OutputStream out = response.getOutputStream();
      wb.write(out);//将excel工作簿写入到输出流中
      //关闭输出流
      out.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
   
   
  }
  
  /**
   * 通过属性名获取 getter方法
   * 
   * @param name
   * @return
   */
  public static String getGetterMetodName(String name) {
    char[] names = name.toCharArray();
    names[0] = Character.toUpperCase(names[0]);
    return "get" + String.valueOf(names);
  }
  
  /**
   * 获得每个field 的type（Class类型）
   * 
   * @param field
   * @return
   */
  public static Class<?> getFieldType(Field field) {

    Class<?> type = field.getType();
    return type;
  }
}
