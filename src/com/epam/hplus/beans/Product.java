package com.epam.hplus.beans;

public class Product {
	private long productId;
	private String name;
	private String productImgPath;
	
	public Product(long productId, String name, String productImgPath) {
		super();
		this.productId = productId;
		this.name = name;
		this.productImgPath = productImgPath;
	}

	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductImgPath() {
		return productImgPath;
	}
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}
	
		

}
