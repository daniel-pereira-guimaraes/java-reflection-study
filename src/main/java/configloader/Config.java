package configloader;

public class Config {

	private String host;
	private int port;
	private String user;
	private String password;

	// Only for test:
	private final int finalLiteral = 1;
	private final int finalConstructor;

	public Config() {
		this.finalConstructor = 2;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + 
				"\n\thost: " + this.host +
				"\n\tport:" + this.port +
				"\n\tuser: " + this.user +
				"\n\tpassword: " + this.password +
				"\n\tfinalLiteral: " + this.finalLiteral +
				"\n\tfinalConstructor: " + this.finalConstructor;
	}

}
