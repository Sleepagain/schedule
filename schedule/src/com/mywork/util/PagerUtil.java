package com.mywork.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PagerUtil {
	
	private static final int pagerSize=10;
	
	public static int getPagerSize(){
		return pagerSize;
	}
	
	public static List<?> getPager(List<?> list, int pagerNumber){
		//起始页
		int start = (pagerNumber-1)*pagerSize+1;
		//结束页
		int end = pagerNumber*pagerSize;
		
		List newlist = new ArrayList();
		
		int max= 0;
		if(end > list.size()){
			max = list.size();
		}else{
			max = end;
		}
		
		for(int i=start-1;i<max;i++){
			newlist.add(list.get(i));
		}
		return newlist;
		
	}
	
	public static Map<String, Object> getPager(List<?> list, int pagerNumber, Map<String, Object> map){
		
		map.put("count", list.size());
		map.put("maxPager", list.size()/getPagerSize()+1);
		map.put("pagerNum", pagerNumber);
		//起始页
		int start = (pagerNumber-1)*pagerSize+1;
		//结束页
		int end = pagerNumber*pagerSize;
		
		List newlist = new ArrayList();
		
		int max= 0;
		if(end > list.size()){
			max = list.size();
		}else{
			max = end;
		}
		
		for(int i=start-1;i<max;i++){
			newlist.add(list.get(i));
		}
		map.put("list", newlist);
		return map;
		
	}
}
