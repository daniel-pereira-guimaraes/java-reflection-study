package configwriter;

import model.Product;

public class Config {

	private String host;
	private int port;
	private String user;
	private String password;

	// Only for test:
	private final int finalLiteral = 1;
	private final int finalConstructor;
	public int[][] intArray = {{1, 2, 3}, {}, {5,6}};
	public String[] strArray = {"One", "Two", "Three"};
	public Product[] productArray = {new Product()};

	public Config() {
		this.finalConstructor = 2;
	}
	
	public Config(String host, int port, String user, String password) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
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
