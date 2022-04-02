package com.mywork.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date getLtime(int time){
		Date date = new Date();
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, time);
		date = cal.getTime();
		return date;
	}
	/**
	 * 将秒数转换为 yyyy-MM-dd HH:mm:ss格式
	 * @param ms
	 * @return
	 */
	public static String getTime(String ms){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(Long.parseLong(ms)*1000);
		String fmdate = sdf.format(date);
		return fmdate;
	}
	 /**
     * 格式化日期 'yyyy-mm-dd '
     *
     * @param date 待格式化的日期
     */
    public static String formatHMS(Date date) {
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
	 /**
     * 格式化日期 'yyyy-mm-dd'
     *
     * @param date 待格式化的日期
     */
    public static String format(Date date) {
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 日期加减操作
     * @return yyyy-MM-dd
     */
	public static String getDay(int number){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(new Date().getTime() + number * 24 * 60 * 60 * 1000));
	}

	
	
	/**
	 * 将2014-12-31转化为2014年12月31日
	 * @param dateStr
	 * @return
	 * @throws ParseException 
	 */
	public static String changeDateFromat(String dateStr) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy年MM月dd日");
		Date date = sdf.parse(dateStr);
		return sdf1.format(date);
		
	}
	/**
	 *  月报表中将201412转化为2014年12月
	 * @param dateStr
	 * @return
	 * @throws ParseException 
	 */
	public static String changeDatesFromat(String dateStr) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy年MM月");
		Date date = sdf.parse(dateStr);
		return sdf1.format(date);
		
	}
	/** 
     * 获取当前时间上个月的年月
     * @param tablename
     * @return
     * @throws SQLException
     * @throws ParseException 
     */
    public static String getKsny() {
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
    	Date date = new Date();
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, -12);
		return fmt.format(cal.getTime());
    } 
    /** 
     * 获取当前时间一年前的年月
     * @param tablename
     * @return
     * @throws SQLException
     * @throws ParseException 
     */
    public static String getJsny() {
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
    	Date date = new Date();
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, -1);
		return fmt.format(cal.getTime());
    }  
	

	//test
	public static void main(String[] args){
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd,yyyy HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());
		String time = formatter.format(curDate).toString();
		System.out.println(time);
	}
}
