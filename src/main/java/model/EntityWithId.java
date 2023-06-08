package model;

public abstract class EntityWithId {
	
	private final long id;

	public EntityWithId(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
