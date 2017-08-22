package o.o.utils;

import java.io.Serializable;

/*
 * author:Micky
 * version:1.0.0
 * Date:2017年8月19日 上午12:47:49
 */
public class TestObjectInputStream implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int age = 10;
	private String name = "micky";
	private transient String address = "earth";

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
