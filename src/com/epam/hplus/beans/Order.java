package com.epam.hplus.beans;

import java.util.Date;

public class Order {
	private long orderId;
	private String product;
	private String productImgPath;
	private Date orderDate;
	private String username;
	
	public Order(int id, String product, String productImgPath, Date orderDate, String username) {
		this.orderId = id;
		this.product = product;
		this.productImgPath = productImgPath;
		this.orderDate = orderDate;
		this.username = username;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getProductImgPath() {
		return productImgPath;
	}
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
		
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
