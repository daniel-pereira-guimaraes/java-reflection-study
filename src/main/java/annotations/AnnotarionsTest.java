package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnnotarionsTest {
	
	public static void main(String[] args) throws Throwable {
		
		final Loaders loaders = new Loaders();
		Set<Method> methods = getAllAnnotatedMethods(loaders, Loader.class);
		methods.forEach(method -> {
			try {
				method.invoke(loaders);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
	private static Set<Method> getAllAnnotatedMethods(Object input, Class<? extends Annotation> annotation) {

        Set<Method> annotatedMethods = new HashSet<>();
        for (Method method : input.getClass().getDeclaredMethods()) {
        	if (method.isAnnotationPresent(annotation)) {
        		annotatedMethods.add(method);
        	}
        }
        return annotatedMethods; 
	}

}
