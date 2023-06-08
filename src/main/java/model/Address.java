package model;

public class Address {

	private final String street;
	private final short number;
	private int[][] onlyTest = {{1, 3, 4}, {5, 6}};
	
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
