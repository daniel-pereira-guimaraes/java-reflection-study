package main;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
	
}
