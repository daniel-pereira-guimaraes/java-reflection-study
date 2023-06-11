package configwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigWriter {
	
	public static void write(Object config, String filePath) throws Throwable {

		final File file = new File(filePath);
		System.out.println("Writting config into file:\n\t" + file.getAbsolutePath());
		
		try (FileOutputStream fos = new FileOutputStream(file)) {
			
			final List<Field> fields = new ArrayList<>();
			
			Class<?> clazz = config.getClass();
			while (clazz != null) {
				fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
				clazz = clazz.getSuperclass();
			}
			
			for (Field field : fields) {
				field.setAccessible(true);// Required?
				final String name = field.getName();
				try {
					final String value = valueToString(field.get(config), field.getType());
					if (value != null) {
						System.out.println("Writting... " + name + "=" + value);
						fos.write(name.getBytes());
						fos.write('=');
						fos.write(value.getBytes());
						fos.write('\n');
					}
				}
				catch(Throwable e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private static String valueToString(Object value, Class<?> type) {
		
		if (value == null) {
			return null;
		}

		if (type.isPrimitive() || type.equals(String.class)) {
			return value.toString();
		}

		if (type.isArray()) {
			return arrayValueToString(value, type);
		}

		final String result = value.toString();
		if (result.contains("\n") || result.contains("\r")) {
			throw new IllegalArgumentException("Unsupported type: " + type.getName());
		}
		
		return result;
	}
	
	private static String arrayValueToString(Object value, Class<?> type) {
		final StringBuilder sb = new StringBuilder();
		final int arrayLength = Array.getLength(value);
		for (int i = 0; i < arrayLength; i++) {
			final Object element = valueToString(Array.get(value, i), type.getComponentType());
			if (element != null) {
				sb.append(sb.length() == 0 ? '[' : ',');
				sb.append(element);
			}
		}
		return sb.length() == 0 ? null : sb.append(']').toString();
	}

}
