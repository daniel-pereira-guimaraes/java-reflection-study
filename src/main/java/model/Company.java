package model;

public class Company {
	
	private final String name;
	private final String city;
	private final Address address;
	
	public Company(String name, String city, Address address) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public Address getAddress() {
		return address;
	}

}
