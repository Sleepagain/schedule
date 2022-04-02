package com.mywork.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * 创建一个singleten连接池
 *
 */
public class ConBuilder {
	//读取connection.properties配置文件
	private static Map<String, String> data=new HashMap<String, String>();
	static{
		Properties ps=new Properties();
		try{
			ps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("connection.properties"));
			data.put("driverClassName",(String) ps.get("jdbc.driverClassName"));
			data.put("url", (String) ps.get("jdbc.url"));
			data.put("username", (String) ps.get("jdbc.username"));
			data.put("password", (String) ps.get("jdbc.password"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void put(String key,String value){
		data.put(key, value);
	}
	public static Object get(String key) {
		return data.get(key);
	}
	private static ConnectionPool connPool = null;
	private static int i = 0;
	private ConBuilder()  {
		try {
			String jdbcDriver = data.get("driverClassName");
			String dbUrl = data.get("url");
			String dbUserName = data.get("username");
			String dbpwd = data.get("password");
			connPool = new ConnectionPool(jdbcDriver,dbUrl,dbUserName,dbpwd);
			//创建连接池
			connPool.createPool();
			i++;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private static ConBuilder cb = null;
	
	public static synchronized ConBuilder getInstance() {
		if(cb==null){
			cb = new ConBuilder();
		}
		return cb;
	}
	/**
	 * 从数据库连接池中获取一个空闲连接
	 */
	public Connection getConn() throws Exception{
		if(connPool!=null){
			Connection conn = connPool.getConnection();
			return conn;
		}else{
			return null;
		}
	}
	/**
	 * 将使用完毕的连接返回给连接池，
	 * 所有使用完毕后的连接必须在第一时间内返回给连接池
	 */
	public void returnConn(Connection conn){
		try {
			if(conn!=null){
				connPool.returnConnection(conn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 刷新连接池中的所有连接
	 * 将所有连接重置给连接池
	 */
	public void refreshConn(){
		try {
			connPool.refreshConnections();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 
	 */
	/**
	 * 关闭连接池中的所有连接，并清空连接池
	 */
	public void closeConnPool(){
		try {
			connPool.closeConnectionPool();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * 获取一般结果集
	 */
	public ResultSet getGeneralRS(String sql){
		ResultSet rs = null;
		try {
			Connection conn = ConBuilder.getInstance().getConn();
			Statement sta = conn.createStatement();
			rs = sta.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/*
	 * 获取一般带结果信息的结果集
	 */
	public ResultSet getRsNoteRS(String sql){
		ResultSet rs = null;
		try {
			Connection conn = ConBuilder.getInstance().getConn();
			Statement sta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = sta.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
