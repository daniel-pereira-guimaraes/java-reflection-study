package dao;
public class Database {
	
	private static class DatabaseConfig {
		
		private final String server;
		private final int port;
		private final String user;
		private final String password;

		public DatabaseConfig(String server, int port, String user, String password) {
			super();
			this.server = server;
			this.port = port;
			this.user = user;
			this.password = password;
		}

		@Override
		public String toString() {
			return "DatabaseConfig [server=" + server + ", port=" + port + ", user=" + user + ", password=" + password
					+ "]";
		}
		
	}
	
	private static Database instance;
	private final DatabaseConfig config;

	private Database(String server, int port, String user, String password) {
		this.config = new DatabaseConfig(server, port, user, password);
	}
	
	public static Database getInstante() {
		if (instance == null) {
			instance = new Database("localhost", 3050, "myUser", "myPassword");
		}
		return instance;
	}

	@Override
	public String toString() {
		return "Database [config=" + config + "]";
	}

}
