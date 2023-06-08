package jsonwriter;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import model.Address;
import model.Company;
import model.Person;

public class Main {
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
	
	public static void main(String[] args) throws Throwable {
		
		final Address companyAddress = new Address("Here", (short) 123);
		final Company company = new Company("Java S/A", "São Paulo", companyAddress);

		final Address personAddress = new Address("Example", (short) 456);
		final Person person = new Person("Emma", true, 21, 5000.126f, personAddress, company);
		
		System.out.println(objectToJson(person));
		
	}

	public static String objectToJson(Object instance) throws Throwable {
		return objectToJson(instance, 0);
	}
	
	private static String objectToJson(Object instance, int indentSize) throws Throwable {
		final Field[] fields = instance.getClass().getDeclaredFields();
		final StringBuilder sb = new StringBuilder();
		
		sb.append('{').append('\n');
		
		for (int i = 0; i < fields.length; i++) {
			
			final Field field = fields[i];
			field.setAccessible(true);
			
			if (field.isSynthetic()) {
				continue;
			}
			
			sb.append(indent(indentSize + 1));
			sb.append(formatStringValue(field.getName())).append(':');
			if (field.getType().isPrimitive()) {
				sb.append(formatPrimitiveValue(field, instance));
			}
			else {
				final Object value = field.get(instance);
				if (value == null) {
					sb.append("null");
				} 
				else if (field.getType().equals(String.class)) {
					sb.append(formatStringValue(value.toString()));
				}
				else {
					sb.append(objectToJson(value, indentSize + 1));
				}
			}

			if (i < fields.length - 1) {
				sb.append(',');
			}
			
			sb.append('\n');
		}
		
		sb.append(indent(indentSize)).append('}');
		
		return sb.toString();
	}
	
	private static String indent(int identSize) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < identSize; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}
	
	private static String formatPrimitiveValue(Field field, Object parentInstance) throws Throwable {
		if (field.getType().equals(boolean.class)
			|| field.getType().equals(int.class)
			|| field.getType().equals(long.class)
			|| field.getType().equals(short.class)) {
			return field.get(parentInstance).toString();
		}
		else if (field.getType().equals(double.class) 
				|| field.getType().equals(float.class)) {
			return DECIMAL_FORMAT.format(field.get(parentInstance));
		}
		throw new RuntimeException(String.format("Type '%s' is not supported.", field.getType().getName()));	
	}
	
	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}

}
