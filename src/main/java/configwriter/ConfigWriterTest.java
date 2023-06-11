package configwriter;

public class ConfigWriterTest {
	
	private static final String CONFIG_PATH = "database.cfg";
	
	public static void main(String[] args) throws Throwable {
		final Config config = new Config("localhost", 3050, "SYSDBA", "masterkey");
		ConfigWriter.write(config, CONFIG_PATH);
	}

}
