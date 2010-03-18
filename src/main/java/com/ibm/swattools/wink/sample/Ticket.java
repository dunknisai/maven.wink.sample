package com.ibm.swattools.wink.sample;

import java.io.Serializable;

public class Ticket implements Serializable{
	private int _id;
	private String _name;
	private String _location;
	private String _sex;
	private int _age;
	
	public Ticket(){
	}
	
	public Ticket(int id, String name, String location, String sex, int age){
		_id = id;
		_name = name;
		_location = location;
		_sex = sex;
		_age = age;
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		_id = id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		_name = name;
	}
	public String getLocation() {
		return _location;
	}
	public void setLocation(String location) {
		_location = location;
	}
	public String getSex() {
		return _sex;
	}
	public void setSex(String sex) {
		_sex = sex;
	}
	public int getAge() {
		return _age;
	}
	public void setAge(int age) {
		_age = age;
	}
}
