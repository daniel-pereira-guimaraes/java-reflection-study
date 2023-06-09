package jsonwriter;

public class JsonWriterTest {

	public static void main(String[] args) throws Throwable {

		final Address companyAddress = new Address("Here", (short) 123);
		final String[] companyOtherNames = { "Java Cia", "Java Company" };
		final Company company = new Company(100L, "Java S/A", "São Paulo", companyAddress, companyOtherNames);

		final Address personAddress = new Address("Example", (short) 456);
		final Address[] personAddresses = {personAddress, companyAddress};
		final Person person = new Person(200L, "Emma", true, 21, 5000.126f, personAddresses, company);

		System.out.println(JsonWriter.objectToJson(person));
	}
	
}
