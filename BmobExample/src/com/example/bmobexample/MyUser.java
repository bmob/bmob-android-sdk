package com.example.bmobexample;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
	private Integer age;
	private Boolean gender;
	// ...
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
}
