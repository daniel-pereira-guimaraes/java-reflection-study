package cacheproxy;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class DatabaseReaderImpl implements DatabaseReader {
	
	@Override
	public void connectToDatabase() {
		System.out.println("Connecting to database....");
	}

	@Override
	public String readCustomerIdByName(String firstName, String lastName) throws IOException {
		System.out.println("readCustomerIdByName(" + firstName + ", " + lastName + ")");
		return firstName + " " + lastName + " - " + (new Random().nextInt(1000));
	}

	@Override
	public int countRowsInCustomersTable() {
		System.out.println("countRowsInCustomersTable");
		return new Random().nextInt(10000);
	}

	@Override
	public void addCustomer(String id, String firstName, String lastName) throws IOException {
		System.out.println("addCustomer(" + id + ", " + firstName + ", " + lastName + ")");
	}

	@Override
	public Date readCustomerBirthday(String id) {
		System.out.println("readCustomerBirthday(" + id + ")");
		return new Date();
	}

}
