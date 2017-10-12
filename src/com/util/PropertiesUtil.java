package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getValue(String key) throws FileNotFoundException{
		Properties prop=new Properties();
		InputStream in=new PropertiesUtil().getClass().getResourceAsStream("/article.properties");
//		InputStream in=new FileInputStream("D:/Use/Diary/src/diary.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)prop.get(key);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new PropertiesUtil().getValue("dbUrl"));
	}
}
