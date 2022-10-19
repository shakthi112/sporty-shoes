package com.sportyshoes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	@GeneratedValue
	private int productid;
	private String productname;
	private String brand;
	private int price;

	public Products() {
		super();
	}

	public Products(int productid, String productname, String brand, int price) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.brand = brand;
		this.price = price;
	}

	public Products(String productname, String brand, int price) {
		super();
		this.productname = productname;
		this.brand = brand;
		this.price = price;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Products [productid=" + productid + ", productname=" + productname + ", brand=" + brand + ", price="
				+ price + "]";
	}

}
