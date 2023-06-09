package configloader;

public class Config {
	
	private String host;
	private int port;
	private String user;
	private String password;

	@Override
	public String toString() {
		return "Config [host=" + host + ", port=" + port + ", user=" + user + ", password=" + password + "]";
	}
	
}
