package com.mywork.bean;//课程类

public class Dict {
	//课程id
	private Integer id;
	//课程类型
	private String type;
	//描述
	private String desc;
	//开始日期
	private Integer beginweek;
	//结束日期
	private Integer endweek;
	public Integer getId() {
		return id;
	}//实体类getter setter方法
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getBeginweek() {
		return beginweek;
	}
	public void setBeginweek(Integer beginweek) {
		this.beginweek = beginweek;
	}
	public Integer getEndweek() {
		return endweek;
	}
	public void setEndweek(Integer endweek) {
		this.endweek = endweek;
	}
}
