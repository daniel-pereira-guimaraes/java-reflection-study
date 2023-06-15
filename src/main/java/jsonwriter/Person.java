package jsonwriter;

public class Person extends BasicPerson {
	private static final long serialVersionUID = 1L;

	private final boolean employed;
	private final int age;
	private final float salary;
	private final Address[] addresses;
	private final Company job;
	
	@SuppressWarnings("unused")
	private final transient int transientForTestField = 10;

	public Person(Long id, String name, boolean employed, int age, float salary, Address[] addresses, Company job) {
		super(id, name);
		this.employed = employed;
		this.age = age;
		this.salary = salary;
		this.addresses = addresses;
		this.job = job;
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
