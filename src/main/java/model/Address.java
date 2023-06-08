package model;

public class Address {

	private final String street;
	private final short number;
	
	public Address(String street, short number) {
		super();
		this.street = street;
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public short getNumber() {
		return number;
	}
	
	
	
}
