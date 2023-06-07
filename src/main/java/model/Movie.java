package model;

public class Movie extends Product {
	
	public static enum Category {
		ACTION,
		COMEDY,
		DRAMA, 
		FANTASY,
		HORROR,
		MYSTERY,
		ROMANCE	
	}
	
	private static double MIN_MINUTES = 5;
	public static int HORROR_MIN_AGE = 18;
	
	private Category category;
	private Integer minutes;
	
	public Category getCategory() {
		return category;
	}
	
	public Integer getMinutes() {
		return minutes;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public void setMinutes(Integer minutes) {
		if (minutes < MIN_MINUTES) {
			throw new RuntimeException("Invalid minutes: " + minutes);
		}
		this.minutes = minutes;
	}
	
}
