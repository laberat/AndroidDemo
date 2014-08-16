package com.yyc.androiddemo.bean;

public class Person {

	private String name;
	private int age;
	private int sex;//1 for male, 0 for female
	public Person(String name, int age, int sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String toString(){
		return "Name : "+this.getName()
			  +"Age : "+this.getAge()
			  +"Sex : "+this.getSex();
	}
	
}
