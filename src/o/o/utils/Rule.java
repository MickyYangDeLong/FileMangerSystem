package o.o.utils;

/*
 * author:Micky
 * version:1.0.0
 * Date:2017年8月20日 上午12:54:52
 */
public class Rule {
	private int age = 10;
	private String name = "micky";
	private transient String address = "earth";

	@Override
	public String toString() {
		return "name:" + getName() + " age:" + getAge() + " address:"
				+ getAddress()+" ";
	}

	public Rule() {
	}

	public Rule(String Name, String address, int age) {
		setAddress(address);
		setAge(age);
		setName(Name);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
