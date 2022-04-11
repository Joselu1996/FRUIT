package entity;

public class Products {
	/* PRODUCTS:
	 	Pears 	0.75€
		Apples 	0.9€
		Oranges 1€
	 */
	private int productId;
	private String productName;
	private Double productPrice;

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
