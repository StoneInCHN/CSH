package com.csh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csh.common.log.LogUtil;

public class ExcelImporter<T> {

	private Class<T> clazz;

	private Field[] fields;
	/** 存放转换后的Entity对像 **/
	List<T> objectList = new ArrayList<T>();

	public ExcelImporter(Class<T> clazz) {
		this.clazz = clazz;
		this.fields = clazz.getDeclaredFields();
	}

	/**
	 * 将List<String> 转换成 List<Object>
	 * 
	 * @param list
	 * @param sortProperties
	 *            按照excel表格中的数据顺序，对应的Entity中成员变量名
	 * @return
	 * @throws Exception
	 */
	public List<T> list2Object(List<List<String>> list, String[] sortProperties) throws Exception {

		for (int index = 1; index < list.size(); index++) {
			List<String> listObj = list.get(index);
			T instance = clazz.newInstance();
			int propIndex = 0;
			for (int k = 0; k < fields.length; k++) {
				String key = sortProperties[propIndex];
				if (null != key || "".equals(key)) {
					String name = fields[k].getName();
					if (key.equals(name)) {
						String methodName = this.getMetodName(name);
						Class<?> parameterType = this.getFieldType(fields[k]);
						Method method = clazz.getMethod(methodName, parameterType);
						Object value = this.getValue(parameterType, listObj.get(propIndex));
						method.invoke(instance, value);
						propIndex++;
						if (propIndex >= sortProperties.length)
            {
              break;
            }
					}
				}
			}
			objectList.add(instance);
		}

		list.clear();
		LogUtil.debug (ExcelImporter.class, "list2Object",
        "object size : " + objectList.size());
		return objectList;
	}

	/**
	 * 通过属性名获取 setter方法
	 * 
	 * @param name
	 * @return
	 */
	public String getMetodName(String name) {
		char[] names = name.toCharArray();
		names[0] = Character.toUpperCase(names[0]);
		return "set" + String.valueOf(names);
	}

	/**
	 * 获取String 转换后的值
	 * 
	 * @param typeClazz
	 * @return
	 * @throws ParseException
	 */
	public Object getValue(Class<?> typeClazz, String value) throws Exception {
		if (typeClazz == String.class) {
			return value;
		} else if (typeClazz == Date.class) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(value);
		} else if (typeClazz == long.class || typeClazz == Long.class) {
			return Long.parseLong(value);
		} else if (typeClazz == int.class || typeClazz == Integer.class) {
			return Integer.valueOf(value);
		} else if (typeClazz == BigDecimal.class){
		  return new BigDecimal (value);
		} else {
			return new Object();
		}
	}

	/**
	 * 获得每个field 的type（Class类型）
	 * 
	 * @param field
	 * @return
	 */
	public Class<?> getFieldType(Field field) {

		Class<?> type = field.getType();
		return type;
	}

	public List<T> getListEntity(String filePath, String[] sortProperties,Integer sheetNumber) throws Exception {
	  File file = new File (filePath);
		return this.list2Object(this.getExcelList(file,sheetNumber), sortProperties);
	}
	public List<T> getListEntity(InputStream is ,String sufix, String[] sortProperties,Integer sheetNumber) throws Exception {
    return this.list2Object(this.getExcelList(is,sufix,sheetNumber), sortProperties);
  }
	/**
	 * 通过一个地址来获取excel表格中的内容
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException 
	 */
	public List<List<String>> getExcelList(File file,Integer sheetNumber) throws FileNotFoundException {
	  FileInputStream is = new FileInputStream (file);
	  String sufix = file.getName().split("\\.")[1];
		
		return POIUtil.getExcelListData(is, sufix,sheetNumber);
	}
	
	public List<List<String>> getExcelList(InputStream is,String sufix,Integer sheetNumber) throws FileNotFoundException {
    
    return POIUtil.getExcelListData(is, sufix,sheetNumber);
  }
}
