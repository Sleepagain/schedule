package com.mywork.common;//分页方法

import java.util.List;
/*
 * 分页对象
 */
public class Pager<T> {
	/*
	 *枚举类
	 *asc正序, desc倒序
	 */
	public enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 500;//最大页数

	private Integer pageNumber = 1; //当前页码
	private Integer pageSize = 20; //每页显示数据条数
	private Integer totalCount = 0; //总数据条数
	private Integer pageCount = 0; //总页数
	private String keyword; //关键词
	private String orderBy = "createDate"; //排序依据
	private OrderType orderType = OrderType.desc; //升序：asc / 降序：desc
	private List<T> list; 
	private String property;
	
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getStartItem(){
		return (pageNumber-1)*pageSize;
	}
	
	public int getEndItem(){
		return pageNumber*pageSize;
	}
	

}
