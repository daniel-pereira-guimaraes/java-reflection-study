package main;

public class Product {
	
	private Long id;
	private String name;
	private Double price = 0.0;
	private Boolean active = true;
	
	public Product() {
		super();
	}

	public Product(String name) {
		super();
		this.name = name;
	}

	public Product(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Product(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product(Long id, String name, Double price, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Boolean isActive() {
		return active;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", active=" + active + "]";
	}
	
}
