package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AnnotarionsTest {
	
	public static void main(String[] args) throws Throwable {
		invokeLoaders();
		printCommentAnnotation(Employee.class);
	}
	
	private static void invokeLoaders() {
		System.out.println("\ninvokeLoaders");
		final Loaders loaders = new Loaders();
		getAllAnnotatedMethods(loaders, Loader.class).forEach(method -> {
			try {
				method.invoke(loaders);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private static Set<Method> getAllAnnotatedMethods(Object input, Class<? extends Annotation> annotation) {
        final Set<Method> annotatedMethods = new HashSet<>();
        for (Method method : input.getClass().getDeclaredMethods()) {
        	if (method.isAnnotationPresent(annotation)) {
        		annotatedMethods.add(method);
        	}
        }
        return annotatedMethods; 
	}
	
	private static void printCommentAnnotation(Class<?> clazz) {
		System.out.println("\nprintCommentAnnotation");

		System.out.print("\t" + clazz.getSimpleName());
		final Comment classComment = clazz.getDeclaredAnnotation(Comment.class);
		if (classComment != null) {
			System.out.print(" - " + classComment.value());
		}
		System.out.println();
		
		for (Field field : clazz.getDeclaredFields()) {
			System.out.print("\t\t" + field.getName());
			final Comment fieldComment = field.getAnnotation(Comment.class);
			if (fieldComment != null) {
				System.out.print(" - " + fieldComment.value());
			}
			System.out.println();
		}
	}

}
