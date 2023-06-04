import java.util.HashMap;
import java.util.Map;

public class Program {
	
	public static void main(String[] args) {
		classOfObject();
		classOfClass();
		classOfPrimitive();
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
		System.out.println(Program.class.getSimpleName() + ".classOfObject()");

		Class<?> strClass = String.class;
		Class<?> doubleClass = Double.class;
		Class<?> mapClass = Map.class;

		System.out.println("\tstrClass:\t" + strClass);
		System.out.println("\tdoubleClass:\t" + doubleClass);
		System.out.println("\tmapClass:\t" + mapClass);
		System.out.println();
	}
	
	private static void classOfPrimitive() {
		System.out.println(Program.class.getSimpleName() + ".classOfObject()");

		Class<?> intClass = int.class;
		Class<?> doubleClass = double.class;
		Class<?> booleanClass = boolean.class;

		System.out.println("\tintClass:\t" + intClass);
		System.out.println("\tdoubleClass:\t" + doubleClass);
		System.out.println("\tbooleanClass:\t" + booleanClass);
		System.out.println();
	}

}
