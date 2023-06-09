package jsonwriter;

public class BasicPerson extends EntityWithId {

	private final String name;

	public BasicPerson(long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
}
