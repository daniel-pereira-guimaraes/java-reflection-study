package testing;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestingFramework {
	
	public void runTestSuite(Class<?> testClass) throws Throwable {
		
		final Method[] methods = testClass.getMethods();
		final Method beforeClass = findMethodByName(methods, "beforeClass");
		final Method afterClass = findMethodByName(methods, "afterClass");
		final Method setupTest = findMethodByName(methods, "setupTest");

		if (beforeClass != null 
				&& beforeClass.getReturnType().equals(void.class) 
				&& beforeClass.getParameterCount() == 0
				&& (beforeClass.getModifiers() & Modifier.PUBLIC) != 0) {
			beforeClass.invoke(testClass.newInstance());
		}
		
		final List<Method> tests = findMethodsByPrefix(methods, "test");
		for (Method test : tests) {
			if (test.getReturnType().equals(void.class) 
					&& test.getParameterCount() == 0
					&& (test.getModifiers() & Modifier.PUBLIC) != 0) {
				final Object instance = testClass.newInstance();
				if (setupTest != null 
						&& setupTest.getReturnType().equals(void.class) 
						&& setupTest.getParameterCount() == 0
						&& (setupTest.getModifiers() & Modifier.PUBLIC) != 0) {
					setupTest.invoke(instance);
				}
				test.invoke(instance);
			}
		}
		
		if (afterClass != null 
				&& afterClass.getReturnType().equals(void.class) 
				&& afterClass.getParameterCount() == 0
				&& (afterClass.getModifiers() & Modifier.PUBLIC) != 0) {
			afterClass.invoke(testClass.newInstance()); 
		}
		
	}

	private Method findMethodByName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}
	
	private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
		final List<Method> list = new ArrayList<>();
		for (Method method : methods) {
			if (method.getName().startsWith(prefix)) {
				list.add(method);
			}
		}
		return list;
	}
}
