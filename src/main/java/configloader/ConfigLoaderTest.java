package configloader;

public class ConfigLoaderTest {
	
	private static final String CONFIG_PATH = "database.cfg";
	
	public static void main(String[] args) throws Throwable {
		final Config config = ConfigLoader.load(Config.class, CONFIG_PATH);
		System.out.println(config);
	}

}
