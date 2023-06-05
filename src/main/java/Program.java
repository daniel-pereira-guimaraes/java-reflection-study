import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Program {

	public static class Person implements Serializable {
		private static final long serialVersionUID = 1L;
		String name;
	}
	
	public static enum Color {
		RED,
		GREEN,
		BLUE
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		classOfObject();
		classOfClass();
		classOfPrimitive();
		classByName();
		innerClassByName();
		checkClassFound();
		printClassInfo(String.class, int.class, int[].class, Collection.class, Program.Person.class, Color.class);
	}
	
	private static void classOfObject() {
		System.out.println(Program.class.getSimpleName() + ".classOfObject()");
		final String str = "Daniel";
		final Double value = 15.0;
		final Map<Long, String> map = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		final Class<String> strClass = (Class<String>)str.getClass();
		
		final Class<?> valueClass = value.getClass();
		final Class<?> mapClass = map.getClass();
		
		System.out.println("\tstrClass:\t" + strClass);
		System.out.println("\tvalueClass:\t" + valueClass);
		System.out.println("\tmapClass:\t" + mapClass);
		System.out.println();
	}
	
	private static void classOfClass() {
		System.out.println(Program.class.getSimpleName() + ".classOfClass()");

		Class<?> strClass = String.class;
		Class<?> doubleClass = Double.class;
		Class<?> mapClass = Map.class;

		System.out.println("\tstrClass:\t" + strClass);
		System.out.println("\tdoubleClass:\t" + doubleClass);
		System.out.println("\tmapClass:\t" + mapClass);
		System.out.println();
	}
	
	private static void classOfPrimitive() {
		System.out.println(Program.class.getSimpleName() + ".classOfPrimitive()");

		Class<?> intClass = int.class;
		Class<?> doubleClass = double.class;
		Class<?> booleanClass = boolean.class;

		System.out.println("\tintClass:\t" + intClass);
		System.out.println("\tdoubleClass:\t" + doubleClass);
		System.out.println("\tbooleanClass:\t" + booleanClass);
		System.out.println();
	}
	
	private static void classByName() throws ClassNotFoundException {
		System.out.println(Program.class.getSimpleName() + ".classByName()");
		
		Class<?> strClass = Class.forName("java.lang.String");
		
		System.out.println("\tstrClass:\t" + strClass);
		System.out.println();
	}
	
	private static void innerClassByName() throws ClassNotFoundException {
		System.out.println(Program.class.getSimpleName() + ".innerClassByName()");
		
		Class<?> personClass = Class.forName("Program$Person");
		
		System.out.println("\tpersonClass:\t" + personClass);
		System.out.println();
	}
	
	private static void checkClassFound() {
		System.out.println(Program.class.getSimpleName() + ".checkClassFound()");
		try {
			Class.forName("com.google.gson.Gson");
			System.out.println("\tGson class found!");
		} catch (ClassNotFoundException e) {
			System.out.println("\tGson class not found!");
		}
		System.out.println();
	}

	private static void printClassInfo(Class<?>... classes) {
		System.out.println(Program.class.getSimpleName() + ".printClassInfo()");
		for (Class<?> clazz : classes) {
			System.out.println("\t" + clazz.getSimpleName());
			System.out.println("\t\tIs array: " + clazz.isArray());
			System.out.println("\t\tIs primitive: " + clazz.isPrimitive());
			System.out.println("\t\tIs enum: " + clazz.isEnum());
			System.out.println("\t\tIs interface: " + clazz.isInterface());
			System.out.println("\t\tIs anonymous: " + clazz.isAnonymousClass());

			if (clazz.getSuperclass() != null) {
				System.out.println("\t\tSuper class: " + clazz.getSuperclass().getName());
			}
			
			if (clazz.getPackage() != null) {
				System.out.println("\t\tPackage: " + clazz.getPackage().getName());
			}
			
			if (clazz.getInterfaces().length > 0) {
				System.out.println("\t\tImplemented interfaces:");
				for (Class<?> implementedInterface : clazz.getInterfaces()) {
					System.out.println("\t\t\t" + implementedInterface.getName());
				}
			}
			System.out.println();
		}
	}
}
