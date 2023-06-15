package annotations;

import java.time.LocalDate;

@Comment("Employee registration")
public class Employee {
	
	@Comment("Unique identification")
	private Long id;
	
	@Comment("Employee full name")
	private String name;
	
	@Comment("Employee birth date.")
	private LocalDate birthDate;
	
	@Comment("Salary value")
	private Double salary;

}
