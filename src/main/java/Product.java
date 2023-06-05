
public class Product {
	
	private long id;
	private String name;
	private double price = 0.0;
	private boolean active = true;
	
	public Product() {
		super();
	}

	public Product(String name) {
		super();
		this.name = name;
	}

	public Product(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Product(long id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product(long id, String name, double price, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean isActive() {
		return active;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", active=" + active + "]";
	}
	
}
