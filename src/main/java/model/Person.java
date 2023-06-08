package model;

public class Person {
	
	private final String name;
	private final boolean employed;
	private final int age;
	private final float salary;
	private final Address[] addresses;
	private final Company job;


	public Person(String name, boolean employed, int age, float salary, Address[] addresses, Company job) {
		super();
		this.name = name;
		this.employed = employed;
		this.age = age;
		this.salary = salary;
		this.addresses = addresses;
		this.job = job;
	}


	public String getName() {
		return name;
	}


	public boolean isEmployed() {
		return employed;
	}


	public int getAge() {
		return age;
	}


	public float getSalary() {
		return salary;
	}


	public Address[] getAddresses() {
		return addresses;
	}


	public Company getJob() {
		return job;
	}


}
