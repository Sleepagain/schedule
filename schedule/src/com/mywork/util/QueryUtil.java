package com.mywork.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mywork.bean.User;
import com.mywork.common.SessionKeys;

/**
 * 数据查询
 * @author gaozq
 *
 */
@SuppressWarnings("unchecked")
public class QueryUtil {
	
	/**
	 * 测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
//		String sql = "select * from sjhz";
//		Connection conn = ConBuilder.getInstance().getConn();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery(sql);
//		List<Map<String, Object>> list = resultSetToList(rs);
//		System.out.println(list);
//		rs.close();  
//        stmt.close();  
//        conn.close();  
//		ConBuilder.getInstance().returnConn(conn);
	}
	
	/**
	 * 根据sql查询list
	 * @param sql
	 * @param type 类型0-不转换id，否的都转换id
	 * @return
	 * @throws Exception
	 */
	public static List queryForList(String sql,int type) throws Exception{
		Connection conn = ConBuilder.getInstance().getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Map<String, Object>> list = resultSetToList(rs,type);
		rs.close();  
        stmt.close();  
        conn.close();  
		ConBuilder.getInstance().returnConn(conn);
		return list;
	}
	
	/**
	 * 获取session用户
	 * @param request
	 * @return
	 */
	public static User getSessionUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
	}
	/**
	 * rs转为list
	 * @param rs 结果集
	 * @param type 类型0-不转换id，否的都转换id
	 * @return
	 * @throws java.sql.SQLException
	 */
	public static List resultSetToList(ResultSet rs, int type) throws java.sql.SQLException {   
        if (rs == null)return Collections.EMPTY_LIST;   
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        List list = new ArrayList();   
        Map rowData = new HashMap();   
        while (rs.next()) {   
            rowData = new HashMap(columnCount);   
            for (int i = 1; i <= columnCount; i++) {   
            	if(type != 0){
            		rowData.put("ID", i);
            	}
                rowData.put(md.getColumnName(i), rs.getObject(i));   
            }   
            list.add(rowData);   
        }   
        return list;   
	}
	/**
	 * 执行sql
	 * @param sql
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	public static boolean executeSql(String sql){
		boolean issuc = false;
		try {
			//ConBuilder获取数据库连接  getInstance().getConn()获取数据库连接的实例
			Connection conn = ConBuilder.getInstance().getConn();
			//conn.createStatement();获取一个Statement操作对象
			Statement stmt = conn.createStatement();
			//Statement执行sql
			stmt.executeUpdate(sql);
			//关闭Statement流
	        stmt.close();
			//关闭连接流
	        conn.close();
			//将连接还给数据库连接池
			ConBuilder.getInstance().returnConn(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		issuc = true;
		return issuc;
	}
}
