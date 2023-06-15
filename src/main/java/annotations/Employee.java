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
	
	public void increaseSalary(@Param(name = "percent") double perc, double maxSalary) {
		this.salary = this.salary * (1 + perc / 100);
		if (this.salary > maxSalary) {
			this.salary = maxSalary;
		}
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", salary=" + salary + "]";
	}

}
