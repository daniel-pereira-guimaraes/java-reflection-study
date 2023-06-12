package jsonwriter;

import java.io.Serializable;

public abstract class EntityWithId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final long id;

	public EntityWithId(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
