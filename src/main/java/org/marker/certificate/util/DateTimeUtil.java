package org.marker.certificate.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	// 时间格式化
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	
	// 时间格式化
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
	
	// 时间格式化
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月");
	
	
	
	/**
	 * 获取格式化时间
	 * yyyy年MM月dd日 HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getTime(Date date){ 
		if(date != null)
			return sdf.format(date); 
		return "";
	}
	
	
	
	/**
	 * 获取格式化时间
	 * @param date
	 * @return
	 */
	public static String getTimeyyyyMMdd(Date date){ 
		if(date != null)
			return sdf2.format(date); 
		return "";
	}
	
	
	
	/**
	 * 获取格式化时间
	 * @param date
	 * @return
	 */
	public static String getTimeyyyyMM(Date date){ 
		if(date != null)
			return sdf3.format(date); 
		return "";
	}
}
