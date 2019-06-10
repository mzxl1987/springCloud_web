package com.miicrown.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String nowToYYMMddHHmmss() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");  
		return formatter.format(new Date());
	}
	
}
