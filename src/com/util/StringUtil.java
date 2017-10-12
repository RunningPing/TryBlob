package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isChinese(char str){
		boolean flag = false;
		String a=String.valueOf(str);
		Pattern p_str=Pattern.compile("[\\u4e00-\\u9fa5]+");
		Matcher m=p_str.matcher(a);
		if(m.find()&&m.group(0).equals(a)){
			flag=true;
		}
		return  flag;
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.isChinese('Äã'));
	}
	
	
}
