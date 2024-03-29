package configloader;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

public class ConfigLoader {
	
	public static <T> T load(Class<T> clazz, String filePath) throws Throwable {

		//final T config = clazz.getDeclaredConstructor().newInstance();
		// or:
		final T config = clazz.newInstance();

		final File file = new File(filePath);
		System.out.println("Reading config from file:\n\t" + file.getAbsolutePath());

		try (Scanner scanner = new Scanner(new FileInputStream(file))) {
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine().trim();
				if (!line.startsWith(";")) { // Ignore comment!
					final String[] nameValue = line.split("=");
					if (nameValue.length == 2) {
						final String name = nameValue[0];
						final String value = nameValue[1];
						try {
							final Field field = clazz.getDeclaredField(name);
							field.setAccessible(true);
							System.out.println("Setting " + name + " with " + value);
							field.set(config, parseValue(field.getType(), value));
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return config;
	}
	
	private static Object parseValue(Class<?> type, String value) {
		if (type.equals(boolean.class))	return Boolean.parseBoolean(value);
		if (type.equals(byte.class))	return Byte.parseByte(value);
		if (type.equals(short.class))	return Short.parseShort(value);
		if (type.equals(int.class))		return Integer.parseInt(value);
		if (type.equals(long.class))	return Long.parseLong(value);
		if (type.equals(float.class))	return Float.parseFloat(value);
		if (type.equals(double.class))	return Double.parseDouble(value);
		if (type.equals(String.class))	return value;
		throw new RuntimeException(String.format("Type %s unsupported.", type.getTypeName()));
	}

}
