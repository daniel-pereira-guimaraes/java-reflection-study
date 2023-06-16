package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Annotations {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Comment {
		String value();
	}
	
	@Repeatable(Roles.class)
	public @interface Role {
		RoleEnum value();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Roles {
		Role[] value();
	}
}
