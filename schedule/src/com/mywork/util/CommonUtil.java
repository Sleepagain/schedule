package com.mywork.util;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;



/**
 * 公共的帮助类
 * @author 
 *
 */
public class CommonUtil {
	
	public static void main(String[] args) throws SQLException{
		//String chars = "abcdefghijklmnopqrstuvwxyz";
		//System.out.println((chars.charAt((int)(Math.random() * 26))+"").toUpperCase());
		Double maxdata = 0.28;  //最大值
		Double mindata = -0.38;  //最小值
//		fmindata = (int)Math.round((mindata-(double)Math.round((maxdata-mindata)*0.2))*100/100);
//		fmaxdata = (int)Math.round((maxdata+(double)Math.round((maxdata-mindata)*0.2))*100/100);
		System.err.println(maxdata+(maxdata-mindata)*0.2);
		System.err.println((int)Math.round((mindata-(maxdata-mindata)*0.2)*100/100));
		System.err.println((int)Math.round((maxdata+(maxdata-mindata)*0.2)*100/100));
	}
	/**
	 * 十位数的四舍五入
	 */
	public static int roundTen(Double d, String type){
		int roundedint = (int) Math.round(d);
		int value = 0;
		if("max".equals(type)){
			if(roundedint >=0 ){
				value = (roundedint/10)*10+10;
			}else{
				value = (roundedint/10)*10;
			}
			
		}else if("min".equals(type)){
			
			if(roundedint >=0 ){
				value = (roundedint/10)*10;
			}else{
				value = (roundedint/10)*10-10;
			}
		}
		return value;
	}
	/**
	 */
	public static int getcount(String str, String key){
		int count = 0;
		int index = 0;
		while((index = str.indexOf(key,index))!= -1){
			index = index + key.length();
			count++;
		}
		return count;
	}
	/**
	 * 验证码
	 */
	public static String getYzm(){
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String randChar1 = (chars.charAt((int)(Math.random() * 26))+"").toUpperCase();
		String randChar2 = (chars.charAt((int)(Math.random() * 26))+"").toUpperCase();
		String randNum1 = (int)(Math.random() * (10))+"";
		String randNum2 = (int)(Math.random() * (10))+"";
		String yzm = randNum1+randChar1+randNum2+randChar2;
		return yzm;
	}
	/**
	 * md5加密

	 */
	public static String md5(String username, String password) {
		return new Md5PasswordEncoder().encodePassword(username, password);
	}
	/**
	 * 获得uuid
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}
	
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || str.trim().equals("");
	}
	
	/**
	 * 获得sessionId，并判断页面的sessionId和Session对象的Id是否相同
	 */
	public static boolean contrastSessionId(HttpServletRequest request,String sessionId){
		String id=request.getSession().getId();
		if((id==null) || (id.equals(""))){
			return false;
		}else if(id.equals(sessionId)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断字符串的编码
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    public static String getEncoding(String str) {
    	try {
	        String encode = "GB2312";
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s = encode;
	            return s;
	        }
	        encode = "ISO-8859-1";
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s1 = encode;
	            return s1;
	        }
	        encode = "UTF-8";
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s2 = encode;
	            return s2;
	        }
	        encode = "GBK";
			if (str.equals(new String(str.getBytes(encode), encode))) {
			    String s3 = encode;
			    return s3;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return "";
    }
    /**
	 * 修改字符编码，将iso转为utf-8
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    public static String changeEncoding(String str){
    	try {
	    	if(getEncoding(str).equals("ISO-8859-1")){// 判断编码
				return new String(str.getBytes("ISO-8859-1"),"UTF-8");
			}else if(getEncoding(str).equals("GB2312")){
				return new String(str.getBytes("GB2312"),"UTF-8");
			}else{
				return str;
			}
    	} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
