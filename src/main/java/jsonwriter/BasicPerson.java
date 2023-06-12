package jsonwriter;

public class BasicPerson extends EntityWithId {
	private static final long serialVersionUID = 1L;

	private final String name;

	public BasicPerson(long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
