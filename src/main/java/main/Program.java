package main;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dao.Database;
import model.Employee;
import model.Movie;
import model.Product;

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
	
	public static void main(String[] args) throws Throwable {
		classOfObject();
		classOfClass();
		classOfPrimitive();
		classByName();
		innerClassByName();
		checkClassFound();
		printClassInfo();
		testClassAnalyzer();
		printAllInterfaces();
		printConstructors(String.class);
		printConstructorsParamTypes(Product.class);
		createNewInstance();
		createInstanceWithArgs();
		createInstanceWithArgsV2();
		usePrivateConstructor();
		testGetArrayClass();
		printFields();
		printFieldsWithValues();
		accessFieldByName();
		fieldClassValueByName();
		inspectArrayObject();
		getArrayElement();
		arrayNewInstance();
		listToArray();
		arrayConcat();
		analyzeMethods();
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
		
		Class<?> personClass = Class.forName("main.Program$Person");
		
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
			
			if (clazz.isAnonymousClass()) {
				System.out.println("\t<Anonymous>");
			} 
			else {
				System.out.println("\t" + clazz.getSimpleName());
			}
			
			System.out.println("\t\tType name: " + clazz.getTypeName());
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
	
	private static void printClassInfo() {
		
		Serializable instanceOfAnonymousClass = new Serializable() {
			private static final long serialVersionUID = 1L;
		};
		
		printClassInfo(
			String.class, 
			int.class, 
			int[].class, 
			Collection.class, 
			Program.Person.class, 
			Color.class,
			instanceOfAnonymousClass.getClass());
	}
	
	private static void testClassAnalyzer(Class<?> clazz)	{
		final PopupTypeInfo info = ClassAnalyzer.createPopupTypeInfoFromClass(clazz);
		System.out.println("This is a " + (info.isJdk() ? "JDK" : "custom") + " type");
		System.out.println("Name: " + info.getName());
		
		System.out.print("Type: ");
		if (info.isPrimitive())
			System.out.println("Primitive");
		else if (info.isInterface())
			System.out.println("Interface");
		else if (info.isEnum())
			System.out.println("Enum");
		else if (info.isArray())
			System.out.println("Array");
		else
			System.out.println("Class");
		
		if (!info.isPrimitive()) {
			System.out.print("Inherits from: ");
			for (int i = 0; i < info.getInheritedClassNames().size(); i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.println(info.getInheritedClassNames().get(i));
			}
		}
		System.out.println();
	}
	
	private static void testClassAnalyzer() {
		testClassAnalyzer(List.class);
		testClassAnalyzer(Product.class);
		testClassAnalyzer(int.class);
	}
	
	private static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();
        
        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);
            allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
        }
        
        return allImplementedInterfaces;
    }
    
    private static void printAllInterfaces(Class<?> clazz) {
    	System.out.println("\nprintAllInterfaces");
    	System.out.println("\tClass name: " + clazz.getSimpleName());
    	System.out.println("\t\tInterfaces:");
    	Set<Class<?>> interfaces = findAllImplementedInterfaces(clazz);
    	for (Class<?> currentInterface : interfaces) {
    		System.out.println("\t\t\t" + currentInterface.getSimpleName());
    	}
    }
    
    private static void printAllInterfaces() {
    	printAllInterfaces(Double.class);
    	printAllInterfaces(String.class);
    }
    
    private static void printConstructors(Class<?> clazz) {
    	System.out.println("\nprintConstructors");
    	for (Constructor<?> c : clazz.getDeclaredConstructors()) {
    		System.out.println("\t" + c.getName());
    		System.out.println("\t\ttoString: " + c.toString());
    		System.out.println("\t\tModifiers: " + c.getModifiers());
    		System.out.println("\t\tParameterCount: " + c.getParameterCount());
    		for (Parameter p : c.getParameters()) {
    			System.out.println("\t\t\tName: " + p.getName());
    			System.out.println("\t\t\tType: " + p.getType().getSimpleName());
    		}
    	}
    }
    
    private static void printConstructorsParamTypes(Class<?> clazz) {
    	System.out.println("\nprintConstructorsParamTypes");
    	for (Constructor<?> c : clazz.getDeclaredConstructors()) {
    		final List<String> paramTypes = Arrays.asList(c.getParameterTypes())
    				.stream().map(paramType -> paramType.getSimpleName())
    				.collect(Collectors.toList());
    		System.out.println("\t" + paramTypes);
    	}
    }

    private static void createNewInstance()  throws Throwable {
    	System.out.println("\ncreateNewInstance");
    	final Product p = Product.class.newInstance();
    	System.out.println("\t" + p);
    }
    
	@SuppressWarnings("unchecked")
	private static <T> T createInstanceWithArgs(Class<T> clazz, Object... args) throws Throwable {
    	System.out.println("\tcreateInstanceWithArgs(" + clazz.getSimpleName() + ".class, " + Arrays.asList(args) + ")");
    	for (Constructor<?> c : clazz.getDeclaredConstructors()) {
    		if (c.getParameterCount() == args.length) {
    			return (T) c.newInstance(args);
    		}
    	}
    	System.out.println("An appropriate constructor was not found.");
    	return null;
    }

    private static void createInstanceWithArgs()  throws Throwable {
    	
    	System.out.println("\ncreateInstanceWithArgs");
    	
    	final Product p1 = createInstanceWithArgs(Product.class);
    	final Product p2 = createInstanceWithArgs(Product.class, "Keyboard");
    	final Product p3 = createInstanceWithArgs(Product.class, 200L,  "Keyboard");

    	System.out.println();
    	System.out.println("\t" + p1);
    	System.out.println("\t" + p2);
    	System.out.println("\t" + p3);
    }
    
	@SuppressWarnings("unchecked")
	private static <T> T createInstanceWithArgsV2(Class<T> clazz, Object... args) throws Throwable {
    	final Class<?>[] paramTypes = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
    	final Constructor<?> constructor = clazz.getConstructor(paramTypes);
    	return (T) constructor.newInstance(args);
    }
	
	private static void createInstanceWithArgsV2() throws Throwable {
		System.out.println("\ncreateInstanceWithArgsV2");
		
		final Product p1 = createInstanceWithArgsV2(Product.class, "Keyboard");
		final Product p2 = createInstanceWithArgsV2(Product.class, 5L, "Keyboard");
		final Product p3 = createInstanceWithArgsV2(Product.class, 5L, "Keyboard", 50.0);
		
		System.out.println("\t" + p1);
		System.out.println("\t" + p2);
		System.out.println("\t" + p3);
	}
    
    private static void usePrivateConstructor() 
    		throws NoSuchMethodException, SecurityException, InstantiationException, 
    			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	System.out.println("\nusePrivateConstructor");

    	// Default
    	final Database defaultDatabase = Database.getInstante();
    	System.out.println("\tDefault: " + defaultDatabase);
    	
    	// Custom
    	final Constructor<Database> constructor = 
    			Database.class.getDeclaredConstructor(String.class, int.class, String.class, String.class);
    	constructor.setAccessible(true);
    	final Database customDatabase = constructor.newInstance("myserver.com", 12345, "myUser", "myPassword");
    	System.out.println("\tCustom: " + customDatabase);
    }
    
    private static Class<?> getArrayClass(Class<?> componentType) throws Throwable {
        String name;
        if(componentType.isArray())
            name = "["+componentType.getName();		// Just add a leading "["
        else if(componentType == boolean.class)
            name = "[Z";
        else if(componentType == byte.class)
            name = "[B";
        else if(componentType == char.class)
            name = "[C";
        else if(componentType == double.class)
            name = "[D";
        else if(componentType == float.class)
            name = "[F";
        else if(componentType == int.class)
            name = "[I";
        else if(componentType == long.class)
            name = "[J";
        else if(componentType == short.class)
            name = "[S";
        else {
            name = "[L"+componentType.getName()+";";	// must be an object non-array class
        }

        return Class.forName(name);
    }
    
    private static void testGetArrayClass() throws Throwable {
    	System.out.println("\ntestGetArrayClass");
    	System.out.println("\t" + getArrayClass(byte.class).getTypeName()); 
    	System.out.println("\t" + getArrayClass(int.class).getTypeName()); 
    	System.out.println("\t" + getArrayClass(long.class).getTypeName()); 
    	System.out.println("\t" + getArrayClass(double.class).getTypeName()); 
    	System.out.println("\t" + getArrayClass(String.class).getTypeName()); 
    	System.out.println("\t" + getArrayClass(Product.class).getTypeName()); 
    	System.out.println("\t" + getArrayClass(Object.class).getTypeName()); 
    }
    
    private static String repeat(char c, int n) {
    	final StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < n; i++)
    		sb.append(c);
    	return sb.toString();
    }
    
    private static void printFields(int level, String caption, Field[] fields) {
    	System.out.println(repeat('\t', level++) + caption + ": " + fields.length);
    	final String namePrefix = repeat('\t', level);
    	final String propPrefix = repeat('\t', level + 1);
    	for (Field field : fields) {
    		System.out.println(namePrefix + field.getName());
    		System.out.println(propPrefix + "Type: " + field.getType().getSimpleName());
    		System.out.println(propPrefix + "Synthetic: " + field.isSynthetic());
    		System.out.println(propPrefix + "Accessible: " + field.isAccessible());
    	}
    }

    private static void printFields(Class<?> clazz) {
    	System.out.println("\nprintClassFields: " + clazz.getSimpleName());
    	printFields(1, "Fields", clazz.getFields());
    	printFields(1, "DeclaredFields", clazz.getDeclaredFields());
    }

    private static void printFields() throws ClassNotFoundException {
    	printFields(Product.class);
    	printFields(Movie.class);
    	printFields(Movie.Category.class);
    	printFields(Employee.Builder.EmployeeImpl.class);
    }
    
    private static <T> void printFieldsWithValues(int level, String caption, 
    		Field[] fields, T instance) throws Throwable {
    	System.out.println(repeat('\t', level++) + caption + ": " + fields.length);
    	final String prefix = repeat('\t', level);
    	for (Field field : fields) {
    		field.setAccessible(true);
    		System.out.println(prefix + field.getName() + " = " + field.get(instance));
    	}
    }
    
    private static <T> void printFieldsWithValues(Class<? extends T> clazz, T instance) throws Throwable {
    	System.out.println("\nprintFieldsWithValues");
    	printFieldsWithValues(1, "Fields", clazz.getFields(), instance);
    	printFieldsWithValues(1, "DeclaredFields", clazz.getDeclaredFields(), instance);
    }
    
    private static void printFieldsWithValues() throws Throwable {
    	final Movie movie = new Movie();
    	movie.setId(10L);
    	movie.setName("Ice Age");
    	printFieldsWithValues(Product.class, movie);
    	printFieldsWithValues(Movie.class, movie);
    }
    
    private static void accessFieldByName() throws Throwable {
    	System.out.println("\naccessFieldByName");

    	final Movie movie = new Movie();
    	movie.setName("Ice Age");
    	
    	final Field field = Product.class.getDeclaredField("name");
    	field.setAccessible(true);
    	
    	final String value = (String) field.get(movie);
    	System.out.println("\tfield.get(movie): " + value);
    	
    	System.out.println("\tSetting new value...");
    	field.set(movie, "Lion King");
    	System.out.println("\tmovie.getName(): " + movie.getName());
    }
    
    @SuppressWarnings("unchecked")
	private static <T> T fieldClassValueByName(Class<?> clazz, String fieldName) {
    	try {
    		final Field field = clazz.getDeclaredField(fieldName);
    		field.setAccessible(true);
    		return (T) field.get(null);
    	}
    	catch (Throwable e) {
			return null;
		}
    }
    
    private static void fieldClassValueByName() {
    	System.out.println("\nshowFieldClassValueByName");
    	final String FIELD_NAME = "MIN_MINUTES";
    	System.out.println("\t" + FIELD_NAME + ": " + fieldClassValueByName(Movie.class, FIELD_NAME));
    }

    private static String arrayToString(Object arrayObject) {
    	if (arrayObject == null || !arrayObject.getClass().isArray()) {
    		throw new IllegalArgumentException("arrayObject argument isn't an array.");
    	}
    	final StringBuilder sb = new StringBuilder();
    	sb.append('[');
    	final int len = Array.getLength(arrayObject);
    	for (int i = 0; i < len; i++) {
    		final Object element = Array.get(arrayObject, i);
    		if (element.getClass().isArray()) {
    			sb.append(arrayToString(element));
    		}
    		else {
    			sb.append(element);
    		}
    		if (i < len - 1) {
    			sb.append(',').append(' ');
    		}
    	}
    	return sb.append(']').toString();
    }
    
    private static void inspectArrayObject(Object arrayObject) {
    	final Class<?> clazz = arrayObject.getClass();
    	if (clazz.isArray()) {
    		final Class<?> arrayComponentType = clazz.getComponentType();
    		System.out.println("\tThe object is an array of " + arrayComponentType.getSimpleName());
    		System.out.println("\t\t" + arrayToString(arrayObject));
    	}
    	else {
    		System.out.println("\tThe object isn't an array. It's " + clazz.getTypeName() + ".");
    	}
    }

    private static void inspectArrayObject() {
    	System.out.println("\ninspectArrayObject");

    	final int [] oneDimensionalArray = {1, 2};
    	final double [][] twoDimensionalArray = {{5.2, 6.5}, {7.1, 8.35}};

    	inspectArrayObject("Hello!");
    	inspectArrayObject(oneDimensionalArray);
    	inspectArrayObject(twoDimensionalArray);
    }
    
    private static Object getArrayElement(Object array, int index) {
    	if (array == null) {
    		throw new IllegalArgumentException("The array argument cannot be null.");
    	}
    	final int len = Array.getLength(array);
    	if (len == 0) {
    		throw new IllegalArgumentException("The array argument cannot be empty.");
    	}
    	index = index >= 0 ? index = index % len : (index + 1) % len + len - 1;
    	return Array.get(array, index);
    }
    
    private static void getArrayElement() {
    	System.out.println("\ngetArrayElement");
    	System.out.print("\t");
    	final int[] test = {1, 2, 3, 4, 5};
    	for (int i = -10; i < 10; i++) {
    		System.out.print(getArrayElement(test, i) + "  ");
    	}
    	System.out.println();
    }

	private static void arrayNewInstance() {
		System.out.println("\ncreateArray");

		final Object myArray = Array.newInstance(int.class, 3);
		Array.set(myArray, 0, 200);
		Array.set(myArray, 1, 300);
		Array.set(myArray, 2, 500);
		
		for (int i = 0; i < Array.getLength(myArray); i++) {
			System.out.println("\t" + i + ": " + Array.get(myArray, i));
		}
	}
	
	private static <T> T[] listToArray(List<T> list) {
		
		if (list == null || list.size() == 0) {
			return null;
		}
		
		final Class<?> elementType = list.get(0).getClass();
		
		@SuppressWarnings("unchecked")
		T[] result = (T[]) Array.newInstance(elementType, list.size());
		for (int i = 0; i < list.size(); i++) {
			Array.set(result, i, list.get(i));
		}
		
		return result;
	}

	private static void listToArray() {
		final List<Integer> intList = Arrays.asList(new Integer[] {10, 20, 30});
		Integer[] intArray = listToArray(intList);
		for (int n : intArray) {
			System.out.println(n);
		}
	}
	
	private static <T> T arrayConcat(Class<?> type, Object... arguments) {
		
		if (arguments.length == 0) {
			return null;
		}

		final List<Object> list = new ArrayList<>();
		for (final Object arg : arguments) {
			if (arg.getClass().isArray()) {
				final int argLength = Array.getLength(arg);
				for (int i = 0; i < argLength; i++) {
					list.add(Array.get(arg, i));
				}
			}
			else {
				list.add(arg);
			}
		}
		
		@SuppressWarnings("unchecked")
		T result = (T) Array.newInstance(type, list.size());
		
		for (int i = 0; i < list.size(); i++) {
			Array.set(result, i, list.get(i));
		}
		
		return result;
	}

	private static void arrayConcat() {
		System.out.println("\narrayConcat");
		
		System.out.print("\tintArray1:\t");
		int[] intArray1 = arrayConcat(int.class, 1, 2, 3, 4, 5);
		for (int i : intArray1) {
			System.out.print(" " + i);
		}

		System.out.print("\n\tintArray2:\t");
		int[] intArray2 = arrayConcat(int.class, 1, 2, 3, new int[] {4, 5, 6}, 7);
		for (int i : intArray2) {
			System.out.print(" " + i);
		}

		System.out.print("\n\tstrArray:\t");
		String[] strArray = arrayConcat(String.class, new String[] {"a", "b"}, "c", new String[] {"d", "e"});
		for (String s : strArray) {
			System.out.print(" " + s);
		}
		
		System.out.println();
	}
	
	private static void appendParameter(StringBuilder sb, Parameter parameter, String parameterName) {
		sb.append(parameter.getType().getSimpleName()).append(' ').append(parameterName);
	}
	
	private static void appendParameters(StringBuilder sb, Parameter[] parameters) {
		final int parameterCount = parameters.length;
		for (int i = 0; i < parameterCount; i++) {
			appendParameter(sb, parameters[i], "arg" + i);
			if (i < parameterCount - 1) {
				sb.append(',').append(' ');
			}
		}
	}

	private static void appendThrows(StringBuilder sb, Class<?>[] exceptionTypes) {
		if (exceptionTypes.length > 0) {
			sb.append(" throws ");
			final int len = exceptionTypes.length;
			for (int i = 0; i < len; i++) {
				sb.append(exceptionTypes[i].getSimpleName());
				if (i < len - 1) {
					sb.append(',').append(' ');
				}
			}
		}
	}
	
	private static void appendMethod(StringBuilder sb, Method method) {
		final String modifiers = Modifier.toString(method.getModifiers());
		if (modifiers != null && !modifiers.isEmpty()) {
			sb.append(modifiers).append(' ');
		}
		sb.append(method.getReturnType().getSimpleName()).append(' ');
		sb.append(method.getName()).append('(');
		appendParameters(sb, method.getParameters());
		sb.append(')');
		appendThrows(sb, method.getExceptionTypes());
		sb.append(';');
	}
	
	private static int visibility(int modifiers) {
		if (Modifier.isPrivate(modifiers)) return 0;
		if (Modifier.isProtected(modifiers)) return 2;
		if (Modifier.isPublic(modifiers)) return 3;
		return 1; // Default
	}
	
	private static void analyzeMethods(Class<?> clazz) {
		System.out.println("\nanalyzeMethods");
		System.out.println("\t" + clazz.getSimpleName());
		
		final List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
		
		// Sort by member visibility
		methods.sort((a, b) -> visibility(a.getModifiers()) - visibility(b.getModifiers()));

		final StringBuilder sb = new StringBuilder();
		for (Method method : methods) {
			sb.append('\t').append('\t');
			appendMethod(sb, method);
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	private static void analyzeMethods() {
		analyzeMethods(String.class);
	}
	
	
}
