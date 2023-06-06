package main;
public class Database {
	
	private static Database instance;
	
	private final String server;
	private final int port;
	private final String user;
	private final String password;
	
	private Database(String server, int port, String user, String password) {
		super();
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
	}
	
	public static Database getInstante() {
		if (instance == null) {
			instance = new Database("localhost", 3050, "SYSDBA", "masterkey");
		}
		return instance;
	}

	@Override
	public String toString() {
		return "Database [server=" + server + ", port=" + port + ", user=" + user + ", password=" + password + "]";
	}

}
