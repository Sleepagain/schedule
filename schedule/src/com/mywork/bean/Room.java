package com.mywork.bean;
//教室对象
public class Room {
	//教室id
	private Integer id;
	//教室名称
	private String name;
	//教室地址
	private String address;
	//人数?有什么用？
	private Integer personcount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPersoncount() {
		return personcount;
	}
	public void setPersoncount(Integer personcount) {
		this.personcount = personcount;
	}
	
	
}
