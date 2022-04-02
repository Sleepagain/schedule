package com.mywork.bean;//课表对象

public class Timetable {
	//id
	private Integer id;
	//专业id
	private Integer majorid;
	//课程
	private String classes;
	//教师id
	private Integer teacherid;
	//教室id
	private Integer roomid;
	//开始周
	private String week;
	//时间
	private String time;
	//专业对象
	private Major major;
	//教师对象
	private User teacher;
	//教室对象
	private Room room;
	//需要多少周
	private String weekcounts;
	//考试周
	private Integer examweek;
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMajorid() {
		return majorid;
	}
	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public Integer getRoomid() {
		return roomid;
	}
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWeekcounts() {
		return weekcounts;
	}
	public void setWeekcounts(String weekcounts) {
		this.weekcounts = weekcounts;
	}
	public Integer getExamweek() {
		return examweek;
	}
	public void setExamweek(Integer examweek) {
		this.examweek = examweek;
	}
	
}
