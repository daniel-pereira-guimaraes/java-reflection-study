package jsonwriter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import model.Address;
import model.Company;
import model.Person;

public class Main {

	public static void main(String[] args) throws Throwable {

		final Address companyAddress = new Address("Here", (short) 123);
		final String[] companyOtherNames = { "Java Cia", "Java Company" };
		final Company company = new Company("Java S/A", "São Paulo", companyAddress, companyOtherNames);

		final Address personAddress = new Address("Example", (short) 456);
		final Address[] personAddresses = {personAddress, companyAddress};
		final Person person = new Person("Emma", true, 21, 5000.126f, personAddresses, company);

		System.out.println(objectToJson(person));

	}

	public static String objectToJson(Object instance) throws Throwable {
		return objectToJson(instance, 0);
	}

	private static String objectToJson(Object instance, int indentSize) throws Throwable {

		final Field[] fields = instance.getClass().getDeclaredFields();
		final StringBuilder sb = new StringBuilder();
		final String objectIndent = indent(indentSize);
		final String fieldIndent = objectIndent + indent(1);

		sb.append('{').append('\n');
		for (int i = 0; i < fields.length; i++) {
			final Field field = fields[i];
			field.setAccessible(true);
			if (!field.isSynthetic()) {
				sb.append(fieldIndent);
				sb.append(formatStringValue(field.getName())).append(':');
				sb.append(formatValue(field.get(instance), field.getType(), indentSize));
				if (i < fields.length - 1) {
					sb.append(',');
				}
				sb.append('\n');
			}
		}
		sb.append(objectIndent).append('}');

		return sb.toString();
	}

	private static String indent(int identSize) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < identSize; i++) {
			sb.append('\t');
		}
		return sb.toString();
	}

	private static String formatArrayValue(Object arrayObject, 
			Class<?> elementType, int indentSize) throws Throwable {

		final StringBuilder sb = new StringBuilder();
		final boolean multiLines = !isSingleLine(elementType);
		final String arrayIndent = indent(indentSize);
		final String elementIndent = arrayIndent + indent(1);
		final int len = Array.getLength(arrayObject);

		sb.append('[');
		if (multiLines) {
			sb.append('\n');
		}
		for (int i = 0; i < len; i++) {
			final Object element = Array.get(arrayObject, i);
			final Class<?> elementClass = element.getClass();
			if (elementClass.isArray()) {
				sb.append(formatArrayValue(element, elementClass.getComponentType(), indentSize + 1));
			} else {
				if (multiLines) {
					sb.append(elementIndent);
				}
				sb.append(formatValue(element, elementType, indentSize));
			}
			if (i < len - 1) {
				sb.append(',');
				if (multiLines) {
					sb.append('\n');
				}
			}
		}
		if (multiLines) {
			sb.append('\n').append(arrayIndent);
		}
		sb.append(']');

		return sb.toString();
	}

	private static boolean isSingleLine(Class<?> objectType) {
		return objectType.isPrimitive() || objectType.isArray();
	}

	private static String formatValue(Object value, Class<?> valueType, int indentSize) throws Throwable {

		if (value == null) {
			return "null";
		}

		if (valueType.equals(String.class)) {
			return formatStringValue(value.toString());
		}

		if (valueType.isPrimitive()) {
			return value.toString();
		}

		if (valueType.isArray()) {
			return formatArrayValue(value, valueType.getComponentType(), indentSize + 1);
		}

		return objectToJson(value, indentSize + 1);
	}

	private static String formatStringValue(Object value) {
		return new StringBuilder()
				.append('"')
				.append(value.toString().replace("\"", "\\\""))
				.append('"').toString();
	}

}
