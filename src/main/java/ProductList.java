

public class ProductList {
	public ProductList(String product, String price, String description, String status) {
		super();
		this.product = product;
		this.price = price;
		this.description = description;
		this.status = status;
	}

	protected String product;
	protected String price;
	protected String description;
	protected String status;

	public String getProduct() {
		return product;
	}

	public void setproduct(String product) {
		this.product = product;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}