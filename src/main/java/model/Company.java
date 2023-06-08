package model;

public class Company extends BasicPerson {
	
	private final String city;
	private final Address address;
	private final String[] otherNames;
	
	public String[] getOtherNames() {
		return otherNames;
	}

	public Company(Long id, String name, String city, Address address, String[] otherNames) {
		super(id, name);
		this.city = city;
		this.address = address;
		this.otherNames = otherNames;
	}

	public String getCity() {
		return city;
	}

	public Address getAddress() {
		return address;
	}

}
