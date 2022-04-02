package com.mywork.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获得属性文件帮助类
 * @author 
 *
 */
public class SysModel {
	private static Map<String, Object> data=new HashMap<String, Object>();
	static{
		Properties ps=new Properties();
		try{
			ps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("SystemConfig.properties"));
			data.put("uploadRoot",ps.get("uploadRoot"));
			data.put("imageExtention", ps.get("imageExtention"));
			data.put("maxSize", ps.get("maxSize"));
			data.put("pictureUtil", ps.get("pictureUtil"));
			data.put("solrServerUrl", ps.get("solrServerUrl"));
			data.put("barcodePath", ps.get("barcodePath"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public static void put(String key,Object value)
	{
		data.put(key, value);
	}
	public static Object get(String key) {
		return data.get(key);
	}
}
