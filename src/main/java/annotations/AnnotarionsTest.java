package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.Set;

import annotations.Annotations.Comment;
import annotations.Annotations.Role;

public class AnnotarionsTest {
	
	public static void main(String[] args) throws Throwable {
		invokeLoaders();
		printCommentAnnotation(Employee.class);
		testParameterAnnotation(Employee.class);
		checkRoleAnnotation();
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
	
	private static void testParameterAnnotation(Class<?> clazz) {
		System.out.println("\ntestParameterAnnotation");
		for (Method method : clazz.getDeclaredMethods()) {
			System.out.print("\t" + method.getName() + "(");
			final Parameter[] parameters = method.getParameters();
			for (int p = 0; p < parameters.length; p++) {
				final Parameter parameter = parameters[p];
				final Param param = parameter.getAnnotation(Param.class);
				final String paramType = parameter.getType().getSimpleName();
				final String paramName = param == null ? "arg" + p : param.name();
				System.out.print(paramType + " " + paramName);
				if (p < parameters.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println(");");
		}
		
	}
	
	private static void checkRoleAnnotation(Class<?> clazz) {
		System.out.println("\ncheckRoleAnnotation(" + clazz.getSimpleName() + ")");
		System.out.println("\t" + clazz.getSimpleName());
		for (Role role : clazz.getDeclaredAnnotationsByType(Role.class)) {
			System.out.println("\t\t" + role.value());
		}
	}

	private static void checkRoleAnnotation() {
		checkRoleAnnotation(Employee.class);
		checkRoleAnnotation(User.class);
	}
	
}
